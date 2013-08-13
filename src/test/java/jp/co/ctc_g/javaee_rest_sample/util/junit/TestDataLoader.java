package jp.co.ctc_g.javaee_rest_sample.util.junit;

import java.io.BufferedInputStream;
import java.io.IOException;

public class TestDataLoader {

    private final static String ENCODING = "UTF-8";
    private String resource;

    protected String loadResource(String resource) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedInputStream bis = new BufferedInputStream(this.getClass().getClassLoader().getResourceAsStream(resource));
        byte[] buffer = new byte[1024];
        while (bis.read(buffer) != -1) {
            builder.append(new String(buffer, ENCODING));
            buffer = new byte[1024];
        }
        return builder.toString();
    }

    public TestDataLoader(String resource) {
        this.resource = resource;
    }

    public String[] load() {
        try {
            String data = loadResource(resource);
            return data.split(";");
        } catch (IOException e) {
            throw new RuntimeException("テストデータファイルの読み込みに失敗しました.");
        }
    }
}