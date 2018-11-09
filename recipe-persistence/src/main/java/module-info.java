module recipe.persistence {
    requires transitive recipe.domain;
    requires spring.jdbc;
    requires spring.core;
    requires spring.context;
    requires java.sql;
    exports com.epm.recipe.persistence.config;
    opens com.epm.recipe.persistence to spring.core;
    opens com.epm.recipe.persistence.config to spring.core;
    exports com.epm.recipe.persistence;
}