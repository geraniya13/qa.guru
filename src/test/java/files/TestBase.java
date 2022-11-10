package files;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestBase {
    TestData testData = new TestData();
    ClassLoader classLoader = TestBase.class.getClassLoader();
    ObjectMapper om = new ObjectMapper();

    @BeforeAll
    static void configure() {
        Configuration.browserSize = "1920x1080";
    }
}
