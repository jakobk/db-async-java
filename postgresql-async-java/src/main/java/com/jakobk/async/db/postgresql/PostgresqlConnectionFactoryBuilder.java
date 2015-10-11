package com.jakobk.async.db.postgresql;

import com.github.mauricio.async.db.Configuration;
import com.github.mauricio.async.db.postgresql.pool.PostgreSQLConnectionFactory;
import com.github.mauricio.async.db.util.ExecutorServiceUtils;
import com.github.mauricio.async.db.util.NettyUtils;
import io.netty.channel.EventLoopGroup;
import scala.concurrent.ExecutionContext;

/**
 *
 */
public class PostgresqlConnectionFactoryBuilder {

    private Configuration configuration;
    private EventLoopGroup group = NettyUtils.DefaultEventLoopGroup();
    private ExecutionContext executionContext = ExecutorServiceUtils.CachedExecutionContext();
    
    public PostgreSQLConnectionFactory build() {
        return new PostgreSQLConnectionFactory(configuration, group, executionContext);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public PostgresqlConnectionFactoryBuilder withConfiguration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    public EventLoopGroup getGroup() {
        return group;
    }

    public PostgresqlConnectionFactoryBuilder withGroup(EventLoopGroup group) {
        this.group = group;
        return this;
    }

    public ExecutionContext getExecutionContext() {
        return executionContext;
    }

    public PostgresqlConnectionFactoryBuilder withExecutionContext(ExecutionContext executionContext) {
        this.executionContext = executionContext;
        return this;
    }
}
