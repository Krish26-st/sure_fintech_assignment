package parser;

public class IssuerDetector {
    public static String detectIssuer(String text) {
        if (text == null) return "Unknown Issuer";
        text = text.toUpperCase();
        if (text.contains("HDFC")) return "HDFC Bank";
        if (text.contains("ICICI")) return "ICICI Bank";
        if (text.contains("SBI")) return "SBI Card";
        if (text.contains("AXIS")) return "Axis Bank";
        if (text.contains("AMERICAN EXPRESS") || text.contains("AMEX")) return "American Express";
        return "Unknown Issuer";
    }
}
