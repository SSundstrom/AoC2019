package Day11;

import Utils.BigIntProgram;
import Utils.Code;
import Utils.Direction;
import Utils.Pos;

public class EPHRobot {

    private BigIntProgram brain;
    private Pos position;
    private Hull hull;
    private Direction facing;

    public EPHRobot(BigIntProgram brain, Hull hull) {
        this.brain = brain;
        this.hull = hull;
        position = Pos.ORIGO;
        facing = Direction.N;
    }

    public void run() {
        Code runCode = Code.PAUS;
        while(runCode.equals(Code.PAUS)) {
            int colorCode = hull.isWhite(position) ? 1 : 0;
            brain.addInput(colorCode);
            runCode = brain.run();
            paintHull();
        }
    }

    public void paintHull(){
        if (brain.hasOutput()) {
            int colorCode = brain.readOutput().intValue();
            int turnDirectionCode = brain.readOutput().intValue();
            hull.paint(colorCode, position);
            move(turnDirectionCode);
        } else {
            System.out.println("Program has no output.");
        }

    }

    public void move(int turnDirection) {
        switch (turnDirection) {
            case 0:
                facing = facing.turn(3);
                break;
            case 1:
                facing = facing.turn(1);
                break;
            default:
                throw new RuntimeException("Wrong input to move in Robot. in="+turnDirection);
        }
        position = position.move(facing);
    }

}
