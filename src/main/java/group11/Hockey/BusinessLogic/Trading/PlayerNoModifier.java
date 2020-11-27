package group11.Hockey.BusinessLogic.Trading;

public enum PlayerNoModifier {
    MULTIPLE_PLAYER_MODIFIER(0.05f);

    private float numVal;

    PlayerNoModifier(float numVal) {
        this.numVal = numVal;
    }

    public float getNumVal() {
        return numVal;
    }
}
