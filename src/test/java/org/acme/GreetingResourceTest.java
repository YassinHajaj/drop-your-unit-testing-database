package org.acme;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.GreetingResource.GreetingEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@QuarkusTestResource(MSSQLServerResource.class)
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        Assertions.assertEquals(0, GreetingEntity.count());

        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("hello !"));

        Assertions.assertEquals(1, GreetingEntity.count());
    }

}