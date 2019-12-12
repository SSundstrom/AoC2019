package Utils;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.Objects;

public class Pos {
    final int x;
    final int y;

    public static final Pos ORIGO = new Pos(0, 0);
    public static final Pos NORTH = new Pos(0, -1);

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pos move(Direction d){
        switch (d) {
            case N:
                return new Pos(x, y+1);
            case S:
                return new Pos(x, y-1);
            case E:
                return new Pos(x+1, y);
            case W:
                return new Pos(x-1, y);
        }
        System.out.println("SOMETHING WENT HORRIBLY WRONG!");
        System.exit(1);
        return this;
    }

    public int manhattanDist(Pos p2) {
        return Math.abs(p2.x - x) + Math.abs(p2.y - y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pos pos = (Pos) o;
        return x == pos.x &&
                y == pos.y;
    }

    public Pos add(Pos p) {
        return new Pos(this.x + p.x, this.y + p.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Pos directionTo(Pos p2) {
        BigInteger x = BigInteger.valueOf(p2.x - this.x);
        BigInteger y = BigInteger.valueOf(p2.y - this.y);
        BigInteger gcd = x.gcd(y);
        BigInteger dX;
        BigInteger dY;
        if (gcd.equals(BigInteger.ZERO)) {
             dX = x.equals(BigInteger.ZERO) ? x : x.divide(x);
             dY = y.equals(BigInteger.ZERO) ? y : y.divide(y);
        } else {
            dX = x.divide(gcd);
            dY = y.divide(gcd);
        }

        return new Pos(dX.intValue(), dY.intValue());
    }

    public Pos scale(int factor) {
        return new Pos(this.x * factor, this.y * factor);
    }

    public static Comparator<Pos> getAngleComparator(Pos relativePos) {
        return new angleComparator(relativePos);
    }

    public double angleTo(Pos p2) {
        int dot = this.x*p2.x + this.y*p2.y;
        double angle = Math.acos(dot/(getLength() * p2.getLength()));
        if (p2.x < 0) {
            angle = Math.toRadians(360) - angle;
        }
        return angle;
    }

    public double getLength() {
        return Math.sqrt((this.x * this.x) + (this.y * this.y));
    }

    static class angleComparator implements Comparator<Pos> {

        private Pos relativePos;

        public angleComparator(Pos relativePos) {
            this.relativePos = relativePos;
        }

        @Override
        public int compare(Pos p1, Pos p2) {
            double a1 = relativePos.angleTo(p1);
            double a2 = relativePos.angleTo(p2);
            return Double.compare(a1, a2);
        }
    }

    public static class xComparator implements Comparator<Pos> {
        @Override
        public int compare(Pos p1, Pos p2) {
            return Integer.compare(p1.x, p2.x);
        }
    }

    public static class yComparator implements Comparator<Pos> {
        @Override
        public int compare(Pos p1, Pos p2) {
            return Integer.compare(p1.y, p2.y);
        }
    }

    public int getAsAOCOutput() {
        return this.x * 100 + this.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}