package com.examples.api.testrunner;

import com.examples.api.common.KarateRunner;
import com.intuit.karate.junit5.Karate;

import java.io.IOException;

class KarateApiIntegrationTest {

    private static final String KARATE_TEST_FEATURES_PATH = "classpath:karatetests/";

    @Karate.Test
    Karate test() throws IOException {
        KarateRunner karateRunner =
                new KarateRunner(Karate.run(KARATE_TEST_FEATURES_PATH)
                        .tags("@ApiTest"));

        return karateRunner.executeTests(1);
    }
}
