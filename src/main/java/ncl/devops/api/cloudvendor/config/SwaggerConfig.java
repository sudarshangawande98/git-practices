package ncl.devops.api.cloudvendor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${cloud-vendor.dev-url}")
    private String devUrl;

    @Value("${book-management.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Cloud-Vendor Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Book Management Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("sudarshng@nse.co.in");
        contact.setName("Sudarshan");
        contact.setUrl("https://www.linkedin.com/in/sudarshan-gawande-187b141a7/");

        License mitLicense = new License().name("NSE License").url("https://www.nseindia.com/");

        Info info = new Info()
                .title("API Management By SUDARSHAN")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage tutorials.").termsOfService("https://www.linkedin.com/in/sudarshan-gawande-187b141a7/")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}