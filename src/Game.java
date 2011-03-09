
public class Game {
	
	public static void main(String[] args) {
		
		
		Square square1 = new Square(150.00,59.01);
		square1.isSlipperySurface = true;
		square1.setBorderIndirectionTo(1, true);
		square1.setBorderIndirectionTo(2, true);
		square1.setBorderIndirectionTo(3, true);
		
		printSquare(square1);
		
		Square square2 = new Square();
		square2.setTemperature(40);
		square2.isSlipperySurface = false;
		square2.setBorderIndirectionTo(2, true);
		square2.setBorderIndirectionTo(3, true);
		square2.setBorderIndirectionTo(4, true);
		
		printSquare(square2);
		
		square1.mergeWithSquareInDirection(square2, 2);
		
		printSquare(square1);
		printSquare(square2);
		
	}
	
	public static void printSquare(Square square){
		
		System.out.println("The temperature of the square " + square.getTemperatureInCelcius());
		System.out.println("The temperature of the square " + square.getHumidity());
		
		if(square.isSlipperySurface == true)
			System.out.println("Square has a slippery surface");
		else
			System.out.println("Square does not have a slippery surface");
		
		for (int i = 1; i<7;i++){
			if (square.getBorderInDirection(i) == true){
				System.out.println("Square has a border in direction " + i);
			}
			else{
				System.out.println("Square does not have a border in direction " + i);
			}
		}
	}
	
	

}
