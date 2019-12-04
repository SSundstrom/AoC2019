package Utils;

import java.text.ParseException;

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
}
