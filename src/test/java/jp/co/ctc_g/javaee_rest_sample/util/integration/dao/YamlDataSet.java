package jp.co.ctc_g.javaee_rest_sample.util.integration.dao;

import java.io.File;
import org.dbunit.dataset.CachedDataSet;
import org.dbunit.dataset.DataSetException;

public class YamlDataSet
        extends CachedDataSet {

    public YamlDataSet(File dir)
            throws DataSetException {
        super(new YamlProducer(dir));
    }
}
