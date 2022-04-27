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
public interface Post<I, U, R> {

    Result<R> post(I i, @Nullable HttpEntity<?> request, U... uriVariables);

    default Result<R> post(I i, U... uriVariables) {
        return post(i, null, uriVariables);
    }

    default Result<R> post(U... uriVariables) {
        return post(null, null, uriVariables);
    }

    default Result<R> post(@Nullable HttpEntity<?> request, U... uriVariables) {
        return post(null, request, uriVariables);
    }

}
