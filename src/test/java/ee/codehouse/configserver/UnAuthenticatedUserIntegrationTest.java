package ee.codehouse.configserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ConfigServerApplication.class)
@AutoConfigureMockMvc
class UnAuthenticatedUserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getApplicationConfig() throws Exception {
        mockMvc.perform(get("/application/dev"))
                .andExpect(status().isUnauthorized());
    }

}
