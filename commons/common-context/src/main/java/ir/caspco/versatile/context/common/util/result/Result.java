package ir.caspco.versatile.context.common.util.result;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Slf4j
public class Result<R> implements BaseResult<R, Throwable> {

    private Supplier<R> supplierIn;
    private boolean beThrown = false;
    private ResultErrorException resultErrorException;

    private Consumer<Optional<R>> resultConsumer;
    private Consumer<Throwable> errorConsumer;


    public static <R> Result<R> accept(Supplier<R> supplier) {

        Result<R> result = new Result<>();
        result.supplierIn = supplier;

        return result;

    }

    @Override
    public Result<R> resultErrorException(ResultErrorException resultErrorException) {
        this.resultErrorException = resultErrorException;

        return this;
    }

    @Override
    @SneakyThrows
    public ResultClass<R> retrieve() {

        try {

            ResultClass<R> resultClass = new ResultClass<>(supplierIn.get());

            if (resultConsumer != null) {
                resultConsumer.accept(resultClass.result());
            }

            return resultClass;

        } catch (Throwable throwable) {

            log.error("Result Error:", throwable);

            if (errorConsumer != null) {
                errorConsumer.accept(throwable);
            }

            if (beThrown || errorConsumer == null) {
                throw resultErrorException != null ? resultErrorException.target(throwable) : throwable;
            }

            return new ResultClass<>();

        }

    }

    @Override
    public Result<R> onSuccess(Consumer<Optional<R>> consumer) {

        this.resultConsumer = consumer;

        return this;

    }

    @Override
    public Result<R> onError(Consumer<Throwable> consumer) {

        this.errorConsumer = consumer;

        return this;

    }

    @Override
    public BaseResult<R, Throwable> throwException() {

        beThrown = true;

        return this;

    }

}
