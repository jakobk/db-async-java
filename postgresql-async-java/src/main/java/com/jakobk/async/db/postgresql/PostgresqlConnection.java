package com.jakobk.async.db.postgresql;

import com.github.mauricio.async.db.Configuration;
import com.github.mauricio.async.db.Connection;
import com.github.mauricio.async.db.column.ColumnDecoderRegistry;
import com.github.mauricio.async.db.column.ColumnEncoderRegistry;
import com.github.mauricio.async.db.postgresql.PostgreSQLConnection;
import com.github.mauricio.async.db.util.ExecutorServiceUtils;
import com.jakobk.async.db.DbConnection;
import com.jakobk.async.db.QueryResult;
import io.netty.channel.EventLoopGroup;
import scala.collection.JavaConversions;
import scala.compat.java8.FutureConverters;
import scala.compat.java8.JFunction1;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static com.jakobk.async.db.postgresql.ScalaUtils.toCompletableFuture;
import static com.jakobk.async.db.postgresql.ScalaUtils.toCompletableFutureWithReturnValue;

/**
 *
 */
public class PostgresqlConnection implements DbConnection {

    private final com.github.mauricio.async.db.postgresql.PostgreSQLConnection delegate;

    public PostgresqlConnection(Configuration configuration, ColumnEncoderRegistry encoderRegistry,
                                ColumnDecoderRegistry decoderRegistry, EventLoopGroup group,
                                ExecutionContext executionContext) {
        delegate = new com.github.mauricio.async.db.postgresql.PostgreSQLConnection(
                configuration, encoderRegistry, decoderRegistry, group, executionContext);
    }

    public PostgresqlConnection(PostgreSQLConnection delegate) {
        this.delegate = delegate;
    }

    @Override
    public CompletableFuture<DbConnection> connect() {
        return toCompletableFutureWithReturnValue(delegate.connect(), this);
    }

    @Override
    public CompletableFuture<DbConnection> disconnect() {
        return toCompletableFutureWithReturnValue(delegate.disconnect(), this);
    }

    @Override
    public boolean isConnected() {
        return delegate.isConnected();
    }

    @Override
    public CompletableFuture<QueryResult> sendQuery(String query) {
        return toCompletableFuture(delegate.sendQuery(query))
                .thenApply(PostgresqlQueryResult::new);
    }


    @Override
    public CompletableFuture<QueryResult> sendPreparedStatement(String query, List<Object> parameters) {
        return toCompletableFuture(delegate.sendPreparedStatement(query, JavaConversions.asScalaBuffer(parameters)))
                .thenApply(PostgresqlQueryResult::new);
    }
    @Override
    public <A> CompletableFuture<A> inTransaction(Function<DbConnection, CompletableFuture<A>> f){
        return toCompletableFuture(delegate.inTransaction(
                (JFunction1<Connection, Future<A>>) scalaConnection
                        -> FutureConverters.toScala(f.apply(new PostgresqlConnection((PostgreSQLConnection) scalaConnection))),
                ExecutorServiceUtils.CachedExecutionContext()));
    }

}
