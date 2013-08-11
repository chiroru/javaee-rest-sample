package jp.co.ctc_g.javaee_rest_sample.integration.util;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;

@DataSourceDefinition(name = "java:app/env/myDatasource",
		className = "oracle.jdbc.driver.OracleDriver",
		user = "TEST",
		password = "TEST",
                url = "jdbc:oracle:thin:@127.0.0.1:1521:XE"
)
@Stateless
public class DbConfiguration {
}
