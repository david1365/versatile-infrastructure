package ir.caspco.versatile.context.common.util.result;

import java.util.Optional;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public final class ResultClass<R> {

    private final R result;

    ResultClass() {
        this.result = null;
    }

    ResultClass(R result) {
        this.result = result;
    }

    public Optional<R> result() {
        return Optional.ofNullable(result);
    }

}
