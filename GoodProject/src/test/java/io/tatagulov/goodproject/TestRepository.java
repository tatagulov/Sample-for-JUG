package io.tatagulov.goodproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.tatagulov.goodproject.web.api.dto.TableData;
import io.tatagulov.goodproject.web.api.dto.TableDataRequest;
import io.tatagulov.goodproject.web.controller.MainController;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:TestRepository-context.xml")
@WebAppConfiguration
public class TestRepository {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Autowired
    MainController mainController;

    @Test
    public void testRequest() throws Exception {
        testRequest("aircraftSelectOne", "/selectOne/aircraftRepository");
        testRequest("flightSelectTable", "/tableData/flightRepository");
        long start = System.currentTimeMillis();
        testRequest("report1", "/tableData/report1");
        long div = System.currentTimeMillis() - start;
        System.out.println("execute time " + div + " millisecond");
    }

    private void testRequest(String folderName, String url) throws Exception {
        String json = getJSON(folderName + "/request.json");
        MockHttpServletRequestBuilder request = post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        String actual = mvc.perform(request).andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        String expected = getJSON(folderName + "/response.json");

        String prettyActual = prettyFormatJson(actual);
        String prettyExpected = prettyFormatJson(expected);

        Assert.assertEquals(prettyExpected,prettyActual);
    }

    private String prettyFormatJson(String actual) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(actual);
        return gson.toJson(je);
    }

    private String getJSON(String name) throws IOException {
        InputStream inputStream = TestRepository.class.getResourceAsStream(name);
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, "UTF-8");
        return writer.toString();
    }
}
