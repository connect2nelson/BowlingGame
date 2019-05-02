package com.example;

import static com.example.BowlingGameContants.ROLL_LIMIT;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.stream.IntStream;
import org.junit.Before;
import org.junit.Test;

public class BowlingGameTest {

  private BowlingGame bowlingGame;

  @Before
  public void setUp() {
    bowlingGame = new BowlingGame();
  }

  @Test
  public void whenAllRollsGoIntoGutter_ThenTotalScoreShouldBeZero() {
    IntStream.range(0, ROLL_LIMIT).forEach(i -> bowlingGame.roll(0));
    assertEquals(0, bowlingGame.getScore());
  }

  @Test
  public void whenEveryRollScores1Pin_ThenTotalScoreShouldBeTwenty() {
    IntStream.range(0, ROLL_LIMIT).forEach(i -> bowlingGame.roll(1));
    assertEquals(20, bowlingGame.getScore());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldNotAllowMoreThan10PinsToBeRolled() {
    bowlingGame.roll(11);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldNotAllowNegativePinsForBeingRolled() {
    bowlingGame.roll(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldNotAllowMoreThan10PinsToBeScoredInTwoConsecutiveRolls() {
    bowlingGame.roll(6);
    bowlingGame.roll(6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldNotAllowAStrikeAfterAScoringRoll() {
    bowlingGame.roll(5);
    bowlingGame.roll(10);
  }

  @Test(expected = NoMoreRollLeft.class)
  public void shouldNotAllowNoOfRollsToExceedMaxRollingLimit() {
    IntStream.range(0, ROLL_LIMIT + 1).forEach(i -> bowlingGame.roll(1));
  }

  @Test
  public void shouldApplyRollLimitOnlyForAFrame() {
    BowlingGame bowlingGame = new BowlingGame();
    addRolls(bowlingGame, new int[]{1, 5, 6, 2});

    assertEquals(14, bowlingGame.getScore());

    bowlingGame = new BowlingGame();
    addRolls(bowlingGame, new int[]{0, 10, 6, 4});

    assertEquals(30, bowlingGame.getScore());

    bowlingGame = new BowlingGame();
    addRolls(bowlingGame, new int[]{10, 0, 6, 4});
    assertEquals(30, bowlingGame.getScore());
  }

  @Test
  public void shouldAllowTwoStrikesConsecutively() {
    BowlingGame bowlingGame = new BowlingGame();
    addRolls(bowlingGame, new int[]{10, 10});
    assertEquals(30, bowlingGame.getScore());
  }

  @Test
  public void shouldAddNextFrameScoreToTotalScore_WhenPlayerScoredAStrikeInFirstRoll() {
    BowlingGame bowlingGame = new BowlingGame();
    addRolls(bowlingGame, new int[]{10, 3, 6});
    assertEquals(28, bowlingGame.getScore());
  }

  private void addRolls(BowlingGame game, int[] pinsRolled) {
    Arrays.stream(pinsRolled).forEach(game::roll);
  }

}