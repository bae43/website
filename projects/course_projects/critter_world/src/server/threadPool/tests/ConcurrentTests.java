package server.threadPool.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.threadPool.SynchronizedRingBuffer;

public class ConcurrentTests {

	@Test
	/** Tests methods with wait(), put and take, using
	 * producer consumer threads */
	public void test() {
		
		final SynchronizedRingBuffer<String> buf = new SynchronizedRingBuffer<String>(10);
		
		Thread producer = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i != 100; i++)
					try {
						buf.put("string" + i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		});		
		Thread consumer = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i != 100; i++)
					try {
						buf.take();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}			
		});		
		
		producer.start();
		consumer.start();		
		assertTrue("thread blocking incorrect", buf.size() == 0);

	}

}
