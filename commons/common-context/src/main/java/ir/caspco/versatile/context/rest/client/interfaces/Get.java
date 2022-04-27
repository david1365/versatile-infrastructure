package ir.caspco.versatile.context.rest.client.interfaces;

import ir.caspco.versatile.context.common.util.result.Result;
import org.springframework.http.HttpEntity;
import org.springframework.lang.Nullable;

/**
 * @author Davood Akbari - 1398
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@FunctionalInterface
@SuppressWarnings({"unchecked", "varargs"})
public interface Get<U, R> {

    Result<R> get(@Nullable HttpEntity<?> request, U... uriVariables);

    default Result<R> get(U... uriVariables) {
        return get(null, uriVariables);
    }

}
