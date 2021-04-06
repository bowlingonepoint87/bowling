package com.onepoint.bowling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static com.onepoint.bowling.utils.Constantes.*;

class BowlingTest {

	private Bowling bowling;

	@BeforeEach
	void init() {
		bowling = new Bowling();
	}

	@Test
	void bowlingWithoutStrikeOrSpareTest() {
		// Given
		mockRolls(10, '5', '2');

		// When
		int score = bowling.getScore();
		int intermediate = bowling.getScoreAfterRolls(3);
		int inValidIntermediate = bowling.getScoreAfterRolls(25);
		boolean valid = bowling.isValid();

		// Then
		Assertions.assertEquals(70, score);
		Assertions.assertEquals(21, intermediate);
		Assertions.assertEquals(DEFAULT_VALUE, inValidIntermediate);
		Assertions.assertTrue(valid);
	}

	@Test
	void bowlingWithStrikeTest() {
		// Given
		mockStrike();
		mockRolls(9, '3', '1');

		// When
		int score = bowling.getScore();
		int intermediate = bowling.getScoreAfterRolls(4);
		boolean valid = bowling.isValid();

		// Then
		Assertions.assertEquals(50, score);
		Assertions.assertEquals(26, intermediate);
		Assertions.assertTrue(valid);
	}

	@Test
	void bowlingWithSpareTest() {
		// Given
		mockSpare();
		mockRolls(9, '6', '3');

		// When
		int score = bowling.getScore();
		int intermediate = bowling.getScoreAfterRolls(3);
		boolean valid = bowling.isValid();

		// Then
		Assertions.assertEquals(97, score);
		Assertions.assertEquals(34, intermediate);
		Assertions.assertTrue(valid);
	}

	@Test
	void bowlingWithSpareAtLastTest() {
		// Given
		mockRolls(9, '2', '2');
		mockSpare();
		mockRoll('5', MISS);

		// When
		int score = bowling.getScore();
		int intermediate = bowling.getScoreAfterRolls(7);
		boolean valid = bowling.isValid();

		// Then
		Assertions.assertEquals(51, score);
		Assertions.assertEquals(28, intermediate);
		Assertions.assertTrue(valid);
	}

	@Test
	void bowlingWithStrikeAtLastTest() {
		// Given
		mockRolls(9, '2', '3');
		mockStrike();
		mockRoll('4', '1');

		// When
		int score = bowling.getScore();
		int intermediate = bowling.getScoreAfterRolls(2);
		boolean valid = bowling.isValid();

		// Then
		Assertions.assertEquals(60, score);
		Assertions.assertEquals(10, intermediate);
		Assertions.assertTrue(valid);
	}

	@Test
	void bowlingWithStrikeAtLastAndStrikeBonusTest() {
		// Given
		mockRolls(9, '8', MISS);
		mockStrike();
		mockStrike();
		mockStrike();

		// When
		int score = bowling.getScore();
		int intermediate = bowling.getScoreAfterRolls(6);
		boolean valid = bowling.isValid();

		// Then
		Assertions.assertEquals(102, score);
		Assertions.assertEquals(48, intermediate);
		Assertions.assertTrue(valid);
	}

	@Test
	void bowlingWithStrikeAtLastAndSpareBonusTest() {
		// Given
		mockRolls(9, '4', '5');
		mockStrike();
		mockSpare();

		// When
		int score = bowling.getScore();
		int intermediate = bowling.getScoreAfterRolls(4);
		boolean valid = bowling.isValid();

		// Then
		Assertions.assertEquals(101, score);
		Assertions.assertEquals(36, intermediate);
		Assertions.assertTrue(valid);
	}

	@Test
	void bowlingWithOnlyStrikesTest() {
		// Given
		mockRolls(12, STRIKE, MISS);

		// When
		int score = bowling.getScore();
		int intermediate = bowling.getScoreAfterRolls(5);
		boolean valid = bowling.isValid();

		// Then
		Assertions.assertEquals(300, score);
		Assertions.assertEquals(150, intermediate);
		Assertions.assertTrue(valid);
	}

	@Test
	void isValidSizeError() {
		// Given
		mockRolls(25, STRIKE, MISS);

		// When
		boolean valid = bowling.isValid();

		// Then
		Assertions.assertFalse(valid);
	}

	@Test
	void isValidRollsError() {
		// Given --> sum > 10
		mockRolls(NB_ROLLS, '7', '9');

		// When
		boolean valid = bowling.isValid();

		// Then
		Assertions.assertFalse(valid);
	}

	@Test
	void isValid12RollsButNoStrikesTest() {
		// Given
		mockRolls(12, MISS, SPARE);

		// When
		boolean valid = bowling.isValid();

		// Then
		Assertions.assertFalse(valid);
	}

	@Test
	void isValid11RollsButNoStrikesTest() {
		// Given
		mockRolls(11, MISS, SPARE);

		// When
		boolean valid = bowling.isValid();

		// Then
		Assertions.assertTrue(valid);
	}
	
	
	

	private void mockStrike() {
		mockRoll(STRIKE, MISS);
	}

	private void mockSpare() {
		mockRoll(MISS, SPARE);
	}

	private void mockRolls(int nbRolls, char firstRoll, char secondRoll) {
		IntStream.rangeClosed(1, nbRolls).forEach(i -> mockRoll(firstRoll, secondRoll));
	}

	private void mockRoll(char firstRoll, char secondRoll) {
		bowling.playRoll(new Roll(firstRoll, secondRoll));
	}

}