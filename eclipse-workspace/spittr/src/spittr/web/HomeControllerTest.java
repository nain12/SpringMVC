package spittr.web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class HomeControllerTest {
	
	@Test
	public void testHome() throws Exception {
		HomeController controller = new HomeController();
		MockMvc mockmvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockmvc.perform(get("/")).andExpect(view().name("home"));
	}

}
