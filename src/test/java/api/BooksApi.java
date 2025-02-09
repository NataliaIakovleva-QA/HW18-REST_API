package api;

import models.*;

import java.util.ArrayList;
import java.util.List;


import static data.BaseUri.BaseURIs.collectionURI;
import static io.restassured.RestAssured.given;
import static specs.DemoQaSpec.*;

public class BooksApi {
    public static void deleteAllBooks(LoginResponseModel loginResponse) {
        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .delete(collectionURI)
                .then()
                .spec(responseSpecWithStatusCode204);
    }

    public static BookCollectionResponse requestBookCollection() {
        return given(requestSpec)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .spec(responseSpecWithStatusCode200)
                .extract().as(BookCollectionResponse.class);
    }

    public static AddBookResponse addBook(String isb, String token, String userId) {

        List<IsbnBookModel> books = new ArrayList<>();
        books.add(new IsbnBookModel(isb));

        AddBookBodyModel bookData = new AddBookBodyModel();
        bookData.setUserId(userId);
        bookData.setCollectionOfIsbns(books);
        return given(requestSpec)
                .header("Authorization", "Bearer " + token)
                .body(bookData)
                .when()
                .post(collectionURI)
                .then()
                .spec(responseSpecWithStatusCode201)
                .extract().as(AddBookResponse.class);
    }
}
