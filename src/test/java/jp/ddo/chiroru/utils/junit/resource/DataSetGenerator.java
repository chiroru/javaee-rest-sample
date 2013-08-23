package jp.ddo.chiroru.utils.junit.resource;

import org.dbunit.dataset.IDataSet;

public interface DataSetGenerator {
    IDataSet generate(String path);
}
