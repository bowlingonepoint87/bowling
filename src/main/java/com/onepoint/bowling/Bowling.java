package com.onepoint.bowling;

import static com.onepoint.bowling.utils.Constantes.DEFAULT_VALUE;
import static com.onepoint.bowling.utils.Constantes.NB_ROLLS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;;

class Bowling {

	private List<Roll> rolls = new ArrayList<>();

	void playRoll(Roll roll) {
		rolls.add(roll);
	}

	// score
	int getScore() {
		return IntStream.range(0, NB_ROLLS).reduce(0,
				(score, index) -> score + getRollAtIndex(index).getNbPinsDown() + getRollBonus(index));
	}

	// intermediate score (without checking if next roll is finished)
	int getScoreAfterRolls(int i) {
		if (i > 0 && i <= 10)
			return IntStream.range(0, i).reduce(0,
					(score, index) -> score + getRollAtIndex(index).getNbPinsDown() + getRollBonus(index));
		else
			return DEFAULT_VALUE;
	}

	private int getRollBonus(int rollIndex) {
		Roll roll = getRollAtIndex(rollIndex);
		if (roll.isStrike()) {
			return getStrikeBonus(rollIndex);
		} else if (roll.isSpare()) {
			return getSpareBonus(rollIndex);
		}
		return 0;
	}

	private int getStrikeBonus(int index) {
		Roll nextRoll = getRollAtIndex(index + 1);
		if (nextRoll.isStrike()) {
			return nextRoll.getNbPinsDown() + getRollAtIndex(index + 2).getScoreForFirstRoll();
		} else {
			return nextRoll.getNbPinsDown();
		}
	}

	private int getSpareBonus(int index) {
		return getRollAtIndex(index + 1).getScoreForFirstRoll();
	}

	private Roll getRollAtIndex(int index) {
		if (index + 1 <= rolls.size()) {
			return rolls.get(index);
		}
		throw new IllegalStateException("Roll " + index + " is missing...");
	}

	// correct number of rolls
	boolean isValid() {
		if (rolls.size() < NB_ROLLS || rolls.size() > NB_ROLLS + 2
				|| rolls.stream().anyMatch((roll -> !roll.isValid())))
			return false;
		else {
			// Game ending wih Strikes
			if (rolls.size() == NB_ROLLS + 2 && getRollAtIndex(NB_ROLLS - 1).isStrike()
					&& getRollAtIndex(NB_ROLLS).isStrike() && getRollAtIndex(NB_ROLLS + 1).isStrike())
				return true;
			else if (rolls.size() == NB_ROLLS + 1
					&& (getRollAtIndex(NB_ROLLS - 1).isStrike() || getRollAtIndex(NB_ROLLS - 1).isSpare()))
				return true;
			else if (rolls.size() == NB_ROLLS)
				return true;
			else
				return false;
		}
	}

}