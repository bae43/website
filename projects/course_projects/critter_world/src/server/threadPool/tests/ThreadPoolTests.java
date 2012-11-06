package server.threadPool.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.threadPool.ThreadPool;

public class ThreadPoolTests {

	@Test
	/** Constructs, which starts running, new thread pool
	 * and checks order of execution on added runnables */
	public void test() {
		
		ThreadPool pool = new ThreadPool(10, 100);
		
		Runnable print = new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello!");				
			}			
		};
		Runnable time = new Runnable() {
			@Override
			public void run() {					
				System.out.println(System.nanoTime());
			}			
		};
		
		for (int i = 0; i != 100000; i++) {
			pool.add(print);
			pool.add(time);
		}

	}
	
	@Test
	/** Tests to see if method returns value correctly
	 * from adding request to thread pool */
	public void returnTest() {
		
		class Caller {
			
			private ThreadPool pool = new ThreadPool(10, 100);
			private int number = 0;
			
			public int getNumber() {
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						number = 3;
					}					
				});
				pool.add(t);
				try {
					//println() statement required for this to work
					System.out.println("waiting for thread to assign value");
					t.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return number;
			}
			
		}
		
		assertTrue("value from thread incorrect", new Caller().getNumber() == 3);
		
	}

}
