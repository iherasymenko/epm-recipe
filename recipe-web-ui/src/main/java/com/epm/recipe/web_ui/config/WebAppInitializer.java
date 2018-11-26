package com.epm.recipe.web_ui.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        System.out.println("WebAppInitializer.getRootConfigClasses");
        return new Class[] {ThymeleafConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        System.out.println("WebAppInitializer.getServletConfigClasses");
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        System.out.println("WebAppInitializer.getServletMappings");
        return new String[] {"/"};
    }

    public WebAppInitializer() {
        System.out.println("WebAppInitializer");
    }

}
