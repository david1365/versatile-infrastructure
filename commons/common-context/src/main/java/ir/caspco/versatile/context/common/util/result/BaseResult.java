package ir.caspco.versatile.context.common.util.result;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

//TODO from davood akbari: Do not forget to test.
public interface BaseResult<R, T extends Throwable> {

    ResultClass<R> retrieve();

    BaseResult<R, T> resultErrorException(ResultErrorException resultErrorException);

    BaseResult<R, T> onSuccess(Consumer<Optional<R>> consumer);

    BaseResult<R, T> onError(Consumer<T> consumer);

    BaseResult<R, T> throwException();

}
