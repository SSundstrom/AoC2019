package Day13;

public enum Tile {
    empty, wall, block, hPaddle, ball;

    public static Tile parseTile(int tileCode) {
        switch (tileCode) {
            case 0: return empty;
            case 1: return wall;
            case 2: return block;
            case 3: return hPaddle;
            case 4: return ball;
            default:
                throw new RuntimeException("No such tileCode: " + tileCode);
        }
    }

}
