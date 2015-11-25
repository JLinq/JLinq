package com.github.jlinq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.jlinq.events.Delegate;
import com.github.jlinq.events.Event;
import com.github.jlinq.events.EventArgs;
import com.github.jlinq.events.EventDelegate;
import com.github.jlinq.events.ParameterDelegate;
import com.github.jlinq.events.RaisedDelegate;
import com.github.jlinq.events.SenderDelegate;

public class EventTestCase {

	private Delegate<EventTestCase, EventArgs> testStartedDelegate = new Delegate<EventTestCase, EventArgs>();
	public final Event<EventTestCase, EventArgs> TestStarted = new Event<EventTestCase, EventArgs>(testStartedDelegate);
	
	
	private boolean fullRaised = false;
	private boolean parameterRaised = false;
	private boolean senderRaised = false;
	private boolean emptyRaised = false;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertFalse(fullRaised);
		assertFalse(parameterRaised);
		assertFalse(senderRaised);
		assertFalse(emptyRaised);
		TestStarted.register(new EventDelegate<EventTestCase, EventArgs>() {
			@Override
			public void raised(EventTestCase s, EventArgs a) {
				fullRaised = true;
			}
		});
		TestStarted.register(new EventDelegate<EventTestCase, EventArgs>() {

			@Override
			public void raised(EventTestCase sender, EventArgs parameter) {
				assertNotNull(sender);
				assertNotNull(parameter);
			}
		});
		TestStarted.register(new RaisedDelegate() {
			@Override
			public void raised() {
				emptyRaised = true;
			}
		});
		TestStarted.registerParameter(new ParameterDelegate<EventArgs>() {
			@Override
			public void raised(EventArgs p) {
				parameterRaised = true;
			}
		});
		TestStarted.registerSender(new SenderDelegate<EventTestCase>() {
			@Override
			public void raised(EventTestCase s) {
				senderRaised = true;
			}
		});
		testStartedDelegate.raise(this, new EventArgs());
		
		assertTrue(fullRaised);
		assertTrue(parameterRaised);
		assertTrue(senderRaised);
		assertTrue(emptyRaised);
	}

}
