import java.util.Iterator;

/**
	The Vector class acts like an ArrayList in that it can store many objects of type E
	in an array, and has different methods to access and modify the list of objects
	@author Zachary Keller
	@version 1
*/


public class Vector<E> implements Iterable<E>
{

/**
	The array that holds the Vector
*/
	private Object[] data;
	
/**
	The number of active items in the Vector - NOT data
*/
	private int size;

/**
	The length of data, aka the amount of space the Vector has available
*/
	private int capacity;
	
/**
	Default Constructor- initializes the capacity to 10
*/
	public Vector()
	{
		this(10);
	}

/** 
	Constructor- initializes the capacity to the a given amount
	@param initCapacity The desired initial capacity
*/	
	public Vector(int initCapacity)
	{
		if (initCapacity <1)
			throw new IllegalArgumentException("You must have a positive capacity");
		data = new Object[initCapacity];
		size = 0;
		capacity = initCapacity;
	}
	
/** 
	Constructor- makes the new Vector a copy of an existing one
	@param other The Vector to be copied
*/	
	public Vector(Vector<E> other)
	{
		if (other == null)
			throw new IllegalArgumentException("Please call with either a capacity or another Vector");
		data = new Object[other.capacity()];
		capacity = data.length;
		size = 0;
		for (int i = 0; i< other.size; i++)
		{
			add(other.get(i));
		}
	}
	
	
/**  
	Gives the size of the Vector
	@return The size of the Vector
*/	
	public int size()
	{
		return size;
	}
	
/**  
	Gives the capacity of the Vector - aka the length of data
	@return The capacity of the Vector
*/		
	public int capacity()
	{
		return capacity;
	}

/**  
	Adds a given item to a specific place on the Vector
	@param index The place in the Vector for the item to go
	@param item The object to be placed in the Vector
*/	
	public void add (int index, E item)
	{
		
		if (index > size || index < 0 )
		{
			throw new IndexOutOfBoundsException("Index " + index + " is not within the size: " + size + " of Vector");
		}
		if (size == capacity)
		{
			increaseCapacity();
		}
		for (int i = size; i > index; i--)
		{
			data[i] = data[i-1];
			
		}
		data[index] = item;
		size +=1;
		
	}
	
/**  
	Adds a given item to the end of the Vector
	@param item The object to be placed in the Vector
*/	
	public void add(E item)
	{
		add(size, item);
	}
	
/**  
	Doubles the capacity of the Vector - aka doubles the size of the array Data
*/	
	@SuppressWarnings("unchecked")
	private void increaseCapacity()
	{
		Object[] holder = data;
		capacity = capacity * 2;
		data = new Object[capacity];
		for (int i = 0; i < holder.length; i++)
		{
			set(i, (E) holder[i]);
		}
	}



/**  
	Returns the item of type a E at a given index in the array
	@param index The place in the array data of the desired item
	@return The item at the given index
*/
	@SuppressWarnings("unchecked")
	public E get(int index)
	{
		if (index < size && index > -1)
			return (E) data[index];
		throw new IndexOutOfBoundsException("Index " + index + " is not within the size: " + size + " of Vector");
	}
	
/**  
	Removes an item at a given index, then returns that item
	All other items are then shifted an index to compensate for the removal
	@param index The place in the array data of the item to be removed
	@return The item at the given index that has been removed
*/
	public E remove(int index)
	{

		E holder = get(index);
		for (int i = index; i < size - 1; i++)
			set(i, get(i+1));
		size -=1;
		return holder;
	
	}


/**  
	Removes the first occurrence of a specified item
	All other items are then shifted an index to compensate for the removal
	@param obj The item to be removed
	@return Whether or not the item has been removed
*/
	public boolean remove(E obj)
	{
		if (contains(obj) == false)
		{
			return false;
		}
		remove(indexOf(obj));
		return true;
	}

/**  
	Inserts an item at a given location regardless of what is already there
	@param obj the item to be placed
	@param index The spot for the item
	@return what was previously in that spot
*/
	public E set(int index, E obj)
	{

		E holder = get(index);
		data[index] = obj;
		return holder;
		/*  I could have used the add and remove methods here, 
			But I thought that this was just easier
		*/
	}


/**  
	Removes all items from the Vector, sets size to 0
*/
	public void clear()
	{
		while (size > 0 )
		{
			remove(0);
		}
	}

/**  
	Identifies whether the Vector is empty; That is to say its size is 0
	@return Whether or not it is empty
*/
	public boolean isEmpty()
	{
		if (size == 0)
			return true;
		else
			return false;
	}

/**  
	Determines whether or not at least one instance of a specific object exists within the Vector
	@param obj The item that is being checked against the Vector
	@return Whether or not the object exists in the Vector
*/
	public boolean contains(E obj)
	{

			if (indexOf(obj) == -1)
				return false;
			else
				return true;
	}

/**  
	Gives the index of the first occurrence of a given item
	@param obj The item that is being checked for
	@return The index of the item, -1 if it does not exist in the Vector
*/
	public int indexOf(E obj)
	{
		for (int i = 0; i < size; i++)
		{
			if (get(i) != null)
			{
				if (get(i).equals(obj))
				{
					return i;
				}
			}
			//In case you want to remove null from a Vector
			else if (get(i) == null && obj == null)
			{
				return i;
			}
			
		}
		return -1;
	}

/**
	Creates and returns an iterator
	@return The Iterator
*/
	public Iterator<E> iterator()
	{
		return new VectorIterator<E>(this);
	}

	
	
	
/** 
	Returns a string representation of the Vector
	@return The string representation of the Vector
*/
	public String toString()
	{
		String s = "";
		for (int i = 0; i < size; i++)
		{
			s += "|" + data[i];
		}
		s += "|";
		return s;
	}
}

