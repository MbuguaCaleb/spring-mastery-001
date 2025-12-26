package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//Spring will scan for components inside the below packages
//Component scan tells spring where to find components annotataed with ther strereotype annotation
//For classes annotated with Component, they have to be scanned in order to be placed inside the Spring Context
@Configuration
@ComponentScan(basePackages = {"services", "repository"})
public class ProjectConfig {

}
