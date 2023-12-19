package org.example;


//static array: a fixed length container containing n elements indexable form the range [0, n-1]
//  - indexable: each slot/index in the array can be referenced with a number
//  - static arrays are a contiguous chunk of memory

//When and where is a static Array used?
//1) Storing and accessing sequential data
//2) Temporarily storing objects
//3) Used by IO routines as buffers
//4) Lookup tables and inverse lookup tables
//5) Can be used to return multiple values from a function
//6) Used in dynamic programming to cache answers to problems

//Complexity
//   Static Array | Dynamic Array
// Access:   O(1) | O(1)
// Search:   O(n) | O(n)
// Insertion:  NA | O(n)
// Appending:  NA | O(1)
// Deletion:   NA | O(n)

//dynamic array: an indexable container that can grow and shrink in size
//a dynamic array can be implemented using a static array
//1) Create a static array with an initial capacity.
//2) Add elements to the underlying static array, keeping track of the number of elements.
//3) If adding another element will exceed the capacity, then create a new static array with twice the capacity and copy the original elements into it.

//similar to ArrayList
@SuppressWarnings("unchecked")
public class Array <T> implements Iterable<T> {

    private T [] arr; //internal static array
    private int len = 0; //length user thinks array is
    private int capacity = 0; //actual array size

    public Array() { this(16); }

    public Array(int capacity){
        if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size() { return len; }
    public boolean isEmpty() { return size() == 0; }
    public T get(int index) { return arr[index]; }
    public void set(int index, T elem) { arr[index] = elem; }
    public void clear() {
        for(int i = 0; i < capacity; i++)
            arr[i] = null;
        len = 0;
    }
    public void add(T elem) {
        if(len+1 >= capacity) {
            if (capacity == 0) capacity = 1;
            else capacity *= 2;
            T[] new_arr = (T[]) new Object[capacity];
            for (int i = 0; i < len; i++)
                new_arr[i] = arr[i];
            arr = new_arr;
        }
        arr[len++] = elem;
    }

    public T removeAt(int rm_index) {
        if (rm_index >= len || rm_index < 0) throw new IndexOutOfBoundsException();
        T data = arr[rm_index];
        T[] new_arr = (T[]) new Object[len - 1];
        for (int i = 0, j = 0; i < len; i++, j++)
            if (i == rm_index) j--; //skip over rm_index by fixing j temporarily
            else new_arr[j] = arr[i];
        arr = new_arr;
        capacity = --len;
        return data;
    }

    public boolean remove(Object obj){
        for(int i = 0; i < len; i++) {
            if (arr[i].equals(obj)){
                removeAt(i); return true;}}
        return false;
    }

    public int indexOf(Object obj){
        for(int i = 0; i < len; i++)
            if (arr[i].equals(obj))
                return i;
        return -1;
    }

    public boolean contains(Object obj){
        return indexOf(obj) != -1;
    }

    //Iterator is still fast but not as fast as iterative for loop
    @Override public java.util.Iterator <T> iterator() {
        return new java.util.Iterator <T> () {
            int index = 0;
            public boolean hasNext() { return index < len; }
            public T next() { return arr[index++]; }
        };
    }

    @Override public String toString() {
        if(len == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for (int i = 0; i < len-1; i++ )
                sb.append(arr[i] + ", ");
            return sb.append(arr[len-1] + "]").toString();
        }
    }

}
