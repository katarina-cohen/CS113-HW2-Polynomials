/**
*Polynomial.java
*
*Class invariant: Entered polynomials need to be given in specific form (e.g. 4x^2 + 3x - 5)
*
*@author Katarina Cohen
*@version 2.0
*
*/

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
//import java.util.Collections;

public class Polynomial extends Term {    
  //CONSTANT VARIABLES
  public static final String DEFAULT_POLYNOMIAL = "4x^2";
  public static final Term[] DEFAULT_TERM = {new Term(4, 2)};

  //INSTANCE VARIABLES
  protected String polynomial = null;
  protected Term[] splitTerms;

  /**
  *Full constructor, initializes polynomnial and split terms array for new Polynomial object.
  *
  *@param p   the polynomial initialized
  *@param splitT   the array of corresponding Term objects initialized
  */
  public Polynomial(String p, Term[] splitT) {
    this.polynomial = p;
    this.splitTerms = splitT;
  }

  /**
  *Default constructor, initializes Term to polynomial and corresponding Term if nothing provided.
  */ 
  public Polynomial() {
    this(DEFAULT_POLYNOMIAL, DEFAULT_TERM);
  }

  /**
  *Copy constructor creates an object of type Polynomial by initializing it with a Polynomial object that has been created previously, if this object is not null.
  *
  *@param original  the Polynomial object you want to copy from
  */
  public Polynomial(Polynomial original) {
    if(original == null) {
      System.out.println("ERROR: Null data given to copy Polynomial constructor. Exiting...");
      System.exit(0);
    }
    else{
      this.setAll(original.polynomial, original.splitTerms);
    }
  }

  /**
  *Sets given polynomial as term's polynomial.
  *
  *@param polynomial   the String entered for the polynomial
  */
  public void setPolynomial(String polynomial) {
    this.polynomial = polynomial;
  }

  /**
  *Sets given split Terms array as object's splitT.
  *
  *@param splitT   the array of Terms entered for the split polynomial
  */
  public void setSplitTerms(Term[] splitT) {
    this.splitTerms = splitT;
  }

  /**
  *Sets given polynomial as term's polynomial and corresponding split Terms array as object's splitT.
  *
  *@param polynomial   the String entered for the polynomial
  *@param splitT   the array of Terms entered for the split polynomial
  */
  public void setAll(String polynomial, Term[] splitT) {
    this.polynomial = polynomial;
    this.splitTerms = splitT;
  }

  /**
  *Returns objects's polynomial as a String.
  *
  *@return  Returns object's polynomial
  */
  public String getPolynomial() {
    return polynomial;
  }

  /**
  *Returns polynomial's split terms as array of objects.
  *
  *@return  Returns polynomials splitTerms
  */
  public Term[] getSplitTerms() {
    return splitTerms;
  }

  /**
  *Splits entered polynomial into individual Term objects and stores in an array. From these Terms, respective coefficients and exponents can be obtained.
  *
  *@param polynomial  the String polynomial that will be split into terms
  */
  public void splitPolynomial(String polynomial) {
    String c, e = null;
    String[] terms = polynomial.replace(" ", "").split("(?=\\+|\\-)");
    splitTerms = new Term[terms.length];

    for (int i = 0; i < terms.length; i++) {
      splitTerms[i] = new Term();
      splitTerms[i].storeTerm(terms[i]);
    }
  }

  /**
  *Takes new entered term and compares to all terms in entered polynomial. Remove term from polynomial if it's a match.
  *
  *@param polynomial  the String polynomial that will be edited
  *@param term the String term that is being removed from the polynomial
  */
  public String removeTerm(Polynomial polynomial, String term) {
    //if term equals term in string polynonial, remove it
    String p = polynomial.getPolynomial();
    String finalPolynomial = "";
    String terms[] = p.replace(" ", "").replace("-", "+-").split("\\+");

    for (String t : terms) {
      if(!t.equals(term)) {
        finalPolynomial += t + " ";
      }
    }

    return finalPolynomial;
  }

  /**
  *Takes index of polynomial to remove, removes the current polynomial, and adds a placeholder at that index.
  *
  *@param polynomials  the Polynomial array that will be edited
  *@param index   the int index of the Polynomial object to be removed
  */
  public Polynomial[] removePolynomial(Polynomial[] polynomials, int index) {
    Polynomial p = new Polynomial();
    p.setPolynomial("");
    List<Polynomial> list = new ArrayList<Polynomial>(Arrays.asList(polynomials));
    list.remove(index);
    list.add(p);
    polynomials = list.toArray(polynomials);

    return polynomials;
  }
  
