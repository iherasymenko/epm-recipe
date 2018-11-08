module recipe.persistence.mariaDB {
    requires recipe.domain;
    requires recipe.persistence;
    requires spring.context;
    requires java.sql;
    opens com.epm.recipe.persistence.mariaDB to spring.core;
    opens com.epm.recipe.persistence.mariaDB.config to spring.core;
    exports com.epm.recipe.persistence.mariaDB.config;
}