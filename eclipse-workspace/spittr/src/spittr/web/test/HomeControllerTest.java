package spittr.web.test;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import spittle.Spittle;
import spittr.data.SpittleRepository;
import spittr.web.HomeController;
import spittr.web.SpittleController;

import static org.mockito.Mockito.*;
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
	public void shouldShowRecentSpittles() throws Exception {
		List<Spittle> expectedSpittles = createSpittleList(20);
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		when(mockRepository.findSpittles(238900, 50)).thenReturn(expectedSpittles);

		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
				.build();
		mockMvc.perform(get("/spittles?max=238900&count=50")).andExpect(view().name("spittles")).andExpect(model().attributeExists("spittleList"))
				.andExpect(model().attribute("spittleList", expectedSpittles.toArray()));

	}

	private List<Spittle> createSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<>();
		for (int i = 0; i < count; i++)
			spittles.add(new Spittle("Spittle " + i, new Date()));
		return spittles;
	}

}
