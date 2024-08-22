package com.tsinsi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.QueryParametersSnippet;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("development")
public class AccountApplicationTests extends AbstractTestNGSpringContextTests {

    private final ManualRestDocumentation restDocumentation = new ManualRestDocumentation();

    @Autowired
    private WebApplicationContext context;

    @Value("${server.port}")
    private int port;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setUp(Method method) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(documentationConfiguration(this.restDocumentation).uris().withPort(port)).build();
        restDocumentation.beforeTest(getClass(), method.getName());
    }

    @AfterMethod
    public void tearDown() {
        restDocumentation.afterTest();
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
    public void accounts() throws Exception {
        QueryParametersSnippet request = queryParameters(
                parameterWithName("before").optional().description("The accounts before it"),
                parameterWithName("after").optional().description("The accounts after it")
        );
        ResponseFieldsSnippet response = responseFields(fieldWithPath("[]").description("An array of accounts")).andWithPrefix("[].", accountFields());
        mockMvc.perform(get("/users").param("after", "r"))
                .andExpect(status().isOk())
                .andDo(document("accounts", request, response));
    }

    @Test
    public void account() throws Exception {
        ResponseFieldsSnippet response = responseFields(accountFields());
        mockMvc.perform(get("/user/{username}", "deppan"))
                .andExpect(status().isOk())
                .andDo(document("account",
                        pathParameters(parameterWithName("username").description("The account's username")),
                        response
                ));
    }
}