package main;

import beans.MyBean;
import config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        //declare a context //plain context
        //i need to specify where the context will be configured as well.

        //Two ways we can configure a context is
        //(a) XML
        //(b) Annotations---More modern way


        //instance of the context
        //the default bean declaration in the spring context is singletonn
        try (var context = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
            MyBean b1 = context.getBean(MyBean.class);
            MyBean b2 = context.getBean(MyBean.class);
            MyBean b3 = context.getBean(MyBean.class);
            System.out.println(b1.getText());
            System.out.println(b2.getText());
            System.out.println(b3.getText());
        }

        //Bean unmanaged by the Context

//        MyBean b = new MyBean();
//        System.out.println(b.getText());


    }
}
