package org.example;

public class Main {
    //data structure: a way of organizing data so that it can be used effectively
    //  - essential ingredients in creating fast and powerful algorithms
    //  - help to manage and organize data
    //  - make code cleaner and easier to understand

    //abstract data type (ADT): an abstraction of a data structure which provides only the interface to which a data structure must adhere to
    //  - the interface does not give any specific details about how something should be implemented or in what programming language

    //      ADT | Implementation (data structure)
    //  --------|--------------------------------
    //  List    | dynamic array, linked list
    //  Queue   | linked list based queue, array based queue, stack based queue
    //  Map     | tree map, hash map / hash table
    //  Vehicle | golf cart, bicycle, smart car

    //complexity analysis: how much time does this algorithm need to finish?  how much space does this algorithm need for its computation?
    //Big-O notation gives an upper bound of the complexity in the worst case, helping to quantify performance as the input size becomes arbitrarily large
    //n = the size of the input
    //complexities from smallest to largest
    //  constant time     | O(1)
    //  logarithmic time  | O(logn)
    //  linear time       | O(n)
    //  linearithmic time | O(nlogn)
    //  quadric time      | O(n^2)
    //  cubic time        | O(n^3)
    //  exponential time  | O(b^n), b > 1
    //  factorial time    | O(n!)
    //Big-O Properties
    //O(n+c) = O(n)
    //O(cn) = O(n), c > 0
    //let f be a function that describes the running time of a particular algorithm for an input of size n:
    //f(n) = 7log(n)^3 + 15n^2 + 2n^3 + 8
    // O(f(n)) = O(n^3)

    //constant time algorithms do not depend on n (input size)

    //binary search: O(log_2(n))
    //finding all subsets of a set: O(2^n)
    //finding all permutations of a string: O(n!)
    //sorting using mergsort: O(nlogn)
    //iterating over all the cells in a matrix of size n by m: O(nm)


    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}