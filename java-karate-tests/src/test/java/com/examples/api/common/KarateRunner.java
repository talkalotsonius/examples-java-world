package com.examples.api.common;

import com.intuit.karate.Results;
import com.intuit.karate.junit5.Karate;

import java.io.IOException;

public class KarateRunner extends Karate {
    private final Karate karateRunner;
    private Results karateTestResults;

    public KarateRunner(Karate karateRunner) throws IOException {
        CucumberReportBuilder.clearTestReports();
        this.karateRunner = karateRunner.outputCucumberJson(true);
    }

    public Karate executeTests(int threads) {
        this.karateTestResults = this.karateRunner.parallel(threads);
        getTestResults();

        return this.karateRunner;
    }

    private void getTestResults() {
        CucumberReportBuilder.generateReport(this.karateTestResults.getReportDir());
    }
}
