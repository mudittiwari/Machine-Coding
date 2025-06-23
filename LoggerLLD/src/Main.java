import logger.DebugLogger;
import logger.ErrorLogger;
import logger.InfoLogger;
import logger.LoggerAbstract;

public class Main {
    public static void main(String[] args) {
        LoggerAbstract logger = new InfoLogger(new DebugLogger(new ErrorLogger(null)));
        logger.log(1, "this is a debug message");
        logger.log(2, "this is a error message");
        logger.log(0, "this is a info message");
        logger.log(8, "this is a info message");
    }
}