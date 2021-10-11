import java.util.Random;

public class RunTest {

	private final static int NQUEUES = 10;
	private final static int NELEMS = 100;  // keep it even

	public static void main(String[] args) throws IllegalQueueRequest {
		Random rnd = new Random();
		rnd.setSeed(237);

		QueueSystem<Integer> q = new ArrayQueueSystem<>(NQUEUES);
		System.out.printf("Current queue: %d (empty? %b)\n", q.current(), q.isEmpty());
		q.create(); 		
		q.activate(NQUEUES);

		//////////////////////

		

		for(int i=0; i<NELEMS; i++) {
			q.focus(rnd.nextInt(NQUEUES+1));	
			q.enqueue(rnd.nextInt(100));
		}
		System.out.print(q);
		
		//System.out.println("SIZE -> "+q.size());
		
		//System.out.println(NQUEUES);
		
		//System.out.println();
		//System.out.print("FOCUS MIN -> "+ q.focusMin() + "\n\n");
		//System.out.print("FOCUS MAX -> "+ q.focusMax() + "\n\n");
		//System.out.println("QUEUE SIZE " + 0 + " -> ");
		//System.out.println();

		//System.out.println("\n\n");

		//q.focus(0);
		//System.out.println("Corrente "+q.current());
		//q.dequeue();
		//q.dequeue();	
		//q.dequeue();

		//q.isActivated(0);
		//System.out.print(q);
		//q.deactivate(0);

		//System.out.println("\n\n");
		//System.out.println("Quantas filas tem "+q.howManyQueues());
		//System.out.println("Quantas filas activas tem "+q.howManyActiveQueues());
		//System.out.println("SIZE " + q.size() );
		//System.out.println("\n");
		//System.out.println("Fila com Max "+q.focusMax());
		//System.out.println("Fila com Min "+q.focusMin());
		//System.out.println("Corrente "+q.current());
		//q.deactivate(0);
		//q.create();
		//System.out.println("Quantas filas tem "+q.howManyQueues());
		
		
		

		int sum=0;
		for(int i=0; i<q.howManyQueues(); i++) {
			if (q.isActivated(i))
				q.focus(i);
			sum += q.front();	
		}
		System.out.printf("Sum of fronts from all %d queues: %d\n", q.howManyActiveQueues(), sum);
		
		

		for(int i=0; i<NELEMS/2; i++) {
			q.focusMax();
			//System.out.println("Valor max " + q.focusMax());
			q.dequeue();
			if (q.isEmpty())
				q.deactivate(q.current());
		}
		
		
		
		//System.out.println("Numero elems " + NELEMS/2);
		System.out.print(q);
		
		

		//System.out.println("NUMERO DE ELEMENTOS CONTADOS PELO CONTADOR ->" + q.size());
		//System.out.println("Quantas filas tem "+q.howManyQueues());
		//System.out.println("Quantas filas activas tem "+q.howManyActiveQueues() + "\n");

		
		
		
		//System.out.println("Corrente "+q.current());
		
		for(int i=0; i<NELEMS/2-1; i++) {
			q.focusMax();
			q.dequeue();
			if (q.isEmpty()) {
				System.out.printf("queue %d deactivated\n", q.current());
				q.deactivate(q.current());
			}
		}
		q.focusMin();
		System.out.printf("There is only %d queue activated with index %d", q.howManyActiveQueues(), q.current());


		//System.out.println("\n\n Quantas filas tem "+q.howManyQueues());
		//System.out.println(" Quantas filas activas tem "+q.howManyActiveQueues());
	}
}
