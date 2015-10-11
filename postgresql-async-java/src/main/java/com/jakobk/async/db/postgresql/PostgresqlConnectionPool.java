package com.jakobk.async.db.postgresql;

import com.github.mauricio.async.db.Configuration;
import com.github.mauricio.async.db.Connection;
import com.github.mauricio.async.db.pool.ConnectionPool;
import com.github.mauricio.async.db.pool.PoolConfiguration;
import com.github.mauricio.async.db.postgresql.PostgreSQLConnection;
import com.github.mauricio.async.db.util.ExecutorServiceUtils;
import com.jakobk.async.db.DbConnection;
import com.jakobk.async.db.DbConnectionPool;
import com.jakobk.async.db.QueryResult;
import scala.collection.JavaConversions;
import scala.compat.java8.FutureConverters;
import scala.compat.java8.JFunction1;
import scala.concurrent.Future;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static com.jakobk.async.db.postgresql.ScalaUtils.toCompletableFuture;
import static com.jakobk.async.db.postgresql.ScalaUtils.toCompletableFutureWithReturnValue;

/**
 *
 */
public class PostgresqlConnectionPool implements DbConnectionPool {

    private final ConnectionPool<PostgreSQLConnection> scalaConnectionPool;

    public PostgresqlConnectionPool(Configuration connectionConfiguration) {
        this.scalaConnectionPool = new ConnectionPool<>(
                new PostgresqlConnectionFactoryBuilder().withConfiguration(connectionConfiguration).build(),
                PoolConfiguration.Default(),
                ExecutorServiceUtils.CachedExecutionContext());
    }

    @Override
    public CompletableFuture<DbConnectionPool> connect() {
        return toCompletableFutureWithReturnValue(scalaConnectionPool.connect(), this);
    }

    @Override
    public CompletableFuture<DbConnectionPool> disconnect() {
        return toCompletableFutureWithReturnValue(scalaConnectionPool.disconnect(), this);
    }

    @Override
    public boolean isConnected() {
        return scalaConnectionPool.isConnected();
    }

    @Override
    public CompletableFuture<QueryResult> sendQuery(String query) {
        return toCompletableFuture(scalaConnectionPool.sendQuery(query))
                .thenApply(PostgresqlQueryResult::new);
    }

    @Override
    public CompletableFuture<QueryResult> sendPreparedStatement(String query, List<Object> parameters) {
        return toCompletableFuture(scalaConnectionPool.sendPreparedStatement(query, JavaConversions.asScalaBuffer(parameters)))
                .thenApply(PostgresqlQueryResult::new);
    }

    @Override
    public <A> CompletableFuture<A> inTransaction(Function<DbConnection, CompletableFuture<A>> f){
        return toCompletableFuture(scalaConnectionPool.inTransaction(
                (JFunction1<Connection, Future<A>>) scalaConnection
                        -> FutureConverters.toScala(f.apply(new PostgresqlConnection((PostgreSQLConnection) scalaConnection))),
                ExecutorServiceUtils.CachedExecutionContext()));
    }
}
