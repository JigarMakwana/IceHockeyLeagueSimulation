/*
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Enums;

public enum PlayerDraft {
    PLAYER_DRAFT_ROUNDS(7),
    ROUNDS_1(0),
    ROUND_7(6);

    private int numVal;

    PlayerDraft(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
