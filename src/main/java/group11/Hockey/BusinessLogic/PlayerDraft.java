package group11.Hockey.BusinessLogic;

public enum PlayerDraft {
    PLAYER_DRAFT_ROUNDS(7),
    ROUNDS_1(0),
    ROUND_2(1),
    ROUND_3(2),
    ROUND_4(3),
    ROUND_5(4),
    ROUND_6(5),
    ROUND_7(6);

    private int numVal;

    PlayerDraft(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
