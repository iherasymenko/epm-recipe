module recipe.persistence.db_mysql {
    requires recipe.domain;
    requires recipe.persistence;
    requires spring.context;
    requires java.sql;
    opens com.epm.recipe.persistence.db_mysql to spring.core;
    opens com.epm.recipe.persistence.db_mysql.config to spring.core;
    exports com.epm.recipe.persistence.db_mysql.config;
}