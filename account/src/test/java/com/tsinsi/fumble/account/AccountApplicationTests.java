package com.tsinsi.fumble.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.RequestParametersSnippet;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountApplicationTests extends AbstractTestNGSpringContextTests {

    private final ManualRestDocumentation restDocumentation = new ManualRestDocumentation();

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setUp(Method method) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(documentationConfiguration(this.restDocumentation).uris().withPort(9999)).build();
        restDocumentation.beforeTest(getClass(), method.getName());
    }

    @AfterMethod
    public void tearDown() {
        restDocumentation.afterTest();
    }

    @Test
    public void index() throws Exception {
        RequestParametersSnippet request = requestParameters(parameterWithName("index").description(""));
        ResponseFieldsSnippet response = responseFields(fieldWithPath("name").description("The page name"));
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andDo(document("account", request, response))
        ;
    }

    @Test
    public void test() {
        String date = "2022-12-21";
        LocalDate localDateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-M-dd"));
        System.out.println(localDateTime);

        LocalDate now = LocalDate.now();
        LocalDate localDate = LocalDate.of(2022, 12, 31);
        Period period = Period.between(now, localDate);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        System.out.println(localDate.toEpochDay() - now.toEpochDay());
    }
}