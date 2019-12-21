package Day18;

import java.util.Objects;

public class Key implements PoI {
    private char c;

    public Key(char c) {
        this.c = c;
    }

    @Override
    public char getChar() {
        return c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return c == key.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(c);
    }

    @Override
    public String toString() {
        return "Key-" + c;
    }
}
