package com.jakobk.async.db.postgresql;

import com.github.mauricio.async.db.Configuration;
import com.github.mauricio.async.db.column.ColumnDecoderRegistry;
import com.github.mauricio.async.db.column.ColumnEncoderRegistry;
import com.github.mauricio.async.db.postgresql.column.PostgreSQLColumnDecoderRegistry;
import com.github.mauricio.async.db.postgresql.column.PostgreSQLColumnEncoderRegistry;
import com.github.mauricio.async.db.util.ExecutorServiceUtils;
import com.github.mauricio.async.db.util.NettyUtils;
import io.netty.channel.EventLoopGroup;
import scala.concurrent.ExecutionContext;

/**
 *
 */
public class PostgresqlConnectionBuilder {

    private Configuration configuration = Configuration.Default();
    private ColumnEncoderRegistry encoderRegistry = PostgreSQLColumnEncoderRegistry.Instance();
    private ColumnDecoderRegistry decoderRegistry = PostgreSQLColumnDecoderRegistry.Instance();
    private EventLoopGroup group = NettyUtils.DefaultEventLoopGroup();
    private ExecutionContext executionContext = ExecutorServiceUtils.CachedExecutionContext();
    
    public PostgresqlConnection build() {
        return new PostgresqlConnection(
                configuration,
                encoderRegistry,
                decoderRegistry,
                group,
                executionContext
        );
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public PostgresqlConnectionBuilder withConfiguration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    public ColumnEncoderRegistry getEncoderRegistry() {
        return encoderRegistry;
    }

    public PostgresqlConnectionBuilder withEncoderRegistry(ColumnEncoderRegistry encoderRegistry) {
        this.encoderRegistry = encoderRegistry;
        return this;
    }

    public ColumnDecoderRegistry getDecoderRegistry() {
        return decoderRegistry;
    }

    public PostgresqlConnectionBuilder withDecoderRegistry(ColumnDecoderRegistry decoderRegistry) {
        this.decoderRegistry = decoderRegistry;
        return this;
    }

    public EventLoopGroup getGroup() {
        return group;
    }

    public PostgresqlConnectionBuilder withGroup(EventLoopGroup group) {
        this.group = group;
        return this;
    }

    public ExecutionContext getExecutionContext() {
        return executionContext;
    }

    public PostgresqlConnectionBuilder withExecutionContext(ExecutionContext executionContext) {
        this.executionContext = executionContext;
        return this;
    }
}
