import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * Class to represent the smallest element of a location
 * 
 * @author benlefevere, Karel Domin
 * @version 1.0
 * 
 * @invar 	getTemperatureInCelcius() must be a valid temperature
 * 			| isValidTemperature(getTemperatureInCelcius())
 * 
 * @invar 	getHumidity() must be a valid humidity
 * 			| isValidHumidity(getHumidity())
 *
 */
public class Square {
	
	
	/**
	 * this constructor will construct a new Square object with the default values assigned to the 
	 * attributes. 
	 */
	@Raw
	public Square(){
		;
	}
	
	/**
	 * this constructor will construct a new Square object with values assigned to temperature
	 * and humidity.
	 * 
	 * @param 	temperature
	 * 			the temperature of this new Square in degrees Celcius
	 * @param 	humidity
	 * 			the humidity of this new Square 
	 * @Post	the temperature of this new Square is equal to temperature
	 * 			| new.getTemperatureInCelcius() == temperature
	 * @Post	the humidity of this new Square is equal to humidity
	 * 			| new.getHumidity() == humidity
	 */
	@Raw
	public Square(double temperature, double humidity){
		this.temperature = temperature;
		this.humidity = humidity;
	}
	
	/**
	 * gives the temperature of the Square in degrees Celcius
	 *
	 * @return	The temperature of the square in degrees Celcius
	 * 			| result == temperature
	 */
	@Basic
	public double getTemperatureInCelcius(){
		return temperature;
	}
	
	/**
	 * gives the temperature of the Square in Kelvin
	 *
	 * @return	The temperature of the square in Kelvin
	 * 			| result == celciusToKelvin(temperature)
	 */
	@Basic
	public double getTemperatureInKelvin(){
		return celciusToKelvin(temperature);
	}
	
	/**
	 * gives the temperature of the Square in degrees Farenheit
	 *
	 * @return	The temperature of the square in degrees Farenheit
	 * 			| result == celciusToFarenheit(temperature)
	 */
	@Basic
	public double getTemperatureInFarenheit(){
		return celciusToFarenheit(temperature);
	}
	
	/**
	 * converts the temperature from degrees Celcius to Farenheit
	 * 
	 * @param 	degreesCelcius
	 * 			the temperature to be converted in degrees Celcius
	 * @return	returns the temperature converted to degrees Farenheit
	 * 			| result == ((degreesCelcius*9)/5) + 32
	 */
	public double celciusToFarenheit (double degreesCelcius){
		return ((degreesCelcius*9)/5) + 32;
	}
	
	/**
	 * converts the temperature from degrees Celcius to Kelvin
	 * 
	 * @param	degreesCelcius
	 * 			the temperature to be converted in degrees Celcius
	 * @return 	returns the temperature converted to Kelvin
	 * 			| result == degreesCelcius + 273
	 */
	public double celciusToKelvin (double degreesCelcius){
		return degreesCelcius + 273;
	}
	
	/**
	 * sets the temperature of this Square to the given temperature
	 * 
	 * @param 	degreesCelcius	
	 * 			the temperature in degrees Celcius
	 * @throws IllegalArgumentException
	 * 			throws an IllegalArgumentException if degreesCelcius is not valid
	 * 			|if (! isValidTemperature(degreesCelcius)) 
	 * 			|	then throw IllegalArgumentException
	 * 
	 * @post	The temperature of the square is equal to degreesCelcius
	 * 			| new.getTemperatureInCelcius()== degreesCelcius
	 */
	//BASIC??
	public void setTemperature(double degreesCelcius) throws IllegalArgumentException{
		temperature = degreesCelcius;
	}
	
	/**
	 * check whether the given temperature is valid
	 * 
	 * @param 	temperature
	 * 			the temperature in degrees Celcius
	 * @return	temperature must lie between MIN_TEMP and MAX_TEMP or be equal to MIN_TEMP or MAX_TEMP
	 * 			| result == (temperature <= MAX_TEMP && temperature >= MIN_TEMP) 
	 */
	//STATIC??
	public boolean isValidTemperature(double temperature){
		return (MIN_TEMP <= getTemperatureInCelcius() && getTemperatureInCelcius() <= MAX_TEMP);
	}
	
	private double temperature;
	
	public final double MIN_TEMP = -200.00;
	public final double MAX_TEMP = 5000.00;
	
	
	/**
	 * returns the cold damage for this Square
	 * 
	 * @return	If the temperature is higher than -15 degrees Celcius, return 0
	 * 			| if (getTemperatureInCelcius() > -15)
	 * 			|	result == 0
	 * @return	If temperature is equal or below -15 degrees Celcius, return the number of points lost due to cold damage
	 * 			| If (getTemperatureInCelcius() <= -15)
	 * 			|	then result == (int) Math.abs((5.0 + getTemperatureInCelcius())/10) 
	 */
	public int getColdDamage(){
		if (getTemperatureInCelcius() > -15)
			return 0;
		
		return (int) Math.abs((5.0 + getTemperatureInCelcius())/10) ;
	}
	
