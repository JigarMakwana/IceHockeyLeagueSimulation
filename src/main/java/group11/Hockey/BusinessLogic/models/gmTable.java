package group11.Hockey.BusinessLogic.models;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class gmTable implements IgmTable{
    private float shrewd;
    private float gambler;
    private float normal;
    private static Logger logger = LogManager.getLogger(gmTable.class);

    public gmTable(float shrewd, float gambler, float normal){
        this.shrewd = shrewd;
        this.gambler = gambler;
        this.normal = normal;
    }

    public float getShrewd() {
    	logger.info("Entered getShrewd()");
        return shrewd;
    }

    public float getGambler() {
    	logger.info("Entered getGambler()");
        return gambler;
    }

    public float getNormal() {
    	logger.info("Entered getNormal()");
        return normal;
    }
}
