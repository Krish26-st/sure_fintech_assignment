package parser;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PDFTextExtractor {

    public static String extractText(String filePath) {
        Path path = Paths.get(filePath);
        try (PDDocument document = Loader.loadPDF(path.toFile())) { // correct for PDFBox 3.0.5
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String extractText(MultipartFile file) {
        try (InputStream in = file.getInputStream();
             PDDocument document = Loader.loadPDF(in.readAllBytes())) { // correct for PDFBox 3.0.5
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
