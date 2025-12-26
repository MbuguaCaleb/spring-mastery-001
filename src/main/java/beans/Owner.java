package beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Owner {

    //Leave Spring to do the Wiring for you
    //If Spring finds a Cat instance in the context, it will just inject it for you
    //By placing Autowired here below, we tell Spring that we want
    // a Bean from the context


    //Whatever is bring since it is a dependency, it must Be a Final
    //With no possibility to change it,
    //final means its a constant
    @Autowired
    @Qualifier("cat2")
    private  Cat cat;


    public Cat getCat() {return cat;}

    @Override
    public String toString() {
        return "Owner{" +
                "cat=" + cat +
                '}';
    }
}
