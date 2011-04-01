import java.math.BigDecimal;


public class Game {
	
	public static void main(String[] args) {
		
		Square square1 = new Square(new Temperature(150.00, TemperatureScales.CELCIUS),new BigDecimal(59.01));
		square1.isSlipperySurface = true;
		square1.setBorderIndirectionTo(Direction.FLOOR, true);
		square1.setBorderIndirectionTo(Direction.NORTH, true);
		square1.setBorderIndirectionTo(Direction.EAST, true);
		
		printSquare(square1);
		
		Square square2 = new Square(new Temperature(40.00, TemperatureScales.CELCIUS),new BigDecimal(89.90));
		square2.isSlipperySurface = false;
		square2.setBorderIndirectionTo(Direction.NORTH, true);
		square2.setBorderIndirectionTo(Direction.EAST, true);
		square2.setBorderIndirectionTo(Direction.SOUTH, true);
		
		printSquare(square2);
		
		square1.mergeWithSquareInDirection(square2, Direction.NORTH);
		
		printSquare(square1);
		printSquare(square2);
		
	}
	
	public static void printSquare(Square square){
		
		System.out.println("\nThe temperature of the square " + square.getTemperature().temperature);
		System.out.println("The humidity of the square " + square.getHumidity().doubleValue());
		
		if(square.isSlipperySurface == true)
			System.out.println("\nSquare has a slippery surface\n");
		else
			System.out.println("\nSquare does not have a slippery surface\n");
		
		for (int i = 1; i<7;i++){
			if (square.getBorderInDirection(Direction.values()[i-1]) == true){
				System.out.println("Square has a border in direction " + i);
			}
			else{
				System.out.println("Square does not have a border in direction " + i);
			}
		}
	}
	
	

}
