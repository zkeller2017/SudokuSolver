import java.util.NoSuchElementException;
import java.util.Iterator;

/*
	The Iterator Class for Vectors
	@author Zachary Keller
	@version Final
**/
public class VectorIterator<E> implements Iterator<E>
{
/*
	The vector that the Iterator will traverse
**/
	private Vector<E> vector;
	
/*
	The index of the Vector that the Iterator will return next
**/
	private int index;
	
/*
	Constructor for a new Iterator
	@param v The vector that the iterator will traverse
**/
	public VectorIterator(Vector<E> v)
	{
		vector = v;
		index = 0;
	}
	
/*
	Determines whether or not the iterator has finished traversing the vector
	@return Whether or not the iterator has finished traversing the Vector
**/
	public boolean hasNext()
	{
		if (index < vector.size())
		{
			return true;
		}
		return false;
	}
	
/*
	Returns the next object in the Vector
	@return The next object in the Vector
**/
	public E next()
	{
		if (! hasNext())
		{
			throw new NoSuchElementException("End of Vector");
		}
		index += 1;
		return vector.get(index - 1);
		
	}
	
/*
	Removes the object in the Vector that the Iterator has just passed over
**/
	public void remove()
	{
		if (index > 0)
		{
			vector.remove(index-1);
		}
		return;
	}
}