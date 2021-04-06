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
		if ('9' >= scoreTryOne && '1' <= scoreTryOne) {
			int res = scoreTryOne - '0';
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
		if ('9' >= scoreTryTwo && '1' <= scoreTryTwo) {
			int res = scoreTryTwo - '0';
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
		return (scoreTryOne == STRIKE || ((scoreTryOne == MISS || scoreTryOne <= '9' && scoreTryOne >= '1')
				&& ((scoreTryTwo == SPARE) || ((scoreTryTwo == MISS || scoreTryTwo <= '9' && scoreTryTwo >= '1')
						&& getScoreForFirstRoll() + getScoreForSecondRoll() <= NB_PINS))));
	}

}