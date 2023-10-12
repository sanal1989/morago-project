package com.habsida.moragoproject.scalar;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@JsonDeserialize(using = FileDeserializer.class)
public class FileUpload{

    private byte[] content;
}
