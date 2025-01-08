package loginsystem1;
import java.util.*;

//Enum to represent severity levels
enum LogLevel {
 INFO,
 DEBUG,
 ERROR
}

//Command interface
interface Command {
 void execute(String message, LogLevel level);
}

//LogCommand class implementing Command interface
class LogCommand implements Command {
 private LogHandler handler;

 public LogCommand(LogHandler handler) {
     this.handler = handler;
 }

 @Override
 public void execute(String message, LogLevel level) {
     handler.handleMessage(message, level);
 }
}

//Abstract LogHandler class
abstract class LogHandler {
 private LogHandler next;

 public void setNext(LogHandler next) {
     this.next = next;
 }

 public void handleMessage(String message, LogLevel level) {
     if (canHandle(level)) {
         log(message);
     } else if (next != null) {
         next.handleMessage(message, level);
     }
 }

 protected abstract boolean canHandle(LogLevel level);

 protected abstract void log(String message);
}

//Concrete handler for INFO level
class InfoHandler extends LogHandler {
 @Override
 protected boolean canHandle(LogLevel level) {
     return level == LogLevel.INFO;
 }

 @Override
 protected void log(String message) {
     System.out.println("INFO: " + message);
 }
}

//Concrete handler for DEBUG level
class DebugHandler extends LogHandler {
 @Override
 protected boolean canHandle(LogLevel level) {
     return level == LogLevel.DEBUG;
 }

 @Override
 protected void log(String message) {
     System.out.println("DEBUG: " + message);
 }
}

//Concrete handler for ERROR level
class ErrorHandler extends LogHandler {
 @Override
 protected boolean canHandle(LogLevel level) {
     return level == LogLevel.ERROR;
 }

 @Override
 protected void log(String message) {
     System.out.println("ERROR: " + message);
 }
}

//Logger class to use Iterator pattern
class Logger {
 private List<Command> commands = new ArrayList<>();

 public void addCommand(Command command) {
     commands.add(command);
 }

 public void processCommands(String message, LogLevel level) {
     for (Command command : commands) {
         command.execute(message, level);
     }
 }
}

//Client class to configure and demonstrate the logging system
public class Client {
 public static void main(String[] args) {
     // Create handlers
     InfoHandler infoHandler = new InfoHandler();
     DebugHandler debugHandler = new DebugHandler();
     ErrorHandler errorHandler = new ErrorHandler();

     // Configure chain of responsibility
     infoHandler.setNext(debugHandler);
     debugHandler.setNext(errorHandler);

     // Create Logger
     Logger logger = new Logger();

     // Add commands to the logger
     logger.addCommand(new LogCommand(infoHandler));

     // Demonstrate the logging system
     logger.processCommands("System is starting up.", LogLevel.INFO);
     logger.processCommands("Debugging connection issues.", LogLevel.DEBUG);
     logger.processCommands("System failure occurred!", LogLevel.ERROR);
 }
}