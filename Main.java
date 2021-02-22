import javax.swing.JOptionPane;
import java.io.*;
import java.util.Scanner;

class deckInfo {
  String name;
  double length;
  double width;
  double price;
}

class Main {
  public static void main(String[] args) {

    deckInfo [] deckList = new deckInfo [6];

    String fileName = "Decks.csv";

    deckList = readFromDeckList(fileName);
    
    int userInput = 0;;

    while (userInput != 4 ){
    userInput = getValidInput();
    
    if (userInput == 1){
      option1(deckList);
    } else if (userInput == 2) {
      option2(deckList);
    } else if (userInput == 3) {
      option3(deckList);
    }
    
    }

    System.out.println("Program has ended");

    System.exit(0);

  }

  public static deckInfo[] readFromDeckList (String nameOfFile) {
    Scanner fileScanner = null;

    deckInfo [] tempDeckInfo = new deckInfo [6];

    int index = 0;

    try {
      fileScanner = new Scanner (new BufferedReader (new FileReader (nameOfFile)));
      fileScanner.useDelimiter("[\\r\\n,]+");
      while (fileScanner.hasNext()) {
        tempDeckInfo[index] = new deckInfo();
        tempDeckInfo[index].name = fileScanner.next();
        tempDeckInfo[index].length = fileScanner.nextDouble();
        tempDeckInfo[index].width = fileScanner.nextDouble();
        tempDeckInfo[index].price = fileScanner.nextDouble();
        index = index + 1;
      }
    } catch (FileNotFoundException error) {
       System.out.println("File not found, fix the code and try again!");
    } finally {
      if(fileScanner!=null) {
        fileScanner.close();
      }
    }
    

      
    return tempDeckInfo;

   }



public static int getValidInput() {
  String msg = "Enter 1 to find the cheapest garden deck\nEnter 2 to display the number of garden decks over a certain length\nEnter 3 to display the number of garden decks that are availible over a certain area\nEnter 4 to quit";
  int input;
  input = Keyboard.getInt(msg);
   while (input < 1 || input > 4) {
     System.out.println("Input is not valid");
    input = Keyboard.getInt(msg);
   }

  return input;

}

public static void option1(deckInfo[] tempDeckInfo) {
  int position = 0;
  double cheapestDeck = tempDeckInfo[0].price;
  for (int index = 0; index < 6; index ++) {
    if (tempDeckInfo[index].price < cheapestDeck) {
      cheapestDeck = tempDeckInfo[index].price;
      position = index;
    }
  }

  System.out.println(tempDeckInfo[position].name + " is the cheapest deck at " + tempDeckInfo[position].price);


}

public static void option2(deckInfo[] tempDeckInfo) {
  String msg = "Enter a minimum deck length between 2 and 15";
  double input;
  input = Keyboard.getReal(msg);
   while (input < 2 || input > 15) {
     System.out.println("Input is not valid");
    input = Keyboard.getReal(msg);
   }
   System.out.println("The decks over " + input + " long are");
   for (int index = 0; index < 6; index ++) {
     if (tempDeckInfo[index].length >= input) {
       System.out.println(tempDeckInfo[index].name);
     }
   }
}
public static void option3(deckInfo[] tempDeckInfo) {
  String msg = "Enter a minimum deck area between 4 and 80";
  double input;
  input = Keyboard.getReal(msg);
   while (input < 4 || input > 80) {
     System.out.println("Input is not valid");
    input = Keyboard.getReal(msg);
   }
   int count = 0;
   for (int index = 0; index < 6; index ++) {
     if ((tempDeckInfo[index].length * tempDeckInfo[index].width) > input) {
       count = count + 1;
     }
   }
   System.out.println("There are " + count + " decks over "+ input + " metres sqaured");

}






}