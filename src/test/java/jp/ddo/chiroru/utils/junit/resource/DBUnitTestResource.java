package jp.ddo.chiroru.utils.junit.resource;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import jp.co.ctc_g.javaee_rest_sample.util.common.resources.ClassPathPropertyLoader;
import org.dbunit.AbstractDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.oracle.Oracle10DataTypeFactory;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public abstract class DBUnitTestResource
        extends AbstractDatabaseTester
        implements TestRule {

    private final static String PROPERTY_PATH = "jdbc.properties";
    private Properties props;
    private final String connectionUrl;
    private final String username;
    private final String password;
    private DataSetFixtureAnnotationParser parser;
    private DataSetFixture globalDataSetFixture = null;

    public DBUnitTestResource(Class<?> testTargetClass) {
        parser = new DataSetFixtureAnnotationParser(testTargetClass);
        Properties configPops = loadConfigProperty();
        connectionUrl = props.getProperty("jdbc.url");
        username = props.getProperty("jdbc.username");
        password = props.getProperty("jdbc.password");
        setSchema(props.getProperty("jdbc.schema"));
    }

    private Properties loadConfigProperty() {
        try {
            ClassPathPropertyLoader loader = ClassPathPropertyLoader.getInstance();
            props = loader.load(PROPERTY_PATH);
            Preconditions.checkState(
                    !Strings.isNullOrEmpty(props.getProperty("jdbc.url")),
                    "JDBC接続情報設定ファイル[" + PROPERTY_PATH + "]のプロパティ[jdbc.url]の値が不正です.");
            Preconditions.checkState(
                    !Strings.isNullOrEmpty(props.getProperty("jdbc.username")),
                    "JDBC接続情報設定ファイル[" + PROPERTY_PATH + "]のプロパティ[jdbc.username]の値が不正です.");
            Preconditions.checkState(
                    !Strings.isNullOrEmpty(props.getProperty("jdbc.password")),
                    "JDBC接続情報設定ファイル[" + PROPERTY_PATH + "]のプロパティ[jdbc.password]の値が不正です.");
            Preconditions.checkState(
                    !Strings.isNullOrEmpty(props.getProperty("jdbc.schema")),
                    "JDBC接続情報設定ファイル[" + PROPERTY_PATH + "]のプロパティ[jdbc.schema]の値が不正です.");
            return props;
        } catch (IOException e) {
            throw new RuntimeException("JDBC接続情報設定ファイル[" + PROPERTY_PATH + "]の読み込みに失敗しました.");
        }
    }

    @Override
    public IDatabaseConnection getConnection()
            throws Exception {
        Connection conn;
        if (username == null && password == null) {
            conn = DriverManager.getConnection(connectionUrl);
        } else {
            conn = DriverManager.getConnection(connectionUrl, username, password);
        }
        DatabaseConnection databaseConnection =
                new DatabaseConnection(conn, getSchema());
        DatabaseConfig config = databaseConnection.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new Oracle10DataTypeFactory());
        return databaseConnection;
    }

    protected void executeQuery(String sql)
            throws Exception {
        try (Connection conn = getConnection().getConnection()) {
            conn.createStatement().execute(sql);
            conn.commit();
        }
    }

    protected void before() throws Exception {
    }

    protected void after() throws Exception {
    }

//    abstract protected IDataSet createDataSet() throws Exception;

    @Override
    public Statement apply(final Statement statement,
            final Description description) {
        return new Statement() {
            @Override
            public void evaluate()
                    throws Throwable {
                before();
//                IDataSet dataSet = createDataSet();
                setDataSet(parser.doParse(description));
//                setDataSet(dataSet);
                onSetup();
                try {
                    statement.evaluate();
                } finally {
                    try {
                        after();
                    } finally {
                        onTearDown();
                    }
                }
            }
        };
    }
}
