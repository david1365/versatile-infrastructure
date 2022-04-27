package ir.caspco.versatile.context.handler.exceptions.services;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public interface ExceptionToDatabase {

    void logToDatabase(Throwable parentException, Throwable exception);
}
