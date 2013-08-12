package jp.co.ctc_g.javaee_rest_sample.util.common.resources;

import java.io.IOException;
import java.util.Properties;

public interface PropertyLoader {

    Properties load(String path) throws IOException;
}
