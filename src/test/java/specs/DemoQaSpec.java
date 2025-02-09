package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class DemoQaSpec {

    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplate())
            .log().all()
            .contentType(JSON);

    public static ResponseSpecification responseSpecWithStatusCode200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();
    public static ResponseSpecification responseSpecWithStatusCode201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(ALL)
            .build();
    public static ResponseSpecification responseSpecWithStatusCode204 = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(ALL)
            .build();
}
