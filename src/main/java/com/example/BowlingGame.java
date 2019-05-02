package com.example;

import static com.example.BowlingGameContants.STRIKE_PINS;
import static com.example.BowlingGameContants.ROLL_LIMIT;

public class BowlingGame {

  private int previousRollScore;
  private int rolls = 0;
  private int[] scoresPerRoll = new int[20];

  public BowlingGame() {
  }

  public void roll(int noOfPins) {

    if (rolls >= ROLL_LIMIT) {
      throw new NoMoreRollLeft();
    }

    if (noOfPins > STRIKE_PINS || noOfPins < 0) {
      throw new IllegalArgumentException();
    }

    if (isSecondRollOfCurrentFrame()) {
      checkRollsExceedMaxPinsPerFrame(noOfPins);
    } else {
      scoresPerRoll[rolls] = noOfPins;
      startNextFrameOnStrike(noOfPins);
      rolls++;
      previousRollScore = noOfPins;
      return;
    }
    scoresPerRoll[rolls] = noOfPins;
    previousRollScore = noOfPins;
    rolls++;
  }

  private boolean isSecondRollOfCurrentFrame() {
    return rolls % 2 == 1;
  }

  private void startNextFrameOnStrike(int noOfPins) {

    if (noOfPins == STRIKE_PINS) {
      rolls ++;
    }
  }

  private void checkRollsExceedMaxPinsPerFrame(int noOfPins) {
    if (previousRollScore + noOfPins > STRIKE_PINS) {
      throw new IllegalArgumentException();
    }
  }

  public int getScore() {
    boolean strikeObserved = false;
    int score = 0;

    for (int i = 0; i < scoresPerRoll.length; i++) {

      if ( i % 2 == 0 ) {
        System.out.println("SAw first roll");
        if (scoresPerRoll[i] == STRIKE_PINS) {
          score += scoresPerRoll[i+2] + scoresPerRoll[i+3];
        }
      }

      System.out.println("score = " + score);
      score += scoresPerRoll[i];
    }
    return score;
  }

  public int getRolls() {
    return rolls;
  }
}
