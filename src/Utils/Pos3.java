package Utils;

import java.util.Objects;

public class Pos3 extends Pos {

    public static final Pos3 ORIGO = new Pos3(0, 0,0);
    final int z;

    public Pos3(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public Pos3(Pos3 p) {
        super(p.x, p.y);
        z = p.z;
    }

    public Pos3 move(Axis axis, int steps){
        switch (axis) {
            case X: return new Pos3(x+steps, y, z);
            case Y: return new Pos3(x, y+steps, z);
            case Z: return new Pos3(x, y, z+steps);
            default:
                throw new RuntimeException("[Pos3:Move] not a known direction = " + axis);
        }
    }

    public int absSum() {
        return Math.abs(x) + Math.abs(y) + Math.abs(z);
    }

    public Pos3 add(Pos3 p) {
        return new Pos3(x+p.x, y+p.y, z+p.z);
    }

    public int getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pos3 pos3 = (Pos3) o;
        return z == pos3.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), z);
    }

    @Override
    public String toString() {

        return "(" + String.format("%3s", x) + "," + String.format("%3s", y) + "," + String.format("%3s", z) + ")";
    }
}
