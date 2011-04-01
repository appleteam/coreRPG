import static org.junit.Assert.*;

import org.junit.*;

public class SquareTest {

	
	
	@Test
	public final void TestSetTemperature() throws Exception{
		Square testSquare = new Square();
		try{
			testSquare.setTemperature(new Temperature(-201.00, TemperatureScales.CELCIUS));
			System.out.println(testSquare.getTemperature().temperature);
			fail("Expected IllegalArgumentException");
		} catch(IllegalArgumentException expected) {}
		testSquare.setTemperature(new Temperature(-200, TemperatureScales.CELCIUS));
		assertEquals(-200,testSquare.getTemperature().temperature,0);
		testSquare.setTemperature(new Temperature(0, TemperatureScales.CELCIUS));
		assertEquals(0,testSquare.getTemperature().temperature,0);
		testSquare.setTemperature(new Temperature(100, TemperatureScales.CELCIUS));
		assertEquals(100,testSquare.getTemperature().temperature,0);
		testSquare.setTemperature(new Temperature(5000, TemperatureScales.CELCIUS));
		assertEquals(5000,testSquare.getTemperature().temperature,0);
		try{
			testSquare.setTemperature(new Temperature(5001, TemperatureScales.CELCIUS));
			fail("Expected IllegalArgumentException");
		} catch(IllegalArgumentException expected) {}
	}
	
	@Test
	public final void testGetTemperatureInKelvin(){
		Square testSquare = new Square(new Temperature(-200, TemperatureScales.CELCIUS),0);
		assertEquals(73, Temperature.convertToKelvin(testSquare.getTemperature()).temperature,0);
		testSquare.setTemperature(new Temperature(5000, TemperatureScales.KELVIN));
		assertEquals(5000, testSquare.getTemperature().temperature,0);
	}
	
	@Test
	public final void testGetTemperatureInFahrenheit(){
		Square testSquare = new Square(new Temperature(-200, TemperatureScales.CELCIUS),0);
		assertEquals(((-200*9)/5) + 32, Temperature.convertToFahrenheit(testSquare.getTemperature()).temperature,0.01);
		testSquare.setTemperature(new Temperature(9032, TemperatureScales.FAHRENHEIT));
		assertEquals(9032, testSquare.getTemperature().temperature,0.01);
	}
	
	@Test
	public final void testGetColdDamage(){
		Square testSquare = new Square();
		testSquare.setTemperature(new Temperature(-15, TemperatureScales.CELCIUS));
		assertEquals(1,testSquare.getColdDamage(),0);
		testSquare.setTemperature(new Temperature(-5, TemperatureScales.CELCIUS));
		assertEquals(0,testSquare.getColdDamage(),0);
		testSquare.setTemperature(new Temperature(-57, TemperatureScales.CELCIUS));
		assertEquals(5,testSquare.getColdDamage(),0);
		testSquare.setTemperature(new Temperature(-200, TemperatureScales.CELCIUS));
		assertEquals(19,testSquare.getColdDamage(),0);
		testSquare.setTemperature(new Temperature(5000, TemperatureScales.CELCIUS));
		assertEquals(0,testSquare.getColdDamage(),0);
		testSquare.setTemperature(new Temperature(5000, TemperatureScales.CELCIUS));
		assertEquals(0,testSquare.getColdDamage(),0);
	}
	
	@Test
	public final void testHeatDamage(){
		Square testSquare = new Square();
		testSquare.setTemperature(new Temperature(35, TemperatureScales.CELCIUS));
		assertEquals(0,testSquare.getHeatDamage(),0);
		testSquare.setTemperature(new Temperature(50, TemperatureScales.CELCIUS));
		assertEquals(1,testSquare.getHeatDamage(),0);
		testSquare.setTemperature(new Temperature(-50, TemperatureScales.CELCIUS));
		assertEquals(0,testSquare.getHeatDamage(),0);
		testSquare.setTemperature(new Temperature(-200, TemperatureScales.CELCIUS));
		assertEquals(0,testSquare.getHeatDamage(),0);
		testSquare.setTemperature(new Temperature(200.00, TemperatureScales.CELCIUS));
		assertEquals(11,testSquare.getHeatDamage(),0);
		testSquare.setTemperature(new Temperature(5000, TemperatureScales.CELCIUS));
		assertEquals(331,testSquare.getHeatDamage(),0);
	}
	
	@Test
	public final void testRustDamage(){
		Square testSquare = new Square();
		testSquare.setHumidity(30);
		assertEquals(0,testSquare.getRustDamage(),0);
		testSquare.setHumidity(37);
		assertEquals(1,testSquare.getRustDamage(),0);
		testSquare.setHumidity(0);
		assertEquals(0,testSquare.getRustDamage(),0);
		testSquare.setHumidity(100);
		assertEquals(10,testSquare.getRustDamage(),0);
	}
	
