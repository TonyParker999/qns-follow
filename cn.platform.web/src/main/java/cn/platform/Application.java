package cn.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
    private static String[] args;
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Application.args = args;
        context = SpringApplication.run(Application.class, args);
    }
}