package wonjjong.dev.ottservice.config;

import com.zaxxer.hikari.HikariDataSource;
import org.h2.tools.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
//@Profile("prod")
public class H2ServerConfig {
/*    @Bean
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
