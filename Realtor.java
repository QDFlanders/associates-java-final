/**
Description: CTP 150 - Final Project
Assignment. A subclass of the abstract Employee class with additional private
            attributes to store a Realtor's commission rate and number of listings. It
            contains a default, secondary, and copy constructor as well as a tertiary
            constructor which can instantiate a Realtor object without requiring the
            realtor's number of listings. It also contains accessors and mutators for
            all private attributes alongside an equals and toString method. This class
            can return the pay, alter the commission rate, and increment the number of
            listings for a Realtor object.
@author Quentin Flanders
@version 12/08/2021
*/

public class Realtor extends Employee
{
   //Additional private attributes.
   private double commissionRate;
   private int numOfListings;
   
   //-----Constructors-----
   
   /**
   A default constructor for Realtor objects when no information is provided.
   */
   public Realtor()
   {
      //Use the secondary constructor from Employee.
      super("(No name provided.)", -1234, -1.0);
      
      //Default commissionRate to 3% and numOfListings to 0.
      this.commissionRate = 0.03;
      this.numOfListings = 0;
   }//End of default constructor.
   
   /**
   A secondary constructor for Realtor objects when all information is provided.
   @String  name           -  The name of the Realtor.
   @int     idNumber       -  The id number of the Realtor.
   @double  baseSalary     -  The base salary of the Realtor.
   @double  commissionRate -  The percentage the Realtor makes when selling a house. 
   @int     numOfListings  -  The number of houses the Realtor has to sell. 
   */
   public Realtor(String name, int idNumber,double baseSalary, double commissionRate, int numOfListings)
   {
      //Pass name, idNumber, and baseSalary to the secondary constructor from Employee.
      super(name,idNumber,baseSalary);
      
      //Confirm that the provided commission rate is valid.
      if(commissionRate < 0)
         this.commissionRate = 0.03;
      else
         this.commissionRate = commissionRate;
         
      //Confirm that the number of listings is valid.
      if(numOfListings < 0)
         this.numOfListings = 0;
      else
         this.numOfListings = numOfListings;
   }//End of secondary constructor.
   
   /**
   A tertiary constructor for Realtor objects when all information except the number of listings a Realtor has is provided.
   @String  name           -  The name of the Realtor.
   @int     idNumber       -  The id number of the Realtor.
   @double  baseSalary     -  The base salary of the Realtor.
   @double  commissionRate -  The percentage the Realtor makes when selling a house.
   */
   public Realtor(String name, int idNumber,double baseSalary, double commissionRate)
   {
      //Pass name, idNumber, and baseSalary to the secondary constructor from Employee.
      super(name,idNumber,baseSalary);
      
      //Confirm that the provided commission rate is valid.
      if(commissionRate < 0)
         this.commissionRate = 0.03;
      else
         this.commissionRate = commissionRate;
         
      //Default numOfListings to 0.
      this.numOfListings = 0;
   }//End of tertiary constructor.
   
   /**
   A copy constructor for Realtor objects to create a deep copy of another Realtor object.
   @Realtor originalRealtor   -  The Realtor object to create a deep copy of.
   */
   public Realtor(Realtor originalRealtor)
   {
      //If the reference is empty, set all attributes to their default values.
      if(originalRealtor != null)
      {
         //Use accessors and mutators from Employee to copy originalRealtor's data.
         this.setName(originalRealtor.getName());
         this.setIdNumber(originalRealtor.getIdNumber());
         this.setBaseSalary(originalRealtor.getBaseSalary());
         //Apply other data as normal.
         this.commissionRate = originalRealtor.commissionRate;
         this.numOfListings = originalRealtor.numOfListings;
      }//End of if.
      else
      {
         //Use mutators from Employee to produce default data.
         this.setName("(No name provided.)");
         this.setIdNumber(-1234);
         this.setBaseSalary(-1.0);
         //Set other data to default as normal.
         this.commissionRate = 0.03;
         this.numOfListings = 0;
      }//End of else.
   }//End of copy constructor.
   
   //-----Setters (Mutators)-----
   
   /**
   Change or set the commission rate of a Realtor object.
   @double  newComRate  -  The new commission rate of a Realtor object.
   */
   public void setCommissionRate(double newComRate)
   {
      //Confirm that the provided commission rate is valid.
      if(newComRate < 0)
         this.commissionRate = 0.03;
      else
         this.commissionRate = newComRate;
   }//End of setCommissionRate.
   
   /**
   Change or set the number of listings for a Realtor object.
   @int  newNumOfListings  -  The new number of listings for a Realtor object.
   */
   public void SetNumOfListings(int newNumOfListings)
   {
      //Confirm that the number of listings is valid.
      if(newNumOfListings < 0)
         this.numOfListings = 0;
      else
         this.numOfListings = newNumOfListings;
   }//End of SetNumOfListings.
   
