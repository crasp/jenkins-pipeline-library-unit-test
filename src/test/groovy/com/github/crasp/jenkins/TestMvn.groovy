package com.github.crasp.jenkins

import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName

import static PipelineLibraryTestHelper.collectShellArguments
import static PipelineLibraryTestHelper.createTempScript
import static org.junit.Assert.assertEquals

class TestMvn extends PipelineLibraryTest {

    @Rule
    public TestName testName = new TestName()

    @Test
    void 'should use standard goals'() throws Exception {

        runScript(createTempScript(testName.getMethodName(),
            'mvn()'
        ))

        assertEquals('mvn clean install --update-snapshots --batch-mode',
            collectShellArguments(helper.callStack))
        assertJobStatusSuccess()
    }

    @Test
    void 'should override goals'() throws Exception {

        runScript(createTempScript(testName.getMethodName(),
            "mvn(goals: 'validate')"
        ))

        assertEquals('mvn validate --update-snapshots --batch-mode',
            collectShellArguments(helper.callStack))
        assertJobStatusSuccess()
    }
}
