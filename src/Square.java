import java.math.BigDecimal;
import java.math.RoundingMode;

import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * Class to represent the smallest element of a location
 * 
 * @author Ben Lefevere, Karel Domin
 * @version 2.0
 * 
 * @invar 	getTemperatureInCelcius() must be a valid temperature
 * 			| isValidTemperature(getTemperatureInCelcius())
 * 
 * @invar 	getHumidity() must be a valid humidity
 * 			| isValidHumidity(getHumidity())
 *
 * @invar 	getMergeConstant() must be valid
 * 			| isValidMergeConstant(getMergeConstant())
 *
 */
public class Square {
	
	
	/**
	 * this constructor will construct a new Square object with the default values assigned to the 
	 * attributes. 
	 */
	
	public Square(){
		this.humidity = new BigDecimal(0.00);
		this.temperature = new Temperature(0, TemperatureScales.CELCIUS);
	}
	
	/**
	 * this constructor will construct a new Square object with values assigned to temperature
	 * and humidity.
	 * 
	 * @param 	temperature
	 * 			the temperature of this new Square in degrees Celcius
	 * @param 	humidity
	 * 			the humidity of this new Square 
	 * @throws	
	 * 			| if((!isValidTemperature(temperature) || (!isValidTemperature(humidity))
	 * 			| 	then throws IllegalArgumentException
	 * @Post	the temperature of this new Square is equal to temperature
	 * 			| new.getTemperatureInCelcius() == temperature
	 * @Post	the humidity of this new Square is equal to humidity
	 * 			| new.getHumidity() == humidity
	 */
	public Square(Temperature temperature, BigDecimal humidity) throws IllegalArgumentException{
		if((!isValidTemperature(temperature)) || (!isValidHumidity(humidity)))
				throw new IllegalArgumentException();
		this.temperature = temperature;
		this.humidity = humidity;
	}
	
	/**
	 * gives the temperature of the Square
	 *
	 * @return	The temperature of the square
	 * 			| result == temperature
	 */
	@Basic
	public Temperature getTemperature(){
		return temperature;
	}
	
	/**
	 * sets the temperature of this Square to the given temperature
	 * 
	 * @param 	temperature	
	 * 			the temperature
	 * @throws IllegalArgumentException
	 * 			throws an IllegalArgumentException if temperature is not valid
	 * 			|if (! isValidTemperature(temperature)) 
	 * 			|	then throw IllegalArgumentException
	 * 
	 * @post	The temperature of the square is equal to temperature
	 * 			| new.getTemperature()== temperature
	 */
	
