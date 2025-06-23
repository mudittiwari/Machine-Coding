package logger;

public class LoggerAbstract {
    private static int infoLevel = 1;
    private static int debugLevel = 2;
    private static int errorLevel = 3;

    private LoggerAbstract nextLogger = null;

    public LoggerAbstract(LoggerAbstract nextLogger){
        this.nextLogger = nextLogger;
    }

    public void log(int logLevel, String message){
        if(nextLogger != null){
            nextLogger.log(logLevel, message);
        }
        else{
            System.out.println("LOG LEVEL IS INVALID");
        }
    }
}
