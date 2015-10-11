package com.jakobk.async.db;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Java interface for DB connection pool.
 */
public interface DbConnectionPool extends DbConnection {

    @Override
    CompletableFuture<DbConnectionPool> connect();

    /**
     * Closes the pool, you should discard the object.
     *
     * @return
     */
    @Override
    CompletableFuture<DbConnectionPool> disconnect();

    /**
     *
     * Picks one connection and runs this query against it. The query should be stateless, it should not
     * start transactions and should not leave anything to be cleaned up in the future. The behavior of this
     * object is undefined if you start a transaction from this method.
     *
     * @param query
     * @return
     */
    @Override
    CompletableFuture<QueryResult> sendQuery(String query);

    /**
     *
     * Picks one connection and runs this query against it. The query should be stateless, it should not
     * start transactions and should not leave anything to be cleaned up in the future. The behavior of this
     * object is undefined if you start a transaction from this method.
     *
     * @param query
     * @param parameters
     * @return
     */
    @Override
    CompletableFuture<QueryResult> sendPreparedStatement(String query, List<Object> parameters);

    /**
     *
     * Picks one connection and executes an (asynchronous) function on it within a transaction block.
     * If the function completes successfully, the transaction is committed, otherwise it is aborted.
     * Either way, the connection is returned to the pool on completion.
     *
     * @param f operation to execute on a connection
     * @return result of f, conditional on transaction operations succeeding
     */
    @Override
    <A> CompletableFuture<A> inTransaction(Function<DbConnection, CompletableFuture<A>> f);

}
