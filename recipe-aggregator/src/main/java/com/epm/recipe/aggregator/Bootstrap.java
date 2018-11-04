package com.epm.recipe.aggregator;

import com.epm.recipe.persistence.in_db.config.DbPersistenceConfiguration;
import com.epm.recipe.persistence.in_memory.config.InMemoryPersistenceConfiguration;
import com.epm.recipe.service.impl.config.ServicesConfiguration;
import com.epm.recipe.web_ui.config.WebUiConfiguration;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.core.env.ConfigurableEnvironment;

public class Bootstrap {

    public static void main(String[] args) throws Exception {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(DbPersistenceConfiguration.class, ServicesConfiguration.class, WebUiConfiguration.class);

//        Environment environment = context.getEnvironment();

        Server server = new Server(8080);
        ServletContextHandler servletContext = new ServletContextHandler();
        servletContext.setContextPath("/");
        servletContext.setResourceBase("");
        servletContext.addServlet(new ServletHolder(new DispatcherServlet(context)), "/*");
        servletContext.addEventListener(new ContextLoaderListener(context));

        server.setHandler(servletContext);
        server.start();

        server.join();
        context.refresh();
    }

}
