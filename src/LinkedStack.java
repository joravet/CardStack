
public class LinkedStack<T> implements StackInterface<T>
{
	protected LLNode<T> top;	//reference to top of stack
	
	public LinkedStack()
	{							
		top = null;				//constructor making the stack empty
	}
	
	public void push(T element)
	{
		LLNode<T> newNode = new LLNode<T>(element); 	//Create new node
		newNode.setLink(top);				//Make new node point to where top used to be
		top = newNode;	 					//Make top point to new node
	}
	
	public void pop() throws StackUnderflowException
	{
		if (isEmpty())						//Throw underflow exception if stack is empty
			throw new StackUnderflowException("Attempt to pop empty stack");
		else								//Make top point to what first node currently
			top = top.getLink();			//points to
	}
	
	public T top() throws StackUnderflowException
	{
		if (isEmpty())						//Throw underflow exception if stack is empty
			throw new StackUnderflowException("Attempted top on empty stack");
		else								//Return object from top of stack
			return top.getInfo();
	}
	
	public boolean isEmpty()
	{
		return (top == null);				//true is stack is empty; false if not
	}
}
