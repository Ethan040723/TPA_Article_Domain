
package com.article.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.article")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
