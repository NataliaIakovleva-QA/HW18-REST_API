package tests;

import api.AuthorizationApi;
import api.BooksApi;
import helpers.WithLogin;

import models.BookCollectionResponse;
import models.LoginResponseModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import pages.ProfilePage;


public class BookStoreTest extends TestBase {
    private final int BOOK_NO = 0;
    ProfilePage profilePage = new ProfilePage();
    BooksApi booksApi = new BooksApi();

    @Test
    @Tag("books_test")
    @WithLogin
    void successDeleteBookFromProfileTest() {

        BookCollectionResponse collection = booksApi.requestBookCollection();

        LoginResponseModel authResponse = new AuthorizationApi().login();

        booksApi.deleteAllBooks(authResponse);

        final String isbn = collection.getBooks()[BOOK_NO].getIsbn();
        final String title = collection.getBooks()[BOOK_NO].getTitle();
        booksApi.addBook(isbn, authResponse.getToken(), authResponse.getUserId());

        profilePage.googleConsent()
                .openPage();

        profilePage.checkForBook(title);

        profilePage.deleteBook();

        profilePage.confirmDelete();

        profilePage.checkTableBody(title);
    }

}
