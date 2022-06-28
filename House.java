/**
Description: CTP 150 - Final Project
Assignment. A class for House objects which contain a Realtor object and other
            attributes representing the house's address, price, and purchase status.
            It contains a default, secondary, and copy constructor as well as a
            tertiary constructor which can instantiate a House object without requiring
            the house's Realtor object or purchase status. It also contains accessors
            and mutators for all private attributes alongside an equals and toString
            method.
@author Quentin Flanders
@version 12/08/2021
*/

public class House
{
   //Private attributes.
   private String streetAddress;
   private String city;
   private String state;
   private int zipCode;
   private Realtor realtor;
   private double price;
   private boolean isSold; 
   
   //-----Constructors-----
   
   /**
   A default constructor for House objects when no information is provided.
   */
   public House()
   {
      //Use the secondary constructor.
      this("(No street address provided.)","(No city provided.)","(No state provided.)",-1,null,-1.0,false);
   }//End of default constructor.
   
   /**
   A secondary constructor for House objects when all information is provided.
   @String  streetAddress  -  The street address of the House object. 
   @String  city           -  The city the House object is located in.
   @String  state          -  The state the House object is located in.
   @int     zipCode        -  The House object's zip code.  
   @Realtor realtor        -  The Realtor object responsible for this House object.
   @double  price          -  The price of the House object.
   @boolean isSold         -  Represents if the House object has been sold or not.
   */
   public House(String streetAddress, String city, String state, int zipCode, Realtor realtor, double price, boolean isSold)
   {
      //Confirm that the provided street address is valid.
      if(streetAddress == null)
         this.streetAddress = "(No street address provided.)";
      else if(streetAddress.isEmpty())
         this.streetAddress = "(No street address provided.)";
      else
         this.streetAddress = streetAddress;
         
      //Confirm that the provided city is valid.
      if(city == null)
         this.city = "(No city provided.)";
      else if(city.isEmpty())
         this.city = "(No city provided.)";
      else
         this.city = city;
      
      //Confirm that the provided state is valid.
      if(state == null)
         this.state = "(No state provided.)";
      else if(state.isEmpty())
         this.state = "(No state provided.)";
      else
         this.state = state;
         
      //Confirm that the provided zip code is valid.
      if(zipCode < 0)
         this.zipCode = -1;
      else
         this.zipCode = zipCode;
       
      //Confirm that the provided Realtor object is valid.
      if(realtor == null)
         realtor = new Realtor("(No name provided.)", -1234, -1.0,0.03);
      //If the data is valid, create a deep copy of it.
      else
         this.realtor = new Realtor(realtor);
         
      //Confirm that the provided price is valid.
      if(price < 0)
         this.price = -1.0;
      else
         this.price = price;
      
      //Apply the provided purchase status.
      this.isSold = isSold;
   }//End of secondary constructor.
   
   /**
   A tertiary constructor for House objects when all information except realtor and isSold are provided.
   @String  streetAddress  -  The street address of the House object. 
   @String  city           -  The city the House object is located in.
   @String  state          -  The state the House object is located in.
   @int     zipCode        -  The House object's zip code.
   @double  price          -  The price of the House object.
   */
   public House(String streetAddress, String city, String state, int zipCode, double price)
   {
      //Confirm that the provided street address is valid.
      if(streetAddress == null)
         this.streetAddress = "(No street address provided.)";
      else if(streetAddress.isEmpty())
         this.streetAddress = "(No street address provided.)";
      else
         this.streetAddress = streetAddress;
         
      //Confirm that the provided city is valid.
      if(city == null)
         this.city = "(No city provided.)";
      else if(city.isEmpty())
         this.city = "(No city provided.)";
      else
         this.city = city;
      
      //Confirm that the provided state is valid.
      if(state == null)
         this.state = "(No state provided.)";
      else if(state.isEmpty())
         this.state = "(No state provided.)";
      else
         this.state = state;
         
      //Confirm that the provided zip code is valid.
      if(zipCode < 0)
         this.zipCode = -1;
      else
         this.zipCode = zipCode;
         
      //Confirm that the provided price is valid.
      if(price < 0)
         this.price = -1.0;
      else
         this.price = price;
      
      //Default isSold to false and realtor to null.
      this.isSold = false;
      this.realtor = null;
   }//End of tertiary constructor.
   
