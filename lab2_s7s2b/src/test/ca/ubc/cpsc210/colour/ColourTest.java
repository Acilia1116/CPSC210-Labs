package ca.ubc.cpsc210.colour;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
class ColourTest {
    private Colour col1;
    private Colour col2;
    private Colour col3;
    private Colour col4;
    private Colour col5;

    @BeforeEach
    void runBefore() {
        col1 = new Colour(0,0,0);
        col2 = new Colour(0,10,20) ;
        col3  = new Colour(120,120,120);
        col4  = new Colour(80,120,160);
        col5 =  new Colour(255,255,255);
    }

    @Test
    void testIsGreyScale() {
        assertTrue(col1.isGreyScale());
        assertFalse(col2.isGreyScale());
        assertTrue(col3.isGreyScale());
        assertFalse(col4.isGreyScale());
        assertTrue(col5.isGreyScale());
    }

    @Test
    void testToHex() {
        assertEquals("0",col1.toHex());
        assertEquals("a14",col2.toHex());
        assertEquals("787878",col3.toHex());
        assertEquals("5078a0",col4.toHex());
        assertEquals("ffffff",col5.toHex());
    }
}