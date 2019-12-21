package Day18;

import javax.swing.*;

public class Door implements PoI {

    private char c;

    public Door(char c) {
        this.c = c;
    }

    @Override
    public char getChar() {
        return c;
    }


    @Override
    public String toString() {
        return "Door-" + (char)c;
    }
}