   //-----Getters (Accessors)-----
   
   /**
   Return the commission rate of a Realtor object.
   @return  commissionRate -  An integer representing the commission rate of a Realtor object.
   */
   public double getCommissionRate()
   {
      return commissionRate;
   }//End of getCommissionRate.
   
   /**
   Return the number of listings for a Realtor object.
   @return  numOfListings  -  An integer representing the number of listings for a Realtor object.
   */
   public int getNumOfListings()
   {
      return numOfListings;
   }//End of getNumOfListings.
   
   //-----Concrete Methods----- 
   
   /**
   Computes and returns the pay for a Realtor object.
   @return  biMonthlyPay   -  A double representing the pay for a Realtor object on a bi-monthly basis.
   */
   public double computePay()
   {
      //Declare and initalize a double to store data for the realtor's pay.
      double biMonthlyPay = 0.0;
      
      //Calculate the bi-weekly pay for a realtor.
      biMonthlyPay = this.getBaseSalary() / 24;
      
      //Return the realtor's pay.
      return biMonthlyPay;
   }//End of computePay.
   
   //-----changeCommissionRate, incrementRealtorListings-----
   
   /**
   Increase or decrease the commission rate of a Realtor by the entered amount.
   @double  commissionChange  -  The amount to change the Realtor's commission rate by.
   */
   public void changeCommissionRate(double commissionChange)
   {
      //Store the current commission rate.
      double commissionRateStart = this.commissionRate;
      
      //Change the commission rate by the passed value.
      this.commissionRate += commissionChange;
      
      //If the change in commissionRate would cause it to become a negative value, set commissionRate back to its original value and inform the user.
      if(this.commissionRate < 0)
      {
         System.out.println("Error. Changing " + this.getName() + "'s commission rate by " + commissionChange + " would cause their commission rate to become " + this.commissionRate + ". Their commission rate has been set back to " + commissionRateStart + ".");
         this.commissionRate = commissionRateStart;
      }//End of if.
   }//End of changeCommissionRate.
   
   /**
   Increase the number of listings a realtor has by one.
   */
   public void incrementRealtorListings()
   {
      this.numOfListings++;
   }//End of incrementRealtorListings.
   
   //-----equals, toString-----
   
   /**
   Checks if two Realtor objects are equal to eachother, then returns a boolean value.
   @Realtor otherRealtor   -  The Realtor object to compare this Realtor object against.
   @returns isSame         -  A boolean value which reads true if both Realtor objects are equal.
   */
   public boolean equals(Realtor otherRealtor)
   {
      //Create a boolean to store the result of a series of checks.
      boolean isSame = false;
      
      //If otherRealtor is empty, the objects are not the same.
      if(otherRealtor == null)
         isSame = false;
      //If otherRealtor is a shallow copy of this object, the objects are the same.
      else if(otherRealtor == this)
         isSame = true;
      //If all attributes are the same, the objects are the same.
      //Check baseSalary and commissionRate to two decimal places.
      else if( this.getName().equalsIgnoreCase(otherRealtor.getName())                          &&
               this.getIdNumber() == otherRealtor.getIdNumber()                                 &&
               (int) (this.getBaseSalary() * 100) == (int) (otherRealtor.getBaseSalary() * 100) &&
               (int) (this.commissionRate * 100) == (int) (otherRealtor.commissionRate * 100)   &&
               this.numOfListings == otherRealtor.numOfListings                                 ) 
         isSame = true;
         
      //Return the boolean value.
      return isSame;
   }//End of equals.
   
   /**
   Returns a String containing the state of the object.
   */
   public String toString()
   {
      //Create a StringBuilder to construct the String.
      StringBuilder employeeString = new StringBuilder();
      
      //Add the name of the realtor.
      employeeString.append("The realtor's name is: " + this.getName() + ".");
      
      //Add the id number of the realtor.
      employeeString.append("\nThe realtor's ID number is: " + this.getIdNumber() + ".");
      
      //Add the base salary of the realtor to two decimal places.
      employeeString.append("\nThe realtor's base salary is: $" + String.format("%.2f",this.getBaseSalary()) + ".");
      
      //Add the bi-weekly salary of the realtor to two decimal places.
      employeeString.append("\nThe realtor's bi-weekly salary is: $" + String.format("%.2f",this.computePay()) + ".");
      
      //Add the commission rate of the realtor as a percentage.
      employeeString.append("\nThe realtor's commission rate is: %" + String.format("%d",(int) (this.commissionRate * 100)) + ".");
      
      //Add the number of listings the realtor is working with.
      employeeString.append("\nThe realtor is working with this many listings: " + this.numOfListings + ".");
      
      //Return the created String.
      return employeeString.toString();
   }//End of toString.
}//End of Realtor subclass.