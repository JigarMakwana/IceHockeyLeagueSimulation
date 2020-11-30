/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.models;

public class gmTable implements IgmTable{
    private float shrewd;
    private float gambler;
    private float normal;

    public gmTable(float shrewd, float gambler, float normal){
        this.shrewd = shrewd;
        this.gambler = gambler;
        this.normal = normal;
    }

    public float getShrewd() {
        return shrewd;
    }

    public float getGambler() {
        return gambler;
    }

    public float getNormal() {
        return normal;
    }
}
