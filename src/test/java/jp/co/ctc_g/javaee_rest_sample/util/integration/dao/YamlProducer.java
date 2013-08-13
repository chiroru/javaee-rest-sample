package jp.co.ctc_g.javaee_rest_sample.util.integration.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.stream.IDataSetConsumer;
import org.dbunit.dataset.stream.IDataSetProducer;

public class YamlProducer
        implements IDataSetProducer {

    private String directory;

    public YamlProducer(File directory) {
        this.directory = directory.getAbsolutePath();
    }

    @Override
    public void setConsumer(IDataSetConsumer idsc)
            throws DataSetException {

    }

    @Override
    public void produce()
            throws DataSetException {

    }

    public static List getTables(URL base, String tableList) throws IOException {
        List orderedNames = new ArrayList();
        InputStream tableListStream = new URL(base, tableList).openStream();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(tableListStream));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String table = line.trim();
                if (table.length() > 0) {
                    orderedNames.add(table);
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return orderedNames;
    }
}
