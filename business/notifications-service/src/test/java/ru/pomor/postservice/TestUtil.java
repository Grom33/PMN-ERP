package ru.pomor.postservice;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import ru.gromov.notification.util.JsonUtil;
import ru.gromov.notification.util.JacksonObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class TestUtil {
    public static <T> ResultMatcher contentJson(T expected) {
        return content().json(JsonUtil.writeValue(expected));
    }

    public static String getContent(ResultActions action) throws UnsupportedEncodingException {
        return action.andReturn().getResponse().getContentAsString();
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return JacksonObjectMapper.getMapper().readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> T readFromJson(ResultActions action, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValue(getContent(action), clazz);
    }


}