   /**
   A copy constructor for House objects to create a deep copy of another House object.
   @House   originalHouse  -  The House object to create a deep copy of.
   */
   public House(House originalHouse)
   {
      //If the reference is empty, set all attributes to their default values.
      if(originalHouse != null)
      {
         this.streetAddress = originalHouse.streetAddress;
         this.city = originalHouse.city;
         this.state = originalHouse.state;
         this.zipCode = originalHouse.zipCode;
         this.price = originalHouse.price;
         this.isSold = originalHouse.isSold;
         
         //Create a deep copy of the Realtor object.
         this.realtor = new Realtor(originalHouse.realtor);
      }//End of if.
      else
      {
         this.streetAddress = "(No street address provided.)";
         this.city = "(No city provided.)";
         this.state = "(No state provided.)";
         this.zipCode = -1;
         this.realtor = null;
         this.price = -1.0;
         this.isSold = false;
      }//End of else.
   }//End of copy constructor.
   
   //-----Setters (Mutators)-----
   
   /**
   Change or set the street address of a House object.
   @String  newStreetAddress  -  The new street address of a House object.
   */
   public void setStreetAddress(String newStreetAddress)
   {
      //Confirm that the provided street address is valid.
      if(newStreetAddress == null)
         this.streetAddress = "(No street address provided.)";
      else if(newStreetAddress.isEmpty())
         this.streetAddress = "(No street address provided.)";
      else
         this.streetAddress = newStreetAddress;
   }//End of setStreetAddress.
   
   /**
   Change or set the city a House object is located in.
   @String  newCity  -  The new city a House object is located in.
   */
   public void setCity(String newCity)
   {
      //Confirm that the provided city is valid.
      if(newCity == null)
         this.city = "(No city provided.)";
      else if(newCity.isEmpty())
         this.city = "(No city provided.)";
      else
         this.city = newCity;
   }//End of setStreetAddress.
   
   /**
   Change or set the state a House object is located in.
   @String  newState -  The new state a House object is located in.
   */
   public void setState(String newState)
   {
      //Confirm that the provided city is valid.
      if(newState == null)
         this.state = "(No city provided.)";
      else if(newState.isEmpty())
         this.state = "(No city provided.)";
      else
         this.state = newState;
   }//End of setStreetAddress.
   
   /**
   Change or set the zip code for a House object.
   @String  newZipCode  -  The new zip code for a House object.
   */
   public void setZipCode(int newZipCode)
   {
      //Confirm that the provided zip code is valid.
      if(newZipCode < 0)
         this.zipCode = -1;
      else
         this.zipCode = newZipCode;
   }//End of setZipCode.
   
   /**
   Change or set the realtor for a House object.
   @Realtor newRealtor  -  The new realtor for a House object.
   */
   public void setRealtor(Realtor newRealtor)
   {
      //Confirm that the provided Realtor object is valid.
      if(newRealtor == null)
         realtor = new Realtor("(No name provided.)", -1234, -1.0,0.03);
      //If the data is valid, create a deep copy of it.
      else
         this.realtor = new Realtor(newRealtor);
   }//End of setRealtor.
   
   /**
   Change or set the price for a House object.
   @double  newPrice -  The new price for a House object.
   */
   public void setPrice(double newPrice)
   {
      //Confirm that the provided price is valid.
      if(newPrice < 0)
         this.price = -1.0;
      else
         this.price = newPrice;
   }//End of setPrice.
   
   /**
   Change or set the purchase status for a House object.
   @boolean newIsSold   -  The new purchase status for a House object.
   */
   public void setIsSold(boolean newIsSold)
   {  
      //Apply the provided purchase status.
      this.isSold = newIsSold;
   }//End of setIsSold.
   
   //-----Getters (Accessors)-----
   
   /**
   Return the street address of a House object.
   @return  streetAddress  -  A String containing the street address of this House object.
   */
   public String getStreetAddress()
   {
      return streetAddress;
   }//End of getStreetAddress.
   
   /**
   Return the city a House object is located in.
   @return  city  -  A String containing the city this House object is located in.
   */
   public String getCity()
   {
      return city;
   }//End of getCity.
   
   /**
   Return the state a House object is located in.
   @return  state -  A String containing the state this House object is located in.
   */
   public String getState()
   {
      return state;
   }//End of getState.
   
   /**
   Return the zip code for a House object.
   @return  zipCode -  An int containing the zip code of this House object.
   */
   public int getZipCode()
   {
      return zipCode;
   }//End of getZipCode.
   
