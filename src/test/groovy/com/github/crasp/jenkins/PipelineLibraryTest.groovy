package com.github.crasp.jenkins

import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestName

import static com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration.library
import static PipelineLibraryTestHelper.createTempScript
import static com.lesfurets.jenkins.unit.global.lib.LocalSource.localSource

abstract class PipelineLibraryTest extends BasePipelineTest {

    @Rule
    public TestName testName = new TestName()

    @Override
    @Before
    /**
     * Do the same setup for all derived test cases in order to use the same library definition everywhere.
     */
    void setUp() throws Exception {
        super.setUp()
        helper.registerSharedLibrary(library('library-under-test')
            .defaultVersion('master')
            .targetPath('target')
            .retriever(localSource('target'))
            .build())
    }

    protected void callMethod(final String method) {
        runScript(createTempScript(testName.getMethodName(),method))
    }
}
