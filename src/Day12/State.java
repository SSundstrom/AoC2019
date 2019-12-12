package Day12;

import Utils.Pos3;

import java.util.ArrayList;
import java.util.Objects;

class State<A extends Object> {
    private final A m1;
    private final A m2;
    private final A m3;
    private final A m4;

    public State(A m1, A m2, A m3, A m4) {
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.m4 = m4;
    }


    public static State<Pos3> getVelocities(ArrayList<Moon> moons) {
        return new State(
                moons.get(0).getVelocity(),
                moons.get(1).getVelocity(),
                moons.get(2).getVelocity(),
                moons.get(3).getVelocity());
    }

    public static State<Pos3> getPositions(ArrayList<Moon> moons) {
        return new State(
                moons.get(0).getPosition(),
                moons.get(1).getPosition(),
                moons.get(2).getPosition(),
                moons.get(3).getPosition());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State<?> state = (State<?>) o;
        return Objects.equals(m1, state.m1) &&
                Objects.equals(m2, state.m2) &&
                Objects.equals(m3, state.m3) &&
                Objects.equals(m4, state.m4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m1, m2, m3, m4);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\t").append(m1);
        sb.append("\t").append(m2);
        sb.append("\t").append(m3);
        sb.append("\t").append(m4);
        sb.append('}');
        return sb.toString();
    }
}
