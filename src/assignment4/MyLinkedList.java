/*
 Tyler Nally
 10/23/15
 Lab 6
 */
package assignment4;

public class MyLinkedList<E> extends MyAbstractList<E> {

    private Node<E> head, tail;

    /**
     * Create a default list
     */
    public MyLinkedList() {
    }

    /**
     * Create a list from an array of objects
     *
     * @param objects
     */
    public MyLinkedList(E[] objects) {
        super(objects);
    }

    /**
     * Return the head element in the list
     *
     * @return head. The first element in the list.
     * @pre size!=0
     * @post return head.element
     */
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    /**
     * Return the last element in the list
     *
     * @return tail. Last element in list.
     * @pre size!=0
     * @post return tail.element
     */
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    /**
     * Add an element to the beginning of the list
     *
     * @param e
     * @pre tail == null
     * @post tail = head
     *
     */
    public void addFirst(E e) {
        Node<E> newNode = new Node<E>(e); // Create a new node
        newNode.next = head; // link the new node with the head
        head = newNode; // head points to the new node
        size++; // Increase list size

        if (tail == null) // the new node is the only node in list
        {
            tail = head;
        }

    }

    /**
     * Add an element to the end of the list
     *
     * @param e
     * @pre tail!=null
     * @post tail=tail.next
     */
    public void addLast(E e) {
        Node<E> newNode = new Node<E>(e); // Create a new for element e
        if (tail == null) {
            head = tail = newNode; // The new node is the only node in list
        } else {
            tail.next = newNode; // Link the new with the last node
            tail = tail.next; // tail now points to the last node
        }
        size++; // Increase size
    }

    /**
     * Add a new element at the specified index in this list The index of the
     * head element is 0
     *
     * @param index
     * @param e
     * @pre index==0, index>=size, index!=0||size
     * @post addFirst, addLast, (current.next).next = temp
     */
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<E>(e);
            (current.next).next = temp;
            size++;
        }

    }

    /**
     * Remove the head node and return the object that is contained in the
     * removed node.
     *
     * @return element to be removed.
     * @pre size!=0
     * @post return temp.element
     */
    public E removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node<E> temp = head;
            head = head.next;
            size--;
            if (head == null) {
                tail = null;
            }
            return temp.element;
        }
    }

    /**
     * Remove the last node and return the object that is contained in the
     * removed node.
     *
     * @return element to be removed.
     * @pre size==1,size>1
     * @post temp.element
     */
    public E removeLast() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        } else {
            Node<E> current = head;
            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }
            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    /**
     * Remove the element at the specified position in this list. Return the
     * element that was removed from the list.
     *
     * @param index
     * @return element to be removed.
     *
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }
            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }

    /**
     * Override toString() to return elements in the list
     *
     * @return the string.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            } else {
                result.append("]"); // Insert the closing ] in the string
            }
        }
        return result.toString();
    }

    /**
     * Clear the list
     */
    public void clear() {
        head = tail = null;
    }

    /**
     * @param e
     * @return true if list contains element.
     * @pre if (current.element.equals(e))
     * @post return true
     */
    @Override
    public boolean contains(E e) {
  Node<E> current = head;

        for (int i = 0; i < size; i++) {
            if (current.element.equals(e)) {
               

                return true;
            }
            current = current.next; // increment current pointer so that it 
            // points to the next node in the list
        }
        return false;
    }

    

    /**
     * @param index
     * @return null if list empty,null if current is null,element in list
     * @pre for (int i = 0; i < index; i++) @post
     * return current.element
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node<E> current = head;
        for (int i = 0; i < index; i++) {

            current = current.next;
        }

        return current.element;
    }

    /**
     * @param e - element
     * @return index of element in list,-1 if element is not in list.
     * @pre if (current.element.equals(e))
     * @post return result
     */
    @Override
    public int indexOf(E e) {
        int result = -1;
        Node<E> current = head;

        for (int i = 0; i < size; i++) {
            if (current.element.equals(e)) {
                result = i;

                return result;
            }
            current = current.next; // increment current pointer so that it 
            // points to the next node in the list
        }
        return result;
    }

    /**
     * /
     *
     **
     * @param e - element
     * @return index of last occurrence of element in list,-1 if element is not
     * in list.
     * @pre if (current.element.equals(e)
     * @post return result;
     */
    @Override
    public int lastIndexOf(E e) {
        int result = -1;        // assume not found, return -1
        int i = 0;
        Node<E> current = head;
        // search for last matching value, result is desired index
        while (current != null) {
            // a match? keep track of location
            if (current.element.equals(e)) {
                result = i;
            }
            current = current.next;
            i++;
        }
        // return last match
        return result;
    }

    /**
     *
     * @param e , index
     * @return element that replaced another element in list
     * @pre for (int i = 0; i < index; i++) @post
     * current.element = e, return e
     */
    @Override
    public E set(int index, E e) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.element = e;
        return e;
    }

    private static class Node<E> {

        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    public boolean contains(E e, int[] a) {
         
        Node<E> current = head;

        for (int i = 0; i < size; i++) {
            if (current.element.equals(e)) {
               a[0]++;

                return true;
            }
            current = current.next; // increment current pointer so that it 
            // points to the next node in the list
        }
        return false;
    }
         
         
    }
         
        /*Node<E> current = head.next;
        for (int i = 0; i < size; i++) {
            if(current == null){
                return false;
            }
            else if (current.element.equals(e)) {
                a[0]++;
                return true;
            }
            else{
            current = current.next;
        }
        
    }
        return false;*/
    
