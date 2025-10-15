package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

public class StatementParser {

    public static Map<String, String> extractData(String text, String issuer) {
        Map<String, String> data = new HashMap<>();
        data.put("Issuer", issuer);

        data.put("Cardholder Name", matchPattern(text, "(?i)(?:Cardholder\\s*Name|Name)\\s*[:\\-]\\s*(.*?)\\s*(?:Card Number|$)"));
        data.put("Last 4 Digits", matchPattern(text, "\\*{4}[\\s\\-]*([0-9]{4})"));
        data.put("Billing Period", matchPattern(text, "(?i)(?:Billing\\s*(?:Period|Cycle)|Statement\\s*Period)\\s*[:\\-]\\s*(.*?)(?:\\r?\\n|$)"));
        data.put("Payment Due Date", matchPattern(text, "(?i)Payment\\s*Due\\s*Date\\s*[:\\-]\\s*([A-Za-z0-9\\-\\./]+)"));
        data.put("Total Amount Due", matchPattern(text, "(?i)Total\\s*Amount\\s*Due\\s*[:\\-]?\\s*(?:Rs\\.?|₹)?\\s*([\\d,\\.]+)"));

        data.replaceAll((k, v) -> sanitizeValue(v));

        return data;
    }

    private static String matchPattern(String text, String regex) {
        if (text == null) return "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find() && matcher.groupCount() >= 1 && matcher.group(1) != null) {
            return matcher.group(1).trim();
        } else {
            return "";
        }
    }

    private static String sanitizeValue(String value) {
        if (value == null || value.trim().isEmpty()) return "N/A";
        value = value.trim();

        // Remove commas (avoid CSV confusion)
        value = value.replace(",", "");

        // Optionally, wrap values that contain spaces or currency symbols in quotes
        if (value.contains(" ") || value.contains("₹") || value.contains(":")) {
            value = "\"" + value + "\"";
        }

        return value;
    }

}
