package megacvet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


@DisplayName("Тесты работы цветочного магазина")
public class SearchTests extends TestBase {

    @CsvSource(value = {"День рождения,Пион", "Свидание,Роза", "Юбилей,Ирис"})
    @ParameterizedTest(name = "На {0} предлагается {1}")
    void OfferingTest(String event, String flower) {
        open("/");
        $("a[href='https://megacvet24.ru/povod/']").hover();
        $$("li.menu_title-3").find(text(event)).lastChild().click();
        $("body").shouldHave(text(flower));
    }

    @CsvFileSource(files = "src/test/resources/goods.csv")
    @ParameterizedTest(name = "На странице с декором {0} отображается элементов {1}")
    void QuantityTest(String deco, String quantity) {
        open("/dekor-dlya-buketov/");
        $$("div.catalog_filter ul li").find(text(deco)).lastChild().click();
        $$("img.img-responsive").shouldHave(size(Integer.parseInt(quantity) + 1));
    }

    static Stream<Arguments> CalculatorTest() {
        return Stream.of(
                Arguments.of("Гербера", 179, "Малиновая", 30),
                Arguments.of("Калла", 299, "Белая", 10),
                Arguments.of("Хризантема", 199, "Карамельная", 10)
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Общая стоимость для цветка {0} {2} с ценой {1} рублей за штуку в упаковке из {3} штук рассчитывается верно")
    void CalculatorTest(String flower, int price, String color, int pack) throws InterruptedException {
        open("/");
        $("div.minicalc-body a.choose").click();
        $$("div.flower").find(text(flower)).find(".flower_image").click();
        $("#calc_for_index div.minicalc-body div.param-color button").shouldNotBe(disabled).click();
        $$("div.flower").find(text(flower + " " + color)).find(".flower_image").click();
        $("div.itog span").shouldHave(text(price * pack + ""));
    }
}
