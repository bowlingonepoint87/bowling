package com.onepoint.bowling;

import static com.onepoint.bowling.utils.Constantes.*;

class Roll {

	private char scoreTryOne;
	private char scoreTryTwo;

	Roll(char scoreTryOne, char scoreTryTwo) {
		this.scoreTryOne = scoreTryOne;
		this.scoreTryTwo = scoreTryTwo;
	}

	boolean isStrike() {
		return scoreTryOne == STRIKE;
	}

	boolean isSpare() {
		return !isStrike() && scoreTryTwo == SPARE;
	}

	int getNbPinsDown() {
		if (isValid()) {
			if (isStrike() || isSpare())
				return NB_PINS;
			else
				return getScoreForFirstRoll() + getScoreForSecondRoll();
		}

		else
			return DEFAULT_VALUE;
	}

	int getScoreForFirstRoll() {
		if (MAX_VALUE >= scoreTryOne && MIN_VALUE <= scoreTryOne) {
			int res = scoreTryOne - STARTING_VALUE;
			return res;
		}
		switch (scoreTryOne) {
		case STRIKE:
			return 10;
		case MISS:
			return 0;
		default:
			return DEFAULT_VALUE;
		}

	}

	int getScoreForSecondRoll() {
		if (MAX_VALUE >= scoreTryTwo && MIN_VALUE <= scoreTryTwo) {
			int res = scoreTryTwo - STARTING_VALUE;
			return res;
		}
		switch (scoreTryTwo) {
		case SPARE:
			return 10;
		case MISS:
			return 0;
		default:
			return DEFAULT_VALUE;
		}
	}

	// check for valid role
	boolean isValid() {
		return (scoreTryOne == STRIKE || ((scoreTryOne == MISS || scoreTryOne <= MAX_VALUE && scoreTryOne >= MIN_VALUE)
				&& ((scoreTryTwo == SPARE) || ((scoreTryTwo == MISS || scoreTryTwo <= MAX_VALUE && scoreTryTwo >= MIN_VALUE)
						&& getScoreForFirstRoll() + getScoreForSecondRoll() <= NB_PINS))));
	}

}