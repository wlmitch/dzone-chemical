package com.sudexpress.test.dzone.chemical;

import java.util.HashSet;
import java.util.Set;

public class Chemical {

	public boolean accept(final String element, final String symbol) {
		return this.validateElement(element) && this.validateSymbol(symbol) && this.validateElementWithSymbol(element, symbol);
	}

	public int countAllSymbols(final String element) {
		return this.buildSymbols(element).size();
	}

	public String firstSymbol(final String element) {
		return this.buildSymbols(element).stream().sorted().findFirst().get();
	}

	boolean validateElement(final String element) {
		return element != null && element.matches("[A-Z][a-z]+");
	}

	boolean validateSymbol(final String symbol) {
		return symbol != null && symbol.matches("[A-Z][a-z]");
	}

	boolean validateElementWithSymbol(final String element, final String symbol) {
		final char[] symbolLetters = this.charArrayOf(symbol);
		int symbolLetterIndex = 0;
		for (final char elementLetter : this.charArrayOf(element)) {
			if (elementLetter == symbolLetters[symbolLetterIndex]) {
				symbolLetterIndex++;
			}
			if (symbolLetterIndex >= symbolLetters.length) {
				return true;
			}
		}
		return false;
	}

	char[] charArrayOf(final String symbol) {
		return symbol.toLowerCase().toCharArray();
	}

	Set<String> buildSymbols(final String element) {
		final char[] elementLetters = this.charArrayOf(element);
		final Set<String> symbols = new HashSet<>();

		for (int firstIndex = 0; firstIndex < elementLetters.length - 1; firstIndex++) {
			for (int secondIndex = firstIndex + 1; secondIndex < elementLetters.length; secondIndex++) {
				final String symbol = this.buildSymbol(elementLetters, firstIndex, secondIndex);
				if (!symbols.contains(symbol) && this.accept(element, symbol)) {
					symbols.add(symbol);
				}
			}
		}

		return symbols;
	}

	String buildSymbol(final char[] elementLetters, final int firstIndex, final int secondIndex) {
		return ("" + elementLetters[firstIndex]).toUpperCase() + elementLetters[secondIndex];
	}

}
