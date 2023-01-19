package tests;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class WikipediaTests extends TestBase {

    @Owner("Bibol Dauren")
    @Test
    @DisplayName("Success search")
    void successSearchTest() {
        back();
        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Python");
        });
        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(sizeGreaterThan(0)));
    }

    @Owner("Bibol Dauren")
    @Test
    @DisplayName("Open article")
    void openArticleTest() {
        back();
        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Iron Maiden");
        });
        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(sizeGreaterThan(0)));
        step("Choose page", () -> {
            $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).findBy(text("Iron Maiden")).click();
            $(AppiumBy.className("android.webkit.WebView")).click();
        });
        step("Verify opened page", () ->
                $(AppiumBy.className("android.widget.TextView")).shouldHave(text("Iron Maiden")));
    }

    @Owner("Bibol Dauren")
    @Test
    @DisplayName("Add language")
    void addLanguageTest() {
        back();
        step("Type search", () ->
                $(AppiumBy.accessibilityId("Search Wikipedia")).click());
        step("Add new language", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/search_lang_button")).click();
            $$(AppiumBy.id("org.wikipedia.alpha:id/wiki_language_title")).findBy(text("ADD LANGUAGE")).click();
            $$(AppiumBy.id("org.wikipedia.alpha:id/localized_language_name")).findBy(text("Español")).click();
        });
        step("Verify added language", () ->
                $$(AppiumBy.className("android.widget.TextView")).findBy(text("Español")).shouldBe(visible));
    }

    @Owner("Bibol Dauren")
    @Test
    @DisplayName("Onboarding screen")
    void getOnBoardingTest() {
        step("Check 'The Free Encyclopedia …in over 300 languages' text is visible", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("The Free Encyclopedia …in over 300 languages"));
        });

        step("Click on 'Сontinue'", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());

        step("Check 'New ways to explore' text is visible", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("New ways to explore"));
        });

        step("Click on 'Сontinue'", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());

        step("Check 'Reading lists with sync' text is visible", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Reading lists with sync"));
        });

        step("Click on 'Сontinue'", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());

        step("Check 'Send anonymous data' text is visible", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Send anonymous data"));
        });
    }
}