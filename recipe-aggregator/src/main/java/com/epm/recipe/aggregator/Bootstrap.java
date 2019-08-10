package com.epm.recipe.aggregator;

import com.epm.recipe.aggregator.config.AggregatorConfiguration;
import com.epm.recipe.persistence.jdbc.config.JdbcPersistenceConfiguration;
import com.epm.recipe.service.impl.config.ServicesConfiguration;
import com.epm.recipe.web_api.config.WebApiConfiguration;
import com.epm.recipe.web_ui.config.WebUiConfiguration;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Bootstrap {
    private static Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args) throws Exception {
        logger.info("Application was started");
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AggregatorConfiguration.class);
        context.register(JdbcPersistenceConfiguration.class);
        context.register(ServicesConfiguration.class);
        context.register(WebUiConfiguration.class);
        context.register(WebApiConfiguration.class);

        Server server = new Server(8080);
        ServletContextHandler servletContext = new ServletContextHandler();
        servletContext.setContextPath("/");
        servletContext.setResourceBase("");
        servletContext.addServlet(new ServletHolder(new DispatcherServlet(context)), "/*");
        servletContext.addEventListener(new ContextLoaderListener(context));

        server.setHandler(servletContext);
        server.start();

        server.join();
    }

}