  /**
  *Method that takes entered polynomials and adds them together. Polynomials are split into individual terms, and their coefficients and exponents are stored. Coefficients are then grouped by exponent, summed, and formatted into the final polynomials output. 
  *
  *@param polynomials   the array containing all polynomials entered by the user
  *
  *@return  the String containing the summed polynomial
  */
  public String addPolynomials(Polynomial[] polynomials) {
    String finalPolynomial = "";
    int polynomialLength, length = 0;
    String longestString = null;
    
    Polynomial p = new Polynomial();
    
    String[] polys = new String[polynomials.length];
    List<Term> allTerms = new ArrayList<>();

    for (int h = 0; h < polynomials.length; h++) {
      polys[h] = polynomials[h].getPolynomial();
    }
    
    for (String s : polys) {
      if (s.length() > length) {
        length = s.length();
        longestString = s;
      }
    }

    p.setPolynomial(longestString);
    p.splitPolynomial(longestString);
    polynomialLength = p.getSplitTerms().length; 

    for (int i = 0; i < polynomials.length; i++) {
      allTerms.addAll(Arrays.asList(polynomials[i].getSplitTerms()));
    }
    
    Term[] all = new Term[allTerms.size()];
    all = allTerms.toArray(all); 
    List<Term> addTerms = new ArrayList<>();

    int maximum = allTerms.get(0).getExponent();
    for(int i = 1; i < allTerms.size(); i++) {
      if (maximum < allTerms.get(i).getExponent()) {
        maximum = allTerms.get(i).getExponent();
      }
    }

    for(int j = 0; j <= maximum; j++) {
      int newCoefficient = 0;
      List<Term> orderedTerms = new ArrayList<>();
      
      for (Term t : allTerms) {
        if (t.getExponent() == j) {
          orderedTerms.add(t);
        }
      }

      for(int k = 0; k < orderedTerms.size(); k++) {
        newCoefficient += orderedTerms.get(k).getCoefficient();
      }

      addTerms.add(new Term(newCoefficient, j));
    }

    List<Term> revAddTerms = new ArrayList<>();
    for (int m = addTerms.size() - 1; m >= 0; m--) {
      revAddTerms.add(addTerms.get(m));
    }
 
    for(int l = 0; l < revAddTerms.size(); l++) {
      if (revAddTerms.get(l).getCoefficient() == 0) {
        finalPolynomial += "";
      }
      else if (revAddTerms.get(l).getCoefficient() == 1) {
        if(revAddTerms.get(l).getExponent() == 0) {
          finalPolynomial += " + 1";
        }
        else if(revAddTerms.get(l).getExponent() == 1) {
          finalPolynomial += " + x";
        }
        else if(revAddTerms.get(l).getExponent() == maximum) {
          finalPolynomial += "x^" + revAddTerms.get(l).getExponent();
        }
        else {
          finalPolynomial += " + x^" + revAddTerms.get(l).getExponent();
        }
      }
      else if (!(revAddTerms.get(l).getCoefficient() == 1) && !(revAddTerms.get(l).getCoefficient() == 0)) {
        if(revAddTerms.get(l).getExponent() == maximum) {
          finalPolynomial += revAddTerms.get(l).getCoefficient() + "x^" + revAddTerms.get(l).getExponent();
        }
        else if (revAddTerms.get(l).getExponent() == 1) {
          finalPolynomial += " + " + revAddTerms.get(l).getCoefficient() + "x";
        }
        else if (revAddTerms.get(l).getExponent() == 0) {
          finalPolynomial += " + " + revAddTerms.get(l).getCoefficient();
        }
        else {
          finalPolynomial += " + " + revAddTerms.get(l).getCoefficient() + "x^" + revAddTerms.get(l).getExponent();
        }
      }
    }

    return finalPolynomial;
   
  }

  /**
  *Takes new entered term and compares exponent to exponents of all terms in entered polynomial. Add coefficients if exponents are the same.
  *
  *@param polynomial  the String polynomial that will be added to
  *@param term the String term that is being added to the polynomial
  */
  public String addTerm(String polynomial, String term) {
    Polynomial p = new Polynomial();   
    String editP = polynomial + " + " + term;

    p.setPolynomial(editP);
    String[] terms = editP.replace(" ", "").split("(?=\\+|\\-)");

    Polynomial[] poly = new Polynomial[terms.length];

    for (int i = 0; i < terms.length; i++) {
      poly[i] = new Polynomial();                  poly[i].setPolynomial(terms[i]);
      poly[i].splitPolynomial(terms[i]);
    }

    return p.addPolynomials(poly);
  }
  
  /**
  *toString() method that outputs the coefficient and exponent of each  term in an entered polynomial.
  *
  *@return  the string containing each term's coefficient and exponent
  */
  @Override
  public String toString() {
    Polynomial polynomial = new Polynomial();
    return polynomial.getPolynomial();
  }
  
  /**
  *equals() method that compares one polynomial to another to assess equality.
  *
  *@param object  reference object to which the current object needs to compare
  *
  *@return  returns false if not equal and true if polynomials are equal  
  */
  @Override
  public boolean equals(Object other) {
    if(other == null || other instanceof Polynomial) {
      return false;
    }

    Polynomial otherPolynomial = (Polynomial)other;

    return this.polynomial.equals(otherPolynomial.polynomial);
  }
  
}