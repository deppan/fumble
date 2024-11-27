package com.tsinsi.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.restdocs.http.HttpDocumentation;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.FormParametersSnippet;
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
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.formParameters;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

@SpringBootTest
public class AuthApplicationTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext context;

    private final ManualRestDocumentation restDocumentation = new ManualRestDocumentation();

    private MockMvc mockMvc;

    @BeforeMethod
    public void setUp(Method method) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation)
                        .operationPreprocessors().withRequestDefaults(prettyPrint())
                        .and()
                        .operationPreprocessors().withResponseDefaults(prettyPrint())
                        .and().snippets().withDefaults(HttpDocumentation.httpRequest(), HttpDocumentation.httpResponse())
                        .and().uris())
                .build();
        restDocumentation.beforeTest(getClass(), method.getName());
    }

    @AfterMethod
    public void tearDown() {
        restDocumentation.afterTest();
    }

    @Test
    void signIn() throws Exception {
        FormParametersSnippet parameters = formParameters(
                parameterWithName("username").optional().description("username"),
                parameterWithName("password").optional().description("password")
        );
        ResponseFieldsSnippet response = responseFields(fieldWithPath("token").description("token"));
        mockMvc.perform(post("/sign-in")
                        .param("username", "deppan")
                        .param("password", "123456")
                )
                .andDo(document("{method-name}", parameters, response));
    }
}
