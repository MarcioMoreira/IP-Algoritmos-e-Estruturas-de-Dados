

import java.lang.reflect.Array;


/**
 *
 * @author marciomoreira nr41972
 *
 * @param <E>
 */
public class ArrayQueueSystemOLD<E> implements QueueSystem<E> {

	private Queue<E>[] queues;
	private boolean [] arrayBoolean;
	private int nQueues;       // how many queues do we have?

	//private int size = 0;
	//private int tail = 0;
	private int current = 0;


	/**
	 * Constructor
	 * @param howManyQueues the initial number of active queues
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueueSystemOLD(int howManyQueues) throws IllegalArgumentException {

		// we start with some extra space to account for new queues
		queues = (Queue<E>[])Array.newInstance(Queue.class, howManyQueues * 2);
		arrayBoolean = new boolean [queues.length];
		//TODO
		nQueues = howManyQueues;
		for (int i = 0; i < queues.length; i++) {
			arrayBoolean[i] = false;
			queues[i] = new ArrayQueue<>();
			//System.out.println("Teste " + newQueue);
			//nQueues = howManyQueues;
		    //System.out.println(arrayBoolean[i] + " ");
		}

	}



	// TODO

	/* note: when creating new queues, the vector might became full.
	 * Use the same strategy applied in classes Stack or Queue
	 * (ie, the new vector will have twice the elements) to achieve
	 * more space.
	 */


	/**
	 * 
	 * Array will have twice the elements to achieve more space.
	 * 
	 */
	public void resize(){

		@SuppressWarnings("unchecked")
		Queue<E>[] novaQueue = (Queue<E>[])Array.newInstance(Queue.class, nQueues * 2);

		for(int i = 0; i < novaQueue.length; i++) {
			novaQueue[i] = new ArrayQueue<>();
		}
		for(int j = 0; j < this.queues.length; j++) {
			novaQueue[j] = queues[j];
		}
		this.queues = novaQueue;
		this.nQueues = nQueues * 2;
	}

	@Override
	public int current() {
		return this.current;
	}

	@Override
	public void enqueue(E e) throws IllegalQueueRequest {
		// TODO Auto-generated method stub

		if(!isActivated(current)){
			throw new IllegalQueueRequest("Must be an active queue");
		}
		this.queues[current].enqueue(e);
	}

	@Override
	public void dequeue() throws IllegalQueueRequest {
		// TODO Auto-generated method stub

		if (!isActivated(current)){
			throw new IllegalQueueRequest ("Not active queue or empty");
		}
		this.queues[current].dequeue();
	}

	@Override
	public E front() throws IllegalQueueRequest { //Estah a funcionar a soma está correcta
		// TODO Auto-generated method stub

		if (this.queues[current].isEmpty() || !isActivated(current)){
			throw new IllegalQueueRequest ("Not active queue or empty");
		}
		else
			return this.queues[current].front();

	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub

		return this.queues[current].size() == 0;
	}

	@Override
	public int howManyQueues() {
		// TODO Auto-generated method stub
		return this.nQueues;
	}

	@Override
	public int howManyActiveQueues() {
		// TODO Auto-generated method stub

		int counter= 0;

		for (int i = 0; i < queues.length; i++) {
			if( isActivated(i)){
				counter++;
			}
		}
		return counter;// escolhido pelos profs
	}

	@Override
	public int size() {
		int total = 0;
		for(int i = 0; i < queues.length; i++) {
			total += queues[i].size();
		}
		return total;
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub

		this.queues[this.howManyQueues()] = new ArrayQueue<>();
		this.arrayBoolean[this.howManyQueues()] = false;
	}

	@Override
	public boolean isActivated(int i) {
		// TODO Auto-generated method stub
		return current  == i;
	}

	@Override
	public void activate(int i) {
		// TODO Auto-generated method stub
		this.arrayBoolean[i] = true;
	}

	@Override
	public void deactivate(int i) throws IllegalQueueRequest {
		// TODO Auto-generated method stub
		
		for(int j = 0; j < queues[i].size(); j++) {
			queues[i].dequeue();
		}
		this.arrayBoolean[i] = false;
	}

	@Override
	public void focus(int i) throws IllegalQueueRequest {
		// TODO Auto-generated method stub

		if (!isActivated(current))
			throw new IllegalQueueRequest ("Not active queue");

		current = i;
	}

	@Override
	public int focusMin() {
		// TODO Auto-generated method stub
		int min = queues[0].size();
		
		for ( int i = 0; i <queues.length; i++){
			if(queues[i].size() < min){
				min = queues[i].size();
				current = i;
			}
		}
		System.out.println("CORRENTE"+ current);
		return current;
	}

	@Override
	public int focusMax() {
		// TODO Auto-generated method stub
		int max = queues[0].size();
		for ( int i = 0; i <queues.length; i++){
			if(queues[i].size() > max){
				max = queues[i].size();
				current = i;
			}
		}
		System.out.println("CORRENTE"+ current);
		return current ;
	}


	public String toString() {
		StringBuilder sb = new StringBuilder();

		for(int i=0; i<nQueues; i++) {
			sb.append(queues[i]).append("\n");
		}

		return sb.toString();
	}

}
