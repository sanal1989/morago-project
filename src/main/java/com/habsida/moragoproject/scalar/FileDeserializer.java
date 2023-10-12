package com.habsida.moragoproject.scalar;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileDeserializer extends StdDeserializer<FileUpload> {

    protected FileDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public FileUpload deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext
    ) throws IOException, JsonProcessingException, IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        return new FileUpload(
//                node.get("name").textValue(),
//                node.get("originalFilename").textValue(),
//                node.get("contentType").textValue(),
                node.get("content").binaryValue());
    }
}
