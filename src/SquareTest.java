import static org.junit.Assert.*;

import org.junit.*;

public class SquareTest {

	
	@Test
	public final void testGetTemperatureInCelcius(){
		Square testSquare1 = new Square(-200,0);
		assertEquals(-200, testSquare1.getTemperatureInCelcius(),0);
		Square testSquare2 = new Square(-201,0);
		Square testSquare3 = new Square(100,0);
		Square testSquare4 = new Square(5000,0);
		Square testSquare5 = new Square(5001,0);
		
	}
}
