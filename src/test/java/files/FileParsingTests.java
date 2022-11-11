package files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import static files.Katze.getKatzeFromJson;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FileParsingTests extends TestBase {

    @Disabled
    @BeforeEach
    void prepareFiles() {
        testData.getFileFromZip(classLoader, "files.zip");
    }

    @Disabled
    @Test
    void pdfTest() {
        assertThat(testData.pdf.text).contains("RML Example");
    }

    @Disabled
    @Test
    void xlsTest() {
        assertThat(testData.xls.excel
                .getSheetAt(0)
                .getRow(1)
                .getCell(1)
                .getStringCellValue()).contains("Dulce");
    }

    @Disabled
    @Test
    void csvTest() {
        assertThat(testData.csv.get(1)).contains("150000", "2016-01-01", "Chris Riley", "trailhead9.ub20k5i9t8ou@example.com");
    }

    @Test
    void jsonTest() {
        Katze katze = getKatzeFromJson(om, classLoader, "katze.json");
        assertThat(katze.getAge()).isEqualTo(3);
        assertThat(katze.isMale()).isEqualTo(false);
        assertThat(katze.getChildren()).contains("Bane");
    }
}
