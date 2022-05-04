package com.company.myLog;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/*mi-am create propriul logger pentru a putea "logga"
* din clase diferite in acelasi fisier, fapt pentru
* care l-am facut singleton */

public class MyLogger {
    private static MyLogger myLogger;
    private static Logger logger;
    private static FileHandler fileHandler;
    private static SimpleFormatter formatter;

    public MyLogger() {
        if (myLogger != null) {
            return;
        }

        try {
            fileHandler = new FileHandler("src/com/company/myLog/pokemons.txt");    //<-aici se afla toate informatiile dintr-o aventura
        } catch (IOException e) {
            e.printStackTrace();
        }

        formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
        logger = Logger.getLogger("MyLogger");
        logger.addHandler(fileHandler);

        myLogger = this;
    }

    public Logger getLogger() {
        return logger;
    }

    public static void closeHandlers(Logger logger) {
        for (Handler handler : logger.getHandlers()) {
            handler.close();
        }
    }
}
