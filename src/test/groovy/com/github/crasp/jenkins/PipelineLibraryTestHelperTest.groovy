package com.github.crasp.jenkins

import com.lesfurets.jenkins.unit.MethodCall
import org.junit.Test

import static PipelineLibraryTestHelper.collectShellArguments
import static PipelineLibraryTestHelper.createTempScript
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

class PipelineLibraryTestHelperTest {

    @Test
    void 'should create a simple script'() throws Exception {
        createTempScript('foobar', 'someCrazyMethod(withMoreCrazyStuff)')
        assertTrue(new File('target/debug-vars/foobar.pipeline').exists())
        assertTrue(new File('target/debug-vars/foobar.pipeline').text.contains('someCrazyMethod(withMoreCrazyStuff)'))
    }

    @Test
    void 'should return empty string for an empty call stack'() throws Exception {
        assertEquals('', collectShellArguments([]))
    }

    @Test
    void 'should return one argument if only one given'() throws Exception {
        List<MethodCall> callStack = []
        MethodCall call = new MethodCall()
        call.setMethodName('sh')
        call.setArgs('hugo')
        callStack.add(call)

        assertEquals('hugo', collectShellArguments(callStack))
    }

    @Test(expected = IllegalStateException)
    void 'should return one argument if two given'() throws Exception {
        List<MethodCall> callStack = []
        MethodCall call1 = new MethodCall()
        MethodCall call2 = new MethodCall()
        call1.setMethodName('sh')
        call1.setArgs('hugo')
        call2.setMethodName('sh')
        call2.setArgs('hans')

        callStack.add(call1)
        callStack.add(call2)

        collectShellArguments(callStack)
    }
}
