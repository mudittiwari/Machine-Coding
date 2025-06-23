package logger;

public class ErrorLogger extends LoggerAbstract{

    public ErrorLogger(LoggerAbstract loggerAbstract){
        super(loggerAbstract);
    }
    public void log(int logLevel, String message){
        if(logLevel == 2){
            System.out.println("ERROR MESSAGE: " + message);
            return;
        }
        super.log(logLevel, message);
    }
}
