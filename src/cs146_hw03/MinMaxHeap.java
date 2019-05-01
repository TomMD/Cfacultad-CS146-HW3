package cs146_hw03;

/*
A min-max heap is a data structure that supports both deleteMin and deleteMax in
O(logN) per operation. The structure is identical to a binary heap, but the heaporder
property is that for any node, X, at even depth, the element stored at X is
smaller than the parent but larger than the grandparent (where this makes sense),
and for any node X at odd depth, the element stored at X is larger than the parent
but smaller than the grandparent. See Figure 6.57.
a. How do we find the minimum and maximum elements?
b. Give an algorithm to insert a new node into the min-max heap.
c. Give an algorithm to perform deleteMin and deleteMax.
d. Can you build a min-max heap in linear time?
e. Suppose we would like to support deleteMin, deleteMax, and merge. Propose a
data structure to support all operations in O(logN) time.


 */
/**
 * A min-max heap, based off of the Heap class I've provided. It provides
 * functionality to find both the max and min in O(log n) per operation.
 *
 * @author Christian Facultad
 * @param <E> the type of object
 */
public class MinMaxHeap<E extends Comparable<? super E>> {

    private int size = 0; // The amount of elements that are in the heap
    private E[] a; // The array
    final private int INITIAL_SIZE = 100;

    /**
     * Initializes a heap of capacity 32.
     */
    public MinMaxHeap() {
        a = (E[]) new Comparable[INITIAL_SIZE];
    }

    /**
     * Inserts data into the tree in heap order. Duplicates are allowed.
     *
     * @param data the data in which to insert into the tree.
     */
    public void insert(E data) {
        E e = data; // e is shorthand for the element we want to insert.

        //Case 1: The heap is not big enough
        if (size == a.length - 1) {
            enlargeArray();
        }
        //case 2: The heap is empty
        if (size == 0) {
            a[1] = e;
            size++;
        } //Case 3: The heap is not empty
        else {
            size++;
            int hole = size; //create a hole in the next availble slot.

            if (findLevel(hole) % 2 == 0) //the hole is on a min level
            {
                if (e.compareTo(a[hole / 2]) < 0) {
                    //the element is less than the parent
                    //push the parent down and start checking grand parents.
                    a[hole] = a[hole / 2];
                    hole /= 2;
                    for (a[0] = e; //initial spot for e
                            e.compareTo(a[hole / 4]) < 0; //Run this loop while e is less than it's grandparent.
                            hole /= 4) //reset the index's position to it's parents.
                    {
                        a[hole] = a[hole / 4]; //push the element's data into it's parent's index.
                    }
                    a[hole] = e; // Once e is actually more than it's grandparent, set it at it's ideal place.
                } else {
                    //the element is more than the parent
                    //just start checking the grandparents.
                    for (a[0] = e; //initial spot for e
                            e.compareTo(a[hole / 4]) > 0; //Run this loop while e is greater than it's grandparent.
                            hole /= 4) //reset the index's position to it's parents.
                    {
                        a[hole] = a[hole / 4]; //push the element's data into it's parent's index.
                    }
                    a[hole] = e; // Once e is actually more than it's grandparent, set it at it's ideal place.

                }
            } else //Case 3.2 The hole is on a min level.
            if (e.compareTo(a[hole / 2]) < 0) {
                //the element is less than the parent
                //just start checking the grandparents.

                for (a[0] = e; //initial spot for e
                        e.compareTo(a[hole / 4]) < 0; //Run this loop while e is kess than it's grandparent.
                        hole /= 4) //reset the index's position to it's parents.
                {
                    a[hole] = a[hole / 4]; //push the element's data into it's parent's index
                }
                a[hole] = e; // Once e is actually less than it's grandparent, set it at it's ideal place.
            } else {
                //the element is greater than the parent
                //push the parent down and start checking grandparents.
                a[hole] = a[hole / 2];
                hole /= 2;
                for (a[0] = e; //initial spot for e
                        e.compareTo(a[hole / 4]) > 0; //Run this loop while e is greater than it's grandparent.
                        hole /= 4) //reset the index's position to it's parents.
                {
                    a[hole] = a[hole / 4]; //push the element's data into it's parent's index
                }
                a[hole] = e; // Once e is actually less than it's grandparent, set it at it's ideal place.

            }
        }

    }
    /*
       Anaysis of this insert method:
       
       Best Case:
       If Heap is empty, which is almost never: 
       O(1),  We only need to compare size, and assign data to a[1].
       
       Worst Case:
       In the even that the input data is the smallest:
       O(log n), We must percolate the element up the tree
       a total amount of times equal to the height of the tree, which is (log(base 2)(n+1))
     */

    /**
     * Returns the smallest entry in the tree.
     *
     * @return the smallest entry.
     */
    public E findMin() {
        return a[1]; //The first entry in the array is always the minimum, since we sort it every time we edit the tree.
    }

    /**
     * Returns the largest entry in the tree.
     *
     * @return the largest entry.
     */
    public E findMax() {
        //different from findMin, since the largest values are kept in the second row
        //we must compare the two, and return the largest.

        //Case 1: There is less than 3 elements.
        if (size < 3) {
            return a[size];
        }

        //Case 2: There is more than 3  or more elements.
        return a[2].compareTo(a[3]) > 0 ? a[2] : a[3];
    }

