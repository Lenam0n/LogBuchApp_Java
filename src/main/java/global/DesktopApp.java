package global;

import controller.MenuController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.swing.SwingUtilities;

@SpringBootApplication
@ComponentScan(basePackages = {    
	    "controller",
	    "view",
	    "ui",
	    "util","service"
	})
public class DesktopApp {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless",               "false");
        System.setProperty("spring.main.web-application-type", "none");

        ConfigurableApplicationContext ctx = SpringApplication.run(DesktopApp.class, args);

        SwingUtilities.invokeLater(() -> {
            ctx.getBean(MenuController.class);
        });
    }
}
