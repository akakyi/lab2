package edu.lab.back.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.lab.back.json.JsonPojo;
import lombok.NonNull;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public abstract class BaseHttpServlet extends HttpServlet {

    protected void writeStringResult(
        @NonNull final String res,
        @NonNull final HttpServletResponse response
    ) throws IOException
    {
        final ServletOutputStream outputStream = response.getOutputStream();
        outputStream.print(res);
        outputStream.close();
    }

    protected void writeResult(
        @NonNull final List<? extends JsonPojo> pojos,
        @NonNull final HttpServletResponse response
    ) throws IOException
    {
        final ObjectMapper mapper = new ObjectMapper();
        final String jsonString = mapper.writeValueAsString(pojos);

        this.writeStringResult(jsonString, response);
    }

    protected <JsonType extends JsonPojo> JsonType readRequest(
        @NonNull final HttpServletRequest req,
        Class<JsonType> clazz
    ) throws IOException
    {
        final ServletInputStream inputStream = req.getInputStream();
        final ObjectMapper mapper = new ObjectMapper();
        final JsonType result = mapper.readValue(inputStream, clazz);

        return result;
    }

    protected void writeValidationError(
            @NonNull final String errMsg,
            @NonNull final HttpServletResponse response
    ) throws IOException
    {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        final ServletOutputStream outputStream = response.getOutputStream();
        outputStream.print(errMsg);
        outputStream.close();
    }

}
