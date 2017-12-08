package ua.khpi.oop.pavlova10.util;

import java.text.Collator;

public enum Strength {

	Primary(Collator.PRIMARY), // base char
	Secondary(Collator.SECONDARY), // base char + accent
	Tertiary(Collator.TERTIARY), // base char + accent + case
	Identical(Collator.IDENTICAL); // base char + accent + case + bits

	private int fStrength;

	private Strength(int aStrength) {
		fStrength = aStrength;
	}

	int getStrength() {
		return fStrength;
	}

}
