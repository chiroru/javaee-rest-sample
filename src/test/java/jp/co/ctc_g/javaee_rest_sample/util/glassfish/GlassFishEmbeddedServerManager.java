package jp.co.ctc_g.javaee_rest_sample.util.glassfish;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import jp.co.ctc_g.javaee_rest_sample.util.integration.IntegrationTest;
//import org.glassfish.embeddable.CommandResult;
//import org.glassfish.embeddable.Deployer;
//import org.glassfish.embeddable.GlassFish;
//import org.glassfish.embeddable.GlassFishException;
//import org.glassfish.embeddable.GlassFishProperties;
//import org.glassfish.embeddable.GlassFishRuntime;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GlassFishEmbeddedServerManager {

//    @BeforeClass
//    public static void setUpClass() {
//        try {
//            GlassFishProperties glassfishProperties = new GlassFishProperties();
//            glassfishProperties.setPort("http-listener", 8080);
//            GlassFish glassfish = GlassFishRuntime.bootstrap().newGlassFish(glassfishProperties);
//            glassfish.start();
//            CommandResult r1 = glassfish.getCommandRunner().run(
//                "create-jdbc-connection-pool",
//                "--datasourceclassname",
//                "oracle.jdbc.pool.OracleDataSource",
//                "--restype",
//                "javax.sql.DataSource",
//                "--property",
//                "user=TEST:password=TEST:url=\"jdbc:oracle:thin:@127.0.0.1:1521:XE\"",
//                "OraclePool");
//            CommandResult r2 = glassfish.getCommandRunner().run(
//                "create-jdbc-resource",
//                "--connectionpoolid",
//                "OraclePool",
//                "jdbc/oracle");
//            File war = new File("target/javaee-rest-sample-1.0-SNAPSHOT.war");
//            Deployer deployer = glassfish.getDeployer();
//            deployer.deploy(war, "--force=true");
//            try {
//                Thread.sleep(60000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        } catch (GlassFishException e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    public void test() {
        try {
            Thread.sleep(60000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GlassFishEmbeddedServerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
