package com.epm.recipe.aggregator;

import com.epm.recipe.web_ui.Ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BootstrapSpring {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "/com/epm/recipe/web_ui/context.xml",
             //   "/com/epm/recipe/service/impl/context.xml",
                "/com/epm/recipe/persistence/in_memory/context.xml"
        );
        context.getBean(Ui.class).showRecipeOfTheDay();
    }

}
