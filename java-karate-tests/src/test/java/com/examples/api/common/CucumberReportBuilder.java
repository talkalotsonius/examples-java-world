package com.examples.api.common;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class CucumberReportBuilder {
    private static final String PROJECT_NAME;
    private static final String ENVIRONMENT_NAME;
    private static final String BRANCH_NAME;
    private static final String KARATE_VERSION;

    static {
        try {
            MavenPropertiesReader propertiesReader
                    = new MavenPropertiesReader("properties-from-pom.properties");
            PROJECT_NAME        = propertiesReader.getProperty("name");
            ENVIRONMENT_NAME    = propertiesReader.getProperty("test.environment");
            BRANCH_NAME         = propertiesReader.getProperty("git.branch");
            KARATE_VERSION      = propertiesReader.getProperty("karate.version");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String PLATTFORM = System.getProperty("os.name")+"/"+
                                            System.getProperty("os.arch")+" "+
                                            System.getProperty("os.version")+" / Java "+
                                            System.getProperty("java.version");
    private static final String RESULT_PATH = "target/";
    private static final String CUCUMBER_TEST_REPORT_PATH = RESULT_PATH+"cucumber-html-reports/";

    public static void clearTestReports() throws NullPointerException {
        File cucumberTestReportPath = new File(CUCUMBER_TEST_REPORT_PATH);
        String[] cucumberTestReportFileList;

        if(cucumberTestReportPath.isDirectory()){
            cucumberTestReportFileList = cucumberTestReportPath.list();
            for (int i = 0; i< Objects.requireNonNull(cucumberTestReportFileList).length; i++) {
                File cucumberTestReportFile = new File(cucumberTestReportPath, cucumberTestReportFileList[i]);
                System.out.println("Deleteing files: "+cucumberTestReportFile);
                cucumberTestReportFile.delete();
            }
        }
    }

    public static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath),
                                                         new String[] {"json"},
                                                true);
        List<String> jsonPaths = new ArrayList<String>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));

        Configuration config = new Configuration(new File(RESULT_PATH), PROJECT_NAME);
        config.addClassifications("Environment", ENVIRONMENT_NAME);
        config.addClassifications("Branch", BRANCH_NAME);
        config.addClassifications("Karate", KARATE_VERSION);
        config.addClassifications("Plattform", PLATTFORM);

        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}