
/**
 * 
 * @author marciomoreira  nr - 41972
 *
 */
public class RunThreeStackDeque {

	public static void main(String[] args) {

		ThreeStackDeque<Integer> run3sDeque = new ThreeStackDeque<Integer>();

		// Stacking initiated
		run3sDeque.initiateStacking(1);
		run3sDeque.initiateStacking(2);
		run3sDeque.initiateStacking(3);
		run3sDeque.initiateStacking(4);
		run3sDeque.initiateStacking(5);
		
		//Splits into 2 stacks
		run3sDeque.splitIn2Stacks();
		
		run3sDeque.addEnd(0);
		run3sDeque.addFront(9);
		
		run3sDeque.delEnd();
		run3sDeque.delFront();
		
		
		
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
		
		System.out.println("Deque Back  -> " +run3sDeque.end());
		System.out.println("Deque Front -> " + run3sDeque.front());
		System.out.println();
		System.out.println("--------------------------------------\n");
		
		
		
		System.out.print("Deque Completed  -> ");
		
		run3sDeque.printDeque();
		
		System.out.println("\n\n--------------------------------------\n");
		
		System.out.println("ALL STACKS PUT TOGETHER INTO STACKMULE\n");
		System.out.println("--------------------------------------\n");
		
		//Puts all Stacks together in STackMule in right order
		run3sDeque.putIn2Order();
		
		System.out.print("StackFront state -> ");
		run3sDeque.printStackFront();
		
		System.out.print("\nStackBack  state -> ");
		run3sDeque.printStackBack();
		
		System.out.print("\nStackMule  state -> ");
		run3sDeque.printStackMule();
		
		System.out.println();
		System.out.println("\n--------------------------------------\n");
		
		
		
	}

}
