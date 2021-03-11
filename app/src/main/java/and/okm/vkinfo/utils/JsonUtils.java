package and.okm.vkinfo.utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T fromJson(String json, Class<T> classOfT) throws IOException, NullPointerException {
        if (null != json && !json.isEmpty()) {
            return mapper.readValue(json, classOfT);
        }
        throw new NullPointerException();
    }

    public static <T> List<T> fromJsonArray(String json, Class<T> classOfT) throws IOException, NullPointerException {
        if (null != json && !json.isEmpty()) {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, classOfT));
        }
        throw new NullPointerException();
    }
}
