package example.world.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import example.world.common.ParameterDispose;
import example.world.controller.model.Responses;
import example.world.controller.model.Responses.Status;
import example.world.entity.City;
import example.world.mapper.CityMapper;
import example.world.service.CityService;

@Service
@Transactional
public class CityServiceImpl implements CityService {
	private final CityMapper cityMapper;

	@Autowired
	public CityServiceImpl(CityMapper cityMapper) {
		super();
		this.cityMapper = cityMapper;
	}

	@Override
	public Responses findCityInfo(String countryCode, String cityName, int pageNum) {
		PageHelper.startPage(pageNum, 15);
		PageInfo<City> pageInfo = new PageInfo<>(
				cityMapper.findCity(ParameterDispose.preventWildcardInject(cityName), countryCode));
		return Status.SUCCESS.toRespones(pageInfo);
	}

	@Override
	public Responses getCityInfo(int cityID) {
		City city = cityMapper.selectCityByID(cityID);

		return Status.SUCCESS.toRespones(city);
	}

	@Override
	public Responses findBelongToCountry(int cityID) {
		return Status.SUCCESS.toRespones(cityMapper.selectCountryByCityID(cityID));
	}

}
