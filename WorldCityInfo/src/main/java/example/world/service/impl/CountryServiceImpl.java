package example.world.service.impl;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import example.world.common.ParameterDispose;
import example.world.controller.model.Responses;
import example.world.controller.model.Responses.Status;
import example.world.mapper.CountryMapper;
import example.world.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
	private final CountryMapper countryMapper;

	public CountryServiceImpl(CountryMapper countryMapper) {
		super();
		this.countryMapper = countryMapper;
	}

	@Override
	public Responses findAllCountry(int pageNum, String countryName) {
		PageHelper.startPage(pageNum, 15);
		return Status.SUCCESS.toRespones(
				new PageInfo<>(countryMapper.selectAllCountry(ParameterDispose.preventWildcardInject(countryName))));
	}

}
