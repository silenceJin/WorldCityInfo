package example.world.entity;

import java.io.Serializable;

public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private String district;

	private Integer population;

	private Country country;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public City() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City(Integer id, String name, String district, Integer population, Country country) {
		super();
		this.id = id;
		this.name = name;
		this.district = district;
		this.population = population;
		this.country = country;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("City [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", district=");
		builder.append(district);
		builder.append(", population=");
		builder.append(population);
		builder.append(", country=");
		builder.append(country);
		builder.append("]");
		return builder.toString();
	}

}