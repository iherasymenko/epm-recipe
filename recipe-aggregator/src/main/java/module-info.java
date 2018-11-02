module recipe.aggregator {
    requires java.sql;
    requires spring.context;
    requires recipe.web.ui;
    requires recipe.services.impl;
    requires recipe.persistence.in_memory;
}