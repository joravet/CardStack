
public class LLNode<T>
{
	protected T info;			//data node stores
	protected LLNode<T> link;		//link to NEXT node
	
	public LLNode(T info)		//Constructor for node class
	{
		this.info = info;		//info = input
		link = null;			//link is null
	}
	
	public void setInfo(T info)	//set info based on parameter input
	{
		this.info = info;
	}
	
	public T getInfo()			//returns info when called
	{
		return info;
	}
	
	public void setLink(LLNode<T> link) //set link based on parameter input
	{
		this.link = link;
	}
	
	public LLNode<T> getLink() 	//returns link when called
	{
		return link;
	}
}
