package ir.caspco.versatile.handler.exceptions;

import ir.caspco.versatile.context.handler.exceptions.services.ExceptionToDatabase;
import ir.caspco.versatile.handler.exceptions.domains.GlobalExceptionLogEntity;
import ir.caspco.versatile.handler.exceptions.repositories.GlobalExceptionLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */


@Slf4j
@Order(-3)
@Component
//TODO from davood akbari: Do not forget to test.
public class ExceptionToDatabaseImpl implements ExceptionToDatabase {

    private final GlobalExceptionLogRepository globalExceptionLogRepository;


    public ExceptionToDatabaseImpl(GlobalExceptionLogRepository globalExceptionLogRepository) {

        this.globalExceptionLogRepository = globalExceptionLogRepository;
    }

    @Override
    public void logToDatabase(Throwable parentException, Throwable exception) {

        globalExceptionLogRepository.save(
                GlobalExceptionLogEntity.builder()
                        .parentExceptionName(parentException.getClass().getName())
                        .parentStackTrace(ExceptionUtils.getStackTrace(parentException))
                        .parentExceptionMessage(parentException.getMessage())
                        .exceptionName(exception.getClass().getName())
                        .exceptionMessage(exception.getMessage())
                        .stackTrace(ExceptionUtils.getStackTrace(exception))
                        .build()
        );
    }
}
