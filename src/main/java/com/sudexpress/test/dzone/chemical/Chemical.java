package com.sudexpress.test.dzone.chemical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Chemical {

	private final Map<String, String> elements = new HashMap<>();
	private final List<String> unregisteredElements = new ArrayList<>();

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

	List<String> buildSymbols(final String element) {
		final char[] elementLetters = this.charArrayOf(element);
		final Set<String> symbols = new LinkedHashSet<>();

		for (int firstIndex = 0; firstIndex < elementLetters.length - 1; firstIndex++) {
			for (int secondIndex = firstIndex + 1; secondIndex < elementLetters.length; secondIndex++) {
				final String symbol = this.buildSymbol(elementLetters, firstIndex, secondIndex);
				if (!symbols.contains(symbol) && this.accept(element, symbol)) {
					symbols.add(symbol);
				}
			}
		}

		return new ArrayList<>(symbols);
	}

	String buildSymbol(final char[] elementLetters, final int firstIndex, final int secondIndex) {
		return ("" + elementLetters[firstIndex]).toUpperCase() + elementLetters[secondIndex];
	}

	public void register(final String... elements) {
		for (final String element : elements) {
			this.register(element);
		}
	}

	private void register(final String element) {
		final List<String> symbols = this.buildSymbols(element);
		final String symbol = this.findFirstUnregisteredSymbol(symbols);
		if (symbol != null) {
			this.elements.put(symbol, element);
		} else {
			this.unregisteredElements.add(element);
		}
	}

	String findFirstUnregisteredSymbol(final List<String> symbols) {
		for (final String symbol : symbols) {
			if (!this.elements.containsKey(symbol)) {
				return symbol;
			}
		}
		return null;
	}

	public String get(final String symbol) {
		return this.elements.get(symbol);
	}

	public List<String> getUnregisteredElements() {
		return this.unregisteredElements;
	}

}
