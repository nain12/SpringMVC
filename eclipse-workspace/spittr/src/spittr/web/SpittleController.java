package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spittr.data.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

	private SpittleRepository spittlerepository;

	@Autowired
	public SpittleController(SpittleRepository spittlerepository) {
		this.spittlerepository = spittlerepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String spittles(Model model) {
		model.addAttribute("spittleList",spittlerepository.findSpittles(Long.MAX_VALUE, 20));
		return "spittles";
	}

}
