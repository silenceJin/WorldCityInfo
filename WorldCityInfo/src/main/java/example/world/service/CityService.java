package example.world.service;

import example.world.controller.model.Responses;

public interface CityService {
	public Responses findCityInfo(String countryCode, String cityName, int pageNum);

	public Responses getCityInfo(int cityID);

	public Responses findBelongToCountry(int cityID);

}
