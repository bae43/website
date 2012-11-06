package server.threadPool;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/** Implementation of BlockingQueue using an array */
public class SynchronizedRingBuffer<E> implements BlockingQueue<E>, Queue<E>, Collection<E> {
	
	/** Array which stores all objects. Does not
	 * use an empty slot to distinguish empty and
	 * full by comparing head and tail */
	private Object[] buffer;
	/** Index of buffer where elements are removed */
	private int head;
	/** Index of buffer where elements are added */
	private int tail;
	/** Count of elements used to determine whether
	 * buffer is empty or full and to return size */
	private int size;
	
	/** Constructs a new SynchronizedRingBuffer with given length */
	public SynchronizedRingBuffer(int length) {
		buffer = new Object[length];
		head = 0;
		tail = 0;
		size = 0;
	}
	
	@Override
	/** Returns iterator of buffer */
	public synchronized Iterator<E> iterator() {
		return new SynchronizedRingBufferIterator();	
	}
	/** Iterator of SynchronizedRingBuffer. Does not
	 * implement remove() method */
	private class SynchronizedRingBufferIterator implements Iterator<E> {
		
		/** Index of current element iterator points to */
		private int index;
		/** Count of elements remaining for next() */
		private int count;
		/** Constructs iterator with first element at head
		 * and count equal to number of elements in buffer */
		public SynchronizedRingBufferIterator() {
			index = head;
			count = size;
		}
		
		@Override
		public synchronized boolean hasNext() {
			return count != 0;
		}
		@Override @SuppressWarnings("unchecked")
		public synchronized E next() {
			if (count == 0)
				throw new NoSuchElementException();
			count--;
			int tempIndex = index;
			index = increment(index);
			return (E) buffer[tempIndex];
		}
		@Override
		public void remove() {
			throw new UnsupportedOperationException();			
		}
		
	}
	
	@Override
	public synchronized int size() {
		return size;
	}
	@Override
	public synchronized boolean isEmpty() {
		return size == 0;
	}
	@Override
	public synchronized int remainingCapacity() {
		return buffer.length - size;
	}
	
	@Override
	public synchronized Object[] toArray() {
		Object[] ret = new Object[buffer.length];
		int retIndex = 0;
		for (int i = head, c = 0; c != size && i != buffer.length; i++, c++)
			ret[retIndex++] = buffer[i];
		if (head >= tail && size != 0)
			for (int i = 0; i != tail; i++)
				ret[retIndex++] = buffer[i];
		return ret;
	}
	@Override
	public synchronized boolean offer(E e) {
		if (size == buffer.length)
			return false;
		size++;
		int index = tail;
		tail = increment(tail);
		buffer[index] = e;
		notify();
		return true;
	}
	@Override @SuppressWarnings("unchecked")
	public synchronized E poll() {
		if (size == 0)
			return null;
		size--;
		int index = head;
		head = increment(head);
		notify();
		return (E) buffer[index];
	}
	@Override @SuppressWarnings("unchecked")
	public synchronized E remove() {
		if (size == 0)
			throw new NoSuchElementException();
		size--;
		int index = head;
		head = increment(head);
		notify();
		return (E) buffer[index];
	}
	@Override @SuppressWarnings("unchecked") 
	public synchronized E peek() {
		if (size == 0)
			return null;
		return (E) buffer[head];
	}
	@Override @SuppressWarnings("unchecked")
	public synchronized E element() {
		if (size == 0)
			throw new NoSuchElementException();
		return (E) buffer[head];
	}
	@Override
	public synchronized boolean add(E e) {
		if (size == buffer.length)
			throw new NoSuchElementException();
		size++;
		int index = tail;
		tail = increment(tail);
		buffer[index] = e;
		notify();
		return true;
	}
	@Override
	public synchronized boolean contains(Object o) {
		for (int i = head, c = 0; c != size; i = increment(i), c++)
			if (o.equals(buffer[i]))
				return true;
		return false;
	}
	@Override @SuppressWarnings("unchecked")
	public synchronized int drainTo(Collection<? super E> c) {
		if (c == null)
			throw new NullPointerException();
		if (c == this)
			throw new IllegalArgumentException();
		int n = 0;
		for (; head != tail; head = increment(head), n++) {
			size--;
			c.add((E) buffer[head]);
		}
		notify();
		return n;
	}
	@Override @SuppressWarnings("unchecked")
	public synchronized int drainTo(Collection<? super E> c, int maxElements) {
		if (c == null)
			throw new NullPointerException();
		if (c == this)
			throw new IllegalArgumentException();
		int n = 0;
		for (; head != tail && n != maxElements; head = increment(head), n++) {
			size--;
			c.add((E) buffer[head]);
		}
		notify();
		return n;
	}
	@Override
	public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		long start = System.nanoTime();
		long time = TimeUnit.NANOSECONDS.convert(timeout, unit);
		while (System.nanoTime() - start < time)
			return offer(e);
		return false;
	}
	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		long start = System.nanoTime();
		long time = TimeUnit.NANOSECONDS.convert(timeout, unit);
		while (System.nanoTime() - start < time)
			return poll();
		return null;		
	}
	@Override
	public synchronized void put(E e) throws InterruptedException {
		while (size == buffer.length)
			wait();		
		size++;
		buffer[tail] = e;
		tail = increment(tail);
		notify();
	}
	@Override @SuppressWarnings("unchecked")
	public synchronized E take() throws InterruptedException {
		while (size == 0)
			wait();		
		size--;	
		int index = head;
		head = increment(head);	
		notify();
		return (E) buffer[index];		
	}
	@SuppressWarnings("unchecked")
	/** Version of take() without notify(). For use with offer() */
	public synchronized E poolTake() throws InterruptedException {
		while (size == 0)
			wait();		
		size--;	
		int index = head;
		head = increment(head);	
		return (E) buffer[index];		
	}
	
	/** Increments index of ringbuffer */
	private int increment(int i) {
		return i + 1 == buffer.length ? 0 : i + 1;
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}
	@Override
	public void clear() {
		throw new UnsupportedOperationException();		
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

}
