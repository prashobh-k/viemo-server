package com.heraizen.vimeo.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.clickntap.vimeo.VimeoResponse;

import java.io.IOException;

public class VimeoResponseSerializer extends JsonSerializer<VimeoResponse> {

    @Override
    public void serialize(VimeoResponse vimeoResponse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("rateLimit", vimeoResponse.getRateLimit());
        jsonGenerator.writeObjectField("X-RateLimit-Limit",vimeoResponse.getRateLimit());
        // Add other fields from VimeoResponse that you need to include
        // ...

        jsonGenerator.writeEndObject();
    }
}

