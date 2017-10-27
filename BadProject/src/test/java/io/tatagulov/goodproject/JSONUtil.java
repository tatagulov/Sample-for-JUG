package io.tatagulov.goodproject;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class JSONUtil {

    public static String getJSON(String name) throws IOException {
        InputStream inputStream = JSONUtil.class.getResourceAsStream(name);
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, "UTF-8");
        return writer.toString();
    }
}
