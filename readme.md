*Spring Context*

*What is Spring Context ?*


```
It is a  collection of many instances/classes that are known managed by Spring.
once an object is in the context, you can use that Object with Spring Functionality.

Everything we want managed by the Spring Framework must be Part of the Spring Context


```
```
Two ways we can configure a context is
        //(a) XML
        //(b) Annotations---More modern way

```
```
It is the dependency we need to be able to place beans/ classes into the
Spring Boot Context.

A Bean is a class whose instances will be placed in the Spring Context.

@Configration --> Marks the class as containing beans, that will be added to the context.
@Bean, adds classeses to the context, marks a class to be a bean
( Usually , @Bean in a Project is used for classes that are not your own, but you need to configure them in a way insidde
the context.



```

**How can i take up a Class and place it inside the context?**
```
1.Method One  - Using Bean annotation

we declare a method inside a configuration class and annotate it with @Bean
One way of declaring Beans and adding them into your context is via the Bean annotation
,


```

*How can i have two instances of the same bean in a context ?*

```
1.Solution one, having a Primary Bean that will be defaulted to incase we have multiple beans
  @Primary
  
 @Bean
    @Primary
    public MyBean myBean() {
        MyBean b = new MyBean();
        b.setText("Caleb & Milka");
        return b;
    }

    @Bean
    public MyBean myBean2() {
        MyBean b = new MyBean();
        b.setText("Shem & Mercy");
        return b;
    }
    
 2.Calling the Bean By Name instead of type/
 
 sample calling bean byType
 MyBean b1 = context.getBean(MyBean.class);
 
 Type can be ambigous if yoh have two beans of the sameType
 The method name of the Bean is the default name we use, not unless we decide to specify
 
 MyBean b1 = context.getBean("myBean1",MyBean.class);

I can choose to let myBean have myCustom name, example below, which will override the Bean Name

 @Bean("A")
    public MyBean myBean1() {
        MyBean b = new MyBean();
        b.setText("Caleb & Milka");
        return b;
    }
    
```

*Method 2 declaring Classes in the context*
```
Stereotypes annotation, @Component,

we want an instance of a certain class inside the context of Spring.
for our own custom classes we can just annotate with @Component

We should however not that by default spring does not scan for components,
it does not by default search by annottations,

In our Context Confuguration, we need to specify the base package where Spring will
begin the scan of components.


@Configuration
@ComponentScan(basePackages = "beans")
public class ProjectConfig {
}

Unlike the other style where i instante an instance as i create the Bin.
(You just tell Spring: Read on this Path)

the above does NOT create the component automatically inilialized not unless when
i use the post-construct.

if i want a class with @Compoment to run immediately after instantiation i use, 
@PostConstruct

@Component
public class MyBean {

    private String text;

    //Once the Bean is created, it will call the PostConstruct
    //Which will initialize the text
    @PostConstruct
    private void init(){
        this.text = "CALEB MBUGUA";
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

it instructs Spring that you have to call this method immediately after the creation of the component.

N/B ( A Post Construct can also work with @Bean Annotation) it does not matter to it how the component
got into the context.)

Usually with @Component, Only one type of that class is is instantiated and placed inside the context.
with @Bean there is a possiblity of instantiating many instances of the same class and place them inside
our context.

Other stereotype annotations are 

(a)@Controller
(b) @Service
(c) Repositoty
```

**Auto-Wiring**

```
Once we know how to create instances and place them within the context, auto-wiring
is the next main concept

Auowiring helps us to create a link between objects in the context.
```