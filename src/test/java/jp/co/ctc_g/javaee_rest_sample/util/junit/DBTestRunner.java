package jp.co.ctc_g.javaee_rest_sample.util.junit;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;
import jp.co.ctc_g.javaee_rest_sample.util.common.resources.ClassPathPropertyLoader;
import org.junit.internal.runners.statements.InvokeMethod;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBTestRunner
        extends BlockJUnit4ClassRunner {

    protected static Logger L = LoggerFactory.getLogger(DBTestRunner.class);
    
    public DBTestRunner(Class<?> clazz)
            throws InitializationError {
        super(clazz);
    }

    @Override
    protected void runChild(FrameworkMethod method, RunNotifier notifier) {
        super.runChild(method, notifier);
    }

    @Override
    protected Statement methodInvoker(FrameworkMethod method, Object test) {
        return new InvokeDBTestMethod(method, test);
    }

    private class InvokeDBTestMethod extends InvokeMethod {

        private FrameworkMethod testMethod;
        private Object testTarget;

        public InvokeDBTestMethod(FrameworkMethod testMethod, Object target) {
            super(testMethod, target);
            this.testMethod = testMethod;
            this.testTarget = target;
        }

        @Override
        public void evaluate() throws Throwable {
            L.info(getTestClass().getName() + "#" + testMethod.getName() + " [start]");
            TransactionStrategy strategy = testMethod.getAnnotation(TransactionStrategy.class);
            DBOperation dbOperation = new DBOperation(strategy.value());
            setUpOperation(dbOperation, testMethod);
            super.evaluate();
            tearDownOperation(dbOperation, testMethod);
            dbOperation.dbOperationEnd();
            L.info(getTestClass().getName() + "#" + testMethod.getName() + " [end]");
        }
    }

    protected void setUpOperation(DBOperation dbOperation, FrameworkMethod testMethod) {
        DBSetUpOperation setupOperation = testMethod.getAnnotation(DBSetUpOperation.class);
        dbOperation.executeDBOperation(setupOperation.value());
    }

    protected void tearDownOperation(DBOperation dbOperation, FrameworkMethod testMethod) {
        DBTearDownOperation tearDownOperation = testMethod.getAnnotation(DBTearDownOperation.class);
        dbOperation.executeDBOperation(tearDownOperation.value());
    }
}
