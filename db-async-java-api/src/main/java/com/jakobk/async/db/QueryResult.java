package com.jakobk.async.db;

import java.sql.ResultSet;
import java.util.Optional;

/**
 * The DB query result, containing either a ResultSet or the number of affected rows.
 */
public interface QueryResult {

    Optional<ResultSet> getResultSet();
    long getAffectedRowCount();
    String getStatusMessage();

}
