package example.world.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import example.world.entity.Country;
import example.world.mapper.provider.CountryMapperSqlProvider;

@Mapper
@CacheConfig(cacheNames = "country")
public interface CountryMapper {
	@Results(id = "country", value = { @Result(column = "Code", property = "code", jdbcType = JdbcType.CHAR, id = true),
			@Result(column = "Name", property = "name", jdbcType = JdbcType.CHAR),
			@Result(column = "Continent", property = "continent", jdbcType = JdbcType.CHAR),
			@Result(column = "Region", property = "region", jdbcType = JdbcType.CHAR),
			@Result(column = "SurfaceArea", property = "surfacearea", jdbcType = JdbcType.REAL),
			@Result(column = "IndepYear", property = "indepyear", jdbcType = JdbcType.SMALLINT),
			@Result(column = "Population", property = "population", jdbcType = JdbcType.INTEGER),
			@Result(column = "LifeExpectancy", property = "lifeexpectancy", jdbcType = JdbcType.REAL),
			@Result(column = "GNP", property = "gnp", jdbcType = JdbcType.REAL),
			@Result(column = "GNPOld", property = "gnpold", jdbcType = JdbcType.REAL),
			@Result(column = "LocalName", property = "localname", jdbcType = JdbcType.CHAR),
			@Result(column = "GovernmentForm", property = "governmentform", jdbcType = JdbcType.CHAR),
			@Result(column = "HeadOfState", property = "headofstate", jdbcType = JdbcType.CHAR),
			@Result(column = "Capital", property = "capital", jdbcType = JdbcType.INTEGER),
			@Result(column = "Code2", property = "code2", jdbcType = JdbcType.CHAR) })
	@Select("SELECT country.Code, country.Name, country.Continent, country.Region, country.SurfaceArea, country.IndepYear, country.Population, country.LifeExpectancy, country.GNP, country.GNPOld, country.LocalName, country.GovernmentForm, country.HeadOfState, country.Capital, country.Code2 FROM country WHERE country.Code = #{code} LIMIT 1")
	@Cacheable(key = "#p0")
	public Country selectCountryByCode(@Param("code") String code);

	@ResultMap("country")
	@SelectProvider(type = CountryMapperSqlProvider.class, method = "selectAllCountry")
	public List<Country> selectAllCountry(@Param("countryName") String countryName);
}
