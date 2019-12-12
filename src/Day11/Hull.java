package Day11;

import Utils.Pos;

import java.util.Comparator;
import java.util.HashSet;

public class Hull {

    private HashSet<Pos> painted;
    private HashSet<Pos> white;

    public Hull() {
        painted = new HashSet<>();
        white = new HashSet<>();
    }

    public boolean isWhite(Pos tile) {
        return white.contains(tile);
    }

    public HashSet<Pos> getPainted() {
        return painted;
    }

    public HashSet<Pos> getWhite() {
        return white;
    }

    public void paint(int colorCode, Pos tile){
        switch (colorCode) {
            case 0:
                painted.add(tile);
                white.remove(tile);
                break;
            case 1:
                painted.add(tile);
                white.add(tile);
                break;
            default:
                throw new RuntimeException("colorCode not available. code=" + colorCode);
        }
    }

    public void print() {
        int offset = 1;
        int minx = this.white.stream().min(new Pos.xComparator()).get().getX() - offset;
        int miny = this.white.stream().min(new Pos.yComparator()).get().getY() - offset;
        int maxx = this.white.stream().max(new Pos.xComparator()).get().getX() + offset;
        int maxy = this.white.stream().max(new Pos.yComparator()).get().getY() + offset;

        StringBuilder sb = new StringBuilder();
        for (int y = maxy; y >= miny; y--) {
            for (int x = minx; x < maxx; x++) {
                String color = white.contains(new Pos(x, y)) ? "#" : " ";
                sb.append(color);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

}
