module recipe.persistence.in_memory {
    requires recipe.domain;
    requires recipe.persistence;
    requires spring.context;
    requires spring.jdbc;
    requires java.sql;
    opens com.epm.recipe.persistence.in_memory.config to spring.core;
    exports com.epm.recipe.persistence.in_memory;
    exports com.epm.recipe.persistence.in_memory.config;
}