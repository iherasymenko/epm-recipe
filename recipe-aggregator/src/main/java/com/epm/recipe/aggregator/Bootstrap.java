package com.epm.recipe.aggregator;

import com.epm.recipe.aggregator.config.PropertyConfiguration;
import com.epm.recipe.persistence.jdbc.config.DBPersistenceConfiguration;
import com.epm.recipe.service.impl.config.ServicesConfiguration;
import com.epm.recipe.web_api.config.WebApiConfiguration;
import com.epm.recipe.web_ui.config.WebUiConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Bootstrap {

    public static void main(String[] args) throws Exception {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(DBPersistenceConfiguration.class, PropertyConfiguration.class, ServicesConfiguration.class, WebUiConfiguration.class, WebApiConfiguration.class);

        Server server = new Server(80);
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
