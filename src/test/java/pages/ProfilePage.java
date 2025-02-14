package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static data.Paths.PROFILE_PATH;


public class ProfilePage {
    final private SelenideElement mainHeader = $(".main-header"),
            deleteButton = $("#delete-record-undefined"),
            okButton = $("#closeSmallModal-ok"),
            consentBanner = $(".fc-consent-root"),
            tableBody = $(".ReactTable");

    @Step("Открытие профиля")
    public ProfilePage openPage() {
        open(PROFILE_PATH);

        return this;
    }
    @Step("Проверка баннера")
    public ProfilePage googleConsent() {
        if (consentBanner.isDisplayed()) {
            consentBanner.$(byText("Consent")).click();
        }
        else{
            System.out.println("No consent banner");
        }
        return this;
    }

    @Step("Проверка, что в коллекции есть книга {isbn}")
    public ProfilePage checkForBook(String isbn) {
        tableBody.shouldHave(text(isbn));
        return this;
    }

    @Step("Удаление книги через UI")
    public ProfilePage deleteBook() {
        deleteButton.click();
        return this;
    }
    @Step("Подтверждение удаления книги")
    public ProfilePage confirmDelete() {
        okButton.click();
        Selenide.switchTo().alert().accept();
        Selenide.switchTo().parentFrame();
        return this;
    }
    @Step("Проверка, что в коллекции нет книги {isbn}")
    public ProfilePage checkTableBody(String isbn) {
        tableBody.shouldNotHave(text(isbn));
        return this;
    }

}