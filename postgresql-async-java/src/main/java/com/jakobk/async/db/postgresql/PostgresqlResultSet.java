package com.jakobk.async.db.postgresql;

import com.github.mauricio.async.db.RowData;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

/**
 *
 */
public class PostgresqlResultSet implements ResultSet {

    private final com.github.mauricio.async.db.ResultSet scalaResultSet;
    private final scala.collection.Iterator<RowData> rowDataIterator;
    private RowData rowData;
    private int rowDataIndex = -1;
    private boolean wasNull;

    public PostgresqlResultSet(com.github.mauricio.async.db.ResultSet scalaResultSet) {
        this.scalaResultSet = scalaResultSet;
        rowDataIterator = scalaResultSet.iterator(); 
    }

    @Override
    public boolean next() throws SQLException {
        if (rowDataIterator.hasNext()) { 
            rowData = rowDataIterator.next();
            rowDataIndex++;
            return true;
        } else {
            rowData = null;
            return false;
        }
    }

    @Override
    public void close() throws SQLException {
        // noop
    }

    @Override
    public boolean wasNull() throws SQLException {
        return wasNull;
    }

    @Override
    public String getString(int columnIndex) throws SQLException {
        wasNull = false;
        Object o = rowData.apply(columnIndex - 1);
        if (o instanceof String) {
            return (String) o;
        } else if (o != null) {
            return o.toString();
        } else {
            wasNull = true;
            return null;
        }
    }

    @Override
    public boolean getBoolean(int columnIndex) throws SQLException {
        wasNull = false;
        Object o = rowData.apply(columnIndex - 1);
        if (o instanceof Boolean) {
            return (Boolean) o;
        } else if (o != null) {
            return Boolean.parseBoolean(o.toString());
        } else {
            wasNull = true;
            return false;
        }
    }

    @Override
    public byte getByte(int columnIndex) throws SQLException {
        wasNull = false;
        Object o = rowData.apply(columnIndex - 1);
        if (o instanceof Byte) {
            return (Byte) o;
        } else if (o != null) {
            return Byte.parseByte(o.toString());
        } else {
            wasNull = true;
            return (byte) 0;
        }
    }

    @Override
    public short getShort(int columnIndex) throws SQLException {
        wasNull = false;
        Object o = rowData.apply(columnIndex - 1);
        if (o instanceof Short) {
            return (Short) o;
        } else if (o != null) {
            return Short.parseShort(o.toString());
        } else {
            wasNull = true;
            return (short) 0;
        }
    }

    @Override
    public int getInt(int columnIndex) throws SQLException {
        wasNull = false;
        Object o = rowData.apply(columnIndex - 1);
        if (o instanceof Integer) {
            return (Integer) o;
        } else if (o != null) {
            return Integer.parseInt(o.toString());
        } else {
            wasNull = true;
            return 0;
        }
    }

    @Override
    public long getLong(int columnIndex) throws SQLException {
        wasNull = false;
        Object o = rowData.apply(columnIndex - 1);
        if (o instanceof Long) {
            return (Long) o;
        } else if (o != null) {
            return Long.parseLong(o.toString());
        } else {
            wasNull = true;
            return 0L;
        }
    }

    @Override
    public float getFloat(int columnIndex) throws SQLException {
        wasNull = false;
        Object o = rowData.apply(columnIndex - 1);
        if (o instanceof Float) {
            return (Float) o;
        } else if (o != null) {
            return Float.parseFloat(o.toString());
        } else {
            wasNull = true;
            return 0f;
        }
    }

