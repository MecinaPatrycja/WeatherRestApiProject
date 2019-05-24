package pl.mecinapatrycja.unittests;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.mecinapatrycja.controller.CountryApiController;
import pl.mecinapatrycja.model.CountryApiGeonames;
import pl.mecinapatrycja.model.CountryApiModel;
import pl.mecinapatrycja.service.impl.CountryApiServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
@RunWith(MockitoJUnitRunner.class)
public class CountryApiControllerTest {
    @InjectMocks
    private CountryApiController countryApiController;
    @Mock
    private CountryApiServiceImpl countryApiService;
    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(countryApiController).build();
    }

    @Test
    public void addNew() throws Exception {
        CountryApiModel countryApiModel = new CountryApiModel();
        CountryApiGeonames countryApiGeonames = new CountryApiGeonames();
        countryApiGeonames.setCountryName("Polska");
        countryApiGeonames.setCode("PL");
        countryApiGeonames.setName("Warsaw");
        List<CountryApiGeonames> countryApiGeonames1 = new ArrayList<>();
        countryApiGeonames1.add(countryApiGeonames);
        countryApiModel.setGeonames(countryApiGeonames1);
        mockMvc.perform(MockMvcRequestBuilders.get("/country?code=PL")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(countryApiModel)))
                .andDo(print())
                //.andExpect(MockMvcResultMatchers.jsonPath("countryName").value("Polska"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private String toJson(CountryApiModel holidayModelResponseEntity) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(holidayModelResponseEntity);
    }
}