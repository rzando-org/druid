/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.bvt.proxy;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Assert;

import junit.framework.TestCase;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterChainImpl;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.proxy.DruidDriver;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl;
import com.alibaba.druid.proxy.jdbc.DataSourceProxy;
import com.alibaba.druid.proxy.jdbc.DataSourceProxyConfig;
import com.alibaba.druid.proxy.jdbc.DataSourceProxyImpl;
import com.alibaba.druid.proxy.jdbc.PreparedStatementProxy;
import com.alibaba.druid.proxy.jdbc.PreparedStatementProxyImpl;
import com.alibaba.druid.proxy.jdbc.ResultSetProxy;
import com.alibaba.druid.proxy.jdbc.ResultSetProxyImpl;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import com.alibaba.druid.stat.JdbcStatManager;

public class JdbcFilterEventAdapterTest extends TestCase {
    protected void tearDown() throws Exception {
        DruidDriver.getProxyDataSources().clear();
        Assert.assertEquals(0, JdbcStatManager.getInstance().getSqlList().size());
    }

    public void test_filterEventAdapter() throws Exception {
        DataSourceProxyConfig config = new DataSourceProxyConfig();
        DataSourceProxy dataSource = new DataSourceProxyImpl(null, config);

        FilterEventAdapter filter = new FilterEventAdapter() {
        };

        String sql = "SELECT * FROM PATROL";
        ConnectionProxy connection = new ConnectionProxyImpl(dataSource, null, new Properties(), 1001);
        final PreparedStatementProxy statement = new PreparedStatementProxyImpl(connection, null, sql, 1002);

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public boolean statement_execute(StatementProxy statement, String sql) throws SQLException {
                    throw new SQLException();
                }
            };

            Throwable error = null;
            try {
                filter.statement_execute(chain, statement, sql);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public boolean statement_execute(StatementProxy statement, String sql) throws SQLException {
                    throw new RuntimeException();
                }
            };

            Throwable error = null;
            try {
                filter.statement_execute(chain, statement, sql);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public boolean statement_execute(StatementProxy statement, String sql) throws SQLException {
                    throw new Error();
                }
            };

            Throwable error = null;
            try {
                filter.statement_execute(chain, statement, sql);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        // //////////////////////////

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public boolean statement_execute(StatementProxy statement, String sql, int autoGeneratedKeys)
                        throws SQLException {
                    throw new SQLException();
                }
            };

            Throwable error = null;
            try {
                filter.statement_execute(chain, statement, sql, Statement.NO_GENERATED_KEYS);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public boolean statement_execute(StatementProxy statement, String sql, int autoGeneratedKeys)
                        throws SQLException {
                    throw new RuntimeException();
                }
            };

            Throwable error = null;
            try {
                filter.statement_execute(chain, statement, sql, Statement.NO_GENERATED_KEYS);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public boolean statement_execute(StatementProxy statement, String sql, int autoGeneratedKeys)
                        throws SQLException {
                    throw new Error();
                }
            };

            Throwable error = null;
            try {
                filter.statement_execute(chain, statement, sql, Statement.NO_GENERATED_KEYS);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        // //////////////////////////

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public boolean statement_execute(StatementProxy statement, String sql, int[] columnIndexes)
                        throws SQLException {
                    throw new SQLException();
                }
            };

            Throwable error = null;
            try {
                filter.statement_execute(chain, statement, sql, new int[0]);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public boolean statement_execute(StatementProxy statement, String sql, int[] columnIndexes)
                        throws SQLException {
                    throw new RuntimeException();
                }
            };

            Throwable error = null;
            try {
                filter.statement_execute(chain, statement, sql, new int[0]);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public boolean statement_execute(StatementProxy statement, String sql, int[] columnIndexes)
                        throws SQLException {
                    throw new Error();
                }
            };

            Throwable error = null;
            try {
                filter.statement_execute(chain, statement, sql, new int[0]);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        // //////////////////////////

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public boolean statement_execute(StatementProxy statement, String sql, String[] columnNames)
                        throws SQLException {
                    throw new SQLException();
                }
            };

            Throwable error = null;
            try {
                filter.statement_execute(chain, statement, sql, new String[0]);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public boolean statement_execute(StatementProxy statement, String sql, String[] columnNames)
                        throws SQLException {
                    throw new RuntimeException();
                }
            };

            Throwable error = null;
            try {
                filter.statement_execute(chain, statement, sql, new String[0]);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public boolean statement_execute(StatementProxy statement, String sql, String[] columnNames)
                        throws SQLException {
                    throw new Error();
                }
            };

            Throwable error = null;
            try {
                filter.statement_execute(chain, statement, sql, new String[0]);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        // //////////////////////////

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public int[] statement_executeBatch(StatementProxy statement) throws SQLException {
                    throw new SQLException();
                }
            };

