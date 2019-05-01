
package cs146_hw03;

/*

Excercise 5.12
Rehashing requires recomputing the hash function for all items in the hash table.
Since computing the hash function is expensive, suppose objects provide a hash
member function of their own, and each object stores the result in an additional
data member the first time the hash function is computed for it. Show how such a
scheme would apply for the Employee class in Figure 5.8, and explain under what
circumstances the remembered hash value remains valid in each Employee.

*/

public class Employee
{
    private String name; //given
    private double salary; //given  
    private int seniority; //given
    
    private int hash; //We can add the field "hash" of type int
    
   //I've added a constructor for the Employee class, to help demonstration
   public Employee(String name, double salary, int seniority)
   {
       this.name = name;
       this.salary = salary;
       this.seniority = seniority;
       
       //upon construction of this object, we can predetermine the hash
       //this way, the hash is only calculated once, instead of needed to calculate every time we need it.
       //a consequence of this is that it adds percisesly 32 bits of memory per object instantiated.
       this.hash = getHashCode();
   }
   
   //given
   public boolean equals( Object rhs )
   {
        return rhs instanceof Employee && name.equals( ((Employee)rhs).name ); 
   }
    //given
   public int hashCode( )
 { 
     return hash;
 }
 
 /**
  * returns the predetermined hash code of the object, to avoid calculating on the spot.
  * use this method instead of hashCode for less processor use.
  * @return the hash code of the object.
  */
 public int getHashCode()
 {
     return name.hashCode( );
 }

 //If at anypoint we want to modify the fields of an instance of this class,
 //We would need to recalculate the hashcode, since the hash code is calculated
 //from the field "name"
 public void setEmployee(String name, double salary, int seniority)
   {
       this.name = name;
       this.salary = salary;
       this.seniority = seniority;
 
       this.hash = getHashCode();
   }
}
