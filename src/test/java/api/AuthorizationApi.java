package api;

import io.qameta.allure.Step;
import models.LoginBodyModel;
import models.LoginResponseModel;

import static data.Paths.LOGIN_PATH;
import static data.Credentials.PASSWORD;
import static data.Credentials.USERNAME;
import static io.restassured.RestAssured.given;
import static specs.DemoQaSpec.requestSpec;
import static specs.DemoQaSpec.responseSpecWithStatusCode200;

public class AuthorizationApi {

     @Step("Авторизация через API")
     public LoginResponseModel login(){
          LoginBodyModel credentialsModel = new LoginBodyModel();
          credentialsModel.setUserName(USERNAME);
          credentialsModel.setPassword(PASSWORD);

          return
                  given(requestSpec)
                          .body(credentialsModel)
                          .when()
                          .post(LOGIN_PATH)
                          .then()
                          .spec(responseSpecWithStatusCode200)
                          .extract().as(LoginResponseModel.class);
     }
}


