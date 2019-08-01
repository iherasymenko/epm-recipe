module recipe.properties {
    requires logback.classic;
    requires logback.core;
    requires slf4j.api;
    requires spring.context;
    opens com.epm.recipe.properties to spring.core;
    exports com.epm.recipe.properties;
}