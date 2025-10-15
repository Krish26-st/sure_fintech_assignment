package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"parser", "web"})
public class BankParserApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankParserApplication.class, args);
    }
}
