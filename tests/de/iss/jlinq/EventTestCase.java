package de.iss.jlinq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.jlinq.events.Delegate;
import com.github.jlinq.events.Event;
import com.github.jlinq.events.EventArgs;
import com.github.jlinq.events.EventDelegate;

public class EventTestCase {

	private Delegate<EventTestCase, EventArgs> testStartedDelegate = new Delegate<>();
	public final Event<EventTestCase, EventArgs> TestStarted = new Event<>(testStartedDelegate);
	
	
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
		TestStarted.register((s, a) -> fullRaised = true);
		TestStarted.register(new EventDelegate<EventTestCase, EventArgs>() {

			@Override
			public void raised(EventTestCase sender, EventArgs parameter) {
				assertNotNull(sender);
				assertNotNull(parameter);
			}
		});
		TestStarted.register(() -> emptyRaised = true);
		TestStarted.registerParameter(p -> parameterRaised = true);
		TestStarted.registerSender(s -> senderRaised = true);
		testStartedDelegate.raise(this, new EventArgs());
		
		assertTrue(fullRaised);
		assertTrue(parameterRaised);
		assertTrue(senderRaised);
		assertTrue(emptyRaised);
	}

}
