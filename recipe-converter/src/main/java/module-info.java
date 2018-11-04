module recipe.converter {
    requires recipe.services.api;
    requires spring.context;
    requires recipe.converter.api;
    opens com.epm.recipe.converter.impl to spring.core;
    opens com.epm.recipe.converter.impl.config to spring.core;
    exports com.epm.recipe.converter.impl;
    exports com.epm.recipe.converter.impl.config;
}