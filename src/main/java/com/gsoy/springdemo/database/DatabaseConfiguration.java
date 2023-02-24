package com.gsoy.springdemo.database;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource("encrypted.properties")
public class DatabaseConfiguration {

    @Value("${oracle.datasource.url}")
    private String oracleDatasourceURL;
    @Value("${srdstest.encrypted.password}")
    private String oracleDatasourcePassword;
    @Value("${oracle.datasource.user}")
    private String oracleDatasourceUser;
    @Value("${jdbc.query.timeout_in_ms}")
    public long connectTimeout;
    @Value("${jdbc.pool_size}")
    public int poolSize;
    @Value("${jdbc.login_timeout_in_sec}")
    public int loginTimeout;



    @Bean
    public DataSource dataSource() throws SQLException {

        HikariDataSource oracleDataSource = new HikariDataSource();

        oracleDataSource.setLoginTimeout(loginTimeout);
        oracleDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        oracleDataSource.setJdbcUrl(oracleDatasourceURL);
        oracleDataSource.setUsername(oracleDatasourceUser);
        oracleDataSource.setPassword(oracleDatasourcePassword);
        oracleDataSource.setConnectionTimeout(connectTimeout);
        oracleDataSource.setMaximumPoolSize(poolSize);
        oracleDataSource.setAutoCommit(false);
        return oracleDataSource;
    }

    @Bean("dataSourceManager")
    public PlatformTransactionManager transactionManager() {
        try {
            return new DataSourceTransactionManager(dataSource());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
