package ir.caspco.versatile.rest.client;


import ir.caspco.versatile.common.util.Shift;
import ir.caspco.versatile.context.rest.client.exceptions.RestClientErrorException;
import ir.caspco.versatile.rest.client.configuration.RestClientBeanConfiguration;
import ir.caspco.versatile.rest.client.model.DateModel;
import ir.caspco.versatile.rest.client.model.Input;
import ir.caspco.versatile.rest.client.model.Output;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Davood Akbari - 1398
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        RestClientBeanConfiguration.class,
        RestTemplate.class,
        ClientChildBaseUrl.class,
        ClientTrowsException.class
})
@TestPropertySource(locations = "classpath:rest-client.properties")
public class ClientTest {

    @Autowired
    private ClientChildBaseUrl clientChildBaseUrl;

    @Autowired
    private ClientTrowsException clientTrowsException;

    @Autowired
    private RestTemplate restTemplate;

    private final String serverAddress = "http://localhost";

    private MockRestServiceServer mockServer;

    private String fullPath = "";


    @BeforeEach
    public void setUp() {

        mockServer = MockRestServiceServer.createServer(restTemplate);

    }

    @Test
    public void ClientPostBaseUrlTest() {
        Input input = new Input();
        input.setId(10);

        Output output = new Output();
        output.setName("Mohammad Taha");
        output.setFamily("Nazani Fatemeh");

        fullPath = serverAddress + "/davood/post-test";

        mockServer.expect(requestTo(fullPath))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(Shift.just(output).toJson(), MediaType.APPLICATION_JSON));

        Output directResult = clientChildBaseUrl.postTest.post(input)
                .onSuccess(result -> {

                    mockServer.verify();

                    if (result.isPresent()) {

                        assertEquals(output.getName(), result.get().getName());

                        assertEquals(output.getFamily(), result.get().getFamily());

                    }

                })
                .onError(exception -> fail())
                .retrieve()
                .result().orElse(new Output());


        assertEquals(output.getName(), directResult.getName());
        assertEquals(output.getFamily(), directResult.getFamily());

    }

    @Test
    public void PostDateFormatTest() {
        DateModel input = DateModel.builder()
                .date(new Date())
                .build();

        DateModel output = DateModel.builder()
                .date(input.getDate())
                .build();


        final String dataFormat = "yyyy/MM-dd";
        String json = Shift.just(output).dateFormat(dataFormat).toJson();
        DateModel expectedDateModel = Shift.just(json).dateFormat(dataFormat).toShift(DateModel.class).toObject();

        fullPath = serverAddress + "/davood/postGlobalFormat";

        mockServer.expect(requestTo(fullPath))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

        clientChildBaseUrl.postGlobalFormat.post(input)
                .onSuccess(result -> {

                    mockServer.verify();

                    result.ifPresent(dateModel -> assertEquals(expectedDateModel.getDate(), dateModel.getDate()));

                })
                .onError(exception -> fail())
                .retrieve();

    }

    @Test
    public void ClientPostBaseUrlTestOnError() {

        AtomicReference<Throwable> atomicReference = new AtomicReference<>();
        try {

            clientChildBaseUrl.postTest.post(null)
                    .onSuccess(result -> fail())
                    .onError(error -> {

                        atomicReference.set(error);

                        String expectedMsg = "'uriVariableValues' must not be null";

                        assertEquals(expectedMsg, error.getMessage());

                    })
                    .throwException()
                    .retrieve();

            fail();

        } catch (Exception e) {

            Throwable throwable = atomicReference.get();
            assertTrue(throwable.getClass().isAssignableFrom(e.getClass()));

        }

    }

    @Test
    public void ClientTrowsException() {

        AtomicReference<Throwable> atomicReference = new AtomicReference<>();
        try {

            clientTrowsException.postTest.post(null)
                    .onSuccess(result -> fail())
                    .onError(error -> {

                        atomicReference.set(error);

                        String expectedMsg = "'uriVariableValues' must not be null";

                        assertEquals(expectedMsg, error.getMessage());

                    })
                    .throwException()
                    .retrieve();

            fail();

        } catch (Exception e) {

            assertTrue(e instanceof RestClientErrorException);

            Throwable target = ((RestClientErrorException) e).getTarget();

            Throwable throwable = atomicReference.get();
            assertTrue(throwable.getClass().isAssignableFrom(target.getClass()));

        }

    }

    @Test
    public void ClientPostNoInputBaseUrlTest() {
        Output output = new Output();
        output.setName("Mohammad Taha");
        output.setFamily("Nazani Fatemeh");

        fullPath = serverAddress + "/davood/post-no-input-test";

        mockServer.expect(requestTo(fullPath))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(Shift.just(output).toJson(), MediaType.APPLICATION_JSON));

        clientChildBaseUrl.postNoInputTest.post()
                .onSuccess(result -> {

                    mockServer.verify();

                    if (result.isPresent()) {

                        assertEquals(output.getName(), result.get().getName());

                        assertEquals(output.getFamily(), result.get().getFamily());

                    }

                })
                .retrieve();


    }

    @Test
    public void ClientGetBaseUrlTest() {
        Output output = new Output();
        output.setName("Mohammad Taha");
        output.setFamily("Nazani Fatemeh");

        fullPath = serverAddress + "/davood/from-properties";

        mockServer.expect(requestTo(fullPath))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(Shift.just(output).toJson(), MediaType.APPLICATION_JSON));

        Output response = clientChildBaseUrl.getTest.get()
                .retrieve().result().orElse(new Output());

        mockServer.verify();

        assertEquals(output.getName(), response.getName());

        assertEquals(output.getFamily(), response.getFamily());
    }

}