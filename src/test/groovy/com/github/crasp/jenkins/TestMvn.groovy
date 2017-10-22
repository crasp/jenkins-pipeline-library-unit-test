package com.github.crasp.jenkins

import org.junit.Test

import static PipelineLibraryTestHelper.collectShellArguments
import static org.junit.Assert.assertEquals

class TestMvn extends PipelineLibraryTest {

    @Test
    void 'should use standard goals'() throws Exception {

        callMethod('mvn()')

        String actual = collectShellArguments(helper.callStack)
        assertEquals('mvn clean install --update-snapshots --batch-mode', actual)
        assertJobStatusSuccess()
    }

    @Test
    void 'should override goals'() throws Exception {

        callMethod("mvn(goals: 'validate')")

        String actual = collectShellArguments(helper.callStack)
        assertEquals('mvn validate --update-snapshots --batch-mode', actual)
        assertJobStatusSuccess()
    }
}
