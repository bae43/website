package server.threadPool;

/** Contains limited size queue of client requests 
 * to be executed by limited number of worker threads */
public class ThreadPool {
	
	/** SynchronizedRingBuffer of Runnable objects */
	private SynchronizedRingBuffer<Runnable> requests;
	
	/** Constructs new ThreadPool with given number of
	 * Worker Threads and given fixed ringbuffer size */
	public ThreadPool(int numWorkers, int queueSize) {
		requests = new SynchronizedRingBuffer<Runnable>(queueSize);
		for (int i = 0; i != numWorkers; i++)
			new Worker().start();
	}
	
	/** Adds request as a Runnable to ringbuffer to be
	 * executed. Ignores if ringbuffer is already full */
	public void add(Runnable r) {
		requests.offer(r);
	}
	
	/** Thread which constantly attempts to take Runnable
	 * objects from requests and execute them. Uses a
	 * version of take() which does not call notify() */
	private class Worker extends Thread {
		public void run() {
			while (true) {
				try {
					requests.poolTake().run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
