module recipe.persistence.in_memory {
        requires recipe.domain;
        requires recipe.persistence;
        requires spring.context;
        opens com.epm.recipe.persistence.in_memory to spring.core;
        opens com.epm.recipe.persistence.in_memory.config to spring.core;
        exports com.epm.recipe.persistence.in_memory.config;
        }