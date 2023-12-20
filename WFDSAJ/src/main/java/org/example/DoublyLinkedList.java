package org.example;


//linked list: a sequential list of nodes that hold data which point to other nodes also containing data
//Where are linked lists used?
//  - used in many list, queue and stack implementations
//  - great for creating circular lists
//  - can easily model real world objects such as trains
//  - used in separate chaining, which is present in certain hash table implementations to deal with hashing collisions
//  - often used in the implementation of adjacency lists for graphs
//terminology
//head: the first node in a LL
//tail: the last node in a LL
//pointer: reference to another node.
//node: an object containing data and pointer(s)

//Singly linked lists only hold a reference to the next node.  In the implementation you always maintain a reference to the head and a reference to the tail node for quick additions and removals.
//  + uses less memory
//  + simpler implementation
//  - cannot easily access previous elements
//In a doubly linked list each node holds a reference to the next and previous node.  In the implementation you always maintain a reference to the head and the tail of the doubly linked list to do quick additions and removals from both ends of your list.
//  + can be traversed backwards
//  - takes 2x memory

//complexity
//         singly linked | doubly linked
//search:           O(n) | O(n)
//insert at head:   O(1) | O(1)
//insert at tail:   O(1) | O(1)
//remove at head:   O(1) | O(1)
//remove at tail:   O(n) | O(1)
//remove in middle: O(n) | O(n)

public class DoublyLinkedList <T> implements Iterable <T> {

    private int size = 0;
    private Node <T> head = null;
    private Node <T> tail = null;

    private class Node <T> {
        T data;
        Node <T> prev, next;
        public Node(T data, Node <T> prev, Node <T> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
        @Override public String toString() {
            return data.toString();
        }
    }

    //empty this LL, O(n)
    public void clear() {
        Node <T> trav = head;
        while(trav != null) {
            Node <T> next = trav.next;
            trav.prev = trav.next = null;
            trav.data = null;
            trav = next;
        }
        head = tail = trav = null;
        size = 0;
    }

    //return the size of this LL
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size() == 0;
    }

    //add an element to the tail of the LL, O(1)
    public void add(T elem) {
        addLast(elem);
    }

    //add an element to the beginning of the LL, O(1)
    public void addFirst(T elem){
        if (isEmpty()){
            head = tail = new Node <T> ( elem, null, null );
        } else {
            head.prev = new Node <T> ( elem, null, head );
            head = head.prev;
        }

        size++;
    }

    //add an element to the tail of the LL, O(1)
    public void addLast(T elem) {
        if (isEmpty()) {
            head = tail = new Node <T> ( elem, null, null );
        } else {
            tail.next = new Node <T> ( elem, tail, null);
            tail = tail.next;
        }
        size++;
    }

    //Check the value of the first node if it exists, O(1)
    public T peekFirst() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return head.data;
    }
    public T peekLast() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return tail.data;
    }

    // remove the first value at the head of the LL, O(1)
    public T removeFirst() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        T data = head.data;
        head = head.next;
        --size;

        if (isEmpty()) tail = null;
        else head.prev = null;
        return data;
    }
    public T removeLast() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        T data = tail.data;
        tail = tail.prev;
        --size;

        if (isEmpty()) head = null;
        else tail.next = null;
        return data;
    }

    // remove an arbitrary node from the LL, O(1)
    private T remove(Node <T> node) {
        if (node.prev == null) return removeFirst();
        if (node.next == null) return removeLast();

        //makes the pointers of adjacent nodes skip over 'node'
        node.next.prev = node.prev;
        node.prev.next = node.next;

        T data = node.data;
        node.data = null;
        node = node.prev = node.next = null;

        --size;

        return data;
    }

    // remove a node at a particular index, O(n)
    public T removeAt(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException();

        int i;
        Node <T> trav;

        //search from the front of the list
        if (index < size/2) {
            for (i = 0, trav = head; i != index; i++)
                trav = trav.next;
        } else
            for (i = size-1, trav = tail; i != index; i--)
                trav = trav.prev;
        return remove(trav);
    }

    // remove a particular value in the LL, O(n)
    public boolean remove(Object obj){
        Node <T> trav = head;
        //support searching for null
        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next){
                if (trav.data == null) {
                    remove(trav);
                    return true;
                }
            }
        } else {//search for non null object
            for (trav = head; trav != null; trav = trav.next) {
                if (obj.equals(trav.data)) {
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    //find the index of a particular value in the LL, O(n)
    public int indexOf(Object obj){
        int index = 0;
        Node <T> trav = head;
        //support searching for null
        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next, index++){
                if (trav.data == null) {
                    return index;
                }
            }
        } else {//search for non null object
            for (trav = head; trav != null; trav = trav.next, index++) {
                if (obj.equals(trav.data)) {
                    return index;
                }
            }
        }
        return -1;
    }

    public boolean contains(Object obj){
        return indexOf(obj) != -1;
    }

    @Override public java.util.Iterator <T> iterator () {
        return new java.util.Iterator <T> () {
            private Node <T> trav = head;
            @Override public boolean hasNext() {
                return trav != null;
            }
            @Override public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }
        };
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node <T> trav = head;
        while(trav != null) {
            sb.append(trav.data + ", ");
            trav = trav.next;
        }
        sb.append(" ]");
        return sb.toString();
    }
}
