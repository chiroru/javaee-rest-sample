package jp.co.ctc_g.javaee_rest_sample.util.junit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import jp.co.ctc_g.javaee_rest_sample.util.common.resources.ClassPathPropertyLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBOperation {

    protected static Logger L = LoggerFactory.getLogger(DBTestRunner.class);
    private final static String PROPERTY_PATH = "jdbc.properties";
    private Properties props;
    private final String connectionUrl;
    private final String username;
    private final String password;
    private Connection conn;
    private final TransactionStrategyType type;

    public DBOperation(TransactionStrategyType type) {
        try {
            ClassPathPropertyLoader loader = ClassPathPropertyLoader.getInstance();
            props = loader.load(PROPERTY_PATH);
            this.connectionUrl = props.getProperty("jdbc.url");
            this.username = props.getProperty("jdbc.username");
            this.password = props.getProperty("jdbc.password");
            this.type = type;
        } catch (IOException e) {
            throw new RuntimeException("データベースの接続先設定ファイルのロード時にエラーが発生しました.");
        }
    }

    private Connection getConnection() {
        try {
            Connection conn;
            if (username == null && password == null) {
                conn = DriverManager.getConnection(connectionUrl);
            } else {
                conn = DriverManager.getConnection(connectionUrl, username, password);
            }
            conn.setAutoCommit(false);
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException("データベースのコネクション確立に失敗しました.");
        }
    }

    protected void executeDBOperation(String[] queries) {
        for (String query : queries) {
            executeQuery(query);
        }
    }

    protected void executeQuery(String query) {
        try {
            if (conn == null) {
                conn = getConnection();
            }
            conn.createStatement().execute(query);
        } catch (SQLException e) {
            throw new RuntimeException("SQL QUERY [" + query + "] の実行が失敗しました.");
        }
    }

    protected void dbOperationEnd() {
        try {
            switch (type) {
                case POST_COMMIT:
                    conn.commit();
                case POST_ROLLBACK:
                    conn.rollback();
                default:
                    conn.commit();
            }
        } catch (SQLException e) {
            throw new RuntimeException("データベースのトランザクション処理に失敗しました.");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    throw new RuntimeException("データベースのコネクションのクローズに失敗しました.");
                }
            }
        }
    }
}
