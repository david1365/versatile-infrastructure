package ir.caspco.versatile.rest.client;

/**
 * @author Davood Akbari - 1398
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

import ir.caspco.versatile.context.rest.client.annotations.*;
import ir.caspco.versatile.context.rest.client.interfaces.Post;
import ir.caspco.versatile.rest.client.model.Input;
import ir.caspco.versatile.rest.client.model.Output;
import org.springframework.stereotype.Component;

@Component
@Headers({@Header(key = "application", value = "header")})
@ApplicationBaseUrl("http://localhost/davood")
@JsonDateFormat("yyyy/MM-dd")
@ThrowException
public class ClientTrowsException extends RestClient {

    @ResponsePath(servicePath = "/post-test", outputType = Output.class)
    public Post<Input, Void, Output> postTest;

}