	public void setTemperature(Temperature temperature) throws IllegalArgumentException{
		if (isValidTemperature(temperature)){
			this.temperature = temperature;
		}
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * check whether the given temperature is valid
	 * 
	 * @param 	temperature
	 * 			the temperature
	 * @return	temperature must lie between MIN_TEMP and MAX_TEMP or be equal to MIN_TEMP or MAX_TEMP
	 * 			| result == (temperature != null && MIN_TEMP.temperature <= Temperature.convertToCelcius(temperature).temperature 
				|		&& Temperature.convertToCelcius(temperature).temperature <= MAX_TEMP.temperature) 
	 */
	
	@Raw
	public static boolean isValidTemperature(Temperature temperature){
		return (temperature != null && MIN_TEMP.temperature <= Temperature.convertToCelcius(temperature).temperature 
				&& Temperature.convertToCelcius(temperature).temperature <= MAX_TEMP.temperature);
	}
	
	private Temperature temperature;
	
	public final static Temperature MIN_TEMP = new Temperature(-200.00, TemperatureScales.CELCIUS);
	public final static Temperature MAX_TEMP = new Temperature(5000.00, TemperatureScales.CELCIUS);
	
	
	/**
	 * returns the cold damage for this Square
	 * 
	 * @return	If the temperature is higher than -15 degrees Celcius, return 0
	 * 			| if (Temperature.convertToCelcius(getTemperature()).temperature > -15)
	 * 			|	result == 0
	 * @return	If temperature is equal or below -15 degrees Celcius, return the number of points lost due to cold damage
	 * 			| If (getTemperatureInCelcius() <= -15)
	 * 			|	then result == (int) Math.abs((5.0 + Temperature.convertToCelcius(getTemperature()).temperature)/10) 
	 */
	public int getColdDamage(){
		if (Temperature.convertToCelcius(getTemperature()).temperature > -15)
			return 0;
		
		return (int) Math.abs((5.0 + Temperature.convertToCelcius(getTemperature()).temperature )/10) ;
	}
	
	/**
	 * returns the heat damage for this Square
	 * 
	 * @return	If the temperature is lower than 50 degrees Celcius, return 0
	 * 			| if (Temperature.convertToCelcius(getTemperature()).temperature < 50)
	 * 			|	result == 0
	 * @return	If temperature is equal or higher than 50 degrees Celcius, return the number of points lost due to heat damage
	 * 			| If (getTemperatureInCelcius() >= 50)
	 * 			|	then result == (int) ((Temperature.convertToCelcius(getTemperature()).temperature - 35)/15)
	 */
	// change doc
	public int getHeatDamage(){
		if (Temperature.convertToCelcius(getTemperature()).temperature  < 50)
			return 0;
		return (int) ((Temperature.convertToCelcius(getTemperature()).temperature - 35)/15);
	}
	
	/**
	 * returns the rust damage for this Square
	 * 
	 * @return	If getHumidity() is higher than or equal to 37 % , return the number of points lost due to rust damage
	 * 			| if ( getHumidity() >= 37.00)
	 * 			|	then result == (int) ((getHumidity() - 30)/7)
	 * 
	 */
	public int getRustDamage(){
		if ( getHumidity().compareTo(RUST_DAMAGE_CONST) >= 0)
			 return (((getHumidity().subtract(RUST_DAMAGE_CONST2))).divide(RUST_DAMAGE_CONST3)).intValue();
		return 0;
	}
	
	public final static BigDecimal RUST_DAMAGE_CONST = new BigDecimal(37.00);
	public final static BigDecimal RUST_DAMAGE_CONST2 = new BigDecimal(30.00);
	public final static BigDecimal RUST_DAMAGE_CONST3 = new BigDecimal(7.00);


	/**
	 * returns the humidity of this Square
	 * 
	 * @return	the humidity percentage of the square
	 * 			| result == humidity
	 * 
	 */
	@Basic
	public BigDecimal getHumidity(){
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
	public void setHumidity(BigDecimal humidity){
		assert isValidHumidity(humidity);	
		this.humidity = humidity;
	}
	
	/**
	 * check whether the given humidity is valid
	 * 
	 * @param 	humidity
	 * 
	 * @pre		the humidity can't have more than two decimal places
	 * 			| (humidity*100)%1 == 0
	 * 			the given humidity for this Square
	 * @return	humidity must lie between 0 and 100 or be equal to 0 or 100
	 * 			| result == (humidity <= 100 && humidity >= 0) 
	 */
	@Raw
	public static boolean isValidHumidity(BigDecimal humidity){
		return (humidity.compareTo(MAX_HUMIDITY) <= 0 && humidity.compareTo(MIN_HUMIDITY)>=0);
	}
	
	private BigDecimal humidity;
	public final static BigDecimal MIN_HUMIDITY = new BigDecimal(0.00);
	public final static BigDecimal MAX_HUMIDITY = new BigDecimal(100.00);

	/**
	 * return whether this Square is slippery or not
	 * 
	 * @return The square is slippery if it has a slippery surface OR 
	 * 				the humidity is 100 and the temperature is higher than zero OR
	 * 				the humidity is higher than 10 and the temperature is zero or below
	 * 			| return == (isSlipperySurface || 
	 * 							(getHumidity() == 100 && Temperature.convertToCelcius(getTemperature()).temperature > 0) ||
	 * 							(getHumidity() > 10 && Temperature.convertToCelcius(getTemperature()).temperature <= 0)) 
	 */
	public boolean isSlippery(){
		return (isSlipperySurface || 
				((getHumidity().compareTo(MAX_HUMIDITY) == 0) && Temperature.convertToCelcius(getTemperature()).temperature > 0) ||
				 ((getHumidity().compareTo(SLIPPERY_HUMIDITY) >0)  && Temperature.convertToCelcius(getTemperature()).temperature <= 0));
	}
	
	public final static BigDecimal SLIPPERY_HUMIDITY = new BigDecimal(10.00);
	
	public boolean isSlipperySurface;
	
	/**
	 * returns the inhabitability of this Square
	 * 
	 * @return		returns the Inhabitability from a square
	 * 				| result == (-1)* (Math.sqrt(Math.pow(getHeatDamage(),3)) / Math.sqrt(101-getHumidity()))
	 * 				|			- Math.sqrt(getColdDamage()) 
	 */
	// change doc and fucking code
	public BigDecimal getInhabitability(){
		double tempInhabilityHeat = getHeatDamage();
		double tempInhabilityCold = getColdDamage();
		BigDecimal tempInhabilityHumidity = getHumidity();
		
		tempInhabilityHeat = Math.pow(tempInhabilityHeat, 3);
		tempInhabilityHeat= Math.sqrt(tempInhabilityHeat);
		
		tempInhabilityCold = Math.sqrt(tempInhabilityCold);
		
		tempInhabilityHumidity = (HUMIDITY_CONSTANT.subtract(tempInhabilityHumidity));
		tempInhabilityHumidity = new BigDecimal(Math.sqrt((tempInhabilityHumidity).doubleValue()));
		//tempInhabilityHumidity = tempInhabilityHumidity.pow(0.5);
		BigDecimal tempInhabilityHeatBigDecimal = new BigDecimal(tempInhabilityHeat);
		BigDecimal tempInhabilityColdBigDecimal = new BigDecimal(tempInhabilityCold);
		return ((tempInhabilityHeatBigDecimal.divide(tempInhabilityHumidity)).subtract(tempInhabilityColdBigDecimal)
				.multiply((BigDecimal.ONE).negate()));
		
	}
	public final static BigDecimal HUMIDITY_CONSTANT = new BigDecimal(101);
	public final static int MIN_DIRECTION = 1;
	public final static int MAX_DIRECTION = 6;
	
	
	private final boolean[] borders = new boolean[MAX_DIRECTION];
	
	/**
	 * sets a new border in a given direction of this Square
	 * 
	 * @param 	direction
	 * 			the direction to set the border of this Square to
	 * @param 	newBorder
	 * 			the new state of the border
	 * @post	if the direction is a valid direction then the border in direction 'direction' is set to newBorder
	 * 			|if(isValidDirection(direction)) 
	 * 			|	then	new.borders[direction.getDirection()  - 1] = newBorder
	 * @post	if the direction is not a valid direction then the border in direction '1' is set to newBorder
	 * 			|if(! isValidDirection(direction)) 
	 * 			|	then	new.borders[0] = newBorder
	 */
	public void setBorderIndirectionTo(Direction direction, boolean newBorder){
		if (isValidDirection(direction)) 
			borders[direction.getDirection() - 1] = newBorder;
		else
			borders[0] = newBorder;
	}
	
	/**
	 * returns the state of the border in direction 'direction'
	 * 
	 * @param 	direction
	 * 			the direction 
	 * @return 	if the direction is a valid direction then return the state of the border in direction 'direction'
	 * 			| if (isValidDirection(direction)) then
	 * 			|	return == borders[direction.getDirection() - 1]
	 * @return 	if the direction is not a valid direction then return the state of direction '1'
	 * 			| if (! isValidDirection(direction)) then
	 * 			|	return == borders[0]
	 * 				
	 */
	@Basic
	public boolean getBorderInDirection(Direction direction){
		if (isValidDirection(direction))
			return  borders[direction.getDirection() - 1];
		else 
			return  borders[0] ;
	}
	
	/**
	 * check whether a given direction is valid
	 * 
	 * @param	direction
	 * 			the given direction
	 * @return	if the direction lies between MAX_DIRECTION+1 and MIN_DIRECTION-1 then return true
	 * 			|if(direction.getDirection() < MAX_DIRECTION+1 && direction.getDirection() > MIN_DIRECTION-1) then
	 * 			|	return == true
	 * @return	if the direction is larger then MAX_DIRECTION return false
	 * 			|if(direction.getDirection() > MAX_DIRECTION) then
	 * 			|	return == false
	 * @return	if the direction equals MIN_DIRECTION-1 then return false
	 * 			|if(direction.getDirection() <= MIN_DIRECTION-1) then
	 * 			|	return == false
	 */
	@Raw
	public static boolean isValidDirection(Direction direction){
		if(direction.getDirection() < MAX_DIRECTION+1 && direction.getDirection() > MIN_DIRECTION-1)
			return true;
		else  
			return false;
	}
	
	/**
	 * merges this Square with another Square in a given direction 
	 * 
	 * @param otherSquare
	 * 			the other Square to merge with this one
	 * @param direction
	 * 			the direction the two squares are merged
	 * 
	 * @throws	throws an IllegalArgumentException if otherSquare's reference is null or the other Square equal to this Square 
	 *  		or the direction is not valid
	 *  		| if(otherSquare == null || otherSquare == this || (! isValidDirection(direction))
	 *  		|	then throws IllegalArgumentException
	 *  
	 * @post	sets the direction in which the squares are merged to false
	 *  		|	then new.getBorderInDirection(direction) == false &&
	 *  		|		(new otherSquare).getBorderInDirection(direction) == false
	 *  
	 * @post	sets the humidity of both squares to the average humidity of both squares
	 *  		|		new.getHumidity() = getAverageHumidity(this.getHumidity(),otherSquare.getHumidity()) &&
	 *  		|		(new otherSquare).getHumidity() = getAverageHumidity(this.getHumidity(),otherSquare.getHumidity())
	 *  
	 * @post	sets the temperature of both squares to the new temperature taken into account the weight, if the humidities are not the same
	 * 			|		if (this.getHumidity() != other.getHumidity())
	 *  		|		then new.getTemperature =   
	 *  		|		(new otherSquare).getTemperature = 
	 *  
	 *  @post	sets the temperature of both squares to the new temperature without taken into account the weight, if the humidities are the same
	 *  		|		if (this.getHumidity() == other.getHumidity())
	 *  		|		then new.getTemperature =
	 *  		|				(new otherSquare).getTemperature =  
	 */
	// check bigdecimals and change doc
	// toDoubleValue() ???
	public void mergeWithSquareInDirection(Square otherSquare, Direction direction) throws IllegalArgumentException{
		
		if (otherSquare == null || otherSquare == this || (! isValidDirection(direction)))
				throw new IllegalArgumentException();
		
		this.setBorderIndirectionTo(direction, false);
		otherSquare.setBorderIndirectionTo(direction, false);
		
		BigDecimal averageHumidity = getAverageHumidity(this.getHumidity(), otherSquare.getHumidity());	
		
		double a = 1 - mergeConstant; 	
		
		double weightTemperatureOne;
		double weightTemperatureTwo;
		
		if (otherSquare.getHumidity() == this.getHumidity()){
			weightTemperatureOne = 1;
			weightTemperatureTwo = 1;	
		}
		else {
			weightTemperatureOne = (a*((this.getHumidity().doubleValue())/(averageHumidity).doubleValue())) + mergeConstant;
			weightTemperatureTwo = (a*((otherSquare.getHumidity().doubleValue())/(averageHumidity.doubleValue()))) + mergeConstant;	
		}
		
		double newTemperature = (Temperature.convertToCelcius(this.getTemperature()).temperature *weightTemperatureOne + 
				Temperature.convertToCelcius(otherSquare.getTemperature()).temperature *weightTemperatureTwo)/2;
		
		
		this.setTemperature(new Temperature(newTemperature, TemperatureScales.CELCIUS));
		otherSquare.setTemperature(new Temperature(newTemperature, TemperatureScales.CELCIUS));
		
		//Makes sure that the new humidity only has two decimal places
		// change doc
		BigDecimal roundUpDecimal = averageHumidity;
		roundUpDecimal = roundUpDecimal.setScale(2, RoundingMode.HALF_UP);
		
		this.setHumidity(roundUpDecimal);
		otherSquare.setHumidity(roundUpDecimal);
	
	}
	
	private static double mergeConstant = 0.2;
	
	public static double MIN_MERGECONSTANT = 0.1;
	public static double MAX_MERGECONSTANT = 0.4;
	
	/**
	 * Returns the merge constant of the class
	 * 
	 * @return	Returns the merge constant of the class
	 * 			| result == mergeConstant 
	 * 
	 */
	@Basic
	public static double getMergeConstant(){
		return mergeConstant;	
	}
	
	/**
	 * Sets the new merge constant
	 * 
	 * @param	mergeConstant 
	 * 			The new mergeConstant
	 * @post	if the mergeConstant is a valid, the new mergeConstant is set to mergeConstant
	 * 			|if(isValidMergeConstant(mergeConstant))
	 * 			|	then	new.getMergeConstant() = mergeConstant;
	 * @post	if the mergeConstant is not valid, the new mergeConstant is set to MAX_MERGECONSTANT
	 * 			|if(! isValidMergeConstant(mergeConstant)) 
	 * 			|	then	new.getMergeConstant() = MAX_MERGECONSTANT;
	 */
	public static void setMergeConstant(double mergeConstant){
		if (isValidMergeConstant(mergeConstant))
			Square.mergeConstant = mergeConstant;
		else
			Square.mergeConstant = MAX_MERGECONSTANT;
	}
	
	/**
	 * Checks if mergeConstant is a valid merge constant
	 * 
	 * @param 	mergeConstant
	 * 			The merge constant
	 * 
	 * @return	mergeConstant must lie between MIN_MERGECONSTANT and MAX_MERGECONSTANT 
	 * 					or be equal to MIN_MERGECONSTANT or MAX_MERGECONSTANT
	 * 			| result == (mergeConstant >= MIN_MERGECONSTANT && mergeConstant <= MAX_MERGECONSTANT) 
	 */
	public static boolean isValidMergeConstant(double mergeConstant){
		return mergeConstant >= MIN_MERGECONSTANT && mergeConstant <= MAX_MERGECONSTANT;
	}
	
	
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
	// change doc 
	public BigDecimal getAverageHumidity(BigDecimal humidity1,BigDecimal humidty2){
		return ((humidity1.add(humidty2)).divide(BIGDECIMAL_2));
	}
	
	public final static BigDecimal BIGDECIMAL_2 = new BigDecimal(2);
	
}
