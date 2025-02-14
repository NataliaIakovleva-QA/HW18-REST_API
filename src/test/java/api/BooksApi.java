package api;

import io.qameta.allure.Step;
import models.*;

import java.util.ArrayList;
import java.util.List;


import static data.Paths.COLLECTION_PATH;
import static io.restassured.RestAssured.given;
import static specs.DemoQaSpec.*;

public class BooksApi {

    @Step("Удаление всех книг из профиля через API")
    public void deleteAllBooks(LoginResponseModel loginResponse) {
        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .delete(COLLECTION_PATH)
                .then()
                .spec(responseSpecWithStatusCode204);
    }

    @Step("Получение коллекции книг через API")
    public BookCollectionResponse requestBookCollection() {
        return given(requestSpec)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .spec(responseSpecWithStatusCode200)
                .extract().as(BookCollectionResponse.class);
    }

    @Step("Добавление новой книги через API")
    public AddBookResponse addBook(String isb, String token, String userId) {

        List<IsbnBookModel> books = new ArrayList<>();
        books.add(new IsbnBookModel(isb));

        AddBookBodyModel bookData = new AddBookBodyModel();
        bookData.setUserId(userId);
        bookData.setCollectionOfIsbns(books);
        return given(requestSpec)
                .header("Authorization", "Bearer " + token)
                .body(bookData)
                .when()
                .post(COLLECTION_PATH)
                .then()
                .spec(responseSpecWithStatusCode201)
                .extract().as(AddBookResponse.class);
    }
}
