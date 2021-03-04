package eci.arsw.covidanalyzer.test;

import com.google.gson.Gson;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import eci.arsw.covidanalyzer.CovidAggregateController;
import eci.arsw.covidanalyzer.CovidAnalyzerAPIApplication;
import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CovidAnalyzerAPIApplication.class})
public class AppTest {
    private MockMvc m;

    @Autowired
    private CovidAggregateController c;
    private final Gson gson = new Gson();

    @Before
    public void setUp(){
        m = MockMvcBuilders.standaloneSetup(c).build();
    }

    @Test
    public void deberiaConsultarTodosLosTruePositive() throws Exception {
        m.perform(
                MockMvcRequestBuilders.get("/covid/result/true-positive")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isAccepted());

    }

    @Test
    public void deberiaConsultarTodosLosTrueNegative() throws Exception {
        m.perform(
                MockMvcRequestBuilders.get("/covid/result/true-negative")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isAccepted());

    }

    @Test
    public void deberiaConsultarTodosLosFalsePositive() throws Exception {
        m.perform(
                MockMvcRequestBuilders.get("/covid/result/false-positive")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isAccepted());

    }


}
