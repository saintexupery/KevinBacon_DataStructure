/*
 * Six Degrees of Kevin Bacon
 *
 * Bongki Moon (bkmoon@snu.ac.kr), Oct/23/2014.
*/

import java.lang.*;
import java.util.*;
import java.io.*;

public class MovieNet {

  static final String KevinBacon = "Bacon, Kevin";

  protected LinkedList<String[]> movielines;

  // Each instance of movielines is String[] such that
  //	String[0] = title of movie
  //	String[1..n-1] = list of actors
  public MovieNet(LinkedList<String[]> movielines) {
    this.movielines = movielines;
    // System.out.println(movielines.get(0));
  }	// Constructor

/*============================================================================*/

  // [Q1]
  public String[] moviesby(String[] actors) {
    String[] answer = new String[this.movielines.size()];

    for (int i = 0; i < this.movielines.size(); i++) {
      int check = 0;

      for (int j = 0; j < actors.length; j++) {

        if (Arrays.asList(this.movielines.get(i)).contains(actors[j]) == false) {
          continue;
        } else {
          check += 1;
        }

        if (j == actors.length - 1 && check == actors.length) {
          // System.out.println(this.movielines.get(i)[0]);
          answer[i] = this.movielines.get(i)[0];
        }
      }
    }
    if (answer[0] == null) {
      System.out.println("hello");
    } else {
      return answer;
    }
    return null;
  }

  // // [Q2]
  // public String[] castin(String[] titles) { }
  //
  // // [Q3]
  // public String[] pairmost(String[] actors) { }
  //
  // // [Q4]
  // public int Bacon(String actor) { }
  //
  // // [Q5]
  // public int distance(String src, String dst) { }
  //
  // // [Q6]
  // public int npath(String src, String dst) { }
  //
  // // [Q7]
  // public String[] apath(String src, String dst) { }
  //
  // // [Q8]
  // public int eccentricity(String actor) { }
  //
  // // [Q9]
  // public float closeness(String actor) { }

/*============================================================================*/

}
