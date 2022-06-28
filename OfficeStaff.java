/**
Description: CTP 150 - Final Project
Assignment. A subclass of the abstract Employee class. It contains a default, secondary,
            and copy constructor as well as an equals and toString method. This class
            can calculate and return the pay for an OfficeStaff object.
@author Quentin Flanders
@version 12/07/2021
*/

public class OfficeStaff extends Employee
{
   //-----Constructors-----
   
   /**
   A default constructor for OfficeStaff objects when no information is provided.
   */
   public OfficeStaff()
   {
      //Use the secondary constructor from Employee.
      super("(No name provided.)", -1234, -1.0);
   }//End of default constructor.
   
   /**
   A secondary constructor for OfficeStaff objects when all information is provided.
   @String  name        -  The name of the OfficeStaff.
   @int     idNumber    -  The id number of the OfficeStaff.
   @double  baseSalary  -  The base salary of the OfficeStaff.
   */
   public OfficeStaff(String name, int idNumber,double baseSalary)
   {
      //Use the secondary constructor from Employee.
      super(name,idNumber,baseSalary);
   }//End of secondary constructor.
   
   /**
   A copy constructor for OfficeStaff objects to create a deep copy of another OfficeStaff object.
   @OfficeStaff   originalOfficeStaff  -  The OfficeStaff object to create a deep copy of.
   */
   public OfficeStaff(OfficeStaff originalOfficeStaff)
   {
      //Parse originalOfficeStaff to an Employee object, then use the copy constructor from Employee.
      super((Employee) originalOfficeStaff);
   }//End of OfficeStaff.
   
   //-----Concrete Methods----- 
   
   /**
   Computes and returns the pay for an OfficeStaff object.
   @return  biMonthlyPay   -  A double representing the pay for an OfficeStaff object on a bi-monthly basis.
   */
   public double computePay()
   {
      //Declare and initalize a double to store data for the office staff's pay.
      double biMonthlyPay = 0.0;
      
      //Calculate the bi-weekly pay for a realtor.
      biMonthlyPay = this.getBaseSalary() * 80;
      
      //Return the office staff's pay.
      return biMonthlyPay;
   }//End of computePay.
   
   //-----equals, toString-----
   
   /**
   Checks if two OfficeStaff objects are equal to eachother, then returns a boolean value.
   @OfficeStaff   otherOfficeStaff  -  The OfficeStaff object to compare this OfficeStaff object against.
   @returns    isSame               -  A boolean value which reads true if both OfficeStaff objects are equal.
   */
   public boolean equals(OfficeStaff otherOfficeStaff)
   {
      //Create a boolean to store the result of a series of checks.
      boolean isSame = false;
      
      //If otherOfficeStaff is empty, the objects are not the same.
      if(otherOfficeStaff == null)
         isSame = false;
      //If otherOfficeStaff is a shallow copy of this object, the objects are the same.
      else if(otherOfficeStaff == this)
         isSame = true;
      //If all attributes are the same, the objects are the same.
      //Check baseSalary to two decimal places.
      else if( this.getName().equalsIgnoreCase(otherOfficeStaff.getName())                          &&
               this.getIdNumber() == otherOfficeStaff.getIdNumber()                                 &&
               (int) (this.getBaseSalary() * 100) == (int) (otherOfficeStaff.getBaseSalary() * 100) )
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
      StringBuilder officeStaffString = new StringBuilder();
      
      //Add the name of the office staff.
      officeStaffString.append("The office staff's name is: " + this.getName() + ".");
      
      //Add the id number of the office staff.
      officeStaffString.append("\nThe office staff's ID number is: " + this.getIdNumber() + ".");
      
      //Add the base salary of the office staff to two decimal places.
      officeStaffString.append("\nThe office staff's base salary is: $" + String.format("%.2f",this.getBaseSalary()) + ".");
      
      //Return the created String.
      return officeStaffString.toString();
   }//End of toString.
}//End of OfficeStaff subclass.