    /**
     * Deletes the first entry in the heap. This is like 'Dequeueing' in a
     * queue.
     *
     * @return
     */
    public E deleteMin() {
        E min = findMin();

        //Case 1: There are less than 3 elements in the heap.
        if (size < 3) {
            a[1] = a[2];
            a[2] = null;
            size--;
            return min;
        } /*else if (size == 3) //Case 2: there are 3 elements in the heap.
        {
            size--;
            if(a[2].compareTo(a[3]) < 0)
            {
                a[1] = a[2];
                a[2] = a[3];
                a[3] = null;
                return min;
            }
            else 
            {
                a[1]=a[3];
                a[3]=null;
                return min;
            }
        } */else {
            
            a[1] = a[size]; //place the last value on the top...
            a[size] = null; //... empty out the last element...
            size--; //... subtract one from the size...
            percolateDownMin(1); //... and percolate that value down into the right spot

            return min;

            /*
        Analysis of deleteMin():
        
        All Cases:
        O(log n): We delete the root, and must percolate the hole down the
        height of the tree, which is (log(base 2)(n+1))

             */
        }
    }

    public E deleteMax() {
        E max = findMax();

        //Case 1: There are less than 3 elements in the heap.
        if (size < 3) {
            a[2] = null;
            size--;
            return max;
        } else {
            int index = a[2].compareTo(a[3]) < 0 ? 3 : 2;
            a[index] = a[size]; //place the last value on the top and then...
            a[size] = null;
            size--;
            percolateDownMax(index); //percolate that value down into the right spot
            
            return max;
        }

        /*
        Analysis of deleteMin():
        
        All Cases:
        O(log n): We delete the root, and must percolate the hole down the
        height of the tree, which is (log(base 2)(n+1))

         */
    }

    /**
     * percolates the hole down on ODD HEIGHTS. That is, take the position found
     * at @param hole, and push it down until it's on the bottom odd layer of
     * the tree. The reason we traverse on evens is because the small numbers
     * are found on even heights.
     *
     * @param hole the index of the array to push down.
     */
    private void percolateDownMin(int hole) {
        E e = a[hole]; //this is element in the hole that we shall be pushing down.
         //We need to remove the last element in the list
        int grandchild; //designate a variable to choose a child later.

        E x = a[size]; //x represents the last element in the array.
        for (;
                hole * 4 <= size; //Run this loop until there are no grandchildren for the hole
                hole = grandchild) { //'traverse down'
            grandchild = 4 * hole; //find the index of the left-most child, first.
            if (grandchild != size) //if the child isn't the last element on the heap
            {
                //Find out which grandchild is the smallest one.
                int minGrandchild = grandchild;
                for (int i = 1; i <= 3; i++) {
                    if (a[grandchild + i] != null && a[grandchild + i].compareTo(a[minGrandchild]) < 0) {
                        minGrandchild = grandchild + i;
                    }
                }
                grandchild = minGrandchild;
            }

            if (a[grandchild].compareTo(e) < 0) //if the grandchild is less than the element we're pushing down...
            {
                a[hole] = a[grandchild]; //push the child up
            } else {
                break;
            }
        }
        a[hole] = x; // The hole where the loop stopped at, put e in.
    }

    private void percolateDownMax(int hole) {
        E e = a[hole]; //this is element in the hole that we shall be pushing down.
        int grandchild; //designate a variable to choose a child later.

        E x = a[size]; //x represents the last element in the array.
        for (;
                hole * 4 <= size; //Run this loop until there are no grandchildren for the hole
                hole = grandchild) { //'traverse down'
            grandchild = 4 * hole; //find the index of the left-most child, first.
            if (grandchild != size) //if the child isn't the last element on the heap
            {
                //Find out which grandchild is the greatest one.
                int maxGrandchild = grandchild;
                for (int i = 1; i <= 3; i++) {
                    if (a[grandchild + i] != null && a[grandchild + i].compareTo(a[maxGrandchild]) > 0) {
                        maxGrandchild = grandchild + i;
                    }
                }
                grandchild = maxGrandchild;
            }

            if (a[grandchild].compareTo(e) > 0) //if the grandchild is greater than the element we're pushing down...
            {
                a[hole] = a[grandchild]; //push the grandchild up
            } else {
                break;
            }
        }
        a[hole] = x; // The hole where the loop stopped at, put e in.
    }

    /**
     * Doubles the array size.
     */
    private void enlargeArray() {
        E[] newArray = (E[]) new Comparable[(size * 2) + 1]; // Double the size
        for (int i = 0; i <= size; i++) {
            newArray[i] = a[i]; //Copy the data to the new array.
        }
        a = newArray; //Designate the new array as the instance array.
        
    }

    /**
     * finds the level the given index is located in.
     *
     * @param x the index
     * @return the level.
     */
    private int findLevel(int x) {
        int init = x;
        x--;
        int i = 0;
        while (x > 0) {
             i++;
            x -= Math.pow(2, i);       
        }
        i++;
        return i;
    }

}
