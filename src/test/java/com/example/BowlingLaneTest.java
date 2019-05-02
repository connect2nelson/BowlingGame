package com.example;

import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;
import org.junit.Before;
import org.junit.Test;

public class BowlingLaneTest {

  private BowlingLane bowlingLaneWithOnePlayer;
  public static final int ROLL_LIMIT = 20;


  @Before
  public void setUp() throws Exception {
    bowlingLaneWithOnePlayer = new BowlingLane();
    bowlingLaneWithOnePlayer.addPlayer("Player1");
  }

  @Test
  public void shouldCreateABowingLaneAndAllowAPlayerToBeAdded() {
    assertEquals(1, bowlingLaneWithOnePlayer.getNoOfPlayers());
  }

  @Test
  public void shouldFetchScoreForThePlayer() {
    assertEquals(0, bowlingLaneWithOnePlayer.getScoreForPlayer("Player1"));
  }

  @Test
  public void shouldFetchScoreAfterARollIsDone() {
    bowlingLaneWithOnePlayer.roll(1);
    assertEquals(1, bowlingLaneWithOnePlayer.getScoreForPlayer("Player1"));
  }

  @Test
  public void whenPlayerHasNotFinishedHisRolls_ThenBowlingLaneShouldSetGameStateAsOngoing() {
    IntStream.range(0, 1).forEach(i -> bowlingLaneWithOnePlayer.roll(0));
    assertEquals(false, bowlingLaneWithOnePlayer.isGameFinished());
  }

  @Test
  public void whenPlayerPlaysAllRolls_ThenBowlingLaneShouldSetGameStateAsFINISHED() {
    IntStream.range(0, ROLL_LIMIT).forEach(i -> bowlingLaneWithOnePlayer.roll(0));
    assertEquals(true, bowlingLaneWithOnePlayer.isGameFinished());
  }

}
