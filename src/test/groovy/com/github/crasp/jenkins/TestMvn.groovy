package com.github.crasp.jenkins

import org.junit.Test

import static org.junit.Assert.assertEquals

class TestMvn extends PipelineLibraryTest {

    @Test
    void 'should use standard goals'() throws Exception {

        callMethod('mvn()')

        assertEquals('mvn clean install --update-snapshots --batch-mode', getShellArguments())
        assertJobStatusSuccess()
    }

    @Test
    void 'should override goals'() throws Exception {

        callMethod("mvn(goals: 'validate')")

        assertEquals('mvn validate --update-snapshots --batch-mode', getShellArguments())
        assertJobStatusSuccess()
    }

    @Test
    void 'should not override short snapshot update option'() throws Exception {

        callMethod("mvn(options: '-U')")

        assertEquals('mvn clean install -U --batch-mode', getShellArguments())
        assertJobStatusSuccess()
    }

    @Test
    void 'should not override short batch mode option'() throws Exception {

        callMethod("mvn(options: '-B')")

        assertEquals('mvn clean install -B --update-snapshots', getShellArguments())
        assertJobStatusSuccess()
    }

    @Test
    void 'should not override snapshot update option'() throws Exception {

        callMethod("mvn(options: '--update-snapshots -DskipTests=false')")

        assertEquals('mvn clean install --update-snapshots -DskipTests=false --batch-mode', getShellArguments())
        assertJobStatusSuccess()
    }

    @Test
    void 'should not override batch mode option'() throws Exception {

        callMethod("mvn(options: '--batch-mode -DskipTests=false')")

        assertEquals('mvn clean install --batch-mode -DskipTests=false --update-snapshots', getShellArguments())
        assertJobStatusSuccess()
    }
}