	/**
	 * returns the heat damage for this Square
	 * 
	 * @return	If the temperature is lower than 50 degrees Celcius, return 0
	 * 			| if (getTemperatureInCelcius() < 50)
	 * 			|	result == 0
	 * @return	If temperature is equal or higher than 50 degrees Celcius, return the number of points lost due to heat damage
	 * 			| If (getTemperatureInCelcius() >= 50)
	 * 			|	then result == (int) ((getTemperatureInCelcius() - 35)/15)
	 */
	public int getHeatDamage(){
		if (getTemperatureInCelcius() < 50)
			return 0;
		return (int) ((getTemperatureInCelcius() - 35)/15);
	}
	
	/**
	 * returns the rust damage for this Square
	 * 
	 * @return	If getHumidity() is higher than or equal to 37 % , return the number of points lost due to rust damage
	 * 			| if ( getHumidity() >= 37.00)
	 * 			|	then result == (int) ((getHumidity() - 30)/7)
	 * 
	 */
	// add @PRE??
	public int getRustDamage(){
		if ( getHumidity() >= 37.00)
			 return (int) ((getHumidity() - 30)/7);
		return 0;
	}
	
	/**
	 * returns the humidity of this Square
	 * 
	 * @return	the humidity percentage of the square
	 * 			| result == humidity
	 * 
	 */
	@Basic
	public double getHumidity(){
		return humidity;
	}
	
	/**
	 * sets the humidity of this Square to a given humidity 
	 * 
	 * @Pre		percent must be a valid humidity
	 * 			| isValidHumidity(percent)
	 * @param 	humidity
	 * 			the new humidity for this Square
	 * @post	percent is the new humidity
	 * 			|	new.getHumidity() == percent
	 */
	public void setHumidity(double humidity){
		assert isValidHumidity(humidity);	
		this.humidity = humidity;
	}
	
	/**
	 * check whether the given humidity is valid
	 * 
	 * @param 	humidity
	 * 			the given humidity for this Square
	 * @return	humidity must lie between 0 and 100 or be equal to 0 or 100
	 * 			| result == (getHumidity()<= 100 && getHumidity() >= 0) 
	 */
	public boolean isValidHumidity(double humidity){
		return (getHumidity()<= 100 && getHumidity() >= 0);
	}
	
	private double humidity;
	
	/**
	 * return whether this Square is slippery or not
	 * 
	 * @return The square is slippery if it has a slippery surface OR 
	 * 				the humidity is 100 and the temperature is higher than zero OR
	 * 				the humidity is higher than 10 and the temperature is zero or below
	 * 			| return == (isSlipperySurface || 
	 * 							(getHumidity() == 100 && getTemperatureInCelcius() > 0) ||
	 * 							(getHumidity() > 10 && getTemperatureInCelcius() <= 0)) 
	 */
	public boolean isSlippery(){
		return (isSlipperySurface || 
				(getHumidity() == 100 && getTemperatureInCelcius() > 0) ||
				 (getHumidity() > 10 && getTemperatureInCelcius() <= 0));
	}
	
	public boolean isSlipperySurface;
	
	/**
	 * returns the inhabitability of this Square
	 * 
	 * @return		returns the Inhabitability from a square
	 * 				| result == (-1)* (Math.sqrt(Math.pow(getHeatDamage(),3)) / Math.sqrt(101-getHumidity()))
	 * 				|			- Math.sqrt(getColdDamage()) 
	 */
	public double getInhabitability(){
		double tempInhabilityHeat = getHeatDamage();
		double tempInhabilityCold = getColdDamage();
		double tempInhabilityHumidity = getHumidity();
		
		Math.pow(tempInhabilityHeat, 3);
		Math.sqrt(tempInhabilityHeat);
		
		Math.sqrt(tempInhabilityCold);
		
		tempInhabilityHumidity = 101 - tempInhabilityHumidity;
		Math.sqrt(tempInhabilityHumidity);
		
		return (-1)*(tempInhabilityHeat/tempInhabilityHumidity) - tempInhabilityCold;
		
	}
	
	private final boolean[] borders = new boolean[6];
	
