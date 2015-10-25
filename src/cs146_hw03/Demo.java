
package cs146_hw03;

import java.util.Hashtable;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class demonstrates the changes I've made to the Employee class.
 * @author Christian
 */
public class Demo {
    
    public static void main(String[] args)
    {
        /*
        System.out.println("Starting demo.");
        
        //instantiate a few example employees and add them to a hashTtable.
        Hashtable map1 = new Hashtable();
        
        Employee a = new Employee("Ash",60000, 5);
        Employee b = new Employee("Briana",100000, 20);
        Employee c = new Employee("Chun",30000, 1);
        Employee d = new Employee("Daria",10000, 4);
        Employee e = new Employee("Espinoza",75000, 8);
        Employee f = new Employee("Farah",50000, 4);
        Employee g = new Employee("Giuseppe",160000, 15);
        
        
        map1.put(a, a);
        map1.put(b, b);
        map1.put(c, c);
        map1.put(d, d);
        map1.put(e, e);
        map1.put(f, f);
        map1.put(g, g);
        
        
        //Time how long it takes to retrieve many random objects when the objects have a precalculated hashcode
        long time = System.currentTimeMillis();
        
        for(int i = 0; i < 5000000; i++)
        {
            Object test = map1.get(a);
            test = map1.get(b);
            test = map1.get(c);
            test = map1.get(d);
            test = map1.get(e);
            test = map1.get(f);
            test = map1.get(g);            
        }
        // this averages about ~240 miliseconds on my computer.
        System.out.println("Time in miliseconds to find objects in map using a precalculated hashcode: " + (System.currentTimeMillis() - time));
        
        
        //Time how long it takes to retrieve many random objects when you have to calcualte the hashcode each time.
        //(simulated)
        time = System.currentTimeMillis();
        
        for(int i = 0; i < 5000000; i++)
        {
            //Calculating each hashcode is simulated by calling the objects getHashCode function
            //This is to avoid coding an entirelly new file
            Object test = map1.get(a);
            a.getHashCode();          
            test = map1.get(b);
            b.getHashCode();
            test = map1.get(c);
            c.getHashCode();
            test = map1.get(d);
            d.getHashCode();
            test = map1.get(e);
            e.getHashCode();
            test = map1.get(f);
            f.getHashCode();
            test = map1.get(g);
            g.getHashCode();
        }
        //This averages about ~900 miliseconds on my computer
        System.out.println("Time in miliseconds to find objects in map using a calculating hashcode each time: " + (System.currentTimeMillis() - time));
        
        
        //This section is used to test recalculation of hashcodes.
        //The given method of calculating an Employee instance's hashcode is to find the hashcode of the name field.
        //The hashcode should change if and only if the name field changes.
        System.out.println("Testing recalculation of hashcodes:");
        System.out.println("Hashcode of Employee with fields { name = 'Ash', salary = 60000, senority = 5: " + a.hashCode());
        a.setEmployee("Ashley", 60000, 5);
        System.out.println("Hashcode of Employee with fields { name = 'Ashley', salary = 60000, senority = 5: " + a.hashCode());
        a.setEmployee("Ashley", 160000, 15);
        System.out.println("Hashcode of Employee with fields { name = 'Ashley', salary = 160000, senority = 15: " + a.hashCode());
        System.out.println("As you can see, the hashcode only changes when the name of the employee changes.");
       */
        
        
        Random r = new Random();
        
        
        /*
        Heap<Integer> h = new Heap();
        for(int i = 0; i < 50; i++)
        {
            int x = r.nextInt(10000);
            h.insert(x);
        }
        P.pp("Testing Heap's deleteMin, should output the numbers in order from least to greatest.");
        for(int i = 0; i < 20; i++)
            P.p(h.deleteMin() + ", ");
                
                */
        
        
        MinMaxHeap<Integer> mmh = new MinMaxHeap();
        ArrayList<Integer>  al = new ArrayList();
        for(int i = 1; i < 1000; i++)
        {
            int x = r.nextInt(100000);
            mmh.insert(x);
            al.add(x);
            
        }

        
        Collections.sort(al);
        P.pp("");
        P.pp("Testing MinMaxHeap's deleteMin, should output the numbers in order from least to greatest.");
        for(int i = 1; i < 10; i++)
            P.pp("MinMaxHeap: "+ mmh.deleteMin() + " ArrayList: " + al.remove(i-1));
        
            
        P.pp("");
        P.pp("Testing MinMaxHeap's deleteMax, should output the numbers in order from greatest to least.");
        for(int i = 1; i < 10; i++)
            P.pp("MinMaxHeap: "+ mmh.deleteMax() + " ArrayList: " + al.remove(al.size()-1));
              
        
    }
}
