package com.sudexpress.test.dzone.chemical;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
}
