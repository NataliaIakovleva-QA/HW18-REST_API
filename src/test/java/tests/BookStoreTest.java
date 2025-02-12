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

    @Test
    @Tag("books_test")
    @WithLogin
    void successDeleteBookFromProfileTest() {

        BookCollectionResponse collection = BooksApi.requestBookCollection();

        LoginResponseModel authResponse = AuthorizationApi.login();

        BooksApi.deleteAllBooks(authResponse);

        final String isbn = collection.getBooks()[BOOK_NO].getIsbn();
        BooksApi.addBook(isbn, authResponse.getToken(), authResponse.getUserId());

        profilePage.googleConsent()
                .openPage();

        profilePage.checkForBook(isbn);

        profilePage.deleteBook();

        profilePage.confirmDelete();

        profilePage.checkTableBody(collection.getBooks()[BOOK_NO].getTitle());

    }



    }


