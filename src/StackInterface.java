
public interface StackInterface<T>
{
	void pop() throws StackUnderflowException; 	//remove top element from stack
												//throws exception if stack empty
	
	T top() throws StackUnderflowException; 	//return top element from stack
												//throws exception if stack empty
	
	boolean isEmpty();		// returns true if stack is empty
	
	void push(T element);	//puts element at top of stack 
}
