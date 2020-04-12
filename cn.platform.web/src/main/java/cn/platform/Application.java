package cn.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan({"cn.platform.*.dao"})
public class Application {
    private static String[] args;
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Application.args = args;
        context = SpringApplication.run(Application.class, args);
    }
}