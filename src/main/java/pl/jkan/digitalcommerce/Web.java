package pl.jkan.digitalcommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import pl.jkan.digitalcommerce.web.controller.ProductsController;

import java.util.Map;

@SpringBootApplication
@ComponentScan({
    "pl.jkan.digitalcommerce.components",
    "pl.jkan.digitalcommerce.web"
})
public class Web {
    public static void main(String[] args) throws Exception {

        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n", envName, env.get(envName));
        }

        SpringApplication.run(Web.class, args);
    }
}
