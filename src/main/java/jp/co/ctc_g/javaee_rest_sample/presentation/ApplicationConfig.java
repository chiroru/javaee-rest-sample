package jp.co.ctc_g.javaee_rest_sample.presentation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ServerProperties;

/**
 * Bean Validation
 * に関する情報は{@link https://jersey.java.net/documentation/latest/bean-validation.html}を参照.
 * JAX-RS リソース、あるいは、メソッドのバリデーションに利用するバリデータのカスタマイズは、ValidationConfig クラスを利用します。
 * ContextResolver<T>機構を介してそれを公開します。 Bean Validation API
 * として提供される以下のインタフェースを実装したカスタムインスタンスを設定することに拡張を行います。
 * <ul>
 * <li>MessageInterpolator : 提供される制約違反のメッセージを補完する</li>
 * <li>TraversableResolver : プロパティがBean Validation プロバイダーからアクセス可能か決定する</li>
 * <li>ConstraintValidatorFactory : </li>
 * </ul>
 */
@ApplicationPath("webresources")
public class ApplicationConfig
        extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        // following code can be used to customize Jersey 2.0 JSON provider:
        try {
            Class<?> jsonProvider = Class.forName("org.glassfish.jersey.jackson.JacksonFeature");
            // Class jsonProvider = Class.forName("org.glassfish.jersey.moxy.json.MoxyJsonFeature");
            // Class jsonProvider = Class.forName("org.glassfish.jersey.jettison.JettisonFeature");
            resources.add(jsonProvider);
            resources.add(LoggingFilter.class);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        addRestResourceClasses(resources);
        return resources;
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        // Validation Error をClientに通知するかどうかを指定するプロパティ. [true : 通知する | false : 通知しない]
        properties.put(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        // ValidateOnExecution をサブクラスに遡ってチェックするかどうかを指定する.
        properties.put(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
        return properties;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(jp.co.ctc_g.javaee_rest_sample.presentation.MovieResource.class);
        resources.add(org.glassfish.jersey.server.validation.internal.ValidationErrorMessageBodyWriter.class);
        resources.add(org.glassfish.jersey.server.wadl.internal.WadlResource.class);
    }
}
