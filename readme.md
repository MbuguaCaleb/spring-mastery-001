*Spring Context*

*What is Spring Context ?*

```
It is a  collection of many instances/classes that are known managed by Spring.
once an object is in the context, you can use that Object with Spring Functionality.

Everything we want managed by the Spring Framework must be Part of the Spring Context

A group of instances that we want managed by the
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
When using stereotype annotations we do not have instances automatically instantiated,
even after being placed kwa context

This is where autowiring begin.

(i)Field Injection

      @Autowiring
    private  ProductRepository productRepository;

//Since the repository does not have to change, it should be final
//if i autowire a field directly it cannot be final since it does not have a value yet.

(ii) constructor injection
Coding best practice, what does not change should be made final

    private final ProductRepository productRepository;

    @Autowired
    public ProductDeliveryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
(a)Wiring

How do we link instances on the context??

The below is another cat, not the one we have in the context
When we call a method from another Bean annotated Method, spring gets it from the context other than creating a new instance

@Configuration
public class ProjectConfig {

    @Bean
    public Cat cat() {
        //cat in the context has a name called Tom
        Cat cat = new Cat();
        cat.setName("Tom");
        return cat;
    }

    @Bean
    public Owner owner() {
        Owner owner = new Owner();

        //the below is another cat, not the one we have in the context
        //When we call a method from another Bean annotated Method, spring gets it from the context other than creating a new instance
        owner.setCat(cat());
        return owner;
    }

Wiring, is when we have two beans, one called inside another,
Spring does not create a new instance rather just gets the value from the context.

(b)Auto-Wiring

Since Cat is a Bean,and is a method param
That value that is in the context is automatically injected to the owner
    
    @Bean
    public Owner owner ( Cat cat){
        Owner owner = new Owner();
        owner.setCat(cat);
        return owner;
    }
    
}


Why is using autowired directly for fields in a Spring application discouraged,

Since Beans in Spring Boot are SingleTon, it is the best practice to have all the fields final
When using a final we have to provide a value in order to be able to Compile,

@Component
public class Owner {

    //Leave Spring to do the Wiring for you
    //If Spring finds a Cat instance in the context, it will just inject it for you
    //By placing Autowired here below, we tell Spring that we want
    // a Bean from the context


    //Whatever is bring since it is a dependency, it must Be a Final
    //With no possibility to change it,
    //final means its a constant
    private final Cat cat;

    @Autowired
    public Owner(Cat cat) {
        this.cat = cat;
    }
 }
 
When i use autowired on Top of a Bean,

(a)It forces the existence of that class in the context.
(b)It thows an execption if tow beans are named the same

There is also the possibility of using autowired false if we want a bean to be ignored


 @Autowired(required = false)
  private  Cat cat;
  
Spring will always throw exceptions, if i do not follow the rules...wooow


Another way of hanldeing mulitiple beans is Qaulifier
 ( Qualifier is a ByName injection that we can use together with Autowired
 
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
   
   As i inject now the bean that has multiple instances i can use @Qualifier,
   
    @Autowired
    @Qualifier("cat2")
    private  Cat cat;


```