package logger;

public class DebugLogger extends LoggerAbstract{

    public DebugLogger(LoggerAbstract loggerAbstract){
        super(loggerAbstract);
    }
    public void log(int logLevel, String message){
        if(logLevel == 1) {
            System.out.println("DEBUG MESSAGE: " + message);
            return;
        }
        super.log(logLevel, message);
    }
}
