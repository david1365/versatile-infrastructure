package ir.caspco.versatile.context.util;

import lombok.SneakyThrows;

import java.net.URI;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public interface UriUtil {

    @SneakyThrows
    default URI getUri(String Url) {
        return new URI(getPrefixUri().toString() + Url);
    }

    @SneakyThrows
    default URI getRealUri(String Url) {
        return new URI(getRealPrefixUri().toString() + Url);
    }

    URI getPrefixUri();

    URI getRealPrefixUri();
}

