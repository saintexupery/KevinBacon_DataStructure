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
  protected List<String[]> newMovielines;
  // protected HashMap<String, ArrayList<String>> movieMap;

  // Each instance of movielines is String[] such that
  //	String[0] = title of movie
  //	String[1..n-1] = list of actors
  public MovieNet(LinkedList<String[]> movielines) {
    this.movielines = movielines;
    this.newMovielines = new ArrayList<String[]>();
    // this.movieMap = new HashMap<String, ArrayList<String>>();

    // movielines is converted to ArrayList from String List;
    ListIterator<String[]> listIterator = this.movielines.listIterator();
    while (listIterator.hasNext()) {
      String[] current = listIterator.next();
      newMovielines.add(current);

      // if (current.equals(this.movielines.get(0)) == false) {
      //   ArrayList<String> currentList = new ArrayList<String>(Arrays.asList(current));
      //   // System.out.println("class: " + current[0].getClass().getSimpleName());
      //   // System.out.println("current value: " + current[0]);
      //   movieMap.put(current[0], );
      // } else {
      //   continue;
      // }
    }

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
    List<String> deleteActors = new ArrayList<String>();
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
        if (current[0].equals(titles[i])) {
          movieList.add(current);
          break;
        }
      }
    }

    // add all actors from first movie to answerList;
    for (int i = 1; i < movieList.get(0).length; i++) {
      answerList.add(movieList.get(0)[i]);
    }

    // if there is not an actor in the next movies, remove the actor from answerList;
    for (int i = 1; i < movieList.size(); i++) {
      for (int j = 0; j < answerList.size(); j++) {
        if (Arrays.asList(movieList.get(i)).contains(answerList.get(j)) == false) {
          deleteActors.add(answerList.get(j));
        }
      }

      for (int k = 0; k < deleteActors.size(); k++) {
        answerList.remove(deleteActors.get(k));
      }
    }

    // return asnwer with type of String[] or null;
    if (answerList.size() != 0) {
      String[] answerArr = new String[answerList.size()];
      answerArr = answerList.toArray(answerArr);
      return answerArr;
    } else {
      return null;
    }
  }

  // [Q3]
  public String[] pairmost(String[] actors) {

    // making combinations with big o of n**2, needs to be modified;
    ArrayList<String[]> combinations = new ArrayList<String[]>();

    for (int i = 0; i < actors.length; i++) {
      for (int j = 0; j < actors.length; j++) {
        if (i != j) {
          String[] a = {actors[i], actors[j]};
          combinations.add(a);
        }
      }
    }

    // regex instead of using contains;
    // HashMap<String[], Integer> map = new HashMap<String[], Integer>();

    for (int i = 0; i < combinations.size(); i++) {
      // Integer key = 0;

      for (String[] movie : this.movielines) {
        if (Arrays.asList(movie).contains(combinations.get(i)[0]) == true) {
          // key += 1;
          System.out.println("Yes!!");
        }
        // Pattern p = Pattern.compile(combinations.get(i));
        // boolean found = false;
        // for (String s : this.newMovielines.get(j)) {
        //   if (p.matcher(s).find()) {
        //     found = true;
        //     break;
        //   }
        // }
        // System.out.println(movie);
      }
    }
    return null;
  }
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
