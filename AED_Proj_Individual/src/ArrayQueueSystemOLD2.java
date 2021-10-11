
import java.lang.reflect.Array;


/**
 * 
 * @author marciomoreira nr49172 
 *
 * @param <E>
 */
public class ArrayQueueSystemOLD2<E> implements QueueSystem<E> {

	private Queue<E>[] queues; // queue of queues
	private boolean [] arrayBoolean; // initiates with true meaning queue is activated
	private int nQueues; // how many queues do we have?
	private int current;

	/**
	 * Constructor
	 * @param howManyQueues the initial number of active queues
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueueSystemOLD2(int howManyQueues) {
		
		// we start with some extra space to account for new queues
		queues = (Queue<E>[])Array.newInstance(Queue.class, howManyQueues * 2);

		arrayBoolean = new boolean [queues.length];

		for (int i = 0; i < queues.length; i++) {
			arrayBoolean[i] = true; // initiates with true
			queues[i] = new ArrayQueue<>();
		}
		nQueues = howManyQueues; 

		/*for(int i = 0; i < nQueues; i++){
			System.out.println(arrayBoolean[i] + " "); // used for debbug arrayBoolean[i]
		}*/
	}

	/* note: when creating new queues, the vector might became full.
	 * Use the same strategy applied in classes Stack or Queue
	 * (ie, the new vector will have twice the elements) to achieve
	 * more space.
	 */


	/**
	 * Array will have twice the elements to achieve more space.
	 * @return same boolean array && same queue array Wsized
	 */
	public void resize(){

		@SuppressWarnings("unchecked")
		// new queue with 2*theOldLength
		Queue<E>[] novaQueue = (Queue<E>[])Array.newInstance(Queue.class, nQueues * 2); 
		// new bigger array
		boolean [] newArrayBoolean = new boolean[novaQueue.length] ; 

		for(int i = 0; i < novaQueue.length; i++) {
			novaQueue[i] = new ArrayQueue<>();
			newArrayBoolean[i] = true; // initiates [i] = true/activated
		}
		for(int j = 0; j < this.queues.length; j++) {
			novaQueue[j] = queues[j];
			newArrayBoolean[j] = arrayBoolean[j];
		}
		this.queues = novaQueue;
		this.nQueues = nQueues * 2;
		this.arrayBoolean = newArrayBoolean;
	}


	public void getSizeQueue(int a){
		int count = 0;
		for (int i = 0; i < queues.length; i++) {
			for (int j = 0; j < queues[i].size(); j++) {
				count++;
			}
			System.out.println("Fila " + i + " com "+ count + " elementos");
		}
	}
	
	/**
	 * Inform which is the current queue
	 * @return the index of the current queue
	 */
	@Override
	public int current() {
		
		return this.current;
	}


	/** 
	 * Add an element to the rear of the current queue
	 * @requires isActivated(current())
	 * @param e The object to insert.
	 * @throws IllegalQueueRequest if the queue is not activated 
	 */
	@Override
	public void enqueue(E e) throws IllegalQueueRequest {
		
		if (!isActivated(current)){
			throw new IllegalQueueRequest ("Not active queue or empty");
		}
		this.queues[current].enqueue(e);

	}


	/** 
	 * Remove the element at the front of the current queue.
	 * @requires isActivated(current())
	 * @requires !isEmpty()
	 * @throws IllegalQueueRequest if the queue is not activated or is empty
	 */
	@Override
	public void dequeue() throws IllegalQueueRequest {

		/*if (this.queues[current].isEmpty() || !isActivated(current)){
			throw new IllegalQueueRequest ("Not active queue or empty");
		  }*/
		//System.out.println("Corrente "+current);
		this.queues[current].dequeue();
	}


	/** 
	 * @return e The element at the front of the current queue.
	 * @requires isActivated(current())
	 * @requires !isEmpty() 
	 * @return head of the current queue
	 * @throws IllegalQueueRequest if the queue is not activated or is empty 
	 */
	@Override
	public E front() throws IllegalQueueRequest { 

		if (this.queues[current].isEmpty() || !isActivated(current)){
			throw new IllegalQueueRequest ("Not active queue or empty");
		}
		else
			return this.queues[current].front();
	}


	/** 
	 * @return If the current queue is empty. 
	 */
	@Override
	public boolean isEmpty() {

		return this.queues[current].size() == 0;
	}


	/**
	 * @return the number of existing queues
	 */
	@Override
	public int howManyQueues() { 

		int counter = 0;
		for (int i = 0; i < nQueues; i++) {
			if(queues[i].size() > 0){
				counter++;
			}
		}
		return counter;
	}


	/**
	 * @return the number of all active queues
	 */
	@Override
	public int howManyActiveQueues() {
		
		int counter = 0;
		for (int i = 0; i < nQueues; i++) {
			if( isActivated(i)){
				counter++;
			}
		}
		return counter;
	}


	/**
	 * The number of elements inside the queue system
	 * @return the number of elements in the queue system
	 */
	@Override
	public int size() {	

		int total = 0;
		for(int i = 0; i < queues.length; i++) {
			total += queues[i].size();
		}
		return total;
	}


	/**
	 * Create a new queue at the end of the system,
	 * which starts non activated
	 * @ensures !isActivated(howManyQueues()-1)
	 */
	@Override
	public void create() { 		

		//boolean [] newArray;
		if(nQueues+1 == queues.length){
			resize();
		}
		this.queues[nQueues] = new ArrayQueue<>();

		nQueues++;

		//newArray = new boolean [nQueues];

		//for (int n = 0; n < newArray.length; n++) {
		//	newArray[n] = arrayBoolean[n];
		//}
		//newArray[newArray.length-1] = true;
		//arrayBoolean = newArray;
	}


	/**
	 * Verify if the i-th queue is an active queue
	 * @param i the index of the queue
	 * @requires i >= 0
	 * @return true if activated false otherwise 
	 */
	@Override
	public boolean isActivated(int i) { 

		return arrayBoolean[i] == true;
	}


	/**
	 * Make the i-th queue an active queue
	 * @ensures isActivated(i)
	 * @param i the index of the queue
	 * @requires i >= 0
	 */
	@Override
	public void activate(int i) {

		this.arrayBoolean[i] = true; // fill state activated
	}


	/**
	 * Make the i-th queue a deactivated queue
	 * @requires isEmpty()
	 * @ensures !isActivated(i)
	 * @param i the index of the queue
	 * @requires i >= 0
	 * @throws IllegalQueueRequest if the queue is not empty 
	 */
	@Override
	public void deactivate(int i) throws IllegalQueueRequest {
	
		if(!queues[i].isEmpty()) {
			throw new IllegalQueueRequest("Not active queue");
		}
		arrayBoolean[i] = false;
	}


	/**
	 * Make the i-th queue, the current queue
	 * @requires isActivated(i)
	 * @ensures current()==i
	 * @param i the index of the new current queue
	 * @requires i >= 0
	 * @throws IllegalQueueRequest if the queue is not activated 
	 */
	@Override
	public void focus(int i) throws IllegalQueueRequest {

		if (!isActivated(i)){
			throw new IllegalQueueRequest ("Not active queue");
		}
		current = i;
	}


	
	
	/**
	 * Focus on a queue with minimal occupation 
	 * (if several equal, pick the queue with the least index)
	 * @ensures isActivated(current())
	 * @return the number of elements in that queue 
	 */
	@Override
	public int focusMin() { 

		int min = queues[0].size();
		for ( int i = 0; i <queues.length; i++){
			if(queues[i].size() < min){
				min = queues[i].size();
				current = i;
			}
		}
		return current;
	}


	/**
	 * Focus on a queue with maximal occupation
	 * (if several equal, pick the queue with the least index)
	 * @ensures isActivated(current())
	 * @return the number of elements in that queue 
	 */
	@Override
	public int focusMax() {
	
		int max = queues[0].size();
		for ( int i = 0; i <queues.length; i++){
			if(queues[i].size() > max){
				max = queues[i].size();
				current = i;
			}
		}
		//System.out.println("Valor " + max);
		return current ;
	}


	/**
	 * @return a textual description of the queue system state 
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for(int i = 0 ; i < nQueues ; i++) {
			sb.append(queues[i]).append("\n");
		}
		return sb.toString();
	}

}
