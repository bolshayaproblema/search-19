package github;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class SelenideRepositorySearch {

    @Test
    void shouldFindSelenideRepository() {
        open ("https://github.com/");

        // - Откройте страницу Selenide в Github
        $("[name='q']").setValue("Selenide").pressEnter();
        $$("ul.repo-list li").first().$("a").click();

        // - Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();

        // - Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $("#wiki-pages-filter").setValue("SoftAssertions");

        // - Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
        $(byText("SoftAssertions")).click();

        $("#wiki-content").shouldHave(text("Using JUnit5 extend test class:"), text("@ExtendWith({SoftAssertsExtension.class})\n" +
        "class Tests {\n" +
        "  @Test\n" +
        "  void test() {\n" +
        "    Configuration.assertionMode = SOFT;\n" +
        "    open(\"page.html\");\n" +
        "\n" +
        "    $(\"#first\").should(visible).click();\n" +
        "    $(\"#second\").should(visible).click();\n" +
        "  }\n" +
        "}")
        );
        sleep(5000);
    }

}
