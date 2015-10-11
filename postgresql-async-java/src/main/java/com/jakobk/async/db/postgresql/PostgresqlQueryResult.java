package com.jakobk.async.db.postgresql;

import com.jakobk.async.db.QueryResult;
import scala.compat.java8.OptionConverters;

import java.sql.ResultSet;
import java.util.Optional;

/**
 *
 */
public class PostgresqlQueryResult implements QueryResult {

    private final com.github.mauricio.async.db.QueryResult delegate;

    public PostgresqlQueryResult(com.github.mauricio.async.db.QueryResult delegate) {
        this.delegate = delegate;
    }

    @Override
    public Optional<ResultSet> getResultSet() {
        Optional<com.github.mauricio.async.db.ResultSet> resultSetOptional = OptionConverters.toJava(delegate.rows());
        if (resultSetOptional.isPresent()) {
            return Optional.of(new PostgresqlResultSet(resultSetOptional.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public long getAffectedRowCount() {
        return delegate.rowsAffected();
    }

    @Override
    public String getStatusMessage() {
        return delegate.statusMessage();
    }

}
