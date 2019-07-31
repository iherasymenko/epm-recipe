module recipe.persistence.jdbc {
    requires java.sql;
    requires recipe.domain;
    requires recipe.persistence;
    requires spring.context;
    opens com.epm.recipe.persistence.jdbc to spring.core;
    opens com.epm.recipe.persistence.jdbc.config to spring.core;
    exports com.epm.recipe.persistence.jdbc.config;
}