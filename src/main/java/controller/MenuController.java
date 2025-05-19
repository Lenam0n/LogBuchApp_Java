// src/main/java/controller/MenuController.java
package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import interfaces.PageController;
import view.MenuFrame;
import ui.Button;

import java.util.Iterator;
import java.util.Map;

@Component
@Scope("prototype")
public class MenuController {

    private final MenuFrame view;
    private final ApplicationContext ctx;

    @Autowired
    public MenuController(MenuFrame view, ApplicationContext ctx) {
        this.view = view;
        this.ctx  = ctx;
    }

    @PostConstruct
    private void init() {
        Iterator<Map.Entry<String, Class<? extends PageController>>> it =
            view.getTopicMap().entrySet().iterator();

        for (Button btn : view.getMenuButtons()) {
            Map.Entry<String, Class<? extends PageController>> entry = it.next();
            Class<? extends PageController> ctrlClass = entry.getValue();

            btn.addActionListener(e -> {
                view.setVisible(false);
                PageController pc = ctx.getBean(ctrlClass);
                pc.show();
            });
        }

        view.setVisible(true);
    }
}
