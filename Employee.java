/**
Description: CTP 150 - Final Project
Assignment. An abstract class for Employee objects which contains private attributes
            for an employee's name, id number, and base salary. It contains a default,
            secondary, and copy constructor as well as accessors and mutators for all
            private attributes. It also contains an equals and toString method. Subclasses
            will need to define the abstract computePay method.
@author Quentin Flanders
@version 12/07/2021
*/
public abstract class Employee implements Comparable<Employee>
{
   //Private attributes.
   private String name;
   private int idNumber;
   private double baseSalary;
   
   //-----Constructors-----
   
   /**
   A default constructor for Employee objects when no information is provided.
   */
   public Employee()
   {
      //Use the secondary constructor.
      this("(No name provided.)", -1234, -1.0);
   }//End of default constructor.
   
   /**
   A secondary constructor for Employee objects when all information is provided.
   @String  name        -  The name of the Employee.
   @int     idNumber    -  The id number of the Employee.
   @double  baseSalary  -  The base salary of the Employee.
   */
   public Employee(String name, int idNumber,double baseSalary)
   {
      //Confirm that the provided name is valid.
      if(name == null)
         this.name = "(No name provided.)";
      else if(name.isEmpty())
         this.name = "(No name provided.)";
      else
         this.name = name;
         
      //Confirm that the provided id number is valid.
      if(idNumber < 0 || idNumber > 9999)
         this.idNumber = -1234;
      else
         this.idNumber = idNumber;
      
      //Confirm that the provided base salary is valid.
      if(baseSalary < 0)
         this.baseSalary = -1.0;
      else
         this.baseSalary = baseSalary;
   }//End of secondary constructor.
   
   /**
   A copy constructor for Employee objects to create a deep copy of another Employee object.
   @Employee   originalEmployee  -  The Employee object to create a deep copy of.
   */
   public Employee(Employee originalEmployee)
   {
      //If the reference is empty, set all attributes to their default values.
      if(originalEmployee != null)
      {
         this.name = originalEmployee.name;
         this.idNumber = originalEmployee.idNumber;
         this.baseSalary = originalEmployee.baseSalary;
      }//End of if.
      else
      {
         this.name = "(No name provided.)";
         this.idNumber = -1234;
         this.baseSalary = -1.0;
      }//End of else.
   }//End of copy constructor.
   
   //-----Setters (Mutators)-----
   
   /**
   Change or set the name of an Employee object.
   @String  newName  -  The new name of an Employee object.
   */
   public void setName(String newName)
   {
      //Confirm that the provided name is valid.
      if(newName == null)
         this.name = "(No name provided.)";
      else if(newName.isEmpty())
         this.name = "(No name provided.)";
      else
         this.name = newName;
   }//End of setName.
   
   /**
   Change or set the id number of an Employee object.
   @int  newIdNumber -  The new id number of an Employee object.
   */
   public void setIdNumber(int newIdNumber)
   {
      //Confirm that the provided id number is valid.
      if(newIdNumber < 0 || newIdNumber > 9999)
         this.idNumber = -1234;
      else
         this.idNumber = newIdNumber;
   }//End of setIdNumber.
   
   /**
   Change or set the base salary of an Employee object.
   @double  newBaseSalary  -  The new base salary of an Employee object.
   */
   public void setBaseSalary(double newBaseSalary)
   {
      //Confirm that the provided base salary is valid.
      if(newBaseSalary < 0)
         this.baseSalary = -1.0;
      else
         this.baseSalary = newBaseSalary;
   }//End of setBaseSalary.
   
   //-----Getters (Accessors)-----
   
   /**
   Return the name of an Employee object.
   @return  name  -  A String containing the name of this Employee object.
   */
   public String getName()
   {
      return name;
   }//End of getName.
   
   /**
   Return the id number of an Employee object.
   @return  idNumber -  An integer containing the id number of this Employee object.
   */
   public int getIdNumber()
   {
      return idNumber;
   }//End of getIdNumber.
   
   /**
   Return the base salary of an Employee object.
   @return  baseSalary  -  A double containing the base salary of this Employee object.
   */
   public double getBaseSalary()
   {
      return baseSalary;
   }//End of getBaseSalary.
   
   //-----Abstract Methods-----
   
   /**
   An abstract method used by subclasses to compute and return the object's pay.
   */
   public abstract double computePay();
   
   //-----Implimented Methods-----
   
   /**
   Compare two Employee objects and determine this object's salary in reference to another.
   @Employee   otherEmployee  -  The Employee object to compare against this one.
   */
   public int compareTo(Employee otherEmployee)
   {
      //Initalize a variable to store the compared value.
      int comparedValue = 0;
      
      //Store the calculated salary for this Employee and the other Employee.
      double thisSalary = this.computePay();
      double otherSalary = otherEmployee.computePay();
      
      //Compare the salary of the Employees.
      //Return -1 if this Employee makes less than the other Employee.
      if(thisSalary < otherSalary)
         comparedValue = -1;
      //Return 0 if this Employee makes the same as the other Employee.
      else if((int) (thisSalary * 100) == (int) (otherSalary * 100))
         comparedValue = 0;
      //Return 1 if this Employee makes more than the other Employee.
      else
         comparedValue = 1;
      
      //Return the compared value.
      return comparedValue;
   }//End of compareTo.
   
   //-----equals, toString-----
   
   /**
   Checks if two Employee objects are equal to eachother, then returns a boolean value.
   @Employee   otherEmployee  -  The Employee object to compare this Employee object against.
   @returns    isSame         -  A boolean value which reads true if both Employee objects are equal.
   */
   public boolean equals(Employee otherEmployee)
   {
      //Create a boolean to store the result of a series of checks.
      boolean isSame = false;
      
      //If otherEmployee is empty, the objects are not the same.
      if(otherEmployee == null)
         isSame = false;
      //If otherEmployee is a shallow copy of this object, the objects are the same.
      else if(otherEmployee == this)
         isSame = true;
      //If all attributes are the same, the objects are the same.
      //Check baseSalary to two decimal places.
      else if( this.name.equalsIgnoreCase(otherEmployee.name)                          &&
               this.idNumber == otherEmployee.idNumber                                 &&
               (int) (this.baseSalary * 100) == (int) (otherEmployee.baseSalary * 100) )
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
      
      //Add the name of the employee.
      employeeString.append("The employee's name is: " + this.name + ".");
      
      //Add the id number of the employee.
      employeeString.append("\nThe employee's ID number is: " + this.idNumber + ".");
      
      //Add the base salary of the employee to two decimal places.
      employeeString.append("\nThe employee's base salary is: $" + String.format("%.2f",this.baseSalary) + ".");
      
      //Return the created String.
      return employeeString.toString();
   }//End of toString.
}//End of Employee abstract class.