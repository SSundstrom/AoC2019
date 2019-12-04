package Day3;

import Utils.Direction;

class Step {
    Direction dir;
    int length;

    public Step(Direction dir, int length) {
        this.dir = dir;
        this.length = length;
    }

    public Direction getDirection() {
        return dir;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Step{" +
                "dir=" + dir +
                ", dist=" + length +
                '}';
    }
}
