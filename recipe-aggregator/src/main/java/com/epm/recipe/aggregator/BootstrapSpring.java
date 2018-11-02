package com.epm.recipe.aggregator;

import com.epm.recipe.persistence.in_memory.config.InMemoryPersistenceConfiguration;
import com.epm.recipe.service.impl.config.ServicesConfiguration;
import com.epm.recipe.web_ui.Ui;
import com.epm.recipe.web_ui.config.WebUiConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BootstrapSpring {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                InMemoryPersistenceConfiguration.class,
                ServicesConfiguration.class,
                WebUiConfiguration.class
        );
        Ui ui = context.getBean(Ui.class);
        ui.showRecipeOfTheDay();
    }

}
