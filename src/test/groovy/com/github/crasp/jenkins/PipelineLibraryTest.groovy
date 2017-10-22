package com.github.crasp.jenkins

import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.Before

import static com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration.library
import static com.lesfurets.jenkins.unit.global.lib.LocalSource.localSource

abstract class PipelineLibraryTest extends BasePipelineTest {

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
}