            Throwable error = null;
            try {
                filter.statement_executeBatch(chain, statement);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public int[] statement_executeBatch(StatementProxy statement) throws SQLException {
                    throw new RuntimeException();
                }
            };

            Throwable error = null;
            try {
                filter.statement_executeBatch(chain, statement);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public int[] statement_executeBatch(StatementProxy statement) throws SQLException {
                    throw new Error();
                }
            };

            Throwable error = null;
            try {
                filter.statement_executeBatch(chain, statement);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        // //////////////////////////

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public ResultSetProxy statement_executeQuery(StatementProxy statement, String sql) throws SQLException {
                    throw new SQLException();
                }
            };

            Throwable error = null;
            try {
                filter.statement_executeQuery(chain, statement, sql);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public ResultSetProxy statement_executeQuery(StatementProxy statement, String sql) throws SQLException {
                    throw new RuntimeException();
                }
            };

            Throwable error = null;
            try {
                filter.statement_executeQuery(chain, statement, sql);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public ResultSetProxy statement_executeQuery(StatementProxy statement, String sql) throws SQLException {
                    throw new Error();
                }
            };

            Throwable error = null;
            try {
                filter.statement_executeQuery(chain, statement, sql);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        // //////////////////////////

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public int preparedStatement_executeUpdate(PreparedStatementProxy statement) throws SQLException {
                    throw new SQLException();
                }
            };

            Throwable error = null;
            try {
                filter.preparedStatement_executeUpdate(chain, statement);
            } catch (SQLException ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public int preparedStatement_executeUpdate(PreparedStatementProxy statement) throws SQLException {
                    throw new RuntimeException();
                }
            };

            Throwable error = null;
            try {
                filter.preparedStatement_executeUpdate(chain, statement);
            } catch (RuntimeException ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public int preparedStatement_executeUpdate(PreparedStatementProxy statement) throws SQLException {
                    throw new Error();
                }
            };

            Throwable error = null;
            try {
                filter.preparedStatement_executeUpdate(chain, statement);
            } catch (Error ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        // //////////////////////////

        // //////////////////////////

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public int statement_executeUpdate(StatementProxy statement, String sql) throws SQLException {
                    throw new SQLException();
                }
            };

            Throwable error = null;
            try {
                filter.statement_executeUpdate(chain, statement, sql);
            } catch (SQLException ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public int statement_executeUpdate(StatementProxy statement, String sql) throws SQLException {
                    throw new RuntimeException();
                }
            };

            Throwable error = null;
            try {
                filter.statement_executeUpdate(chain, statement, sql);
            } catch (RuntimeException ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public int statement_executeUpdate(StatementProxy statement, String sql) throws SQLException {
                    throw new Error();
                }
            };

            Throwable error = null;
            try {
                filter.statement_executeUpdate(chain, statement, sql);
            } catch (Error ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        // //////////////////////////

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public int statement_executeUpdate(StatementProxy statement, String sql, int autoGeneratedKeys)
                        throws SQLException {
                    throw new SQLException();
                }
            };

            Throwable error = null;
            try {
                filter.statement_executeUpdate(chain, statement, sql, Statement.NO_GENERATED_KEYS);
            } catch (SQLException ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public int statement_executeUpdate(StatementProxy statement, String sql, int autoGeneratedKeys)
                        throws SQLException {
                    throw new RuntimeException();
                }
            };

            Throwable error = null;
            try {
                filter.statement_executeUpdate(chain, statement, sql, Statement.NO_GENERATED_KEYS);
            } catch (RuntimeException ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public int statement_executeUpdate(StatementProxy statement, String sql, int autoGeneratedKeys)
                        throws SQLException {
                    throw new Error();
                }
            };

            Throwable error = null;
            try {
                filter.statement_executeUpdate(chain, statement, sql, Statement.NO_GENERATED_KEYS);
            } catch (Error ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        // //////////////////////////

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public int statement_executeUpdate(StatementProxy statement, String sql, int[] columnIndexes)
                        throws SQLException {
                    throw new SQLException();
                }
            };

            Throwable error = null;
            try {
                filter.statement_executeUpdate(chain, statement, sql, new int[0]);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public int statement_executeUpdate(StatementProxy statement, String sql, int[] columnIndexes)
                        throws SQLException {
                    throw new RuntimeException();
                }
            };

            Throwable error = null;
            try {
                filter.statement_executeUpdate(chain, statement, sql, new int[0]);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public int statement_executeUpdate(StatementProxy statement, String sql, int[] columnIndexes)
                        throws SQLException {
                    throw new Error();
                }
            };

            Throwable error = null;
            try {
                filter.statement_executeUpdate(chain, statement, sql, new int[0]);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        // //////////////////////////

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public int statement_executeUpdate(StatementProxy statement, String sql, String[] columnNames)
                        throws SQLException {
                    throw new SQLException();
                }
            };

