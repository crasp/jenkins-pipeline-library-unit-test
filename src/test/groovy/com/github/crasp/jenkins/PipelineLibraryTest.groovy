package com.github.crasp.jenkins

import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestName

import static com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration.library
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

        registerLibrary()
        registerMethods()
    }

    private void registerLibrary() {
        helper.registerSharedLibrary(library('library-under-test')
            .defaultVersion('master')
            .targetPath('target')
            .retriever(localSource('target'))
            .build())
    }

    private void registerMethods() {
        helper.registerAllowedMethod('someFunnyStuff', [Map.class, Closure.class], null)
    }

    protected void callMethod(final String method) {
        runScript(createTempScript(method))
    }

    /**
     * Create a script file containing the whole library definition stuff around the given method call.
     */
    private String createTempScript(final String methodCall) {
        File outputDir = new File("target/debug-vars")
        if (!outputDir.exists()) {
            outputDir.mkdirs()
        }
        File file = new File(outputDir, "${getClass().getSimpleName()} - ${testName.getMethodName()}.pipeline")
        file.newWriter().withWriter { writer ->
            writer << "@Library('library-under-test') _\n"
            writer << "node() {\n"
            writer << "    stage('testMe') {\n"
            writer << "        ${methodCall}\n"
            writer << "    }\n"
            writer << "}\n"
        }
        return file.getPath()
    }

    /**
     * Collect all shell arguments in the current call stack.
     */
    protected String getShellArguments() {
        return helper.callStack.findAll { 'sh' == it.methodName }.collect { it.argsToString() }.join(',')
    }
}
