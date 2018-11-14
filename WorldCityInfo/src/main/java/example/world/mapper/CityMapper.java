package example.world.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import example.world.entity.City;
import example.world.entity.Country;
import example.world.mapper.provider.CityMapperSqlProvider;

@Mapper
@CacheConfig(cacheNames = "city")
public interface CityMapper {

	@Select("SELECT city.ID, city.NAME, city.CountryCode, city.District, city.Population FROM city WHERE city.Name LIKE CONCAT('%', #{name}, '%') ESCAPE '/'")
	@ResultMap("city")
	public List<City> selectCityByLikeName(@Param("name") String name);

	@Select("SELECT city.ID, city.NAME, city.CountryCode, city.District, city.Population FROM city WHERE city.ID = #{id}")
	@Results(id = "city", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "Name", property = "name", jdbcType = JdbcType.CHAR),
			@Result(column = "CountryCode", property = "country", one = @One(select = "example.world.mapper.CountryMapper.selectCountryByCode")),
			@Result(column = "District", property = "district", jdbcType = JdbcType.CHAR),
			@Result(column = "Population", property = "population", jdbcType = JdbcType.INTEGER) })
	@Cacheable(key = "#p0")
	public City selectCityByID(@Param("id") int id);

	@Select("SELECT country.Code, country.Name, country.Continent, country.Region, country.SurfaceArea, country.IndepYear, country.Population, country.LifeExpectancy, country.GNP, country.GNPOld, country.LocalName, country.GovernmentForm, country.HeadOfState, country.Capital, country.Code2 FROM city INNER JOIN country ON city.CountryCode = country.Code WHERE city.ID = #{id} LIMIT 1")
	@Cacheable(key = "#p0", cacheNames = "belongToCountry")
	public Country selectCountryByCityID(@Param("id") int id);

	@ResultMap("city")
	@SelectProvider(type = CityMapperSqlProvider.class, method = "findCity")
	public List<City> findCity(@Param("cityName") String cityName, @Param("countryCode") String countryCode);

}
