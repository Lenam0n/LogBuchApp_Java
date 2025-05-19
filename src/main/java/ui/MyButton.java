package ui;

import javax.swing.JButton;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MyButton extends JButton {
    public MyButton() {
        super("Klick mich");
        addActionListener(e -> System.out.println("Button gedrückt!"));
    }
}
