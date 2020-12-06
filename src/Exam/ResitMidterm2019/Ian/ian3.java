package Exam.ResitMidterm2019.Ian;

//import java.io.*;
import java.util.*;

/**
 * WARNING: The spec tests are not necessarily equal to your grade!
 * You can use them help you test for the correctness of your algorithm,
 * but the final grade is determined by a manual inspection of your implementation.
 */
class Gringotts {

  /**
   * You should implement this method.
   * @param n the number of cards
   * @param cards the cards at indices 1 through n, note that you should ignore index 0!
   * @return a card that is equivalent to the majority, or null if no such card exists.
   */
  public static Card bankFraud(int n, Card[] cards) {
    //Instantiate a list of triples which stores current count of duplicates, index and the card
    List<Triple> allCards = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      allCards.add(new Triple(1, i-1, cards[i]));
    }
    
    //Call helper method
    Triple res = findCard(allCards);
    
    //If no card was equivalent to majority return null
    if (res == null) return null;
    //Return card equivalent to majority if such a card exists
    return res.card;
  }
  
  /**
   * Recursively finds the triple equivalent to majority in the given list
   * @param cards the list of triples for which to find majority
   */
  static Triple findCard(List<Triple> cards) {
    //If only one card in list, that card composes the majority
    if (cards.size() == 1) return cards.get(0);
    //If there are two cards in the list, a card composes the majority iff it is equal to the other card
    if (cards.size() == 2) {
      Triple first = cards.get(0);
      if (first.card.isEquivalent(cards.get(1).card)) {
        //If equivalent, set the count of equivalent cards to two
        first.setCnt(2);
        return first;
      }
      return null;
    }
    
    //Split list of cards into two lists of equal size
    int m = cards.size() / 2;
    List<Triple> half_one = cards.subList(0, m);
    List<Triple> half_two = cards.subList(m, cards.size());
    
    //Find the majority card in the first half
    Triple left = findCard(half_one);
    if (left != null) {
      //Compare the left card to every card in the second half and increment count if equivalent is found
      for (Triple curr: half_two) {
        if (left.card.isEquivalent(curr.card)) left.setCnt(left.cnt + 1);
      }
      //If the count of the left card composes majority, return the left card
      if (left.cnt > m) return left;
    }
    
    //Find the majority card in the right half
    Triple right = findCard(half_two);
    if (right != null) {
      //Compare the right card to every card in the first half and increment count if equivalent is found
      for (Triple curr: half_one) {
        if (right.card.isEquivalent(curr.card)) right.setCnt(right.cnt + 1);
      }
      //If the count of the right card composes majority, return the right card
      if (right.cnt > m) return right;
    }
    
    //If no card is equivalent to majority, return null
    return null;
  }

  /*
     A class you may find useful to keep track of three pieces of data together.
     Although our reference solution uses it, there is no obligation to use it!
     */
  static class Triple {

    int cnt;

    int index;

    Card card;
    
    public Triple(int cnt, int index, Card card) {
      this.cnt = cnt;
      this.index = index;
      this.card = card;
    }
    
    public void setCnt(int cnt) {
      this.cnt = cnt;
    }
  }
}

interface Card {

  boolean isEquivalent(Card other);
}

class SpecTestCard implements Card {

  public static int counter = 0;

  int id;

  Set<Integer> equivalent;

  public SpecTestCard(int id, Set<Integer> equivalent) {
    this.id = id;
    this.equivalent = equivalent;
  }

  @Override
  public boolean isEquivalent(Card other) {
    counter++;
    if (other == this) {
      return true;
    }
    if (other instanceof SpecTestCard) {
      return this.equivalent.contains(((SpecTestCard) other).id);
    }
    return false;
  }
}
