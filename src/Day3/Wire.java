package Day3;

import Utils.Direction;
import Utils.Pos;

import java.util.HashMap;
import java.util.LinkedList;

class Wire {

    LinkedList<Step> steps;
    HashMap<Pos, Integer> placement;
    Pos endpoint;
    int length;

    public Wire() {
        placement = new HashMap<>();
        steps = new LinkedList<>();
        endpoint = new Pos(0, 0);
        length = 0;
    }

    public void addStep(Direction dir, int length) {
        steps.add(new Step(dir, length));
        for (int i = 0; i < length; i++) {
            this.length++;
            endpoint = endpoint.move(dir);
            placement.putIfAbsent(endpoint, this.length);
        }
    }

    public LinkedList<Step> getSteps() {
        return steps;
    }

    public HashMap<Pos, Integer> getPlacements() {
        return placement;
    }
}
