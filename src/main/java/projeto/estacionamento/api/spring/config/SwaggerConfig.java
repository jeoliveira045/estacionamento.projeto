package projeto.estacionamento.api.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket getDocket(){
        return new Docket(DocumentationType.SWAGGER_2) // Define qual a aplicação para a documentação
        .select()
        .apis(RequestHandlerSelectors.basePackage("projeto.estacionamento.api.spring")) // define o pacote que será documentado
        .build()
        .apiInfo(metaData()); // Define de onde a informação da parte inicial da documentação sera extraída
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
        .title("Estacionamento REST API") // Titulo da página inicial
        .description("Um conjunto de APIs REST que realizam um controle de estacionamento") // Descrição da página inicial
        .version("1.0.0") // Versão da nossa api
        .license("Apache License Version") // Licença usada
        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"") // Endereço da licença usada
        .build();
    }
}
