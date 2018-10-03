package spittr.web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spittle.Spittle;
import static org.mockito.Mockito.*;
import spittr.data.SpittleRepository;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class HomeControllerTest {

	@Test
	public void testHome() throws Exception {
		HomeController controller = new HomeController();
		MockMvc mockmvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockmvc.perform(get("/")).andExpect(view().name("home"));
	}

	@Test
	public void shouldShowRecentSpittles() {
		List<Spittle> expectedSpittles = createSpittleList(20);
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		when(mockRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(expectedSpittles);

		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).setSingleView("/WEB-INF/views/spittles.jsp")
				.build();
		mockMvc.perform(get("/")).andExpect(view().name("spittles")).andExpect(model().attributeExists("spittleList"))
				.andExpect(model().attribute("spittleList", expectedSpittles.toArray()));

	}

	private List<Spittle> createSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<>();
		for (int i = 0; i < count; i++)
			spittles.add(new Spittle("Spittle " + i, new Date()));
		return spittles;
	}

}
