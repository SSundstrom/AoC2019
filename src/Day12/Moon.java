package Day12;

import Utils.Axis;
import Utils.Pos3;
import Utils.Tuple;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Function;

public class Moon {

    private Pos3 velocity;
    private Pos3 position;

    public Moon(Pos3 position) {
        this.position = position;
        velocity = Pos3.ORIGO;
    }

    public Tuple<Integer> getThings(Axis axis) {
        return new Tuple<>(axisToFunc(axis).apply(position), axisToFunc(axis).apply(velocity));
    }

    private Function<Pos3, Integer> axisToFunc(Axis axis) {
        switch (axis) {
            case Z:
                return Pos3::getZ;
            case X:
                return Pos3::getX;
            case Y:
                return Pos3::getY;
            default:
                throw new IllegalStateException("Unexpected value: " + axis);
        }
    }

    public void updateVelocity(ArrayList<Moon> moons) {
        updateVelocity(Axis.X, moons);
        updateVelocity(Axis.Y, moons);
        updateVelocity(Axis.Z, moons);
    }

    public void updateVelocity(Axis axis, ArrayList<Moon> moons) {

        int diff = moons.stream()
                .map(Moon::getPosition)
                .map(axisToFunc(axis))
                .mapToInt(y -> y - axisToFunc(axis).apply(position))
                .map(y -> y == 0 ? 0 : y / Math.abs(y))
                .sum();

        velocity = velocity.move(axis, diff);
    }

    public Pos3 getPosition() {
        return new Pos3(position);
    }

    public Pos3 getVelocity() {
        return new Pos3(velocity);
    }

    public void move(){
        position = position.add(velocity);
    }

    public int getPotentialEnergy() {
        return position.absSum();
    }

    public int getKineticEnergy() {
        return velocity.absSum();
    }

    @Override
    public String toString() {
        return "{" +
                "position=" + position +
                ", velocity=" + velocity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moon moon = (Moon) o;
        return Objects.equals(velocity, moon.velocity) &&
                Objects.equals(position, moon.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(velocity, position);
    }
}
