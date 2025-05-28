package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.*;


class TestA {
	
	private DiaDia gioca;
	private IOSimulator ioSim;
	
	
	@BeforeEach
	public void setUp() {
		
		
		
		String[] istruzioni = {"vai sud", "prendi lanterna", "vai ovest", "posa lanterna", "prendi uotrapssap", "vai est", "vai est", "posa uotrapssap", "prendi passpartou", "vai ovest", "posa passpartou", "vai nord"};
		this.ioSim = new IOSimulator(istruzioni);
		
		gioca = new DiaDia(ioSim);
		
	}
	
	
	@Test
	void testSpostamento() {	
		gioca.gioca();
		String[] b = ioSim.getMessaggiProdotti();
		assertEquals("Aula N10", b[1]);
	}
	
	@Test
	void testPrendi() {	
		gioca.gioca();
		String[] b = ioSim.getMessaggiProdotti();
		assertEquals("Hai ottenuto lanterna!", b[2]);
	}
	
	@Test
	void testPosa() {	
		gioca.gioca();
		String[] b = ioSim.getMessaggiProdotti();
		assertEquals("Hai posato lanterna!", b[4]);
	}
	
	@Test
	void testVinci() {	
		gioca.gioca();
		String[] b = ioSim.getMessaggiProdotti();
		assertEquals("Hai vinto!", b[13]);
	}
}