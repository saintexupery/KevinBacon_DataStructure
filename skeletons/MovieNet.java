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
    List<String> answerList = new ArrayList<String>();

    for (int i = 0; i < this.movielines.size(); i++) {
      int check = 0;

      for (int j = 0; j < actors.length; j++) {

        if (Arrays.asList(this.movielines.get(i)).contains(actors[j]) == false) {
          break;
        } else {
          check += 1;
        }

        if (j == actors.length - 1 && check == actors.length) {
          // System.out.println(this.movielines.get(i)[0]);
          // answer[i] = this.movielines.get(i)[0];
          answerList.add(this.movielines.get(i)[0]);
        }
      }
    }

    // Convert ArrayList to StringArray with codes below;
    // http://hashcode.co.kr/questions/153/arrayliststring%EC%9D%84-string-array%EB%A1%9C-%EB%B0%94%EA%BE%B8%EB%8A%94-%EB%B2%95
    // check above site;
    if (answerList.size() != 0) {
      String[] answerArr = new String[answerList.size()];
      answerArr = answerList.toArray(answerArr);
      return answerArr;
    } else {
      return null;
    }
  }

  // [Q2]
  public String[] castin(String[] titles) {
    List<String> answerList = new ArrayList<String>();
    List<String[]> movieList = new ArrayList<String[]>();

    ListIterator<String[]> listIterator = this.movielines.listIterator();

    // case of that input is null;
    if (titles.length == 0) {
      return null;
    }

    // add titles with actors to movieList;
    while (listIterator.hasNext()) {
      String[] current = listIterator.next();

      for (int i = 0; i < titles.length; i++) {
        if (current[0] == titles[i]) {
          movieList.add(current);
          break;
        }
      }
    }


    return null;
  }

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
