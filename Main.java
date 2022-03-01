/**
*Main.java
*
*Driver program that allows users to input a selected number
*of polynomials. Users can then edit these (add/remove terms),
*clear a polynomial entirely, or get the sum of all entered
*polynomials.
*
*@author Katarina Cohen
*@version 2.0
*
*/

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    List<Polynomial> polynomial = new ArrayList<Polynomial>();
    Polynomial poly = new Polynomial();
    Polynomial editPoly = new Polynomial();
    int choice = 0;
    int answer = 0;

    do {
      System.out.println("--- Main Menu ---");
      System.out.println();
      for(int i = 0; i < answer; i++) {
        System.out.println("Polynomial " + (i + 1) + ": " + polynomial.get(i).getPolynomial());
      }
      System.out.println();
      System.out.println("1) Enter a Polynomial");
      System.out.println("2) Edit a Polynomial");
      System.out.println("3) Return Sum");
      System.out.println("4) Exit Program");

      choice = scanner.nextInt();
      scanner.nextLine();

      if (choice == 1) {
        System.out.println("\nHow many polynomials would you like to enter?");
        answer = scanner.nextInt();

        Polynomial[] polyArray = polynomial.toArray(new Polynomial[0]);

        polyArray = new Polynomial[answer];
        scanner.nextLine();
        System.out.println();

        for(int i = 0; i < answer; i++) {
          System.out.println("Please enter a polynomial to store.");

          String input = scanner.nextLine();

          polyArray[i] = new Polynomial();
          polyArray[i].setPolynomial(input);
          polyArray[i].splitPolynomial(input);
          System.out.println("");
          
        }
        polynomial = Arrays.asList(polyArray);
      }
      else if (choice == 2) {
        int option = 0;
        int polyChoice = 0;
        String newTerm = null;
        
        System.out.println("1) Add Term");
        System.out.println("2) Remove Term");
        System.out.println("3) Clear Polynomial");
        System.out.println("4) Return to Main Menu");

        option = scanner.nextInt();
        scanner.nextLine();
      
        if (option == 1) {
          System.out.println("\nWhat polynomial would you like to edit?");
          polyChoice = scanner.nextInt();
          scanner.nextLine();
          
          System.out.println("Enter the term you would like to add: ");
          newTerm = scanner.nextLine();

          String newP = editPoly.addTerm(polynomial.get(polyChoice - 1).getPolynomial(), newTerm);
          editPoly.setPolynomial(newP);
          polynomial.get(polyChoice - 1).setPolynomial(editPoly.getPolynomial());
          
        }
        else if (option == 2) {
          System.out.println("\nWhat polynomial would you like to edit?");
          polyChoice = scanner.nextInt();
          scanner.nextLine();

          System.out.println("Enter the term you would like to remove: ");
          newTerm = scanner.nextLine();

          String newP = editPoly.removeTerm(polynomial.get(polyChoice - 1), newTerm);
          editPoly.setPolynomial(newP);
          polynomial.get(polyChoice - 1).setPolynomial(editPoly.getPolynomial());
        }
        else if (option == 3) {
          Polynomial[] polyArray = polynomial.toArray(new Polynomial[0]);
          
          System.out.println("\nWhat polynomial would you like to clear?");
          polyChoice = scanner.nextInt();
          scanner.nextLine();

          Polynomial[] newPArray = editPoly.removePolynomial(polyArray, polyChoice - 1);
          polynomial = Arrays.asList(newPArray);
          
          }
        else if (option == 4) {
          System.out.println("");
        }
        else {
          System.out.println("This is not a valid option. Please try again.\n");
      }

      }
      else if (choice == 3) {
        Polynomial[] polyArray = polynomial.toArray(new Polynomial[0]);
        System.out.println("\nThe sum of the entered polynomnials is: ");
        System.out.println(poly.addPolynomials(polyArray));
        System.out.println("");
      }
      else if (choice > 4 || choice < 1) {
        System.out.println("This is not a valid option. Please try again.\n");
      }
      
    } while (choice != 4);
    
    
  }
}