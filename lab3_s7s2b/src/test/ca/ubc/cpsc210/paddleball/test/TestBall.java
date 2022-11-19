package ca.ubc.cpsc210.paddleball.test;

import ca.ubc.cpsc210.paddleball.model.Ball;
import ca.ubc.cpsc210.paddleball.model.Puddle;
import ca.ubc.cpsc210.paddleball.model.PBG;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * Unit tests for the Ball class.
 */
class TestBall {
	private static final int XLOC = PBG.DIMENSION1 / 2;
	private static final int YLOC = 50;
	private Ball ball;
	
	@BeforeEach
	void runBefore() {
		ball = new Ball(XLOC, YLOC);
	}
	
	@Test
	void testGetX() {
		assertEquals(XLOC, ball.getX());
	}
	
	@Test
	void testGetY() {
		assertEquals(YLOC, ball.getY());
	}
	
	@Test
	void testUpdate() {
		final int NUM_UPDATES = 4;
		
		ball.move();
		assertEquals((int) (XLOC + ball.getDx()), ball.getX());
		assertEquals((int) (YLOC + ball.getDy()), ball.getY());
		
		for(int count = 1; count < NUM_UPDATES; count++) {
			ball.move();
		}

		assertEquals((int) (XLOC + NUM_UPDATES * ball.getDx()), ball.getX());
		assertEquals((int) (YLOC + NUM_UPDATES * ball.getDy()), ball.getY());
	}

	@Test
	void testBounceOffPaddle() {
		double xVel = ball.getDx();
		double yVel = ball.getDy();
		ball.bounceOffPaddle();
		assertEquals(xVel, ball.getDx());
		assertEquals(-yVel, ball.getDy());
	}
	
	@Test
	void testCollideWith() {
		Puddle p = new Puddle(XLOC);

		Ball b = new Ball(0, 0);
		assertFalse(b.doSomething(p));

		b = new Ball(p.getX(), Puddle.Y_POS);
		assertTrue(b.doSomething(p));

		b = new Ball(p.getX() + Puddle.DIMENSION1 / 2 + Ball.SIZE / 2 - 1, Puddle.Y_POS);
		assertTrue(b.doSomething(p));

		b = new Ball(p.getX() + Puddle.DIMENSION1 / 2 + Ball.SIZE / 2, Puddle.Y_POS);
		assertFalse(b.doSomething(p));

		b = new Ball(p.getX() - Puddle.DIMENSION1 / 2 - Ball.SIZE / 2 + 1, Puddle.Y_POS);
		assertTrue(b.doSomething(p));

		b = new Ball(p.getX() - Puddle.DIMENSION1 / 2 - Ball.SIZE / 2, Puddle.Y_POS);
		assertFalse(b.doSomething(p));

		b = new Ball(p.getX(), Puddle.Y_POS - Puddle.DIMENSION2 / 2 - Ball.SIZE / 2 + 1);
		assertTrue(b.doSomething(p));

		b = new Ball(p.getX(), Puddle.Y_POS - Puddle.DIMENSION2 / 2 - Ball.SIZE / 2);
		assertFalse(b.doSomething(p));
	}
}
