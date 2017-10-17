package com.github.crasp.pipeline.unit

import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.Before
import org.junit.Test

import static com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration.library
import static com.lesfurets.jenkins.unit.global.lib.LocalSource.localSource

class TestExampleJob extends BasePipelineTest {

    @Override
    @Before
    void setUp() throws Exception {
        super.setUp()
        helper.registerSharedLibrary(library('jenkins-library')
            .defaultVersion('master')
            .targetPath('target')
            .retriever(localSource('target'))
            .build())
    }

    @Test
    void should_execute_without_errors() throws Exception {
        runScript("src/test/resources/exampleJob.pipeline")
        printCallStack()
        assertJobStatusSuccess()
    }
}
