package ir.caspco.versatile.batch.listener;

import ir.caspco.versatile.batch.listener.domains.GlobalBatchExceptionLogEntity;
import ir.caspco.versatile.batch.listener.repositories.GlobalBatchExceptionLogRepository;
import ir.caspco.versatile.common.util.Shift;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.annotation.OnProcessError;
import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.batch.core.annotation.OnWriteError;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */


@Slf4j
@Component
@StepScope
public class ExceptionListener {

    private Long jobId;
    private String stepName;

    private final GlobalBatchExceptionLogRepository globalBatchExceptionLogRepository;


    public ExceptionListener(GlobalBatchExceptionLogRepository globalBatchExceptionLogRepository) {

        this.globalBatchExceptionLogRepository = globalBatchExceptionLogRepository;
    }


    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {

        this.stepName = stepExecution.getStepName();
        this.jobId = stepExecution.getJobExecution().getJobId();
    }

    @OnReadError
    public void onReadError(Exception problem) {

        log(problem);
    }

    @OnWriteError
    public void onWriteError(Exception problem, List<?> item) {

        log(item, problem, ItemError.WRITE_ERROR);
    }

    @OnProcessError
    public void onProcessError(Object item, Exception problem) {

        log(item, problem, ItemError.PROCESS_ERROR);
    }

    private void log(Exception problem) {

        log(null, problem, ItemError.READ_ERROR);
    }

    public void log(Object item, Exception problem, ItemError itemError) {

        GlobalBatchExceptionLogEntity globalBatchExceptionLog =
                GlobalBatchExceptionLogEntity.builder()
                        .jobId(jobId)
                        .stepName(stepName)
                        .itemError(itemError)
                        .item(item == null ? null : Shift.just(item).toJson())
                        .exceptionName(problem.getClass().getName())
                        .exceptionMessage(problem.getMessage())
                        .stackTrace(ExceptionUtils.getStackTrace(problem))
                        .build();

        log.error("Current Error: " + globalBatchExceptionLog.toString(), problem);

        globalBatchExceptionLogRepository.saveAndFlush(globalBatchExceptionLog);
    }
}
