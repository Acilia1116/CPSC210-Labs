package ca.ubc.cpsc210.colour;

// Represents a colour having a red, green and blue component,
// each of which is in the range [0, 255]
public class Colour {
    private int r;
    private int g;
    private int b;

    // EFFECTS: constructs the colour (r, g, b)
    public Colour(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        // stub
    }

    // EFFECTS: returns true if this colour is on the grey scale,
    // false otherwise
    public boolean isGreyScale() {
        if(r == (b = g)){
            return true;
        }
        else
            return false;
        // stub

    }

    // EFFECTS: returns the hexadecimal representation of this colour
    public String toHex() {
        if (r==0 && b ==0 && g ==0){
            return "0";
        }
        else return Integer.toHexString((r * 256 + g) * 256 + b);
        // stub
    }
}
