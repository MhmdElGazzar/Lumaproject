package Utilities;

import org.apache.log4j.Logger;

public class LogsUtils {

    private static final Logger logger = Logger.getLogger(LogsUtils.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void warning(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }
}
