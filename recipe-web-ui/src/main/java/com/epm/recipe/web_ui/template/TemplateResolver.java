package com.epm.recipe.web_ui.template;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.templateresolver.UrlTemplateResolver;
import org.thymeleaf.templateresource.ITemplateResource;
import org.thymeleaf.templateresource.UrlTemplateResource;

import java.net.URL;
import java.util.Map;

public class TemplateResolver extends UrlTemplateResolver {

    @Override
    protected ITemplateResource computeTemplateResource(IEngineConfiguration configuration, String ownerTemplate, String template, String resourceName, String characterEncoding, Map<String, Object> templateResolutionAttributes) {
        URL resource = getClass().getResource(resourceName);
        if (resource == null) {
            throw new IllegalArgumentException("Cannot resolve " + resourceName);
        }
        return new UrlTemplateResource(resource, characterEncoding);
    }

}
