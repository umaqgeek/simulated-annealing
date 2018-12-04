package simulated.annealing;

public class City {

	private float x;
	private float y;
	private String cityName;

	//Constructor
	//create a city given its name and (x,y) coordinate location
	public City(String cityName, float x, float y){
		this.setCityName(cityName);
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x){
		this.x = x;
	}

	public float getY(){
		return y;
	}

	public void setY(float y){
		this.y = y;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
