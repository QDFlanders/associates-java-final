/**
Description: CTP 150 - Final Project
Assignment. A driver for House and Employee objects which takes data from two csv files,
            one containing information on Employee objects and another containing 
            information on House objects. It process the data, stores it in two
            separate ArrayLists, then assigns Houses to all Realtors in the ArrayList
            containing Employee objects. Users can display data based on specific
            parameters relevant to the data, or "sell" a House object.
@author Quentin Flanders
@version 12/09/2021
*/

import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class EmployeesAndHousesDriver
{
   public static void main(String[] args) throws IOException
   {
      //Create an ArrayList to store Employee objects.
      ArrayList<Employee> employees = createEmployeeDataStructure();
      
      //Create an ArrayList to store House objects.
      ArrayList<House> houses = createHouseDataStructure();
      
      //Fill the employees ArrayList with data from the declared file.
      readAndFillEmployee("employeesFinalProject.csv",employees);
      
      //Fill the houses ArrayList with data from the declared file.
      readAndFillHouse("housesFinalProject.csv",houses);
      
      //Assign Realtor objects to the houses ArrayList.
      assignRealtors(houses,employees);
      
      //Allow the user to interact with the program.
      userInterface(houses,employees);
   }//End of main.
   
   //-----Create Data Structures-----
   
   /**
   Create an ArrayList to store Employee objects, then return the address.
   @return  employees   -  An ArrayList to store Employee objects.
   */
   public static ArrayList<Employee> createEmployeeDataStructure()
   {
      //Instantiate an ArrayList to store data on Employee objects.
      ArrayList<Employee> employees = new ArrayList<Employee>();
      
      //Return the ArrayList.
      return employees;
   }//End of createDataStructure.
   
   /**
   Create an ArrayList to store House objects, then return the address.
   @return  houses   -  An ArrayList to store House objects.
   */
   public static ArrayList<House> createHouseDataStructure()
   {
      //Instantiate an ArrayList to store data on House objects.
      ArrayList<House> houses = new ArrayList<House>();
      
      //Return the ArrayList.
      return houses;
   }//End of createDataStructure.
   
   //-----Read and Fill ArrayLists-----
   
   /**
   Read and store data from a csv file in an ArrayList containing Employee objects when given the name of a csv file.
   @String              fileName    - The name of the file you want to import.
   @ArrayList<Employee> employees   - An ArrayList for Employee objects where data will be stored.
   */
   public static void readAndFillEmployee(String fileName,ArrayList<Employee> employees) throws IOException
   {
      //Check if the file name is valid. If so, create a scanner. If not, tell the user so and end the program.
      Scanner employeeScan = ToolBox.createScannerFromString(fileName);
      
      //Toss the first line.
      employeeScan.nextLine();
      
      //Declare and initialize variables to temporarily store values as they are tokenized.
      char employeeType = 'a';
      String name = "(No name provided.)";
      int idnumber = -1234;
      double salary = -1.0;
      
      //Create four temporary objects to store each line as they are read.
      String tempLine = "";
      StringTokenizer tempTokenizer;
      Realtor tempRealtor;
      OfficeStaff tempOfficeStaff;
      
      //Run a loop to process information from the data file until it has been read in full.
      while (employeeScan.hasNext())
      {
         //Get the next line and store it as a String.
         tempLine = employeeScan.nextLine();
         
         //Create a StringTokenizer, pass in the line which was read, and use "," as the delimiter.
         tempTokenizer = new StringTokenizer(tempLine,",");
         
         //Read through tempLine, passing data into the appropriate variables as they are tokenized.
         employeeType = tempTokenizer.nextToken().charAt(0);
         name = tempTokenizer.nextToken();
         idnumber = Integer.parseInt(tempTokenizer.nextToken());
         salary = Double.parseDouble(tempTokenizer.nextToken());
         
         //Instantiate an Employee object using the tokenized data, then add it to the ArrayList.
         switch(employeeType)
         {
            case 'o':   tempOfficeStaff = new OfficeStaff(name,idnumber,salary);
                        employees.add(tempOfficeStaff);
                        break;
            case 'r':   tempRealtor = new Realtor(name,idnumber,salary,0.03);
                        employees.add(tempRealtor);
                        break;
            default:    System.out.println("Data not recognized.");
                        break;
         }//End of switch.
      }//End of while loop.
      
      //Close the scanner.
      employeeScan.close();
   }//End of readAndFillEmployee.
   
   /**
   Read and store data from a csv file in an ArrayList containing House objects when given the name of a csv file.
   @String           fileName - The name of the file you want to import.
   @ArrayList<House> houses   - An ArrayList for House objects where data will be stored.
   */
   public static void readAndFillHouse(String fileName,ArrayList<House> houses) throws IOException
   {
      //Check if the file name is valid. If so, create a scanner. If not, tell the user so and end the program.
      Scanner houseScan = ToolBox.createScannerFromString(fileName);
      
      //Toss the first line.
      houseScan.nextLine();
      
      //Declare and initialize variables to temporarily store values as they are tokenized.
      String streetAddress = "(No street address provided.)";
      String city = "(No city provided.)";
      String state = "(No state provided.)";
      int zipCode = -1;
      double price = -1.0;
      
      //Create three temporary objects to store each line as they are read.
      String tempLine = "";
      StringTokenizer tempTokenizer;
      House tempHouse;
      
      //Run a loop to process information from the data file until it has been read in full.
      while (houseScan.hasNext())
      {
         //Get the next line and store it as a String.
         tempLine = houseScan.nextLine();
         
         //Create a StringTokenizer, pass in the line which was read, and use "," as the delimiter.
         tempTokenizer = new StringTokenizer(tempLine,",");
         
         //Read through tempLine, passing data into the appropriate variables as they are tokenized.
         streetAddress = tempTokenizer.nextToken();
         city = tempTokenizer.nextToken();
         state = tempTokenizer.nextToken();
         zipCode = Integer.parseInt(tempTokenizer.nextToken());
         price = Double.parseDouble(tempTokenizer.nextToken());
         
         //Instantiate a House object using the tokenized data, then add it to the ArrayList.
         tempHouse = new House(streetAddress,city,state,zipCode,price);
         houses.add(tempHouse);
      }//End of while loop.
      
      //Close the scanner.
      houseScan.close();
   }//End of readAndFillHouse.
   
   /**
   Assign realtors one at a time, in order, to the House objects.
   @ArrayList<House>    houses      - An ArrayList for House objects where data will be stored.
   @ArrayList<Employee> employees   - An ArrayList for Employee objects where data will be pulled from.
   */
   public static void assignRealtors(ArrayList<House> houses,ArrayList<Employee> employees)
   {
      //Declare and initialize an integer representing the final index of the employees ArrayList.
      int employeesSize = employees.size() - 1;
      
      //Declare and initialize an integer to keep track of the index of the employees ArrayList.
      int employeesIndex = 0;
      
      //Create a temporary object to store each Employee object as they are read.
      Realtor tempRealtor;
      
      //Traverse the length of the houses ArrayList, assigning realtors as necessary.
      for(House currentHouse : houses)
      {
         //Loop through the employees ArrayList until a Realtor object is found.
         while(employees.get(employeesIndex) instanceof OfficeStaff)
         {
            employeesIndex++;
         }//End of inner while loop.
         
         //Store the current employee in tempRealtor.
         tempRealtor = (Realtor) employees.get(employeesIndex);
         
         //Parse the Employee object to a Realtor, then assign it to the current house.
         currentHouse.setRealtor(tempRealtor);
         
         //Increment tempRealtor's numOfListings.
         tempRealtor.incrementRealtorListings();
         
         //Increment employeesIndex.
         employeesIndex++;
         
         //If employeesIndex is over employeesSize, reset employeesIndex to zero.
         if(employeesIndex > employeesSize)
            employeesIndex = 0;
      }//End of for loop.
   }//End of assignRealtors.
   
   //-----User Interaction-----
   
   /**
   Display all information in the provided ArrayLists.
   @ArrayList<House>    houses      - An ArrayList containing House objects.
   @ArrayList<Employee> employees   - An ArrayList containing Employee objects.
   */
   public static void displayAll(ArrayList<House> houses,ArrayList<Employee> employees)
   {
      //Loop through the ArrayList for Employee objects.
      for(Employee thisEmployee : employees)
      {
         //Parse the employee to the appropriate type, then print it.
         if(thisEmployee instanceof Realtor)
            System.out.println((Realtor) thisEmployee  + "\n-----");
         else
            System.out.println((OfficeStaff) thisEmployee  + "\n-----");
      }//End of for loop.
      
      //Loop through the ArrayList for House objects.
      for(House thisHouse : houses)
         System.out.println(thisHouse  + "\n-----");
   }//End of displayAll.
   
   /**
   Display all Realtor objects in the provided ArrayList.
   @ArrayList<Employee> employees   - An ArrayList containing Employee objects.
   */
   public static void displayAllRealtors(ArrayList<Employee> employees)
   {
      //Loop through the ArrayList for Employee objects.
      for(Employee thisEmployee : employees)
      {
         //Parse the employee to the appropriate type, then print it.
         if(thisEmployee instanceof Realtor)
            System.out.println((Realtor) thisEmployee  + "\n-----");
      }//End of for loop.
   }//End of displayAllRealtors.
   
   /**
   Display all OfficeStaff objects in the provided ArrayList.
   @ArrayList<Employee> employees   - An ArrayList containing Employee objects.
   */
   public static void displayAllOfficeStaff(ArrayList<Employee> employees)
   {
      //Loop through the ArrayList for Employee objects.
      for(Employee thisEmployee : employees)
      {
         //Parse the employee to the appropriate type, then print it.
         if(thisEmployee instanceof OfficeStaff)
            System.out.println((OfficeStaff) thisEmployee  + "\n-----");
      }//End of for loop.
   }//End of displayAllOfficeStaff.
   
   /**
   Prompt the user for a state, then display all House objects in that state.
   @ArrayList<House> houses   - An ArrayList containing House objects.
   @Scanner          keyBoard - A scanner which accepts input from a keyboard.
   */
   public static void displayHouseByState(ArrayList<House> houses, Scanner keyBoard)
   {
      //Prompt the user for input.
      System.out.println("Please enter the two-letter state acronym for the state you'd like to see houses in.");
      
      //Get the user's input.
      String userState = ToolBox.checkIfInputIsEmpty(keyBoard.nextLine(),keyBoard);
      
      //Declare and initalize variables to keep track of data as the ArrayList is traversed.
      String currentState = ""; 
      boolean houseNotFound = true;
      
      //Loop through the ArrayList for House objects.
      for(House thisHouse : houses)
      {
         //Get the current house's state.
         currentState = thisHouse.getState();
         
         //Check if the user's state matches the current house's state.
         if(currentState.toLowerCase().contains(userState.toLowerCase()))
         {
            //If the user's state matches the house's state, print out the house.
            System.out.println(thisHouse + "\n-----");
            
            //Set houseNotFound to false.
            houseNotFound = false;
         }//End of if.
      }//End of for loop.
      
      //If the user's state query does not produce any results, tell them so.
         if(houseNotFound)
         {
            System.out.println("No houses were found within the state of: " + userState + ".");
         }//End of houseNotFound.
      
   }//End of displayHouseByState.
   
   /**
   Prompt the user for minimum and maximum price, then display all House objects in that price range.
   @ArrayList<House> houses   - An ArrayList containing House objects.
   @Scanner          keyBoard - A scanner which accepts input from a keyboard.
   */
   public static void DisplayHouseByPriceRange(ArrayList<House> houses, Scanner keyBoard)
   {
      //Create a double to temporarily store the price of each object.
      double tempPrice = 0.0;
      
      //Declare and initialize variables to keep track of user requests.
      double upperBound = 0.0;
      double lowerBound = 0.0;
      boolean matchNotFound = true;
      String userText = "";
      boolean invalidEntry = true;
      
      
      //Store the user's reponse, check if it is valid, and loop until it is.
      while(invalidEntry || (int) (lowerBound * 100) < 0)
      {
         //Ask the user for a lower bound range to search for.
         System.out.println("What is the cheapest price for a house you'd like to see? (It must be greater than $0.00.)");
            
         //Store their response.
         userText = ToolBox.checkIfInputIsEmpty(keyBoard.nextLine(),keyBoard);
             
         //Check if their response is valid.
         try
         {
            lowerBound = Double.parseDouble(userText);
               
            //If the user's entry is valid, exit the loop.
            invalidEntry = false;
         }//End of try
         catch(NumberFormatException exception)
         {
            System.out.println("Error. " + userText + " can not be parsed into a floating point number. A numeric integer (Examples: 1, 2, or 3) or floating point number (Examples: 1.50, 1.75, or 2.00) must be entered.");
         }//End of catch for NumberFormatException.
      }//End of while loop.
      
      //Set invalidEntry to true again.
      invalidEntry = true;
      
      //Store the user's reponse, check if it is valid, and loop until it is.
      while(invalidEntry || (int) (upperBound*100) < (int) (lowerBound * 100))
      {
         //Ask the user for an upper bound range to search for.
         System.out.println("What is the greatest price for a house you'd like to see? (It must be greater than $" + String.format("%.2f",lowerBound) +".)");
            
         //Store their response.
         userText = ToolBox.checkIfInputIsEmpty(keyBoard.nextLine(),keyBoard);
             
         //Check if their response is valid.
         try
         {
            upperBound = Double.parseDouble(userText);
               
            //If the user's entry is valid, exit the loop.
            invalidEntry = false;
         }//End of try
         catch(NumberFormatException exception)
         {
            System.out.println("Error. " + userText + " can not be parsed into a floating point number. A numeric integer (Examples: 1, 2, or 3) or floating point number (Examples: 1.50, 1.75, or 2.00) must be entered.");
         }//End of catch for NumberFormatException.
      }//End of while loop.
      
      //Traverse the ArrayList.
      for(House thisHouse : houses)
      {
         //Store the price of this House object.
         tempPrice = thisHouse.getPrice();
         
         //Check if the price of this house falls within the user's declared price range.
         if((int) (tempPrice*100) > (int) (lowerBound * 100) && (int) (tempPrice*100) < (int) (upperBound*100))
         {
            //Print out the house.
            System.out.println(thisHouse + "\n-----");
            
            //Keep track of if the user's request has been found.
            matchNotFound = false;
         }//End of if.
      }//End of for loop.
      
      //If the user's request was not found, tell them.
      if(matchNotFound)
      {
         System.out.println("Sorry. No house with a price between $" + String.format("%.2f",lowerBound) + " and $" + String.format("%.2f",upperBound) + " could be found.");
      }//End of if.
   }//End of DisplayHouseByPriceRange.
   
   /**
   Prompt the user for a realtor's name, then display all House objects being managed by a realtor with that name.
   @ArrayList<House> houses   - An ArrayList containing House objects.
   @Scanner          keyBoard - A scanner which accepts input from a keyboard.
   */
   public static void DisplayHouseByRealtorName(ArrayList<House> houses, Scanner keyBoard)
   {
      //Prompt the user for input.
      System.out.println("Please enter the name of realtor you'd like to see houses from.");
      
      //Get the user's input.
      String userRealtorName = ToolBox.checkIfInputIsEmpty(keyBoard.nextLine(),keyBoard);
      
      //Declare and initalize variables to keep track of data as the ArrayList is traversed.
      String currentRealtorName = ""; 
      boolean nameNotFound = true;
      
      //Loop through the ArrayList for House objects.
      for(House thisHouse : houses)
      {
         //Get the current house's realtor's name.
         currentRealtorName = thisHouse.getRealtorName();
         
         //Check if the user's realtor name matches the current house's realtor's name.
         if(currentRealtorName.toLowerCase().contains(userRealtorName.toLowerCase()))
         {
            //If the user's realtor name matches the current house's realtor's name, print out the house.
            System.out.println(thisHouse + "\n-----");
            
            //Set houseNotFound to false.
            nameNotFound = false;
         }//End of if.
      }//End of for loop.
      
      //If the user's realtor-name query does not produce any results, tell them so.
         if(nameNotFound)
         {
            System.out.println("No houses are being managed by a realtor named: " + userRealtorName + ".");
         }//End of if.
   }//End of DisplayHouseByRealtorName.
   
   /**
   "Sell" a house by requesting an address by the user, setting the house's status to sold, then adjusting the base salary of the realtor who sold the house.
   @ArrayList<House>    houses      - An ArrayList containing House objects.
   @ArrayList<Employee> employees   - An ArrayList containing Employee objects.
   @Scanner             keyBoard    - A scanner which accepts input from a keyboard.
   */
   public static void sellHouse(ArrayList<House> houses,ArrayList<Employee> employees, Scanner keyBoard)
   {
      //Declare and initialize variables to keep track of user requests.
      int userZipCode = 0;
      int tempZipCode = 0;
      int tempRealtorID = 0;
      boolean matchNotFound = true;
      String userText = "";
      boolean invalidEntry = true;
      
      //Ask the user for the zip code of the house they want to purchase.
      System.out.println("Please enter the zip code of the house you want to sell.");
      
      //Store the user's input, then ensure it is valid.
      while(invalidEntry)
      {
         userText = ToolBox.checkIfInputIsEmpty(keyBoard.nextLine(),keyBoard);
         
         //Ensure the user's input is valid.
         try
         {
            userZipCode = Integer.parseInt(userText);
            
            //If the user's input is valid, exit the loop.
            invalidEntry = false;
         }//End of try.
         catch(NumberFormatException exception)
         {
            System.out.println("Error. " + userText + " can not be parsed into an integer. A numeric integer (Examples: 1, 2, or 3) must be entered.");
         }//End of catch for InputMismatchException.
      }//End of while loop.
      
      //Loop through the houses ArrayList.
      for(House thisHouse : houses)
      {
         //Get the current house's zip code.
         tempZipCode = thisHouse.getZipCode();
         
         //If the current house's zip code is equal to the user's zip code, ask the user for confirmation.
         if(tempZipCode == userZipCode)
         {
            System.out.println(thisHouse + "\n");
            
            //Set invalidEntry to true again.
            invalidEntry = true;
            
            while(invalidEntry)
            {
            System.out.println("Is this the house you wanted to sell? (Please enter yes or no.)");
            
            //Get the user's input.
            userText = keyBoard.next();
            if(userText.equalsIgnoreCase("yes") || userText.equalsIgnoreCase("y") || userText.equalsIgnoreCase("ye") )
            {
               System.out.println("Selling the house...");
               
               //If the house was correct, sell the house.
               thisHouse.setIsSold(true);
               
               //Get the realtor's id number, then add the appropriate commission to the house's realtor's base salary.
               tempRealtorID = thisHouse.getRealtorIdNumber();
               
               //Loop through the employees ArrayList.
               for(Employee thisEmployee : employees)
               {
                  //When you find a match for the realtor's ID, add the appropriate commission to the house's realtor's base salary.
                  if(tempRealtorID == thisEmployee.getIdNumber())
                     thisEmployee.setBaseSalary(thisEmployee.getBaseSalary() + (((Realtor) thisEmployee).getCommissionRate() * thisHouse.getPrice()));
               }//End of inner for loop.
               
               //Keep track of the user's request.
               matchNotFound = false;
               
               //Set the user's input to be valid.
               invalidEntry = false;
            }//End of inner if.
            else if(userText.equalsIgnoreCase("no") || userText.equalsIgnoreCase("n"))
            {
               System.out.println("Understood. Moving on...");
               
               //Set the user's input to be valid.
               invalidEntry = false;
            }//End of inner else-if.
            else
               System.out.println("I didn't understand " + userText + ". Please try again.");
            }//End of inner while loop.
         }//End of if.
      }//End of for loop.
      
      //If no match was found, inform the user.
      if(matchNotFound)
         System.out.println("Sorry. No zip code with an address of " + userZipCode + " could be found in our listings.");
   }//End of sellHouse.
   
   /**
   Take data from an ArrayList containing Realtor objects, place them in a new ArrayList in alphabetical order, then print out realtors in alphabetical order with their number of listings.
   @ArrayList<Employee> employees   - An ArrayList containing Employee objects.
   */
   public static void DisplayRealtorsAlphabetically(ArrayList<Employee> employees)
   {
      //Create a new ArrayList for Realtor objects.
      ArrayList<Realtor> alphabetic = new ArrayList<Realtor>();
      
      //Declare and initialize variables to keep track of the Realtor objects.
      char newRealtorInitial = '!';
      String newRealtorName = "";
      char oldRealtorInitial = '!';
      String oldRealtorName = "";
      boolean winnerNotFound = true;
      int alphabeticSize = 0;
      int letterCount = 0;
      
      //Create a temporary object to store each Realtor object as they are read.
      Realtor tempRealtor;
      
      //Traverse the passed ArrayList.
      for(Employee currentEmployee : employees)
      {
         //Check if the current Employee is a realtor.
         if(currentEmployee instanceof Realtor)
         {
            //Get the size of the alphabetic ArrayList.
            alphabeticSize = alphabetic.size();
            
            //Check if the alphabetic ArrayList's length is greater than 0.
            if(alphabeticSize > 0)
            {  
               //Set winnerNotFound to true.
               winnerNotFound = true;
               
               //Set letterCount to zero.
               letterCount = 0;
               
               //Get the name of this employee.
               newRealtorName = currentEmployee.getName().toLowerCase();
               
               //Traverse the alphabetic ArrayList.
               for(int i = 0; alphabeticSize > i; i++)
               {
                  //Store the address of the current Realtor object.
                  tempRealtor = alphabetic.get(i);
                  
                  //Get the name of the current Realtor object.
                  oldRealtorName = tempRealtor.getName().toLowerCase();
                  
                  //Check if newRealtorName is greater than or less than oldRealtorName.
                  if(newRealtorName.compareTo(oldRealtorName) > 0)
                  {
                     //If newRealtorName is greater than oldRealtorName, check if you're at the end of the alphabetic ArrayList.
                     if(i == alphabeticSize-1)
                     {
                        //If you're at the end of the alphabetic array, add currentEmployee.
                        alphabetic.add((Realtor) currentEmployee);
                        
                        //Exit the inner for loop.
                        i = alphabeticSize;
                     }//End of if.
                  }//End of if.
                  else if(newRealtorName.compareTo(oldRealtorName) < 0)
                  {
                     //If newRealtorName is less than oldRealtorName, insert it before oldRealtorName.
                     alphabetic.add(i,(Realtor) currentEmployee);
                     
                     //Exit the inner for loop.
                     i = alphabeticSize;
                  }//End of else if.
                  else
                  {
                     //If the names are equal, add the Employee object before this Realtor object.
                     alphabetic.add(i,(Realtor) currentEmployee);
                     
                     //Exit the inner for loop.
                     i = alphabeticSize;
                  }//End of else.
               }//End of inner for loop.
            }//End of if.
            //If the alphabetic ArrayList's length is 0, add the first Realtor to the ArrayList.
            else
            {
               alphabetic.add((Realtor) currentEmployee);
            }//End of else.
         }//End of if.
      }//End of for loop.
      
      //Print out the new alphabetic ArrayList.
      for(Realtor thisRealtor : alphabetic)
         System.out.println(thisRealtor.getName() + " is working with " + thisRealtor.getNumOfListings() + " house listings.");
   }//End of DisplayRealtorsAlphabetically.
   
   //-----Private Methods-----
   
   /**
   Display a menu which allows the user to interact with the program through keyboard input.
   @ArrayList<House>    houses      - An ArrayList containing House objects.
   @ArrayList<Employee> employees   - An ArrayList containing Employee objects.
   */
   private static void userInterface(ArrayList<House> houses,ArrayList<Employee> employees)
   {
      //Create a keyboard scanner to accept user input.
      Scanner keyBoard = ToolBox.createKeyBoardScanner();
      
      //Declare and initialize variables to keep track of user requests.
      int userChoice = 0;
      String userText = "";
      boolean invalidInput = true;
      
      //Run a do-while loop until the user decides to quit.
      do{
         //Ask the user how they want to interact with the data in the ArrayLists, and present them with options.
         System.out.println("What information would you like on these houses and employees?");
         System.out.printf("1: %s\n2: %s\n3: %s\n4: %s\n5: %s\n6: %s\n7: %s\n8: %s\n9: %s\n","Display Everything","Display All Realtors","Display All Office Staff","Display Houses by State","Display Houses by Price Range","Display Houses by Realtor Name","Sell a House","Display All Realtors Alphabetically","Nothing (Exit the program.)");
         
         //Store the user's response, then check if it is valid.
         while(invalidInput)
         {
            //Store the user's response.
            userText = ToolBox.checkIfInputIsEmpty(keyBoard.nextLine(),keyBoard);
            
            //Ensure the user's response is valid.
            try
            {
               //Parse the user's input as an integer.
               userChoice = Integer.parseInt(userText);
               
               //If the user's input can be parsed as an integer, exit the inner while loop.
               invalidInput = false;
            }//End of try.
            catch(NumberFormatException exception)
            {
               System.out.println("Error. " + userText + " can not be parsed into an integer. A numeric integer (Examples: 1, 2, or 3) must be entered.");
            }//End of catch for InputMismatchException.
         }//End of inner while loop.
         
         //Run the user's response through a switch.
         switch(userChoice)
         {
            case 1:  displayAll(houses,employees);
                     break;
            case 2:  displayAllRealtors(employees);
                     break;
            case 3:  displayAllOfficeStaff(employees);
                     break;
            case 4:  displayHouseByState(houses,keyBoard);
                     break;
            case 5:  DisplayHouseByPriceRange(houses,keyBoard);
                     break;
            case 6:  DisplayHouseByRealtorName(houses,keyBoard);
                     break;
            case 7:  sellHouse(houses,employees,keyBoard);
                     break;
            case 8:  DisplayRealtorsAlphabetically(employees);
                     break;
            default: //If the user did not enter 9, ask if they want to close the program.
                     if(!(userChoice == 9))
                     {
                        //Loop until the user provides valid input.
                        do{
                           System.out.println("You didn't enter a number on the list. Do you want to exit the program? (Please enter yes or no.)");
                           userText = keyBoard.next();
                           if(userText.equalsIgnoreCase("yes") || userText.equalsIgnoreCase("y") || userText.equalsIgnoreCase("ye") )
                           {
                              invalidInput = false;
                              userChoice = 9;
                           }//End of inner if.
                           else if(userText.equalsIgnoreCase("no") || userText.equalsIgnoreCase("n"))
                           {
                              invalidInput = false;
                           }//End of inner else-if.
                        }while(invalidInput);
                     }//End of if.
                     break;
         }//End of switch.
         
         //Reset the invalid input to true in case the user doesn't enter a number on the list again.
         invalidInput = true;
         
         //Print out a line for legability.
         System.out.println("");
      }while(!(userChoice == 9));
      
      //Tell the user the program is closing.
      System.out.println("Closing the program. Thank you.");
      
      //Close the scanner.
      keyBoard.close();
   }//End of userInterface.
}//End of class.