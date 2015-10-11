package com.jakobk.async.db;

import com.github.mauricio.async.db.Configuration;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import scala.Option;
import scala.Some;
import scala.compat.java8.OptionConverters;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 *
 */
public class ConfigurationBuilder {

    private String username;
    private String host = "localhost";
    private int port = 5432;
    private String password;
    private String database;
    private Charset charset = StandardCharsets.UTF_8;
    private int maximumMessageSize = 16777216;
    private ByteBufAllocator allocator = PooledByteBufAllocator.DEFAULT;
    private Duration connectTimeout = Duration.of(5l, ChronoUnit.SECONDS);
    private Duration testTimeout = Duration.of(5l, ChronoUnit.SECONDS);
    private Duration queryTimeout;

    public Configuration build() {
        if (username == null) {
            throw new IllegalArgumentException("username is required");
        }
        return new Configuration(
                username,
                host,
                port,
                password != null ? new Some<>(password) : none(),
                database != null ? new Some<>(database) : none(),
                charset,
                maximumMessageSize,
                allocator,
                toScala(connectTimeout),
                toScala(testTimeout),
                queryTimeout != null ? new Some<>(toScala(queryTimeout)) : none());
    }

    private static <T> Option<T> none() {
        return OptionConverters.toScala(java.util.Optional.empty());
    }

    private static scala.concurrent.duration.Duration toScala(Duration java) {
        return scala.concurrent.duration.Duration.fromNanos(java.toNanos());
    }

    public String getUsername() {
        return username;
    }

    public ConfigurationBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getHost() {
        return host;
    }

    public ConfigurationBuilder withHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public ConfigurationBuilder withPort(int port) {
        this.port = port;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ConfigurationBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDatabase() {
        return database;
    }

    public ConfigurationBuilder withDatabase(String database) {
        this.database = database;
        return this;
    }

    public Charset getCharset() {
        return charset;
    }

    public ConfigurationBuilder withCharset(Charset charset) {
        this.charset = charset;
        return this;
    }

    public int getMaximumMessageSize() {
        return maximumMessageSize;
    }

    public ConfigurationBuilder withMaximumMessageSize(int maximumMessageSize) {
        this.maximumMessageSize = maximumMessageSize;
        return this;
    }

    public ByteBufAllocator getAllocator() {
        return allocator;
    }

    public ConfigurationBuilder withAllocator(ByteBufAllocator allocator) {
        this.allocator = allocator;
        return this;
    }

    public Duration getConnectTimeout() {
        return connectTimeout;
    }

    public ConfigurationBuilder withConnectTimeout(Duration connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public Duration getTestTimeout() {
        return testTimeout;
    }

    public ConfigurationBuilder withTestTimeout(Duration testTimeout) {
        this.testTimeout = testTimeout;
        return this;
    }

    public Duration getQueryTimeout() {
        return queryTimeout;
    }

    public ConfigurationBuilder withQueryTimeout(Duration queryTimeout) {
        this.queryTimeout = queryTimeout;
        return this;
    }
}
