package config;

import beans.Cat;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "beans")
public class ProjectConfig {

//    @Bean
//    public Cat cat() {
//        //cat in the context has a name called Tom
//        Cat cat = new Cat();
//        cat.setName("Tom");
//        return cat;
//    }

    /*
    @Bean
    public Owner owner() {
        Owner owner = new Owner();

        //the below is another cat, not the one we have in the context
        //When we call a method from another Bean annotated Method, spring gets it from the context other than creating a new instance
        owner.setCat(cat());
        return owner;
    }*/


    //since Cat is a Bean,and is a method param
    //that value that is in the context is automatically injected tp the owner
//    @Bean
//    public Owner owner (Cat cat){
//        Owner owner = new Owner();
//        owner.setCat(cat);
//        return owner;
//    }

    @Bean
    @Qualifier("cat1")
    public Cat cat1(){
        Cat cat = new Cat();
        cat.setName("Tom");
        return cat;
    }

    @Bean
    @Qualifier("cat2")
    public Cat cat2(){
        Cat cat = new Cat();
        cat.setName("Jerry");
        return cat;
    }
}
