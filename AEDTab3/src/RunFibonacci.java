/**
 * 
 * @author marciomoreira 41972
 *
 */
public class RunFibonacci {

	public static void main(String[] args) {

		FibonacciGenerator fg = new FibonacciGenerator();
		//System.out.println("SOLUCAO :\n5 3 2 1 <-> 1 2 3 5 8 13 21 34 <-> 1 1 2 3 5");

		for (int i = 0; i < 5; i++) {
			fg.next(); 
		}
		
		//System.out.println(fg.printStacks());

		for(int i=0; i<4; i++) {
			System.out.printf("%d ", fg.prev());

		}
		
		//System.out.println();
		//System.out.println(fg.printStacks());



		//System.out.println();
		for(int i=0; i<8; i++) {
			System.out.printf("%d ", fg.next());
		}
		//System.out.println();

		//System.out.println(fg.printStacks());
		//System.out.println("reset...");
		fg.reset();



		//System.out.println(fg.printStacks());

		for(int i=0; i<5; i++) {
			System.out.printf("%d ", fg.next());
		}

		//System.out.println(fg.printStacks());


	}

}