    @Override
    public double getDouble(int columnIndex) throws SQLException {
        wasNull = false;
        Object o = rowData.apply(columnIndex - 1);
        if (o instanceof Double) {
            return (Double) o;
        } else if (o != null) {
            return Double.parseDouble(o.toString());
        } else {
            wasNull = true;
            return 0d;
        }
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public byte[] getBytes(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Date getDate(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Time getTime(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public InputStream getUnicodeStream(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public String getString(String columnLabel) throws SQLException {
        wasNull = false;
        Object o = rowData.apply(columnLabel);
        if (o instanceof String) {
            return (String) o;
        } else if (o != null) {
            return o.toString();
        } else {
            wasNull = true;
            return null;
        }
    }

    @Override
    public boolean getBoolean(String columnLabel) throws SQLException {
        wasNull = false;
        Object o = rowData.apply(columnLabel);
        if (o instanceof Boolean) {
            return (Boolean) o;
        } else if (o != null) {
            return Boolean.parseBoolean(o.toString());
        } else {
            wasNull = true;
            return false;
        }
    }

    @Override
    public byte getByte(String columnLabel) throws SQLException {
        wasNull = false;
        Object o = rowData.apply(columnLabel);
        if (o instanceof Byte) {
            return (Byte) o;
        } else if (o != null) {
            return Byte.parseByte(o.toString());
        } else {
            wasNull = true;
            return (byte) 0;
        }
    }

    @Override
    public short getShort(String columnLabel) throws SQLException {
        wasNull = false;
        Object o = rowData.apply(columnLabel);
        if (o instanceof Short) {
            return (Short) o;
        } else if (o != null) {
            return Short.parseShort(o.toString());
        } else {
            wasNull = true;
            return (short) 0;
        }
    }

    @Override
    public int getInt(String columnLabel) throws SQLException {
        wasNull = false;
        Object o = rowData.apply(columnLabel);
        if (o instanceof Integer) {
            return (Integer) o;
        } else if (o != null) {
            return Integer.parseInt(o.toString());
        } else {
            wasNull = true;
            return 0;
        }
    }

    @Override
    public long getLong(String columnLabel) throws SQLException {
        wasNull = false;
        Object o = rowData.apply(columnLabel);
        if (o instanceof Long) {
            return (Long) o;
        } else if (o != null) {
            return Long.parseLong(o.toString());
        } else {
            wasNull = true;
            return 0L;
        }
    }

    @Override
    public float getFloat(String columnLabel) throws SQLException {
        wasNull = false;
        Object o = rowData.apply(columnLabel);
        if (o instanceof Float) {
            return (Float) o;
        } else if (o != null) {
            return Float.parseFloat(o.toString());
        } else {
            wasNull = true;
            return 0f;
        }
    }

    @Override
    public double getDouble(String columnLabel) throws SQLException {
        wasNull = false;
        Object o = rowData.apply(columnLabel);
        if (o instanceof Double) {
            return (Double) o;
        } else if (o != null) {
            return Double.parseDouble(o.toString());
        } else {
            wasNull = true;
            return 0d;
        }
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public byte[] getBytes(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Date getDate(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Time getTime(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Timestamp getTimestamp(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public InputStream getAsciiStream(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public InputStream getUnicodeStream(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public InputStream getBinaryStream(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void clearWarnings() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public String getCursorName() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return new ResultSetMetaDataImpl();
    }

    @Override
    public Object getObject(int columnIndex) throws SQLException {
        wasNull = false;
        Object o = rowData.apply(columnIndex - 1);
        if (o != null) {
            return o;
        } else {
            wasNull = true;
            return null;
        }
    }

    @Override
    public Object getObject(String columnLabel) throws SQLException {
        wasNull = false;
        Object o = rowData.apply(columnLabel);
        if (o != null) {
            return o;
        } else {
            wasNull = true;
            return null;
        }
    }

    @Override
    public int findColumn(String columnLabel) throws SQLException {
        int index = scalaResultSet.columnNames().indexOf(columnLabel);
        if (index != -1) {
            index++;  // jdbc is 1 based
        }
        return index;
    }

    @Override
    public Reader getCharacterStream(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Reader getCharacterStream(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public boolean isBeforeFirst() throws SQLException {
        return rowDataIndex == -1;
    }

    @Override
    public boolean isAfterLast() throws SQLException {
        return rowDataIndex == scalaResultSet.size();
    }

    @Override
    public boolean isFirst() throws SQLException {
        return rowDataIndex == 0;
    }

    @Override
    public boolean isLast() throws SQLException {
        return rowDataIndex == scalaResultSet.size() - 1;
    }

    @Override
    public void beforeFirst() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void afterLast() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public boolean first() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public boolean last() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public int getRow() throws SQLException {
        return rowDataIndex + 1;
    }

    @Override
    public boolean absolute(int row) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public boolean relative(int rows) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public boolean previous() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public int getFetchDirection() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public int getFetchSize() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public int getType() throws SQLException {
        return ResultSet.TYPE_FORWARD_ONLY;
    }

    @Override
    public int getConcurrency() throws SQLException {
        return ResultSet.CONCUR_READ_ONLY;
    }

    @Override
    public boolean rowUpdated() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public boolean rowInserted() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public boolean rowDeleted() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateNull(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateBoolean(int columnIndex, boolean x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateByte(int columnIndex, byte x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateShort(int columnIndex, short x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateInt(int columnIndex, int x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateLong(int columnIndex, long x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateFloat(int columnIndex, float x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateDouble(int columnIndex, double x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateString(int columnIndex, String x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateBytes(int columnIndex, byte[] x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateDate(int columnIndex, Date x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateTime(int columnIndex, Time x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateObject(int columnIndex, Object x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateNull(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateBoolean(String columnLabel, boolean x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateByte(String columnLabel, byte x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateShort(String columnLabel, short x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateInt(String columnLabel, int x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateLong(String columnLabel, long x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateFloat(String columnLabel, float x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateDouble(String columnLabel, double x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateString(String columnLabel, String x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateBytes(String columnLabel, byte[] x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateDate(String columnLabel, Date x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateTime(String columnLabel, Time x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateObject(String columnLabel, Object x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void insertRow() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateRow() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void deleteRow() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void refreshRow() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void cancelRowUpdates() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void moveToInsertRow() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void moveToCurrentRow() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Statement getStatement() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Ref getRef(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Blob getBlob(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Clob getClob(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Array getArray(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Ref getRef(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Blob getBlob(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Clob getClob(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Array getArray(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Date getDate(String columnLabel, Calendar cal) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Time getTime(int columnIndex, Calendar cal) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Time getTime(String columnLabel, Calendar cal) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public URL getURL(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public URL getURL(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateRef(int columnIndex, Ref x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateRef(String columnLabel, Ref x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateBlob(int columnIndex, Blob x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateBlob(String columnLabel, Blob x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateClob(int columnIndex, Clob x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateClob(String columnLabel, Clob x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateArray(int columnIndex, Array x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateArray(String columnLabel, Array x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public RowId getRowId(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public RowId getRowId(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateRowId(int columnIndex, RowId x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateRowId(String columnLabel, RowId x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public int getHoldability() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public boolean isClosed() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateNString(int columnIndex, String nString) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateNString(String columnLabel, String nString) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public NClob getNClob(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public NClob getNClob(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public String getNString(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public String getNString(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Reader getNCharacterStream(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Reader getNCharacterStream(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateClob(int columnIndex, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateClob(String columnLabel, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    class ResultSetMetaDataImpl implements ResultSetMetaData {

        @Override
        public int getColumnCount() throws SQLException {
            return scalaResultSet.columnNames().size();
        }

        @Override
        public boolean isAutoIncrement(int column) throws SQLException {
            return false;  // cannot be determined
        }

        @Override
        public boolean isCaseSensitive(int column) throws SQLException {
            return false;
        }

        @Override
        public boolean isSearchable(int column) throws SQLException {
            return true;
        }

        @Override
        public boolean isCurrency(int column) throws SQLException {
            return false;
        }

        @Override
        public int isNullable(int column) throws SQLException {
            return columnNullableUnknown;
        }

        @Override
        public boolean isSigned(int column) throws SQLException {
            return false;
        }

        @Override
        public int getColumnDisplaySize(int column) throws SQLException {
            return 100;
        }

        @Override
        public String getColumnLabel(int column) throws SQLException {
            return scalaResultSet.columnNames().apply(column - 1);
        }

        @Override
        public String getColumnName(int column) throws SQLException {
            return scalaResultSet.columnNames().apply(column - 1);
        }

        @Override
        public String getSchemaName(int column) throws SQLException {
            return null;
        }

        @Override
        public int getPrecision(int column) throws SQLException {
            return 0;
        }

        @Override
        public int getScale(int column) throws SQLException {
            return 0;
        }

        @Override
        public String getTableName(int column) throws SQLException {
            return null;
        }

        @Override
        public String getCatalogName(int column) throws SQLException {
            return null;
        }

        @Override
        public int getColumnType(int column) throws SQLException {
            throw new SQLFeatureNotSupportedException("not implemented");
        }

        @Override
        public String getColumnTypeName(int column) throws SQLException {
            throw new SQLFeatureNotSupportedException("not implemented");
        }

        @Override
        public boolean isReadOnly(int column) throws SQLException {
            throw new SQLFeatureNotSupportedException("not implemented");
        }

        @Override
        public boolean isWritable(int column) throws SQLException {
            throw new SQLFeatureNotSupportedException("not implemented");
        }

        @Override
        public boolean isDefinitelyWritable(int column) throws SQLException {
            throw new SQLFeatureNotSupportedException("not implemented");
        }

        @Override
        public String getColumnClassName(int column) throws SQLException {
            throw new SQLFeatureNotSupportedException("not implemented");
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return null;
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return false;
        }
    }
    
}
