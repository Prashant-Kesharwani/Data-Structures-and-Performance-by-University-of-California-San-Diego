package textgen;

import java.util.AbstractList;


public class MyLinkedList<E> extends AbstractList<E> {
	int size;
    LLNode<E> head, tail;

	public MyLinkedList() {
        head = new LLNode <>(null);
        tail = new LLNode <>(null);
        head.setNext(tail);
        tail.setPrev(head);
    }


	public boolean add(E element)
	{
        if (element == null)
            throw new NullPointerException("Element cannot be null!");

        new LLNode<>(element, tail);
        size++;
        return true;
    }

	public E get(int index)
	{
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index is out of bounds");

        return getNode(index).data;
    }

    public LLNode<E> getNode(int index){
        LLNode<E> curr = head;
        for(int i=-1; i<index; i++){
            //System.out.println(curr.data + " <- currdata ||" +  i + " <- number ||" + curr.next.data + " <- currNextdata");
            curr = curr.next;
        }
        return curr;
    }

	public void add(int index, E element ) {
        if (element == null)
            throw new NullPointerException("element cannot be passed as null");
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("index is out of bounds");

        LLNode<E> prevNode = getNode(index);
        if(prevNode!=null){ size++; }

        new LLNode<>(element, prevNode);
    }

	public int size() { return size; }

	public E remove(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index is out of bounds");
        }
        LLNode <E> delVal = getNode(index);

        delVal.prev.setNext(delVal.next);
        delVal.next.setPrev(delVal.prev);
        delVal.setNext(null);
        delVal.setPrev(null);

        size--;
        return delVal.data;

    }


	public E set(int index, E element) 
	{
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index is out of bounds");

        if (element == null)
            throw new NullPointerException("Element cannot be null!");

        LLNode<E> oldVal = getNode(index);
        oldVal.prev.setNext(new LLNode <E>(element));
        oldVal.next.setPrev(new LLNode <E>(element));
        return oldVal.data;
	}   
}

class LLNode<E>
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e)
	{
		this.data = e;
		//this.prev = null;
		//this.next = null;
	}

	public LLNode(E e, LLNode<E> next){
	    this(e);
        this.setPrev(next.prev);
        this.setNext(next);
        next.prev.setNext(this);
        next.setPrev(this);

    }

    public void setNext(LLNode <E> next) {
        this.next = next;
    }

    public void setPrev(LLNode <E> prev) {
        this.prev = prev;
    }
}
