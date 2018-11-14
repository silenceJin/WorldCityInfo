package example.world.mapper.provider;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;

public class CityMapperSqlProvider {
	public static String findCity(@Param("cityName") String cityName, @Param("countryCode") String countryCode) {
		StringBuilder sqlBuilder = new StringBuilder(
				"SELECT city.ID, city.Name, city.CountryCode, city.District, city.Population FROM city WHERE 1 = 1 ");
		if (StringUtils.isNotEmpty(countryCode)) {
			sqlBuilder.append(" AND city.CountryCode = #{countryCode} ");
		}
		if (StringUtils.isNotEmpty(cityName)) {
			sqlBuilder.append(" AND city.Name LIKE CONCAT('%', #{cityName}, '%') ESCAPE '/' ");
		}

		return sqlBuilder.toString();

	}
}
