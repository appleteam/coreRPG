import static org.junit.Assert.*;

import org.junit.*;

public class SquareTest {

	
	
	@Test
	public final void TestSetTemperature() throws Exception{
		Square testSquare = new Square();
		try{
			testSquare.setTemperature(-201);
			System.out.println(testSquare.getTemperatureInCelcius());
			fail("Expected IllegalArgumentException");
		} catch(IllegalArgumentException expected) {}
		testSquare.setTemperature(-200);
		assertEquals(-200,testSquare.getTemperatureInCelcius(),0);
		testSquare.setTemperature(0);
		assertEquals(0,testSquare.getTemperatureInCelcius(),0);
		testSquare.setTemperature(100);
		assertEquals(100,testSquare.getTemperatureInCelcius(),0);
		testSquare.setTemperature(5000);
		assertEquals(5000,testSquare.getTemperatureInCelcius(),0);
		try{
			testSquare.setTemperature(5001);
			fail("Expected IllegalArgumentException");
		} catch(IllegalArgumentException expected) {}
	}
	
	@Test
	public final void testGetTemperatureInKelvin(){
		Square testSquare = new Square(-200,0);
		assertEquals(73, testSquare.getTemperatureInKelvin(),0);
		testSquare.setTemperatureKelvin(5000);
		assertEquals(5000, testSquare.getTemperatureInKelvin(),0);
	}
	
	@Test
	public final void testGetTemperatureInFahrenheit(){
		Square testSquare = new Square(-200,0);
		assertEquals(((-200*9)/5) + 32, testSquare.getTemperatureInFarenheit(),0.01);
		testSquare.setTemperatureFahrenheit(9032);
		assertEquals(9032, testSquare.getTemperatureInFarenheit(),0.01);
	}
	
	@Test
	public final void testGetColdDamage(){
		Square testSquare = new Square();
		testSquare.setTemperature(-15);
		assertEquals(1,testSquare.getColdDamage(),0);
		testSquare.setTemperature(-5);
		assertEquals(0,testSquare.getColdDamage(),0);
		testSquare.setTemperature(-57);
		assertEquals(5,testSquare.getColdDamage(),0);
		testSquare.setTemperature(-200);
		assertEquals(19,testSquare.getColdDamage(),0);
		testSquare.setTemperature(2000);
		assertEquals(0,testSquare.getColdDamage(),0);
		testSquare.setTemperature(5000);
		assertEquals(0,testSquare.getColdDamage(),0);
	}
	
	@Test
	public final void testHeatDamage(){
		Square testSquare = new Square();
		testSquare.setTemperature(35);
		assertEquals(0,testSquare.getHeatDamage(),0);
		testSquare.setTemperature(50);
		assertEquals(1,testSquare.getHeatDamage(),0);
		testSquare.setTemperature(-50);
		assertEquals(0,testSquare.getHeatDamage(),0);
		testSquare.setTemperature(-200);
		assertEquals(0,testSquare.getHeatDamage(),0);
		testSquare.setTemperature(200);
		assertEquals(11,testSquare.getHeatDamage(),0);
		testSquare.setTemperature(5000);
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
		testSquare.setTemperature(0);
		assertTrue(testSquare.isSlippery());
		testSquare.setHumidity(100);
		testSquare.setTemperature(1);
		assertTrue(testSquare.isSlippery());
		testSquare.setHumidity(100);
		testSquare.setTemperature(-200);
		assertTrue(testSquare.isSlippery());
		testSquare.setHumidity(100);
		testSquare.setTemperature(5000);
		assertTrue(testSquare.isSlippery());
		testSquare.setHumidity(80);
		testSquare.setTemperature(0);
		assertTrue(testSquare.isSlippery());
		testSquare.setTemperature(1);
		assertFalse(testSquare.isSlippery());
		testSquare.setHumidity(10);
		testSquare.setTemperature(-1);
		assertFalse(testSquare.isSlippery());
		testSquare.setHumidity(11);
		testSquare.setTemperature(-1);
		assertTrue(testSquare.isSlippery());
	}
	
	@Test
	public final void testSetBorderInDirectionTo(){
		Square testSquare = new Square();
		testSquare.setBorderIndirectionTo(0, true);
		assertTrue(testSquare.getBorderInDirection(1));
		testSquare.setBorderIndirectionTo(7, true);
		assertTrue(testSquare.getBorderInDirection(1));
	}
	
	@Test
	public final void testGetBorderInDirection(){
		Square testSquare = new Square();
		testSquare.setBorderIndirectionTo(1, true);
		assertTrue(testSquare.getBorderInDirection(1));
		testSquare.setBorderIndirectionTo(6, true);
		assertTrue(testSquare.getBorderInDirection(6));
	}
	
	
	@Test
	public final void testMergeWithSquareInDirection(){
		Square testSquare1 = new Square();
		Square testSquare2 = new Square();
		testSquare1.setBorderIndirectionTo(3, true);
		testSquare2.setBorderIndirectionTo(3, true);
		
		testSquare1.mergeWithSquareInDirection(testSquare2, 3);
		
		assertEquals(false,testSquare1.getBorderInDirection(3));
		assertEquals(false,testSquare2.getBorderInDirection(3));

		try{
			testSquare1.mergeWithSquareInDirection(testSquare1, 1);
			fail("Expected IllegalArgumentException");
		} catch(IllegalArgumentException expected) {}
		
		try{
			testSquare1.mergeWithSquareInDirection(null, 1);
			fail("Expected IllegalArgumentException");
		} catch(IllegalArgumentException expected) {}
		try{
			testSquare1.mergeWithSquareInDirection(testSquare2, 7);
			fail("Expected IllegalArgumentException");
		} catch(IllegalArgumentException expected) {}

		
	}
	
	@Test
	public final void testGetInhabitability(){
		Square testSquare = new Square();
		assertEquals(0,testSquare.getInhabitability(),0.01);
		testSquare.setHumidity(100);
		testSquare.setTemperature(-200);
		assertEquals(-4.359,testSquare.getInhabitability(),0.01);
		testSquare.setHumidity(0);
		testSquare.setTemperature(5000);
		assertEquals(-599.213,testSquare.getInhabitability(),0.01);
		testSquare.setHumidity(25);
		testSquare.setTemperature(612);
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
