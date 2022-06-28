/**
Description: CTP 150 - ToolBox
Create a ToolBox to store generic methods for reuse.
@author Quentin Flanders
@version 12/01/2021
*/

import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.Random;

public class ToolBox
{  
   //-----Exception Handling Examples-----
   
   /**
   *  Declare and initialize an array of integers, then intentionally go beyond the
      bounds of that array. Print out an error message when the
      ArrayIndexOutOfBoundsException error is triggered.
   @int  ArraySize   -  The size of the array to display this example with.
   */
   public static void exceptionHandlingDemo1(int ArraySize)
   {
      //Declare and initialize an array which can hold integers.
      int[] arrayExample = new int[ArraySize];
      
      //Display each element of the array.
      try
      {
         for(int currentIndex = 0;ArraySize >= currentIndex;currentIndex++)
         {
            //Print out the integer at the current index.
            System.out.println("Integer at index #" + currentIndex + ": " + arrayExample[currentIndex]);
         }//End of for loop.
      }//End of try to display all array elements.
      //If the loop attempts to display information outside of the array, print "Gone beyond array space" instead.
      catch(ArrayIndexOutOfBoundsException exception)
      {
         System.out.println("Gone beyond array space.");
      }//End of catch for attempting to read a point outside the bounds of the array.
   }//End of exceptionHandlingDemo1.
   
   /**
   *  Declare an array of integers, then ask the user for an integer to instantiate it.
      Account for the user entering invalid data in the form of a String or negative
      integer.
   @Scanner keyBoard -  A Scanner to recieve input from the user via keyboard.
   */
   public static void exceptionHandlingDemo2(Scanner keyBoard)
   {
      //Declare an array which can hold integers.
      int[] arrayExample;
      
      //Create a String object to hold user input.
      String userEntry = "";
      
      //Prompt the user for input.
      System.out.println("What should the length of the array be?");
      
      //Get input from the user.
      userEntry = keyBoard.nextLine();
      
      //Instantiate the array with the user's input.
      try
      {
         //Convert the String to an integer, then instantiate the array.
         arrayExample = new int[Integer.parseInt(userEntry)];
      }//End of try to recieve the array's element quantity from the user.
      //If the user does not enter an integer, tell them the problem.
      catch(NumberFormatException exception)
      {
         System.out.println("Error. " + userEntry + " can not be parsed into an integer. A numeric integer (Examples: 1, 2, or 3) must be entered.");
      }//End of catch for a non-integer entry.
      //If the user enters a negative integer, tell them the problem.
      catch(NegativeArraySizeException exception)
      {
         System.out.println("Error. You must enter a positive, numeric integer (Examples: 1, 2, or 3). You entered " + userEntry + " instead.");
      }//End of catch for a negative integer entry.
   }//End of exceptionHandlingDemo2.
   
   /**
   *  Ask the user for two integers, then divide the first by the second and return the result.
   @Scanner keyBoard -  A Scanner to recieve input from the user via keyboard.
   */
   public static int exceptionHandlingDemo3(Scanner keyBoard)
   {
      //Declare and initialize integers to store numbers for the user's division and its result.
      int exampleResult = 0;
      int exampleNumerator = 0;
      int exampleDenominator = 0;
      
      //Declare and initialize a boolean to track if the user has entered valid data.
      boolean entryIsInvalid = true;
      
      //Create a String object to hold user input.
      String userEntry = "";
      
      //Recieve the user's first integer. If the data is not valid, loop until it is.
      while(entryIsInvalid)
      {
         try
         {
            //Prompt the user for input, then store their response.
            System.out.println("Please enter the numerator for your division.");
            userEntry = keyBoard.nextLine();
            
            //Convert the user's entry to an integer.
            exampleNumerator = Integer.parseInt(userEntry);
            
            //If the first entry is valid, recieve the user's second integer, then divide the user's numerator and denominator. If any errors occur, loop until there are none.
            while(entryIsInvalid)
            {
               try
               {
                  //Prompt the user for input, then store their response.
                  System.out.println("Please enter the denominator for your division.");
                  userEntry = keyBoard.nextLine();
                  
                  //Convert the user's entry to an integer.
                  exampleDenominator = Integer.parseInt(userEntry);
                  
                  //Attempt to divide the user's numerator and denominator.
                  exampleResult = exampleNumerator / exampleDenominator;
                  
                  //If all tests have succeeded, end the loops.
                  entryIsInvalid = false;
               }//End of try to recieve the user's second integer and divide their numerator and denominator.
               //If the user does not enter an integer, tell them the problem.
               catch(NumberFormatException exception)
               {
                  System.out.println("Error. " + userEntry + " can not be parsed into an integer. A numeric integer (Examples: 1, 2, or 3) must be entered.");
               }//End of catch for a non-integer entry.
               //If the user entered zero for their denominator, tell them the problem.
               catch(ArithmeticException exception)
               {
                  System.out.println("Error. This program can not divide by zero. Please enter a numeric integer (Examples: 1, 2, or 3) instead.");
               }//End of catch for dividing by zero.
            }//End of inner while loop.
            
         }//End of try to recieve the user's first integer.
         //If the user does not enter an integer, tell them the problem.
         catch(NumberFormatException exception)
         {
            System.out.println("Error. " + userEntry + " can not be parsed into an integer. A numeric integer (Examples: 1, 2, or 3) must be entered.");
         }//End of catch for a non-integer entry.
      }//End of while loop.
      
      //Return the result of the user's division.
      return exampleResult;
   }//End of exceptionHandlingDemo3.
   
