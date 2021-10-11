import java.util.Stack;

/**
 *
 * @author marciomoreira nr - 41972
 *
 * Class that uses 3 Stacks. One for front operations, one for back,
 * another as mule just to keep elements as needed
 *
 * @param <E> Abstract type ADT
 *
 * -----------------------------------------------------------------------
 *
 * Conclusão:
 *
 * O pior caso ocorre poucas vezes, neste caso O(n) nas operacoes de
 * passagem dos elementos da stackMule para as outras stacks ou quando
 * o caminho oposto acontece para reordenacao da pilha com os elementos
 * da Deque todos ordenados e juntos.
 * O melhor caso a maioria das vezes com as outras operacoes todas,
 * o custo das N operacoes poderá ser O(N) o que significa que a media
 * das operacoes sera O(N)/N = O(1) amortizado.
 * Logo é O(1) amortizado a conclusão deste trabalho.
 *
 *
 */
public class ThreeStackDeque<E> implements Deque<E> {

	private Stack<E> stackFront;
	private Stack<E> stackBack;
	private Stack<E> stackMule;

	/**
	 * Constructor
	 */
	public ThreeStackDeque(){
		stackFront = new Stack<>();
		stackBack = new Stack<>();
		stackMule = new Stack<>();
	}


	@Override
	/**
	 * Add an element E to the front of the Stack/DQueue
	 * @param e element to add
	 * Complexity O(1)
	 */
	public void addFront(E e) {
		stackFront.push(e);
	}

	@Override
	/**
	 * Delete an element E from the front of the Stack/DQueue
	 * Complexity O(1)
	 */
	public void delFront() {
		stackFront.pop();
	}

	@Override
	/**
	 * Add an element E to the end of the Stack/DQueue
	 * @param e element to add
	 * Complexity O(1)
	 */
	public void addEnd(E e) {
		stackBack.push(e);
	}

	@Override
	/**
	 * Delete an element E from the end of the Stack/DQueue
	 * Complexity O(1)
	 */
	public void delEnd() {
		stackBack.pop();
	}

	@Override
	/**
	 * Peeks the element E on top of the Stack/DQueue
	 * Complexity O(1)
	 */
	public E front() {
		return stackFront.peek();
	}

	@Override
	/**
	 * Peeks the element E on back of the Stack/DQueue
	 * Complexity O(1)
	 */
	public E end() {
		return stackBack.peek();
	}

	/**
	 * Mule Stack used to enter elements
	 * @param e element to push in the Stack
	 * Complexity O(1)
	 */
	public void initiateStacking(E e) {
		stackMule.push(e);
	}

	/**
	 * Gets STackMule size
	 * @return size of Stack
	 * Complexity O(1)
	 */
	private int getStackSize(){
		return stackMule.size();
	}

	/**
	 * Splits Mule Stack into 2 others equaly if possible
	 * else StackFront will have one plus more element
	 * Complexity O(n)
	 */
	public void splitIn2Stacks(){

		if(getStackSize() % 2 == 0){
			while(stackFront.size() <= getStackSize() % 2){
				stackFront.push(stackMule.pop());
			}
		}
		else{
			for(int i = 0; i < getStackSize()/2; i++){
				stackFront.push(stackMule.pop());
			}
		}
		while(!stackFront.empty()){
			stackBack.push(stackFront.pop());
		}
		while(!stackMule.empty()){
			stackFront.push(stackMule.pop());
		}
		//System.out.println("\nMULE SIZE -> "  + getStackSize() + "\n");
	}

	/**
	 * Puts all elements back in Mule Stack in right order
	 * Complexity O(n)
	 */
	public void putIn2Order(){
		while(!stackBack.empty()){
			stackMule.push(stackBack.peek());
			stackBack.pop();
		}
		while(!stackFront.empty()){
			stackBack.push(stackFront.peek());
			stackFront.pop();
		}
		while(!stackBack.empty()){
			stackMule.push(stackBack.peek());
			stackBack.pop();
		}
	}

	/**
	 * Print Front Stack
	 */
	public void printStackFront() {

		System.out.print( "[ ") ;
		if (stackFront.empty()) {
			System.out.print("STACK EMPTY");
		}
		for(E e : stackFront) {
			System.out.print( e + " ") ;
			//	System.out.print( " ,") ;
		}
		System.out.print( " ]") ;
	}

	/**
	 * Print Back Stack
	 */
	public void printStackBack() {

		System.out.print( "[ ") ;
		if (stackBack.empty()) {
			System.out.print("STACK EMPTY");
		}
		for(E e : stackBack) {
			System.out.print( e + " ") ;
			//	System.out.print( " ,") ;
		}
		System.out.print( " ]") ;
	}

	/**
	 * Print Mule Stack
	 */
	public void printStackMule() {

		System.out.print( "[ ") ;
		if (stackMule.empty()) {
			System.out.print("STACK EMPTY");
		}
		for(E e : stackMule) {
			System.out.print( e + " ") ;
			//	System.out.print( " ,") ;
		}
		System.out.print( " ]") ;
	}

	/**
	 * Print Deque Completed
	 */
	public void printDeque() {

		System.out.print( "[ ") ;
		if (stackMule.empty()) {
			putIn2Order();
			for(E e : stackMule) {
				System.out.print( e + " ") ;
			}
		}

		System.out.print( "]") ;
	}

}
