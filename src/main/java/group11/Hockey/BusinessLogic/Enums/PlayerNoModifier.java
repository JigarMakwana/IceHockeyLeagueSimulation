/*
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Enums;

public enum PlayerNoModifier {
    MULTIPLE_PLAYER_MODIFIER(0.05f),
    DRAFTTRADE_MODIFIER(0.50f);

    private float numVal;

    PlayerNoModifier(float numVal) {
        this.numVal = numVal;
    }

    public float getNumVal() {
        return numVal;
    }
}