   /**
   Return the realtor for a House object.
   @return  realtorCopy -  A deep copy of this House object's Realtor object.
   */
   public Realtor getRealtor()
   {
      //Create a Realtor object to store the deep copy.
      Realtor realtorCopy = new Realtor(this.realtor);
      
      //Return the deep copy of the realtor object.
      return realtorCopy;
   }//End of getRealtor.
   
   /**
   Return the House object's Realtor object's commission rate.
   @return  commissionRate -  A double representing this House object's Realtor object's commission rate.
   */
   public double getRealtorCommissionRate()
   {
      //Get the House object's Realtor object's commission rate.
      double commissionRate = this.realtor.getCommissionRate();
      
      //Return the value.
      return commissionRate;
   }//End of getRealtorCommissionRate.
   
   /**
   Return the House object's Realtor object's number of listings.
   @return  numOfListings  -  An int representing this House object's Realtor object's number of listings.
   */
   public int getRealtorNumOfListings()
   {
      //Get the House object's Realtor object's number of listings.
      int numOfListings = this.realtor.getNumOfListings();
      
      //Return the value.
      return numOfListings;
   }//End of getRealtorNumOfListings.
   
   /**
   Return the House object's Realtor object's name.
   @return  name  -  A String representing this House object's Realtor object's name.
   */
   public String getRealtorName()
   {
      //Get the House object's Realtor object's name.
      String name = this.realtor.getName();
      
      //Return the value.
      return name;
   }//End of getRealtorName.
   
   /**
   Return the House object's Realtor object's ID Number.
   @return  idNumber -  An int representing this House object's Realtor object's ID Number.
   */
   public int getRealtorIdNumber()
   {
      //Get the House object's Realtor object's ID Number.
      int idNumber = this.realtor.getIdNumber();
      
      //Return the value.
      return idNumber;
   }//End of getRealtorIdNumber.
   
   /**
   Return the House object's Realtor object's base salary.
   @return  baseSalary  -  A double representing this House object's Realtor object's base salary.
   */
   public double getRealtorBaseSalary()
   {
      //Get the House object's Realtor object's ID Number.
      double baseSalary = this.realtor.getBaseSalary();
      
      //Return the value.
      return baseSalary;
   }//End of getRealtorBaseSalary.
   
   /**
   Return the House object's price.
   @return  price -  A double containing the House object's price.
   */
   public double getPrice()
   {
      return price;
   }//End of getPrice.
   
   /**
   Return the House object's purchase status.
   @return  isSold   -  A boolean containing the House object's purchase status.
   */
   public boolean getIsSold()
   {
      return isSold;
   }//End of getIsSold.
   
   //-----equals, toString-----
   
   /**
   Checks if two House objects are equal to eachother, then returns a boolean value.
   @House   otherHouse  -  The House object to compare this House object against.
   @returns isSame      -  A boolean value which reads true if both House objects are equal.
   */
   public boolean equals(House otherHouse)
   {
      //Create a boolean to store the result of a series of checks.
      boolean isSame = false;
      
      //If otherHouse is empty, the objects are not the same.
      if(otherHouse == null)
         isSame = false;
      //If otherHouse is a shallow copy of this object, the objects are the same.
      else if(otherHouse == this)
         isSame = true;
      //If all attributes are the same, the objects are the same.
      //Check price to two decimal places.
      else if( this.streetAddress.equalsIgnoreCase(otherHouse.streetAddress)  &&
               this.city.equalsIgnoreCase(otherHouse.city)                    &&
               this.state.equalsIgnoreCase(otherHouse.state)                  &&
               this.zipCode == otherHouse.zipCode                             &&
               this.realtor.equals(otherHouse.realtor)                        &&
               (int) (this.price * 100) == (int) (otherHouse.price * 100)     &&
               (this.isSold == otherHouse.isSold)                             )
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
      StringBuilder houseString = new StringBuilder();
      
      //Add the house's address.
      houseString.append("The house is found at: " + this.streetAddress + ", " + this.city + ", " + this.state + " " + this.zipCode + ".");
      
      //Add the house's price to two decimal places and its purchase status.
      if(this.isSold)
         houseString.append("\nThe house sold for: $" + String.format("%.2f",this.price) + ".");
      else
         houseString.append("\nThe house is selling for: $" + String.format("%.2f",this.price) + ".");
         
      //Add the realtor.
      if(this.realtor != null)
         houseString.append("\nThe following realtor is responsible for this house: " + this.getRealtorName() + ".");
      else
         houseString.append("\nNo realtor is responsible for this house.");
      
      //Return the created String.
      return houseString.toString();
   }//End of toString.
}//End of House class.