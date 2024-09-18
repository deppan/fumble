package com.tsinsi.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.QueryParametersSnippet;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("development")
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class UserApplicationTests {

    private RestDocumentationResultHandler documentationHandler;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {
        documentationHandler = document("{method-name}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()));

        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(documentationHandler)
                .build();
    }

    private FieldDescriptor[] accountFields() {
        return new FieldDescriptor[]{
                fieldWithPath("id").description("id"),
                fieldWithPath("username").description("Username of the account"),
                fieldWithPath("nickname").description("Nickname of the account"),
                fieldWithPath("gender").description("Gender of the account")
        };
    }

    @Test
    public void users() throws Exception {
        QueryParametersSnippet request = queryParameters(
                parameterWithName("before").optional().description("The accounts before it"),
                parameterWithName("after").optional().description("The accounts after it")
        );
        ResponseFieldsSnippet response = responseFields(fieldWithPath("[]").description("An array of accounts")).andWithPrefix("[].", accountFields());
        mockMvc.perform(get("/users").param("after", "r"))
                .andExpect(status().isOk())
                .andDo(this.documentationHandler.document(request, response));
    }

    @Test
    public void user() throws Exception {
        ResponseFieldsSnippet response = responseFields(accountFields());
        mockMvc.perform(get("/user/{username}", "deppan"))
                .andExpect(status().isOk())
                .andDo(documentationHandler.document(pathParameters(parameterWithName("username").description("The account's username")), response));
    }
}