   //-----Other ToolBox Methods-----
   
   /**
   Check for a file, then tell the program if a file is found.
   @inFile File - From the File io. This is the name of the file you're looking for.  
   @return fileExists - This indicates if the file is found.
   */
   public static boolean checkForFile(File inFile)
   {
      boolean fileExists = true;
      if (!inFile.exists())
      {
         System.out.println("File does not exist.");
         fileExists = false;
      }
      //Error checking code for future use.
      //else
      //   System.out.println("File exists.");
      return fileExists;
   }//end boolean
   
   /**
   Check if a file exists after being given a string containing the file name, then create a scanner for it if it does exist. Exit the program if it does not.
   @fileName   String   - The name of the file you want to create a scanner for.
   @return     fileScan - This is the scanner for the file name you entered.
   */
   public static Scanner createScannerFromString(String fileName) throws IOException
   {
      //Create a new file object using the string passed into this method.
      File inFile = new File(fileName);
      
      //Check if the file exists.
      if (!inFile.exists())
         {
         System.out.println("File does not exist.");
         System.exit(0);
         }//end if
      
      //Create the scanner.
      Scanner fileScan = new Scanner(inFile);
      
      //Return the scannner for external use.
      return fileScan;
   }
   
   /**
   Create a simple scanner for user input via keyboard. 
   @return  scan  - From the Scanner util. A scanner to accept user input via their keyboard. 
   */
   public static Scanner createKeyBoardScanner()
   {
      //Create a scanner.
      Scanner scan = new Scanner(System.in);
      
      //Return the scanner.
      return scan;
   }//end createKeyBoardScanner
   
   /**
   Get a quantity of characters from the user minus one, then randomly choose a number from that quantity.
   @randNumLength int         - This value is a positive integer used to create a range for a random number to be generated from.
   @randomNumber  Random      - From the random utility. This is the name of the Random object created in the main program.
   @return        newRandNum  - This is the user's requested random number.
   */
   public static int randomNumberFromLength(int randNumLength, Random randomNumber)
   {
      int newRandNum = 0;
      newRandNum = randomNumber.nextInt(randNumLength);
      //Error checking code for future use.
      //System.out.println(newRandNum);
      return newRandNum;
   }
   
   /**
   Get a string array, find the length of the longest string in that array, then return that length as an integer.
   @arrayName  String []   - The array you want to find the longest string in. 
   @return     longest     - The length of the longest string in the given array. 
   */
   public static int findLongestStringInArray(String [] arrayName)
   {
      //Declare and initialize variable.
      int longestString = 0;
      
      //Find the length of the longest value in arrayName. 
      for(int i = 0; i < arrayName.length; i++)
      {
         if(longestString < arrayName[i].length())
            longestString = arrayName[i].length();
      }//end for
      
      //Return the value.
      return longestString;
   }//end findLongestStringInArray
   
   /**
   Use this after getting input from a user via System.in. This checks if a String is empty,
   flushes the line if it is, then prompts the user for entry and returns a String.
   @firstRead  String      - A String to check the status of.
   @keyBoard   Scanner     - A Scanner meant to recieve input from a keyboard.
   @return     secondRead  - The new String to return.
   */
   public static String checkIfInputIsEmpty(String firstRead, Scanner keyBoard)
   {
      //Declare and initialize a String.
      String secondRead = "";
      
      //If firstRead is blank, get the user's response again.
      if(firstRead.length() < 1)
         secondRead = keyBoard.nextLine();
      else
         secondRead = firstRead;
         
      //Return the corrected String.
      return secondRead;
   }//End of checkIfInputIsEmpty.
}//end class