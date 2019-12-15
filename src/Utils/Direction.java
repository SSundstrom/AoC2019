package Utils;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;
import java.text.ParseException;
import java.util.stream.Stream;

public enum Direction {
    N, E, S, W;

    public static Direction parseChar(char c) throws ParseException {
        switch (c) {
            case 'U' :
                return N;
            case 'R' :
                return E;
            case 'D' :
                return S;
            case 'L' :
                return W;
            default:
                throw new ParseException("" + c, 0);
        }
    }

    public Direction turn(int stepsClockwise) {
        switch (stepsClockwise % 4) {
            case 0:
                return this;
            case 1:
                return turnRight();
            case 2:
                return turnRight().turnRight();
            case 3:
                return turnRight().turnRight().turnRight();
            default:
                throw new RuntimeException("Turning in direction went horribly wrong");
        }
    }

    private Direction turnRight() {
        switch (this) {
            case N: return E;
            case E: return S;
            case S: return W;
            case W: return N;
            default:
                throw new RuntimeException("[Direction:turnRight] This really should not happen");
        }
    }

    public static Stream<Direction> getAllDirections() {
        return Stream.of(N, S, W, E);
    }
}
