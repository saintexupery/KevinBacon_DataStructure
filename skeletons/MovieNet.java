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

  // With node;
  protected Map<String, List<ActorNode>> graph;
  protected Set<String> keySet;

  // Each instance of movielines is String[] such that
  //	String[0] = title of movie
  //	String[1..n-1] = list of actors
  public MovieNet(LinkedList<String[]> movielines) {
    this.movielines = movielines;

    //With node;
    this.graph = new HashMap<String, List<ActorNode>>();
    this.keySet = new HashSet<String>();

    //set graph
    ListIterator<String[]> listIterator = this.movielines.listIterator();
    while (listIterator.hasNext()) {
      String[] current = listIterator.next();
      String currentTitle = current[0];

      for (int i = 1; i < current.length; i++) {
        keySet.add(current[i]);
      }

      for (String key : keySet) {
        String currentKey = key;

        if (graph.get(currentKey) != null) {
          for (String anotherKey : keySet) {
            if (anotherKey.equals(currentKey) == false) {
              Boolean contains = false;
              List<ActorNode> currentList = graph.get(currentKey);

              if (currentList.isEmpty() == false) {
                for (ActorNode node : currentList) {
                  if (node.getActor().equals(anotherKey) == true) {
                    node.getMovies().add(currentTitle);
                    contains = true;
                    break;
                  }
                }

                if (contains == false) {
                  ActorNode newNode = new ActorNode(anotherKey);
                  newNode.getMovies().add(currentTitle);
                  graph.get(currentKey).add(newNode);
                }
              }
            }
          }
        } else {
          List<ActorNode> newList = new ArrayList<ActorNode>();

          for (String anotherKey : keySet) {
            if (anotherKey.equals(currentKey) == false) {
              ActorNode newNode = new ActorNode(anotherKey);
              newNode.getMovies().add(currentTitle);
              newList.add(newNode);
            }
          }
          graph.put(currentKey, newList);
        }
      }
      keySet.clear();
    }
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
    String[] answerList = new String[2];
    List<ActorNode> actorsNodes = new ArrayList<ActorNode>();
    int max = 0;

    for (String actor : actors) {
      List<ActorNode> collaborateList = this.graph.get(actor);
      for(ActorNode node : collaborateList) {
        if (Arrays.asList(actors).contains(node.getActor()) == true && node.getMovies().size() > max) {
          max = node.getMovies().size();
          answerList[0] = actor;
          answerList[1] = node.getActor();
        }
      }
    }

    // return asnwer with type of String[] or null;
    if (max != 0) {
      return answerList;
    } else {
      return null;
    }
  }

   // [Q4]
   public int Bacon(String actor) {
    // return 0 if input actor is Kevin Bacon himself;
    // I hope there is no testcase with null actor value;
    if (actor.equals(KevinBacon) == true) {
      return 0;
    } else if (actor == null) {
      return -1;
    }

    if (graph.containsKey(actor) == true) {
      Queue<ActorNode> queue = new LinkedList<ActorNode>();
      ActorNode currentActor = new ActorNode(actor);
      currentActor.setDistance(0);
      queue.add(currentActor);
      Set<String> alreadyVisit = new HashSet<String>();
      alreadyVisit.add(actor);

      while (queue.isEmpty() == false) {
        ActorNode current = queue.remove();
        String currentActorName = current.getActor();

        if (currentActorName.equals(KevinBacon) == true) {
          return current.getDistance();
        } else {
          for (ActorNode node : graph.get(currentActorName)) {
            node.setDistance(current.getDistance() + 1);
            node.setPrevActor(current);

            if (alreadyVisit.contains(node.getActor()) == false) {
              queue.add(node);
              alreadyVisit.add(node.getActor());
            }
          }
        }
      }
    }

    return -1;
   }
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

class ActorNode {
  private String actor;
  private ArrayList<String> movies = new ArrayList<String>();
  private int distance;
  private ActorNode prevActor = null;

  public ActorNode(String actor) {
    this.actor = actor;
  }

  public String getActor() {
    return this.actor;
  }

  public void setDistance(int distance) {
    this.distance = distance;
  }

  public int getDistance() {
    return this.distance;
  }

  public ArrayList<String> getMovies() {
    return this.movies;
  }

  public void setPrevActor(ActorNode node) {
    this.prevActor = node;
  }

  public ActorNode getPrevActor() {
    return this.prevActor;
  }
}