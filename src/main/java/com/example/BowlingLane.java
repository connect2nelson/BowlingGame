package com.example;

import static com.example.BowlingGameContants.ROLL_LIMIT;

import java.util.Objects;

public class BowlingLane {

  private BowlingGame game;

  public void addPlayer(String playerName) {
    if (Objects.isNull(game)) {
      game = new BowlingGame();
    }
  }

  public int getNoOfPlayers() {
    return 1;
  }

  public int getScoreForPlayer(String playerName) {
    return game.getScore();
  }

  public void roll(int noOfPins) {
    game.roll(noOfPins);
  }

  public boolean isGameFinished() {
    return game.getRolls() >= ROLL_LIMIT;
  }
}
