package com.habsida.moragoproject.configuration;

import com.habsida.moragoproject.repository.FileRepository;
import com.habsida.moragoproject.scalar.FileUpload;
import graphql.schema.*;
import graphql.schema.idl.SchemaDirectiveWiring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.data.query.QuerydslDataFetcher;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.IOException;

@Configuration
public class GraphQlConfig {
    @Bean
    public GraphQLScalarType dateScalar() {
        return GraphQLScalarType.newScalar()
                .name("FileUpload")
                .description("A file part in a multipart request")
                .coercing(new Coercing<FileUpload, Void>() {

                    @Override
                    public Void serialize(Object dataFetcherResult) {
                        throw new CoercingSerializeException("Upload is an input-only type");
                    }

                    @Override
                    public FileUpload parseValue(Object input) {
                        System.out.println("fgdfg");
                        if (input instanceof Part) {
                            Part part = (Part) input;
                            try {
                                String contentType = part.getContentType();
                                byte[] content = new byte[part.getInputStream().available()];
                                part.delete();
                                return new FileUpload(content);

                            } catch (IOException e) {
                                throw new CoercingParseValueException("Couldn't read content of the uploaded file");
                            }
                        } else if (null == input) {
                            return null;
                        } else {
                            throw new CoercingParseValueException(
                                    "Expected type " + Part.class.getName() + " but was " + input.getClass().getName());
                        }
                    }

                    @Override
                    public FileUpload parseLiteral(Object input) {
                        throw new CoercingParseLiteralException(
                                "Must use variables to specify Upload values");
                    }
                })
                .build();
    }

    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer() {
        GraphQLScalarType scalarType = dateScalar();
        return wiringBuilder -> wiringBuilder.scalar(scalarType);
    }
}
