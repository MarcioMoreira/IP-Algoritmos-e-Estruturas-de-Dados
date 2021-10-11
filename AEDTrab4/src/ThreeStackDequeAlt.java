import java.util.Stack;

/**
 * 
 * @author marciomoreira  41972
 * 
 * Class that uses 3 Stacks. One for front operations, one for back, 
 * another as mule just to keep elements as needed
 *
 * @param <E> Abstract type ADT
 */
public class ThreeStackDequeAlt<E> implements Deque<E>{

	private Stack<E> stackFront;
	private Stack<E> stackBack;
	private Stack<E> stackMule;

	/**
	 * Constructor
	 */
	public ThreeStackDequeAlt(){
		stackFront = new Stack<>();
		stackBack = new Stack<>();
		stackMule = new Stack<>();
	}

	@Override
	/**
	 * Add an element E to the front of the Stack/DQueue
	 * @param e element to add
	 */
	public void addFront(E e) {
		if(stackFront.empty()){
			stackFront.push(e);
		}
		else{
			stackMule.push(e);
			while(!stackFront.empty()){
				stackBack.push(stackFront.pop());
			}
			stackBack.push(stackMule.pop());
		}	
		
	}

	@Override
	/**
	 * Delete an element E from the front of the Stack/DQueue
	 */
	public void delFront() {
		E temp;
		if(!stackFront.empty()){
			while(!stackFront.empty()){
				temp = stackFront.pop();
				stackBack.push(temp);
			}
		}
		stackBack.pop();
	}

	@Override
	/**
	 * Add an element E to the end of the Stack/DQueue
	 * @param e element to add
	 */
	public void addEnd(E e) {
		if(stackBack.empty()){
			stackBack.push(e);
			if(!stackFront.empty()){
				while(!stackFront.empty()){
					stackBack.push(stackFront.pop());
				}
			}
		}
		else{
			stackMule.push(e);
			while(!stackBack.empty()){
				stackFront.push(stackBack.pop());
			}
			stackFront.push(e);
			stackMule.pop();
		}	
		
	}

	@Override
	/**
	 * Delete an element E from the end of the Stack/DQueue
	 */
	public void delEnd() {
		if(!stackFront.empty()){
			stackFront.pop();
		}
		else{
			while(!stackBack.empty()){
				stackFront.push(stackBack.pop());
			}
			stackFront.pop();
		}
	}

	@Override
	/**
	 * Peeks the element E on top of the Stack/DQueue
	 */
	public E front() {

		if(!stackBack.empty()){
			return stackBack.peek();
		}
		else{
			while(!stackFront.empty()){
				stackBack.push(stackFront.pop());
			}
		}
		return stackBack.peek();
	}

	@Override
	/**
	 * Peeks the element E on back of the Stack/DQueue
	 */
	public E end() {
		if(!stackFront.empty()){
			return stackFront.peek();
		}
		else{
			while(!stackBack.empty()){
				stackFront.push(stackBack.pop());
			}
		}
		return stackFront.peek();
	}
	
	public int stackFrontSize(){
		return stackFront.size();
		
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
		if (!stackFront.empty()) {
			for(E e : stackFront) {
				System.out.print( e + " ") ;
				//	System.out.print( " ,") ;
			}
		}
		if (!stackBack.empty()) {

			for(E e : stackBack) {	
				System.out.print( e + " ") ;
				//	System.out.print( " ,") ; 
			}
		}
		System.out.print( "]") ;
	}

}