	/**
	 * sets a new border in a given direction of this Square
	 * 
	 * @param 	direction
	 * 			the direction to set the border of this Square to
	 * @param 	newBorder
	 * 			the new state of the border
	 * @post	if the direction is a valid direction then the border in direction 'direction' is set to newBorder
	 * 			|if(isValidDirection(direction)) 
	 * 			|	then	new.borders[direction - 1] = newBorder
	 * @post	if the direction is not a valid direction then the border in direction '1' is set to newBorder
	 * 			|if(! isValidDirection(direction)) 
	 * 			|	then	new.borders[0] = newBorder
	 */
	public void setBorderIndirectionTo(int direction, boolean newBorder){
		if (isValidDirection(direction)) 
			borders[direction - 1] = newBorder;
		else if (! isValidDirection(direction)) 
			borders[0] = newBorder;
	}
	
	/**
	 * returns the state of the border in direction 'direction'
	 * 
	 * @param 	direction
	 * 			the direction 
	 * @return 	if the direction is a valid direction then return the state of the border in direction 'direction'
	 * 			| if (isValidDirection(direction)) then
	 * 			|	return == borders[direction - 1]
	 * @return 	if the direction is not a valid direction then return the state of direction '1'
	 * 			| if (! isValidDirection(direction)) then
	 * 			|	return == borders[0]
	 * 				
	 */
	//TOTAAL
	@Basic
	public boolean getBorderInDirection(int direction){
		if (isValidDirection(direction))
			return  borders[direction - 1];
		else 
			return  borders[0] ;
	}
	
	/**
	 * check whether a given direction is valid
	 * 
	 * @param	direction
	 * 			the given direction
	 * @return	if the direction lies between 7 and 0 then return true
	 * 			|if(direction < 7 && direction > 0) then
	 * 			|	return == true
	 * @return	if the direction is larger then 7 return false
	 * 			|if(direction > 7) then
	 * 			|	return == false
	 * @return	if the direction equals 0 then return false
	 * 			|if(direction <=0) then
	 * 			|	return == false
	 */
	public boolean isValidDirection(int direction){
		if(direction < 7 && direction > 0)
			return true;
		else  
			return false;
	}
	

	/**
	 * 
	 * @param otherSquare
	 * @param direction
	 * 
	 *  @post	...
	 *  		| if(otherSquare == null || otherSquare == this || (! isValidDirection(direction))
	 *  		|	then throws IllegalArgumentException
	 *  
	 *  @post	...
	 *  		| 	if(otherSquare != null && otherSquare != this && (isValidDirection(direction))
	 *  		|	then new.getBorderInDirection(direction) == false &&
	 *  		|		(new otherSquare).getBorderInDirection(direction) == false
	 *  @post	...
	 *  		|	if(otherSquare != null &&  (isValidDirection(direction) && otherSquare != this)
	 *  		|		new.getHumidity() = getAverageHumidity(this.getHumidity(),otherSquare.getHumidity()) &&
	 *  		|		(new otherSquare).getHumidity() = getAverageHumidity(this.getHumidity(),otherSquare.getHumidity())
	 *  @effect	...
	 *  		|	if(otherSquare != null && (isValidDirection(direction)&& otherSquare != this)
	 *  		|		
	 */
	public void mergeWithSquareInDirection(Square otherSquare, int direction) throws IllegalArgumentException{
		;
	}
	
	/**
	 * 
	 * @param firstTemperature
	 * @param secondTemperature
	 * @return
	 */
	public void setMergedTemperatures(Square otherSquare){
		double weight = 2-(2*mergeConstant);
		double averageHumidity = getAverageHumidity(this.getHumidity(), otherSquare.getHumidity());
		
		double a = (weight * averageHumidity)/(this.getHumidity()+otherSquare.getHumidity());
		
		double weightTemperatureOne = (a*(this.getHumidity()/averageHumidity)) + mergeConstant;
		double weightTemperatureTwo = (a*(otherSquare.getHumidity()/averageHumidity)) + mergeConstant;
		
		this.setTemperature(this.getTemperatureInCelcius()*weightTemperatureOne);
		otherSquare.setTemperature(otherSquare.getTemperatureInCelcius()*weightTemperatureTwo);
	}
	
	private static double mergeConstant;
	
	//Getter setter mergeConstant??
	
	
	/**
	 * returns the average humidity of two given humidities 
	 * 
	 * @param 	humidity1
	 * 			the first given humidity
	 * @param	humidty2
	 * 			the second given humidity
	 * @return	the result equals the average of the two humidities
	 * 			|	result == (humidity1+humidty2)/2
	 * 			
	 */
	public double getAverageHumidity(double humidity1,double humidty2){
		return (humidity1+humidty2)/2;
	}
	
	
}
