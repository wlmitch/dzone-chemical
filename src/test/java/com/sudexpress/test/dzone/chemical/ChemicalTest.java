package com.sudexpress.test.dzone.chemical;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class ChemicalTest extends AbstractTest<Chemical> {

	@Test
	public void accept() {
		assertThat(this.test.accept("Spenglerium", "Ee"), is(true));
		assertThat(this.test.accept("Zeddemorium", "Zr"), is(true));
		assertThat(this.test.accept("Venkmine", "Kn"), is(true));
		assertThat(this.test.accept("Stantzon", "Zt"), is(false));
		assertThat(this.test.accept("Melintzum", "Nn"), is(false));
		assertThat(this.test.accept("Tullium", "Ty"), is(false));
		assertThat(this.test.accept("Tullium", "Mm"), is(false));
	}

	@Test
	public void validateSymbol() {
		assertThat(this.test.validateSymbol("Ee"), is(true));
		assertThat(this.test.validateSymbol("ee"), is(false));
		assertThat(this.test.validateSymbol("EE"), is(false));
		assertThat(this.test.validateSymbol("E"), is(false));
		assertThat(this.test.validateSymbol("Eee"), is(false));
		assertThat(this.test.validateSymbol(null), is(false));
	}

	@Test
	public void validateElement() throws Exception {
		assertThat(this.test.validateElement("Eefezhujhfe"), is(true));
		assertThat(this.test.validateElement("eee"), is(false));
		assertThat(this.test.validateElement("EeE"), is(false));
		assertThat(this.test.validateElement("Eee"), is(true));
		assertThat(this.test.validateElement(null), is(false));
	}

	@Test
	public void firstSymbol() throws Exception {
		assertThat(this.test.firstSymbol("Gozerium"), is("Ei"));
		assertThat(this.test.firstSymbol("Slimyrine"), is("Ie"));
	}

	@Test
	public void countAllSymbols() {
		assertThat(this.test.countAllSymbols("Zuulon"), is(11));
	}

	@Test
	public void buildSymbol() throws Exception {
		assertThat(this.test.buildSymbol("Zuulon".toCharArray(), 1, 3), is("Ul"));
	}

	@Test
	public void charArrayOf() throws Exception {
		assertThat(this.test.charArrayOf("Abc"), is(new char[] { 'a', 'b', 'c' }));
	}

	@Test
	public void buildSymbols() throws Exception {
		final List<String> symbols = this.test.buildSymbols("Iron");

		assertThat(symbols.get(0), is("Ir"));
		assertThat(symbols.get(1), is("Io"));
		assertThat(symbols.get(2), is("In"));
		assertThat(symbols.get(3), is("Ro"));
		assertThat(symbols.get(4), is("Rn"));
		assertThat(symbols.get(5), is("On"));
	}

	@Test
	public void register() {
		this.test.register("Chlorine", "Chromium", "Cesium", "Cerium");

		assertThat(this.test.get("Ch"), is("Chlorine"));
		assertThat(this.test.get("Cr"), is("Chromium"));
		assertThat(this.test.get("Ce"), is("Cesium"));
		assertThat(this.test.get("Ci"), is("Cerium"));
	}

	@Test
	public void wipeTheTable() {
		final String[] elements = this.readAllElements();

		this.test.register(elements);

		assertThat(this.test.get("Pt"), is("Protactinium"));
		assertThat(this.test.get("Cf"), is("Californium"));

		// Wrong test
		// assertThat(this.test.get("Iu"), is("Lionium"));
		// Corrected test
		assertThat(this.test.get("Iu"), is("Lionoium"));

		System.out.println("Unregistered elements : ");
		for (final String element : this.test.getUnregisteredElements()) {
			System.out.println("\t" + element);
		}
	}

	private String[] readAllElements() {
		try (
				InputStream inputStream = this.test.getClass().getResourceAsStream("/elements.txt");
				Scanner scannerBuilder = new Scanner(inputStream, "UTF-8"); Scanner scanner = scannerBuilder.useDelimiter("\\A")) {
			final String text = scanner.next();
			return text.split("\r\n");
		} catch (final IOException e) {
			throw new RuntimeException("unable to read file", e);
		}
	}

}
