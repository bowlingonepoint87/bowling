package com.onepoint.bowling;

import static com.onepoint.bowling.utils.Constantes.DEFAULT_VALUE;
import static com.onepoint.bowling.utils.Constantes.MISS;
import static com.onepoint.bowling.utils.Constantes.SPARE;
import static com.onepoint.bowling.utils.Constantes.STRIKE;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RollTest {

	@Test
	void isStrikeTest() {
		// Given
		Roll roll = new Roll(STRIKE, MISS);

		// When
		boolean strike = roll.isStrike();
		boolean spare = roll.isSpare();

		// Then
		Assertions.assertTrue(strike);
		Assertions.assertFalse(spare);
	}

	@Test
	void isSpareTest() {
		// Given
		Roll roll = new Roll('1', SPARE);

		// When
		boolean strike = roll.isStrike();
		boolean spare = roll.isSpare();

		// Then
		Assertions.assertFalse(strike);
		Assertions.assertTrue(spare);
	}

	@Test
	void rollBetweenZeroAndNine() {
		// Given
		Roll roll = new Roll('5', '1');

		// When
		boolean strike = roll.isStrike();
		boolean spare = roll.isSpare();

		// Then
		Assertions.assertFalse(strike);
		Assertions.assertFalse(spare);
	}

	@Test
	void getNbPinsDownTest() {
		// Given
		Roll strikeRoll = new Roll(STRIKE, MISS);
		Roll spareRoll = new Roll('5', SPARE);
		Roll spareRollWithMiss = new Roll(MISS, SPARE);
		Roll invalidScoreCharRoll = new Roll('*', '2');
		Roll outBoundScoreRoll = new Roll('8', '5');
		Roll nominalRoll = new Roll('1', '1');

		// When
		int strikeScore = strikeRoll.getNbPinsDown();
		int intScore = spareRoll.getNbPinsDown();
		int missFirst = spareRollWithMiss.getNbPinsDown();
		int invalidScoreChar = invalidScoreCharRoll.getNbPinsDown();
		int outBoundScore = outBoundScoreRoll.getNbPinsDown();
		int nominalRollScore = nominalRoll.getNbPinsDown();

		// Then
		Assertions.assertTrue(strikeScore == 10);
		Assertions.assertTrue(intScore == 10);
		Assertions.assertTrue(missFirst == 10);
		Assertions.assertTrue(invalidScoreChar == DEFAULT_VALUE);
		Assertions.assertTrue(outBoundScore == DEFAULT_VALUE);
		Assertions.assertTrue(nominalRollScore == 2);
	}

	@Test
	void getScoreForFirstRollTest() {
		// Given
		Roll strikeRoll = new Roll(STRIKE, MISS);
		Roll spareRoll = new Roll('5', SPARE);
		Roll spareRollWithMiss = new Roll(MISS, SPARE);
		Roll invalidScoreCharRoll = new Roll('*', '1');

		// When
		int strikeScore = strikeRoll.getScoreForFirstRoll();
		int intScore = spareRoll.getScoreForFirstRoll();
		int missFirst = spareRollWithMiss.getScoreForFirstRoll();
		int invalidScoreChar = invalidScoreCharRoll.getScoreForFirstRoll();

		// Then
		Assertions.assertTrue(strikeScore == 10);
		Assertions.assertTrue(intScore == 5);
		Assertions.assertTrue(missFirst == 0);
		Assertions.assertTrue(invalidScoreChar == DEFAULT_VALUE);

	}

	@Test
	void getScoreForSecodRollTest() {
		// Given
		Roll strikeRoll = new Roll('8', STRIKE);
		Roll spareRoll = new Roll('5', SPARE);
		Roll spareRollWithMiss = new Roll('4', MISS);
		Roll invalidScoreCharRoll = new Roll('2', '*');

		// When
		int strikeScore = strikeRoll.getScoreForSecondRoll();
		int intScore = spareRoll.getScoreForSecondRoll();
		int missForLast = spareRollWithMiss.getScoreForSecondRoll();
		int invalidScoreChar = invalidScoreCharRoll.getScoreForSecondRoll();

		// Then
		Assertions.assertTrue(strikeScore == DEFAULT_VALUE);
		Assertions.assertTrue(intScore == 10);
		Assertions.assertTrue(missForLast == 0);
		Assertions.assertTrue(invalidScoreChar == DEFAULT_VALUE);

	}

	@Test
	void isValidStrikeTest() {
		// Given
		Roll strikeRoll = new Roll(STRIKE, MISS);
		Roll spareRoll = new Roll('5', SPARE);
		Roll spareRollWithMiss = new Roll(MISS, SPARE);
		Roll outBoundScoreRoll = new Roll('8', '9');
		Roll invalidScoreCharRoll = new Roll('*', '1');

		// When
		boolean strike = strikeRoll.isValid();
		boolean spare = spareRoll.isValid();
		boolean spareWithMiss = spareRollWithMiss.isValid();
		boolean outBoundScore = outBoundScoreRoll.isValid();
		boolean invalidScoreChar = invalidScoreCharRoll.isValid();

		// Then
		Assertions.assertTrue(strike);
		Assertions.assertTrue(spare);
		Assertions.assertTrue(spareWithMiss);
		Assertions.assertFalse(outBoundScore);
		Assertions.assertFalse(invalidScoreChar);

	}

}