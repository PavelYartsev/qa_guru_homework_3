import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TestGithubPage {

    @Test
    void findSomethingInGithub() {
        String testUrl = "https://github.com/";

        open(testUrl);
        $(".application-main").shouldHave(text("Sign up for GitHub"));
        $("[name=q]").setValue("selenide").pressEnter();
        $(".repo-list").shouldHave(text("selenide/selenide"));
        $(".repo-list a").click();
        $("h1").shouldHave(text("selenide / selenide"));
        $$("ul.UnderlineNav-body li").findBy(text("Wiki")).click();
        $(".application-main").shouldHave(text("Welcome to the selenide wiki!"));
        $$(".wiki-rightbar ul li").findBy(text("SoftAssertions")).click();
        $(".application-main").shouldHave(text("How to soft assert using Selenide"));
        $(".markdown-body").shouldHave(text("Using JUnit5 extend test class")).shouldBe(visible);
    }

    @Test
    void dragAndDropToTest() {
        String testUrl = "https://the-internet.herokuapp.com/drag_and_drop";

        open(testUrl);
        $("#column-a").dragAndDropTo($("#column-b"));
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
        $("#column-a").dragAndDropTo($("#column-b"));
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));
    }

    @Test
    void dragAndDropActionsTest() {
        String testUrl = "https://the-internet.herokuapp.com/drag_and_drop";
        open(testUrl);
        actions().moveToElement($("#column-a")).clickAndHold().moveByOffset(250, 0).release().perform();
        actions().dragAndDrop($("#column-a"), $("#column-b")).perform();
        actions().dragAndDropBy($("#column-a"), 50, 0).perform();
    }
}
