package wonjjong.dev.ottservice.config;

import org.springframework.context.annotation.Configuration;

@Configuration
//@Profile("prod")
public class H2ServerConfig {
    /*@Bean
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource dataSource() throws SQLException {
        Server.createTcpServer("-tcp",
                "-tcpPort",
                "9092",
                "-tcpAllowOthers",
                "-ifNotExists" //Databases are created when accessed
        ).start();

        return new HikariDataSource();
    }*/
}
