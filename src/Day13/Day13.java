package Day13;

import Utils.BigIntProgram;
import Utils.Code;
import Utils.Input;
import Utils.Pos;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day13 {

    static long part1(ArrayList<BigInteger> instructions) {
        BigIntProgram prg = new BigIntProgram();
        HashMap<Pos, Tile> board = new HashMap<Pos, Tile>();
        prg.setInstructions(instructions, 10000);
        prg.run();
        step(prg, board);
        return countBlocks(board);
    }

    static long countBlocks(HashMap<Pos, Tile> board) {
        return board.values().stream().filter(t -> t.equals(Tile.block)).count();
    }


    static int part2(ArrayList<BigInteger> instructions) {
        instructions.set(0, BigInteger.TWO);
        BigIntProgram prg = new BigIntProgram();
        HashMap<Pos, Tile> board = new HashMap<Pos, Tile>();
        prg.setInstructions(instructions, 10000);
        int score = 0;
        int input;
        Code returnCode = Code.PAUS;
        boolean run = true;
        while (run) {
            input = simpleBot(board);
            prg.addInput(input);
            returnCode = prg.run();
            score = step(prg, board);
            run = countBlocks(board) > 0 && returnCode == Code.PAUS;
        }
        return score;
    }

    static int simpleBot(HashMap<Pos, Tile> board) {
        Pos ball = Pos.ORIGO, paddle = Pos.ORIGO;
        for (Map.Entry<Pos, Tile> posTileEntry : board.entrySet()) {
            paddle = posTileEntry.getValue() == Tile.hPaddle ? posTileEntry.getKey() : paddle;
            ball = posTileEntry.getValue() == Tile.ball ? posTileEntry.getKey() : ball;
        }
        return ball.getX() - paddle.getX();
    }

    static int step(BigIntProgram prg, HashMap<Pos, Tile> board) {
        final Pos scorePos = new Pos(-1, 0);
        int score = 0;
        while (prg.hasOutput()) {
            int x = prg.readOutput().intValue();
            int y = prg.readOutput().intValue();
            int tileCode = prg.readOutput().intValue();
            Pos pos = new Pos(x, y);
            if (pos.equals(scorePos)) {
                score = tileCode;
            } else {
                board.put(new Pos(x, y), Tile.parseTile(tileCode));
            }
        }
        return score;
    }

    public static void main(String[] args) {
        String file = "input/day13.txt";

        ArrayList<BigInteger> instructions = Input.getInputListBigInt(file);

        System.out.println("Part1: " + part1(new ArrayList<>(instructions)));
        System.out.println("Part2: " + part2(instructions));
    }
}