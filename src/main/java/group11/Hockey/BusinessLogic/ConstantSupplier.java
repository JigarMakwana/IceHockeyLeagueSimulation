package group11.Hockey.BusinessLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConstantSupplier implements IConstantSupplier {
    private int teamSize;
    private int skaterSize;
    private int goalieSize;

    public ConstantSupplier(int teamSize, int skaterSize, int goalieSize) {
        this.teamSize = teamSize;
        this.skaterSize = skaterSize;
        this.goalieSize = goalieSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public int getSkaterSize() {
        return skaterSize;
    }

    public int getGoalieSize() {
        return goalieSize;
    }
}
