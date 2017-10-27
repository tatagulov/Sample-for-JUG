package io.tatagulov.goodproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.tatagulov.badproject.web.dto.airport.AirportListRequest;
import io.tatagulov.badproject.web.dto.booking.BookingListRequest;
import io.tatagulov.badproject.web.dto.report1.Report1Data;
import io.tatagulov.badproject.web.entity.AirportsEntity;
import io.tatagulov.badproject.web.entity.BookingsEntity;
import io.tatagulov.badproject.web.entity.TicketFlightsEntity;
import io.tatagulov.badproject.web.entity.TicketFlightsEntityPK;
import io.tatagulov.badproject.web.repo.AirportRepository;
import io.tatagulov.badproject.web.repo.BookingRepository;
import io.tatagulov.badproject.web.repo.TicketFlightRepository;
import io.tatagulov.badproject.web.report.Report1;
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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-servlet.xml")
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
    TicketFlightRepository ticketFlightRepository;


    @Autowired
    AirportRepository airportRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    Report1 report1;

    @Test
    public void testReport1() throws Exception {
        long start = System.currentTimeMillis();
        List<Report1Data> datas = report1.getData();
        for (Report1Data data : datas) {
            System.out.println(data.getMonth()+":"+data.getTotalAmount());
        }
        long div = System.currentTimeMillis() - start;
        System.out.println("execute time " + div + " millisecond");
    }

    @Test
    public void testBookList() throws Exception {
        BookingListRequest request = new BookingListRequest();

        request.setStartDate("2016-08-19");
        request.setEndDate("2016-08-19");
        request.setMaxTotalAmmount(100000);
        List<BookingsEntity> list = bookingRepository.list(request);
        System.out.println(list.size());
    }

    @Test
    public void name() throws Exception {
        TicketFlightsEntityPK a = new TicketFlightsEntityPK();
        a.setFlightId(30625);
        a.setTicketNo("0005435212370");
        TicketFlightsEntity ticketFlightsEntity = ticketFlightRepository.findById(a);
        System.out.println(ticketFlightsEntity.getTicketNo());
    }

    @Test
    public void testAirportList() throws Exception {
        AirportListRequest request = new AirportListRequest();
        request.setAirportCode("CSY");
        request.setPage(1);
        request.setPageSize(10);
        List<AirportsEntity> list = airportRepository.getList(request);
        for (AirportsEntity airportsEntity : list) {
            System.out.println(airportsEntity.getAirportName());
        }
    }

    @Test
    public void testAirportList2() throws Exception {
        AirportListRequest request = new AirportListRequest();
        request.setAirportStartName("ха");
        request.setPage(1);
        request.setPageSize(10);
        List<AirportsEntity> list = airportRepository.getList(request);
        for (AirportsEntity airportsEntity : list) {
            System.out.println(airportsEntity.getAirportName());
        }
    }

    @Test
    public void testRequest() throws Exception {
//        testRequest("aircraftSelectOne", "/selectOne/aircraftRepository");
//        testRequest("flightSelectTable", "/tableData/flightRepository");
    }

    private void testRequest(String folderName, String url) throws Exception {
        String json = JSONUtil.getJSON(folderName + "/request.json");
        MockHttpServletRequestBuilder request = post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        String actual = mvc.perform(request).andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        String expected = JSONUtil.getJSON(folderName + "/response.json");

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
}
