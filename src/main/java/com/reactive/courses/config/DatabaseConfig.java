package com.reactive.courses.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactive.courses.config.converter.CourseMetadataToJsonConverter;
import com.reactive.courses.config.converter.JsonToCourseMetadataConverter;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties({R2dbcProperties.class, FlywayProperties.class})
public class DatabaseConfig extends AbstractR2dbcConfiguration {
    @Value("${com.reactive.courses.database.host}")
    private String host;

    @Value("${com.reactive.courses.database.port}")
    private int port;

    @Value("${com.reactive.courses.database.name}")
    private String database;

    @Value("${com.reactive.courses.database.schema}")
    private String schema;

    @Value("${com.reactive.courses.database.username}")
    private String username;

    @Value("${com.reactive.courses.database.password}")
    private String password;

    @Value("${com.reactive.courses.database.pool.size.initial}")
    private int initialPoolSize;

    @Value("${com.reactive.courses.database.pool.size.max}")
    private int maxPoolSize;


    @Override
    @Bean
    public ConnectionFactory connectionFactory() {

        final PostgresqlConnectionFactory connectionFactory = new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host(host)
                        .port(port)
                        .database(database)
                        .schema(schema)
                        .username(username)
                        .password(password)
                        .build()
        );

        final ConnectionPoolConfiguration poolConfiguration = ConnectionPoolConfiguration
                .builder(connectionFactory)
                .initialSize(initialPoolSize)
                .maxSize(maxPoolSize)
                .build();


        return new ConnectionPool(poolConfiguration);
    }

    @Bean
    public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    @Override
    public R2dbcCustomConversions r2dbcCustomConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new CourseMetadataToJsonConverter(objectMapper()));
        converters.add(new JsonToCourseMetadataConverter(objectMapper()));
        return new R2dbcCustomConversions(getStoreConversions(), converters);
    }
}
