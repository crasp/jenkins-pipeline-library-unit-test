package com.github.crasp.jenkins

import com.lesfurets.jenkins.unit.MethodCall
import groovy.transform.CompileStatic

@CompileStatic
class PipelineLibraryTestHelper {

    /**
     * Create a script file containing the whole library definition stuff around the given method call.
     */
    static String createTempScript(final String name, final String methodCall) {
        File outputDir = new File("target/debug-vars")
        if (!outputDir.exists()) {
            outputDir.mkdirs()
        }
        File file = new File(outputDir, "${name}.pipeline")
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
    static String collectShellArguments(final List<MethodCall> callStack) {
        List<MethodCall> shellSteps = callStack.findAll { 'sh' == it.methodName }

        if (1 < shellSteps.size()) {
            throw new IllegalStateException('More than one shell step found! Only test one at a time!')
        }

        // get rid of the braces around the arguments '[args]' -> 'args'
        return shellSteps.empty ? '' : shellSteps.first().args.toString()[1..-2]
    }
}

