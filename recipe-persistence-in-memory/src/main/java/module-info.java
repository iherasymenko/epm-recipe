module recipe.persistence.in_memory {
    requires recipe.domain;
    requires recipe.persistence;
    requires spring.context;
    requires spring.jdbc;
    requires java.sql;
    requires liquibase.core;
    requires h2;
    requires bonecp;

    opens com.epm.recipe.persistence.in_memory to spring.core;
    opens com.epm.recipe.persistence.in_memory.config to spring.core;
    exports com.epm.recipe.persistence.in_memory;
    exports com.epm.recipe.persistence.in_memory.config;
    exports com.epm.recipe.persistence.in_memory.exception;

}