package example.world.mapper.provider;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;

public class CountryMapperSqlProvider {
	public static String selectAllCountry(@Param("countryName") String countryName) {
		StringBuilder sql = new StringBuilder(
				"SELECT country.Code, country.Name, country.Continent, country.Region, country.SurfaceArea, country.IndepYear, country.Population, country.LifeExpectancy, country.GNP, country.GNPOld, country.LocalName, country.GovernmentForm, country.HeadOfState, country.Capital, country.Code2 FROM country WHERE 1 = 1");
		if (StringUtils.isNoneEmpty(countryName)) {
			sql.append(" AND country.Name LIKE CONCAT('%', #{countryName}, '%') ESCAPE '/' ");
		}
		return sql.toString();

	}
}
