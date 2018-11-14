package example.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.world.controller.model.Responses;
import example.world.service.CityService;

@RestController
@RequestMapping("/city")
public class CityController {
	public final CityService cityService;

	@Autowired
	public CityController(CityService cityService) {
		super();
		this.cityService = cityService;
	}

	@GetMapping("/findCity/{pageNum}")
	public Responses findCityInfo(@PathVariable int pageNum, @RequestParam(required = false) String countryCode,
			@RequestParam(required = false) String cityName) {
		return cityService.findCityInfo(countryCode, cityName, pageNum);
	}

	@GetMapping("/{id}")
	public Responses cityInfo(@PathVariable("id") int id) {
		return cityService.getCityInfo(id);
	}

	@GetMapping("/{id}/belongToCountry")
	public Responses belongToCountry(@PathVariable("id") int cityID) {
		return cityService.findBelongToCountry(cityID);
	}

}
