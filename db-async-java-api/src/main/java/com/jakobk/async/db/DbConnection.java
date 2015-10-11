package com.jakobk.async.db;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Java interface for DB connection.
 * Equivalent to scala class {@link com.github.mauricio.async.db.Connection}, without using Scala types.
 */
public interface DbConnection {

    CompletableFuture<? extends DbConnection> connect();
    CompletableFuture<? extends DbConnection> disconnect();

    boolean isConnected();

    CompletableFuture<QueryResult> sendQuery(String query);
    CompletableFuture<QueryResult> sendPreparedStatement(String query, List<Object> parameters);

    <A> CompletableFuture<A> inTransaction(Function<DbConnection, CompletableFuture<A>> f);

}
