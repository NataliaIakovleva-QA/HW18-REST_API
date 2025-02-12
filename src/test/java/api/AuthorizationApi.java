package api;

import io.qameta.allure.Step;
import models.LoginBodyModel;
import models.LoginResponseModel;

import static data.Paths.loginPath;
import static data.Credentials.PASSWORD;
import static data.Credentials.USERNAME;
import static io.restassured.RestAssured.given;
import static specs.DemoQaSpec.requestSpec;
import static specs.DemoQaSpec.responseSpecWithStatusCode200;

public class AuthorizationApi {

     @Step("Авторизация через API")
     public static LoginResponseModel login(){
          LoginBodyModel credentialsModel = new LoginBodyModel();
          credentialsModel.setUserName(USERNAME);
          credentialsModel.setPassword(PASSWORD);

          return
                  given(requestSpec)
                          .body(credentialsModel)
                          .when()
                          .post(loginPath)
                          .then()
                          .spec(responseSpecWithStatusCode200)
                          .extract().as(LoginResponseModel.class);
     }
}


