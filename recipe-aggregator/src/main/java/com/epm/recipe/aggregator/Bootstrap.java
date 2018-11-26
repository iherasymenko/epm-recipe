package com.epm.recipe.aggregator;

import com.epm.recipe.persistence.in_mariadb.config.DbPersistenceConfiguration;
import com.epm.recipe.service.impl.config.ServicesConfiguration;
import com.epm.recipe.web_ui.config.WebUiConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Bootstrap {
    public static void main(String[] args) throws Exception {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(DbPersistenceConfiguration.class, ServicesConfiguration.class, WebUiConfiguration.class);
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
