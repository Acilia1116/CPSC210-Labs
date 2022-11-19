package ca.ubc.cpsc210.paddleball.test;

import ca.ubc.cpsc210.paddleball.model.PBG;
import ca.ubc.cpsc210.paddleball.model.Puddle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Unit tests for the Paddle class.
 */
class TestPaddle {
	private static final int XLOC = PBG.DIMENSION1 / 2;
	private Puddle p;
	
	@BeforeEach
	void runBefore() {
		p = new Puddle(XLOC);
	}
	
	@Test
	void testGetX() {
		assertEquals(XLOC, p.getX());
	}
	
	@Test
	void testUpdate() {
		final int NUM_UPDATES = 8;
		
		p.move();
		assertEquals(XLOC + Puddle.DX, p.getX());
		
		for(int count = 1; count < NUM_UPDATES; count++) {
			p.move();
		}
		
		assertEquals(XLOC + NUM_UPDATES * Puddle.DX, p.getX());
	}
	
	@Test
	void testFlipDirection() {
		p.move();
		assertEquals(XLOC + Puddle.DX, p.getX());
		p.left();
		p.move();
		assertEquals(XLOC, p.getX());
		p.right();
		p.move();
		assertEquals(XLOC + Puddle.DX, p.getX());
	}
	
	@Test 
	void testLeftBoundary() {
		p.left();
		for(int count = 0; count < (PBG.DIMENSION1 / 2 - Puddle.DIMENSION1 / 2) / Puddle.DX + 1; count++)
			p.move();
		assertEquals(Puddle.DIMENSION1 / 2, p.getX());
		p.move();
		assertEquals(Puddle.DIMENSION1 / 2, p.getX());
	}
	
	@Test
	void testRightBoundary() {
		p.right();
		for(int count = 0; count < (PBG.DIMENSION1 / 2 - Puddle.DIMENSION1 / 2) / Puddle.DX + 1; count++)
			p.move();
		assertEquals(PBG.DIMENSION1 - Puddle.DIMENSION1 / 2, p.getX());
		p.move();
		assertEquals(PBG.DIMENSION1 - Puddle.DIMENSION1 / 2, p.getX());
	}
}
