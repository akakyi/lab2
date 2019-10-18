package edu.lab.back.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface JsonPojo {

    default String toJsonString() throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        final String jsonString = mapper.writeValueAsString(this);

        return jsonString;
    }

}
