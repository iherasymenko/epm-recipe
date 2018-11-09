package com.epm.recipe.persistence.in_memory.config;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.jolbox.bonecp.BoneCPConfig;

@Configuration
public class BoneCpDBConfiguration {

    @Bean
    public BoneCPConfig boneCPConfig() throws ClassNotFoundException {

        BoneCPConfig config = new BoneCPConfig();
        Class.forName("org.h2.Driver");
        config.setJdbcUrl("jdbc:h2:mem:recipe;create=true");
        config.setUsername("root");
        config.setPassword("");
        config.setConnectionTestStatement("SELECT 1");
        config.setMinConnectionsPerPartition(1);
        config.setMaxConnectionsPerPartition(15);
        config.setPartitionCount(10);
        return config;
    }

    @Bean
    public BoneCPDataSource dataSource(BoneCPConfig config) {
        return new BoneCPDataSource(config);
    }

}
