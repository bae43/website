package server.threadPool.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import server.threadPool.SynchronizedRingBuffer;

public class NonConcurrentTests {

	@Test
	/** Tests all methods of SynchronizedRingBuffer
	 * without a call to wait() */
	public void test() {
		
		SynchronizedRingBuffer<String> buf = new SynchronizedRingBuffer<String>(10);
		assertTrue("isEmpty() fails on empty string", buf.isEmpty());
		assertTrue("peek() fails on empty string", buf.peek() == null);
		assertTrue("poll() returns incorrectly", buf.poll() == null);
		
		assertTrue("add() fails unexpectedly", buf.add("hello0"));
		assertFalse("isEmpty() fails on empty string", buf.isEmpty());
		assertTrue("peek() fails on non empty string", buf.peek().equals("hello0"));
		assertTrue("size() incorrect", buf.size() == 1);
		assertTrue("remainingCapacity() incorrect", buf.remainingCapacity() == 9);
		assertTrue("contains() incorrect", buf.contains("hello0"));
		assertFalse("contains() incorrect", buf.contains("goodbye"));
		
		assertTrue("add() fails unexpectedly", buf.add("hello1"));
		assertTrue("add() fails unexpectedly", buf.add("hello2"));
		assertTrue("add() fails unexpectedly", buf.add("hello3"));
		assertTrue("add() fails unexpectedly", buf.add("hello4"));
		assertTrue("offer() fails unexpectedly", buf.offer("hello5"));
		assertTrue("offer() fails unexpectedly", buf.offer("hello6"));
		assertTrue("offer() fails unexpectedly", buf.offer("hello7"));
		assertTrue("offer() fails unexpectedly", buf.offer("hello8"));
		assertTrue("offer() fails unexpectedly", buf.offer("hello9"));
		
		assertTrue("size() incorrect", buf.size() == 10);
		assertTrue("remainingCapacity() incorrect", buf.remainingCapacity() == 0);
		assertTrue("contains() incorrect", buf.contains("hello1"));
		assertTrue("contains() incorrect", buf.contains("hello2"));
		assertTrue("contains() incorrect", buf.contains("hello3"));
		assertTrue("contains() incorrect", buf.contains("hello4"));
		assertTrue("contains() incorrect", buf.contains("hello5"));
		assertTrue("contains() incorrect", buf.contains("hello6"));
		assertTrue("contains() incorrect", buf.contains("hello7"));
		assertTrue("contains() incorrect", buf.contains("hello8"));
		assertTrue("contains() incorrect", buf.contains("hello9"));		
		assertFalse("offer() should fail", buf.offer("hello10"));
		
		assertTrue("poll() returns incorrectly", buf.poll().equals("hello0"));
		assertTrue("size() incorrect", buf.size() == 9);
		assertTrue("remainingCapacity() incorrect", buf.remainingCapacity() == 1);
		assertFalse("contains() incorrect", buf.contains("hello0"));
		
		assertTrue("poll() returns incorrectly", buf.poll().equals("hello1"));
		assertTrue("poll() returns incorrectly", buf.poll().equals("hello2"));
		assertTrue("remove() returns incorrectly", buf.remove().equals("hello3"));
		assertTrue("remove() returns incorrectly", buf.remove().equals("hello4"));
		assertTrue("size() incorrect", buf.size() == 5);
		assertTrue("remainingCapacity() incorrect", buf.remainingCapacity() == 5);
		assertFalse("contains() incorrect", buf.contains("hello1"));
		assertFalse("contains() incorrect", buf.contains("hello2"));
		assertFalse("contains() incorrect", buf.contains("hello3"));
		assertFalse("contains() incorrect", buf.contains("hello4"));
		
		assertTrue("add() fails unexpectedly", buf.add("hello10"));
		assertTrue("add() fails unexpectedly", buf.add("hello11"));
		assertTrue("add() fails unexpectedly", buf.add("hello12"));
		assertTrue("offer() fails unexpectedly", buf.offer("hello13"));
		assertTrue("offer() fails unexpectedly", buf.offer("hello14"));		
		assertTrue("size() incorrect", buf.size() == 10);
		assertTrue("remainingCapacity() incorrect", buf.remainingCapacity() == 0);
		
		assertTrue("peek() returns incorrectly", buf.peek().equals("hello5"));		
		assertTrue("element() returns incorrectly", buf.element().equals("hello5"));
		
		Iterator<String> bufIter = buf.iterator();
		assertTrue("hasNext() incorrect", bufIter.hasNext());
		assertTrue("next() returns incorrectly", bufIter.next().equals("hello5"));
		assertTrue("next() returns incorrectly", bufIter.next().equals("hello6"));
		assertTrue("next() returns incorrectly", bufIter.next().equals("hello7"));
		assertTrue("next() returns incorrectly", bufIter.next().equals("hello8"));
		assertTrue("next() returns incorrectly", bufIter.next().equals("hello9"));
		assertTrue("next() returns incorrectly", bufIter.next().equals("hello10"));
		assertTrue("next() returns incorrectly", bufIter.next().equals("hello11"));
		assertTrue("next() returns incorrectly", bufIter.next().equals("hello12"));
		assertTrue("next() returns incorrectly", bufIter.next().equals("hello13"));
		assertTrue("next() returns incorrectly", bufIter.next().equals("hello14"));
		assertFalse("hasNext() incorrect", bufIter.hasNext());		
		
		Object[] bufArray = buf.toArray();
		Object[] testArray = {"hello5", "hello6", "hello7", "hello8", "hello9", 
				"hello10", "hello11", "hello12", "hello13", "hello14"};
		for (int i = 0; i != bufArray.length; i++) {
			assertTrue("toArray() incorrect", bufArray[i].equals(testArray[i]));
		}
		
		assertTrue("poll() returns incorrectly", buf.poll().equals("hello5"));
		assertTrue("poll() returns incorrectly", buf.poll().equals("hello6"));
		assertTrue("remove() returns incorrectly", buf.remove().equals("hello7"));
		assertTrue("remove() returns incorrectly", buf.remove().equals("hello8"));
		assertTrue("poll() returns incorrectly", buf.poll().equals("hello9"));
		assertTrue("poll() returns incorrectly", buf.poll().equals("hello10"));
		assertTrue("remove() returns incorrectly", buf.remove().equals("hello11"));
		assertTrue("remove() returns incorrectly", buf.remove().equals("hello12"));
		assertTrue("poll() returns incorrectly", buf.poll().equals("hello13"));
		assertTrue("remove() returns incorrectly", buf.remove().equals("hello14"));
		assertTrue("size() incorrect", buf.size() == 0);
		assertTrue("remainingCapacity() incorrect", buf.remainingCapacity() == 10);
		
		buf.offer("hello0");
		buf.offer("hello1");
		buf.offer("hello2");
		buf.offer("hello3");
		buf.offer("hello4");
		
		Collection<String> bufCollection = new ArrayList<String>();
		assertTrue("drainTo() returns incorrectly", buf.drainTo(bufCollection, 2) == 2);
		Collection<String> testCollection = new ArrayList<String>();
		testCollection.add("hello0");
		testCollection.add("hello1");
		assertTrue("drainTo() incorrect", bufCollection.equals(testCollection));
		assertTrue("size() incorrect", buf.size() == 3);
		
		bufCollection = new ArrayList<String>();
		assertTrue("drainTo() returns incorrectly", buf.drainTo(bufCollection) == 3);
		testCollection = new ArrayList<String>();
		testCollection.add("hello2");
		testCollection.add("hello3");
		testCollection.add("hello4");
		assertTrue("drainTo() incorrect", bufCollection.equals(testCollection));
		assertTrue("size() incorrect", buf.size() == 0);
		
		try {
			assertTrue("offer() returns incorrectly", buf.offer("hello0", 100, TimeUnit.SECONDS));
			assertTrue("poll() returns incorrectly", buf.poll(100, TimeUnit.SECONDS).equals("hello0"));
			assertTrue("poll() returns incorrectly", buf.poll(100, TimeUnit.SECONDS) == null);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}