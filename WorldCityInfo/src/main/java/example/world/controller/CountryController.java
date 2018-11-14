package example.world.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.world.controller.model.Responses;
import example.world.service.CountryService;

@RestController
@RequestMapping("/country")
public class CountryController {
	private final CountryService countryService;

	public CountryController(CountryService countryService) {
		super();
		this.countryService = countryService;
	}

	@GetMapping("/allCountry/{pageNum}")
	public Responses allCountry(@PathVariable("pageNum") int pageNum,
			@RequestParam(required = false) String countryName) {
		return countryService.findAllCountry(pageNum, countryName);
	}
}
