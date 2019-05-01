
package cs146_hw03;

/**
 * A generic Heap built from ground up to demonstrate solution to problem 6.13:
 * Each deleteMin operation uses 2 logN comparisons in the worst case.
 * 
 * a. Propose a scheme so that the deleteMin operation uses only logN + log logN +
 * O(1) comparisons between elements. This need not imply less data movement.
 *
 * b. Extend your scheme in part (a) so that only logN + log log logN + O(1)
 * comparisons are performed.
 * 
 * c. How far can you take this idea?
 * 
 * d. Do the savings in comparisons compensate for the increased complexity of your
 * algorithm?
 * 
 * 
 * @author Christian Facultad
 * @param <E> the type of object
 */
public class Heap<E extends Comparable<? super E>> {
    private int size = 0; // The amount of elements that are in the heap
    private E[] a; // The array
    final private int INITIAL_SIZE = 32;
    
    /**
     * Initializes a heap of capacity 32.
     */
    public Heap()
    {
        a = (E[]) new Comparable[INITIAL_SIZE];
    }
    /**
     * Inserts data into the tree in heap order. Duplicates are allowed.
     * @param data the data in which to insert into the tree.
     */
    public void insert(E data)
    {
        E e = data; // e is shorthand for the element we want to insert.
        
        
        //Case 1: The heap is not big enough
        if (size == a.length-1)
        {
            enlargeArray();
        }
        
        /*Case 2: The heap is empty
        if (size == 0)
        {
            size++;
            a[1] = data; //set the 1 or 2 as the data.
        }
        */
        //Case 3: The heap is not empty
        else
        {
            size++;
        int hole = size; //create a hole in the next availble slot.
        for (a[0] = e; //initial spot for e
               e.compareTo( a[ hole/2 ] ) < 0;   //Run this loop while e is less than it's parent.
               hole /= 2 ) //reset index's position to the parent..
           a[ hole ] = a[ hole/2 ]; //push the hole's data into the parent's position.
       a[ hole ] = e; // Once we found area where e is less than it's parent's data, place it on the hole.

        }

        
       /*
       Anaysis of insert():
       
       Best Case:
       If Heap is empty, which is almost never: 
       O(1),  We only need to compare size, and assign data to a[1].
       
       Worst Case:
       In the even that the input data is the smallest:
       O(log n), We must percolate the element up the tree
       a total amount of times equal to the height of the tree, which is (log(base 2)(n+1))
       */
       
    }
    /**
     * Returns the smallest entry in the tree.
     * @return the smallest entry.
     */
    public E findMin()
    {
        return a[1]; //The first entry in the array is always the minimum, since we sort it every time we edit the tree.
    }
    /**
     * Deletes the first entry in the heap. This is like 'Dequeueing' in a queue.
     * @return 
     */
    public E deleteMin()
    {
        
        E min = findMin();
        size--;
        a[1] = a[size]; //place the last value on the top and then...

        percolateDown(1); //percolate that value down into the right spot
        
        return min;
        
        /*
        Analysis of deleteMin():
        
        All Cases:
        O(log n): We delete the root, and must percolate the hole down the
        height of the tree, which is (log(base 2)(n+1))

        */
    }
    /**
     * percolates the hole down. That is, take the position found at @param hole, 
     * and push it down until it's on the bottom layer of the tree.
     * @param hole the index of the array to push down.
     */
    private void percolateDown(int hole)
    {
        E e = a[hole]; //this is element in the hole that we shall be pushing down.
        int child; //designate a variable to choose a child later.
       
       
       E x = a[size]; //x represents the last element in the array.
       for ( ; 
               hole * 2 <= size;   //Run this loop until there are no children for the hole
               hole = child ) { //'traverse down'
           child = 2*hole; //find the index of the left child, first.
           if (child != size && //if the child isn't the last element on the heap
                   a[child].compareTo(a[child+1]) > 0)  //if the left is bigger than right
               child++; //choose the right child instead.
           if(a[child].compareTo(e) < 0) //if the child is less than the element we're pushing down...
               a[hole] = a[child]; //push the child up
           else
               break;
       }
       a[ hole ] = x; // The hole where the loop stopped at, put e in.
    }  
    /**
     * Doubles the array size.
     */
    private void enlargeArray()
    {
        E[] newArray = (E[]) new Comparable[(a.length*2)]; // Double the size
        for(int i = 1; i <= size; i++)
        {
            newArray[i] = a[i]; //Copy the data to the new array.
        }
        a = newArray; //Designate the new array as the instance array.
    }
    
}
