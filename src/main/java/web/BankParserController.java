package web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import parser.PDFTextExtractor;
import parser.IssuerDetector;
import parser.StatementParser;

import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Controller
public class BankParserController {

    @GetMapping("/")
    public String uploadForm() {
        return "upload";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("files") MultipartFile[] files, Model model, HttpSession session) throws IOException {
        List<Map<String, String>> allData = new ArrayList<>();

        for (MultipartFile file : files) {
            String text = PDFTextExtractor.extractText(file);
            String issuer = IssuerDetector.detectIssuer(text);
            Map<String, String> extracted = StatementParser.extractData(text, issuer);
            allData.add(extracted);
        }

        String csv = buildCSV(allData);
        session.setAttribute("csvData", csv);

        model.addAttribute("dataList", allData);
        return "upload";
    }


    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadCsv(HttpSession session) {
        Object csvObj = session.getAttribute("csvData");
        if (csvObj == null) {
            String msg = "No CSV available. Please upload files first.";
            return ResponseEntity.badRequest()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(msg.getBytes());
        }

        String csv = csvObj.toString();
        byte[] bytes = csv.getBytes();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"parsed_statements.csv\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(bytes);
    }


    private String buildCSV(List<Map<String, String>> dataList) {
        if (dataList == null || dataList.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        List<String> headers = new ArrayList<>(dataList.get(0).keySet());
        StringJoiner hj = new StringJoiner(",");
        for (String h : headers) {
            hj.add('"' + h + '"');
        }
        sb.append(hj.toString()).append("\n");

        for (Map<String, String> row : dataList) {
            StringJoiner sj = new StringJoiner(",");
            for (String h : headers) {
                String v = row.getOrDefault(h, "");
                v = v.replace("\"", "\"\"");
                sj.add('"' + v + '"');
            }
            sb.append(sj.toString()).append("\n");
        }

        return sb.toString();
    }
}
