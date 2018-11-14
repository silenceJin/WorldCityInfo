package example.world.service;

import example.world.controller.model.Responses;

public interface CountryService {
	public Responses findAllCountry(int pageNum, String countryName);
}