	@Test
	public final void testGetHumidity(){
		Square testSquare = new Square();
		testSquare.setHumidity(0);
		assertEquals(0, testSquare.getHumidity(),0);
		testSquare.setHumidity(45);
		assertEquals(45, testSquare.getHumidity(),0);
		testSquare.setHumidity(100);
		assertEquals(100, testSquare.getHumidity(),0);
	}
	
	@Test
	public final void testIsSlippery(){
		Square testSquare = new Square();
		testSquare.isSlipperySurface = true;
		assertTrue(testSquare.isSlippery());
		testSquare.isSlipperySurface = false;
		assertFalse(testSquare.isSlippery());
		testSquare.setHumidity(100);
		testSquare.setTemperature(new Temperature(0, TemperatureScales.CELCIUS));
		assertTrue(testSquare.isSlippery());
		testSquare.setHumidity(100);
		testSquare.setTemperature(new Temperature(1, TemperatureScales.CELCIUS));
		assertTrue(testSquare.isSlippery());
		testSquare.setHumidity(100);
		testSquare.setTemperature(new Temperature(-200, TemperatureScales.CELCIUS));
		assertTrue(testSquare.isSlippery());
		testSquare.setHumidity(100);
		testSquare.setTemperature(new Temperature(5000, TemperatureScales.CELCIUS));
		assertTrue(testSquare.isSlippery());
		testSquare.setHumidity(80);
		testSquare.setTemperature(new Temperature(0, TemperatureScales.CELCIUS));
		assertTrue(testSquare.isSlippery());
		testSquare.setTemperature(new Temperature(1.00, TemperatureScales.CELCIUS));
		assertFalse(testSquare.isSlippery());
		testSquare.setHumidity(10);
		testSquare.setTemperature(new Temperature(-1, TemperatureScales.CELCIUS));
		assertFalse(testSquare.isSlippery());
		testSquare.setHumidity(11);
		testSquare.setTemperature(new Temperature(-1, TemperatureScales.CELCIUS));
		assertTrue(testSquare.isSlippery());
	}
	
	@Test
	public final void testSetBorderInDirectionTo(){
		Square testSquare = new Square();
		testSquare.setBorderIndirectionTo(Direction.FLOOR, true);
		assertTrue(testSquare.getBorderInDirection(Direction.FLOOR));
	}
	
	@Test
	public final void testGetBorderInDirection(){
		Square testSquare = new Square();
		testSquare.setBorderIndirectionTo(Direction.FLOOR, true);
		assertTrue(testSquare.getBorderInDirection(Direction.FLOOR));
		testSquare.setBorderIndirectionTo(Direction.CEILING, true);
		assertTrue(testSquare.getBorderInDirection(Direction.CEILING));
	}
	
	
	@Test
	public final void testMergeWithSquareInDirection(){
		Square testSquare1 = new Square();
		Square testSquare2 = new Square();
		testSquare1.setBorderIndirectionTo(Direction.EAST, true);
		testSquare2.setBorderIndirectionTo(Direction.EAST, true);
		
		testSquare1.mergeWithSquareInDirection(testSquare2, Direction.EAST);
		
		assertEquals(false,testSquare1.getBorderInDirection(Direction.EAST));
		assertEquals(false,testSquare2.getBorderInDirection(Direction.EAST));

		try{
			testSquare1.mergeWithSquareInDirection(testSquare1, Direction.CEILING);
			fail("Expected IllegalArgumentException");
		} catch(IllegalArgumentException expected) {}
		
		try{
			testSquare1.mergeWithSquareInDirection(null, Direction.CEILING);
			fail("Expected IllegalArgumentException");
		} catch(IllegalArgumentException expected) {}
		
	}
	
	@Test
	public final void testGetInhabitability(){
		Square testSquare = new Square();
		assertEquals(0,testSquare.getInhabitability(),0.01);
		testSquare.setHumidity(100);
		testSquare.setTemperature(new Temperature(-200.00, TemperatureScales.CELCIUS));
		assertEquals(-4.359,testSquare.getInhabitability(),0.01);
		testSquare.setHumidity(0);
		testSquare.setTemperature(new Temperature(5000, TemperatureScales.CELCIUS));
		assertEquals(-599.213,testSquare.getInhabitability(),0.01);
		testSquare.setHumidity(25);
		testSquare.setTemperature(new Temperature(612, TemperatureScales.CELCIUS));
		assertEquals(-26.87,testSquare.getInhabitability(),0.01);
	}
	
	@Test
	public final void testSetMergeConstant(){
		Square.setMergeConstant(0.4);
		assertEquals(0.4,Square.getMergeConstant(),0);
		Square.setMergeConstant(0.1);
		assertEquals(0.1,Square.getMergeConstant(),0);
		Square.setMergeConstant(0.5);
		assertEquals(0.4,Square.getMergeConstant(),0);
		Square.setMergeConstant(0.05);
		assertEquals(0.4,Square.getMergeConstant(),0);
	}
	

}
