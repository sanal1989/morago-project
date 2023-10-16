package com.habsida.moragoproject.configuration;

import com.habsida.moragoproject.repository.FileRepository;
import graphql.schema.*;
import graphql.schema.idl.SchemaDirectiveWiring;
import name.nkonev.multipart.spring.graphql.coercing.webmvc.UploadCoercing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.data.query.QuerydslDataFetcher;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.IOException;

@Configuration
public class GraphQlConfig {
//    @Bean
//    public RuntimeWiringConfigurer runtimeWiringConfigurerUpload() {
//        GraphQLScalarType uploadScalar = GraphQLScalarType.newScalar()
//                .name("Upload")
//                .coercing(new UploadCoercing())
//                .build();
//
//        return wiringBuilder -> wiringBuilder.scalar(uploadScalar);
//    }
}
