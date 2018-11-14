package example.world.entity;

public class Countrylanguage {
	private String isofficial;
	private Float percentage;
	private String countrycode;
	private String language;

	public String getCountrycode() {
		return countrycode;
	}

	public String getIsofficial() {
		return isofficial;
	}

	public String getLanguage() {
		return language;
	}

	public Float getPercentage() {
		return percentage;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public void setIsofficial(String isofficial) {
		this.isofficial = isofficial;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setPercentage(Float percentage) {
		this.percentage = percentage;
	}
}