package files;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.nio.charset.StandardCharsets.UTF_8;


public class TestData {
    PDF pdf;
    XLS xls;
    List<String[]> csv;

    void getFileFromZip(ClassLoader classLoader, String path) {
//        try (ZipInputStream zis = new ZipInputStream(Objects.requireNonNull(classLoader.getResourceAsStream(path)))) {
//            ZipEntry zipEntry = zis.getNextEntry();
//            while (zipEntry != null) {
//                String filename = zipEntry.getName();
//                byte[] allBytes = zis.readAllBytes();
//                if (filename.contains(".pdf")) {
//                    pdf = new PDF(allBytes);
//                } else if (filename.contains(".csv")) {
//                    try (CSVReader csvReader = new CSVReader(new InputStreamReader(new ByteArrayInputStream(allBytes), UTF_8))) {
//                        csv = csvReader.readAll();
//                    }
//                } else if (filename.contains(".xlsx") || filename.contains(".xls")) {
//                    xls = new XLS(allBytes);
//                }
//                zipEntry = zis.getNextEntry();
//            }
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
    }
}
