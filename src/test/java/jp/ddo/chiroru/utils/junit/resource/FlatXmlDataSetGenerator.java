package jp.ddo.chiroru.utils.junit.resource;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

public class FlatXmlDataSetGenerator
        implements DataSetGenerator {

    @Override
    public IDataSet generate(String path) {
        try {
            return new FlatXmlDataSetBuilder().build(getClass().getResourceAsStream(path));
        } catch (DataSetException e) {
            throw new RuntimeException("テストデータの定義ファイル[" + path + "]の読み込みに失敗しました.", e);
        }
    }
}
