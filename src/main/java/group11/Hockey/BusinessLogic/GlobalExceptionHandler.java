//Author: Harry B00856244
package group11.Hockey.BusinessLogic;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler, IGlobalExceptionHandler {
		 
		private static Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
	 
	    @Override
		public void uncaughtException(Thread t, Throwable e) {
	    	logger.error("Unhandled exception caught : "+e);
	    }
	}
