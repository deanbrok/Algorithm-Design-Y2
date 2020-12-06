package Exam.ResitMidterm2019.Ian;

//import java.io.*;
import java.util.*;

/**
 * WARNING: The spec tests are not necessarily equal to your grade!
 * You can use them help you test for the correctness of your algorithm,
 * but the final grade is determined by a manual inspection of your implementation.
 */
class Tournament {

  /**
   *  You should implement this method.
   *  @param n the number of participants.
   *  @param a an array of size n+1, containing the account creation a_1 through a_n. You should ignore a[0].
   *  @param b an array of size n+1, containing the interview times b_1 through b_n. You should ignore b[0].
   *  @return The minimum latest end time.
   */
  public static int boardgameTime(int n, int[] a, int[] b) {
    //Sort the participants in descending order of account creation time
    Participant[] participants = new Participant[n];
    for (int i = 1; i <=n; i++) {
      participants[i-1] = new Participant(a[i], b[i]);
    }
    Arrays.sort(participants);
    
    //Set the universal time to 0
    int time = 0;
    
    //For every participant, check the interview and account creation times
    for (Participant p: participants) {
      //If the time by which account creation is finished is greater than the current time, set it accordingly
      //i.e. The next participant must wait if the previous participant is not finished
      time = Integer.max(time, p.accCreation);
      //Add the interview time to the current time
      time += p.interview;
    }
    return time;
  }
}

//Class representing a participant defined by account creation time and interview time
class Participant implements Comparable<Participant> {
  int accCreation;
  int interview;
  
  public Participant(int accCreation, int interview) {
    this.accCreation = accCreation;
    this.interview = interview;
  }
  
  @Override
  public int compareTo(Participant other) {
    return Integer.compare(this.accCreation, other.accCreation);
  }
}
