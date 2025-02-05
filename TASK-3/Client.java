import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

enum LogLevel {
    INFO, DEBUG, ERROR;
}

interface Command {
    void execute(String message);
}

class LogCommand implements Command {
    private LogHandler handler;

    public LogCommand(LogHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(String message) {
        handler.handle(message);
    }
}

abstract class LogHandler {
    protected LogHandler nextHandler;

    public void setNextHandler(LogHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handle(String message) {
        if (canHandle(message)) {
            log(message);
        } else if (nextHandler != null) {
            nextHandler.handle(message);
        }
    }

    protected abstract boolean canHandle(String message);

    protected abstract void log(String message);
}

class InfoHandler extends LogHandler {
    @Override
    protected boolean canHandle(String message) {
        return message.startsWith("INFO:");
    }

    @Override
    protected void log(String message) {
        System.out.println("[INFO]: " + message);
    }
}

class DebugHandler extends LogHandler {
    @Override
    protected boolean canHandle(String message) {
        return message.startsWith("DEBUG:");
    }

    @Override
    protected void log(String message) {
        System.out.println("[DEBUG]: " + message);
    }
}

class ErrorHandler extends LogHandler {
    @Override
    protected boolean canHandle(String message) {
        return message.startsWith("ERROR:");
    }

    @Override
    protected void log(String message) {
        System.out.println("[ERROR]: " + message);
    }
}

class Logger {
    private List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void processCommands(String message) {
        Iterator<Command> iterator = commands.iterator();
        while (iterator.hasNext()) {
            Command command = iterator.next();
            command.execute(message);
        }
    }
}

public class Client {
    public static void main(String[] args) {
        InfoHandler infoHandler = new InfoHandler();
        DebugHandler debugHandler = new DebugHandler();
        ErrorHandler errorHandler = new ErrorHandler();

        infoHandler.setNextHandler(debugHandler);
        debugHandler.setNextHandler(errorHandler);

        Logger logger = new Logger();
        logger.addCommand(new LogCommand(infoHandler));
        logger.addCommand(new LogCommand(debugHandler));
        logger.addCommand(new LogCommand(errorHandler));

        String[] logMessages = {
            "INFO: Application started",
            "DEBUG: Debugging the application",
            "ERROR: Something went wrong",
            "INFO: Process completed successfully"
        };

        // Process predefined log messages
        for (String message : logMessages) {
            logger.processCommands(message);
        }
    }
}
