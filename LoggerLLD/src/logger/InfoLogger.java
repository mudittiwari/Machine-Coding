package logger;

public class InfoLogger extends LoggerAbstract{

    public InfoLogger(LoggerAbstract loggerAbstract){
        super(loggerAbstract);
    }

    public void log(int logLevel, String message){
        if(logLevel == 0){
            System.out.println("INFO MESSAGE: " + message);
            return;
        }
        super.log(logLevel, message);
    }
}
