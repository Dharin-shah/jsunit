package net.jsunit.interceptor;

import com.opensymphony.xwork.Action;

import net.jsunit.RemoteMachineRunnerHitter;
import net.jsunit.RemoteRunnerHitter;
import net.jsunit.action.RemoteRunnerHitterAware;
import junit.framework.TestCase;

public class RemoteRunnerHitterInterceptorTest extends TestCase {

	public void testSimple() throws Exception {
		RemoteRunnerHitterInterceptor interceptor = new RemoteRunnerHitterInterceptor();
		MockAction action = new MockAction();
		MockActionInvocation invocation = new MockActionInvocation(action);
		interceptor.intercept(invocation);
		assertNotNull(action.hitter);
		assertTrue(action.hitter instanceof RemoteMachineRunnerHitter);
	}
	
	static class MockAction implements RemoteRunnerHitterAware, Action {

		private RemoteRunnerHitter hitter;

		public String execute() throws Exception {
			return null;
		}

		public void setRemoteRunnerHitter(RemoteRunnerHitter hitter) {
			this.hitter = hitter;
		}
		
	}
	
}