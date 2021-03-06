package gmail.anastasiacoder.tests;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import com.codeborne.selenide.Configuration;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static gmail.anastasiacoder.filters.CustomLogFilter.customLogFilter;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static io.restassured.http.ContentType.JSON;



public class RestApiTests {
    static final String url = "http://demowebshop.tricentis.com/";

    @BeforeAll
    static void prepare() {
        RestAssured.baseURI = url;
        Configuration.baseUrl = url;
    }

    @Test
    void addToCartWithCookieTest() {
        Response response =
                given()
                        .filter(customLogFilter().withCustomTemplates())
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .body("product_attribute_16_5_4=14&product_attribute_16_6_5=15&" +
                                "product_attribute_16_3_6=19&product_attribute_16_4_7=44&" +
                                "product_attribute_16_8_8=22&addtocart_16.EnteredQuantity=1")
                        .cookie("Nop.customer=971260e5-3e4a-4f10-bd56-3f15a9b94ea6;")
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/details/16/1")
                        .then()
                        .statusCode(200)
                        .body("success", is(true))
                        .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                        .extract().response();

        System.out.println("Response: " + response.path("updatetopcartsectionhtml"));
    }

    @Test
    void checkUsersAddress() {
        String login = "test@qa.test";
        String password = "test@qa.test";
        SelenideElement address = $(".address-list");

        step("Get cookie and set it to browser by API", () -> {
            String authorizationCookie = given()
                    .filter(customLogFilter().withCustomTemplates())
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("Email", login)
                    .formParam("Password", password)
                    .when()
                    .post("login")
                    .then()
                    .statusCode(302)
                    .extract()
                    .cookie("NOPCOMMERCE.AUTH");

            step("Open minimal content, because cookie can be set when site is opened", () ->
                    open("Themes/DefaultClean/Content/images/logo.png"));

            step("Set cookie to to browser", () ->
                    getWebDriver().manage().addCookie(
                            new Cookie("NOPCOMMERCE.AUTH", authorizationCookie)));
        });

        step("Open user's address", () ->
                open("customer/addresses"));

        step("Check the address's title", () ->
                address.$(".title").shouldHave(text("Anastasia Tester")));

        step("Check the user's name", () ->
                address.$(".name").shouldHave(text("Anastasia Tester")));

        step("Check the email", () ->
                address.$(".email").shouldHave(text("Email: " + login)));

        step("Check the phone number", () ->
                address.$(".phone").shouldHave(text("Phone number: +7987654321")));

        step("Check the address", () ->
                address.$(".address1").shouldHave(text("Address")));

        step("Check the city, state, zip code", () ->
                address.$(".city-state-zip").shouldHave(text("city, AA (Armed Forces Americas) zip")));

        step("Check the country", () ->
                address.$(".country").shouldHave(text("United States")));
    }
}
