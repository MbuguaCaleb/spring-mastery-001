package beans;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    private String text;

    //Once the Bean is created, it will call the PostConstruct
    //Which will initialize the text
    //Post construct can only be used when a class is inside the Spring Context

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