            Throwable error = null;
            try {
                filter.statement_executeUpdate(chain, statement, sql, new String[0]);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public int statement_executeUpdate(StatementProxy statement, String sql, String[] columnNames)
                        throws SQLException {
                    throw new RuntimeException();
                }
            };

            Throwable error = null;
            try {
                filter.statement_executeUpdate(chain, statement, sql, new String[0]);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public int statement_executeUpdate(StatementProxy statement, String sql, String[] columnNames)
                        throws SQLException {
                    throw new Error();
                }
            };

            Throwable error = null;
            try {
                filter.statement_executeUpdate(chain, statement, sql, new String[0]);
            } catch (Throwable ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        // ///////////////////////////

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public ResultSetProxy statement_getGeneratedKeys(StatementProxy statement) throws SQLException {
                    return null;
                }
            };

            filter.statement_getGeneratedKeys(chain, statement);
        }

        {
            final ResultSetProxy resultSet = new ResultSetProxyImpl(statement, null, 2001, null);
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public ResultSetProxy statement_getGeneratedKeys(StatementProxy statement) throws SQLException {
                    return resultSet;
                }
            };

            filter.statement_getGeneratedKeys(chain, statement);
        }

        // //////////////////////////

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public boolean preparedStatement_execute(PreparedStatementProxy statement) throws SQLException {
                    throw new SQLException();
                }
            };

            Throwable error = null;
            try {
                filter.preparedStatement_execute(chain, statement);
            } catch (SQLException ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public boolean preparedStatement_execute(PreparedStatementProxy statement) throws SQLException {
                    throw new RuntimeException();
                }
            };

            Throwable error = null;
            try {
                filter.preparedStatement_execute(chain, statement);
            } catch (RuntimeException ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public boolean preparedStatement_execute(PreparedStatementProxy statement) throws SQLException {
                    throw new Error();
                }
            };

            Throwable error = null;
            try {
                filter.preparedStatement_execute(chain, statement);
            } catch (Error ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        // //////////////////////////

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public ResultSetProxy preparedStatement_executeQuery(PreparedStatementProxy statement)
                        throws SQLException {
                    throw new SQLException();
                }
            };

            Throwable error = null;
            try {
                filter.preparedStatement_executeQuery(chain, statement);
            } catch (SQLException ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public ResultSetProxy preparedStatement_executeQuery(PreparedStatementProxy statement)
                        throws SQLException {
                    throw new RuntimeException();
                }
            };

            Throwable error = null;
            try {
                filter.preparedStatement_executeQuery(chain, statement);
            } catch (RuntimeException ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public ResultSetProxy preparedStatement_executeQuery(PreparedStatementProxy statement)
                        throws SQLException {
                    throw new Error();
                }
            };

            Throwable error = null;
            try {
                filter.preparedStatement_executeQuery(chain, statement);
            } catch (Error ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }


        // //////////////////////////

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public void dataSource_recycle(DruidPooledConnection connection) throws SQLException {
                    throw new SQLException();
                }
            };

            Throwable error = null;
            try {
                filter.dataSource_releaseConnection(chain, null);
            } catch (SQLException ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public void dataSource_recycle(DruidPooledConnection connection) throws SQLException {
                    throw new RuntimeException();
                }
            };

            Throwable error = null;
            try {
                filter.dataSource_releaseConnection(chain, null);
            } catch (RuntimeException ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public void dataSource_recycle(DruidPooledConnection connection) throws SQLException {
                    throw new Error();
                }
            };

            Throwable error = null;
            try {
                filter.dataSource_releaseConnection(chain, null);
            } catch (Error ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        // //////////////////////////

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public DruidPooledConnection dataSource_connect(DruidDataSource dataSource, long maxWaitMillis)
                        throws SQLException {
                    throw new SQLException();
                }
            };

            Throwable error = null;
            try {
                filter.dataSource_getConnection(chain, null, 0L);
            } catch (SQLException ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public DruidPooledConnection dataSource_connect(DruidDataSource dataSource, long maxWaitMillis)
                        throws SQLException {
                    throw new RuntimeException();
                }
            };

            Throwable error = null;
            try {
                filter.dataSource_getConnection(chain, null, 0L);
            } catch (RuntimeException ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }

        {
            FilterChain chain = new FilterChainImpl(new DataSourceProxyImpl(null, config)) {
                public DruidPooledConnection dataSource_connect(DruidDataSource dataSource, long maxWaitMillis)
                        throws SQLException {
                    throw new Error();
                }
            };

            Throwable error = null;
            try {
                filter.dataSource_getConnection(chain, null, 0L);
            } catch (Error ex) {
                error = ex;
            }
            Assert.assertNotNull(error);
        }
    }
}
