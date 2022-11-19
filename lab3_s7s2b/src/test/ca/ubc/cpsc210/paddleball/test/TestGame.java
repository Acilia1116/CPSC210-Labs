package ca.ubc.cpsc210.paddleball.test;

import ca.ubc.cpsc210.paddleball.model.PBG;
import ca.ubc.cpsc210.paddleball.model.Puddle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Unit tests for the Game class.
 */
class TestGame {
	private PBG g;
	
	@BeforeEach
	void runBefore() {
		g = new PBG();
	}
	
	@Test
	void testInit() {
		Puddle t = g.getPaddle();
		assertEquals(PBG.DIMENSION1 / 2, t.getX());
	}
	
	@Test
	void testUpdate() {
		Puddle t = g.getPaddle();
		assertEquals(PBG.DIMENSION1 / 2, t.getX());
		g.update();
		assertEquals(PBG.DIMENSION1 / 2 + Puddle.DX, t.getX());
		g.update();
		assertEquals(PBG.DIMENSION1 / 2 + 2 * Puddle.DX, t.getX());
	}
	
	@Test
	void testNonKeyPadKeyEvent() {
		Puddle t = g.getPaddle();
		g.keyPressed(KeyEvent.VK_LEFT);
		g.update();
		assertEquals(PBG.DIMENSION1 / 2 - Puddle.DX, t.getX());
		g.update();
		assertEquals(PBG.DIMENSION1 / 2 - 2 * Puddle.DX, t.getX());
		g.keyPressed(KeyEvent.VK_RIGHT);
		g.update();
		assertEquals(PBG.DIMENSION1 / 2 - Puddle.DX, t.getX());
		g.update();
		assertEquals(PBG.DIMENSION1 / 2, t.getX());
	}
	
	@Test
	void testKeyPadKeyEvent() {
		Puddle t = g.getPaddle();
		g.keyPressed(KeyEvent.VK_KP_LEFT);
		g.update();
		assertEquals(PBG.DIMENSION1 / 2 - Puddle.DX, t.getX());
		g.update();
		assertEquals(PBG.DIMENSION1 / 2 - 2 * Puddle.DX, t.getX());
		g.keyPressed(KeyEvent.VK_KP_RIGHT);
		g.update();
		assertEquals(PBG.DIMENSION1 / 2 - Puddle.DX, t.getX());
		g.update();
		assertEquals(PBG.DIMENSION1 / 2, t.getX());
	}
}
