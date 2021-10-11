
/**
 * 
 * @author marciomoreira  41972
 *
 */
public class RunThreeStackDeque1 {

	public static void main(String[] args) {

		ThreeStackDequeAlt<Integer> run3sDeque = new ThreeStackDequeAlt<Integer>();


		run3sDeque.addFront(1);
		run3sDeque.addFront(2);
		run3sDeque.addFront(3);
		run3sDeque.addFront(4);
		
		run3sDeque.delFront();
		run3sDeque.delFront();
		
		run3sDeque.addEnd(9);
		run3sDeque.addEnd(8);
		run3sDeque.addEnd(3);
		
		run3sDeque.addFront(7);
		run3sDeque.addFront(0);
		run3sDeque.delEnd();
		run3sDeque.addEnd(4);
		
		//System.out.println(" DEQUE SIZE "+run3sDeque.stackFrontSize());
		
		System.out.println("--------------------------------------\n");
		System.out.println("      DEQUE USING THREE STACKS\n");
		System.out.println("--------------------------------------\n");

		System.out.print("StackFront state -> ");
		run3sDeque.printStackFront();
		
		System.out.print("\nStackBack  state -> ");
		run3sDeque.printStackBack();
		
		System.out.print("\nStackMule  state -> ");
		run3sDeque.printStackMule();
		
		System.out.println("\n\n--------------------------------------\n");
		
		System.out.print("Deque Completed  -> ");
		run3sDeque.printDeque();
		
		System.out.println("\n\n--------------------------------------\n");
		
		System.out.println("Deque Back  -> " +run3sDeque.end());
		System.out.println("Deque Front -> " + run3sDeque.front());
		System.out.println();
		System.out.println("--------------------------------------\n");
		
		
	}

}
