package src.assi_4;
public class Logging {
    // usage: Logging.LogType.ERROR
    public enum LogType {
        ERROR, SUCCESS, QUESTION
    }
    public static void print(String consoleMessage, LogType... type) {

        String message;
        if (type.length > 0 && type[0].equals(Logging.LogType.ERROR)){
            message = "\u001B[31m" + consoleMessage + "\u001B[0m \n";
        } else if (type.length > 0 && type[0].equals(Logging.LogType.SUCCESS)){
            message = "\u001B[32m" + consoleMessage + "\u001B[0m \n";
        } else {
            message = consoleMessage;
        }

        System.out.print(message);
    }

    public static void println(String consoleMessage, LogType... type) {

        String message;
        if (type.length > 0 && type[0].equals(Logging.LogType.ERROR)){
            message = "\u001B[31m" + consoleMessage + "\u001B[0m \n";
        } else if (type.length > 0 && type[0].equals(Logging.LogType.SUCCESS)) {
            message = "\u001B[32m" + consoleMessage + "\u001B[0m \n";
        } else {
            message = consoleMessage;
        }

        System.out.println(message);
    }
}
