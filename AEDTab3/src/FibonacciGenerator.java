import java.util.Stack;
/**
 * 
 * @author marciomoreira  41972
 *
 */
public class FibonacciGenerator implements Generator<Long> {

	private  Stack<Long> stackFinal;
	private  Stack<Long> stackPrev;
	
	

	public FibonacciGenerator() {

		this.stackFinal = new Stack<Long>();
		this.stackPrev = new Stack<Long>();
		initializeStack();
	}

	/**
	 * Clears the stacks - required for initilization
	 */
	private void initializeStack() {
		this.stackFinal.clear();
		this.stackPrev.clear();

	}

	/*
	 * Calculates the next number of a fibonacci sequence by giving the previous and
	 * the current elements
	 */
	private long getNextFibonacciElement(long previous, long current) {
		return previous + current;
	}

	/*
	 * returns a string with the representative of the stacks state
	 */
	public String printStacks() {
		StringBuilder sb = new StringBuilder () ;
		sb.append("Final Stack: " ).append(this.stackFinal.toString()).append("\n").
		append("Previous Stack: ").append(this.stackPrev.toString());
		return sb.toString();
	}

	/*
	 * Gets the next value of the fibonacci sequence
	 */
	@Override
	public Long next() {

		long previous;
		long current;

		stackPrev = reverseStack(stackPrev);


		// if the final stack is empty then it should be initialized
		if (stackFinal.empty() || stackPrev.empty()) {
			// dummy values for sum the first element in the sequence
			previous = 0;
			current = 1;
		}
		else {
			previous = stackPrev.peek();
			current =  stackFinal.peek();
		}

		// saves the last state before adding a new value
		stackPrev.push (current);
		// adds the next fibonacci element
		stackFinal.push(getNextFibonacciElement(previous, current));

		stackPrev = reverseStack(stackPrev);

		return stackFinal.peek();
	}

	/*
	 * Gets the previous element of a fibonacci sequence
	 */
	@Override
	public Long prev() {

		//Stack<Long> stackPrevReverse = reverseStack (stackPrev);
		//long value = stackPrev.peek();
		stackPrev = reverseStack(stackPrev);

		long value = this.stackPrev.pop();
		this.stackFinal.pop();

		stackPrev = reverseStack(stackPrev);
		//normalization
		if (stackPrev.size()==1) {
			stackPrev.pop();
			stackFinal.pop();
		}
		return value;
	}

	/*
	 * Reverses a stack structure
	 */
	private Stack<Long> reverseStack (Stack<Long> stackOriginal) {
		
		Stack<Long> tempStack = (Stack<Long>) stackOriginal.clone();
		Stack<Long> reverse = new Stack<Long>();

		for (int i=0; i<stackOriginal.size(); i++) {
			reverse.push(tempStack.pop());
		}

		return reverse;
	}

	/**
	 * reset the stacks until
	 */
	@Override
	public void reset() {

		int currentStackFinalCardinality = this.stackFinal.size()-1;

		for (int i=0; i<currentStackFinalCardinality; i++)
			prev();
	}


}
