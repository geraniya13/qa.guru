package megacvet;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://megacvet24.ru";
        Configuration.browserSize = "1920x1080";
    }
}
