package calculator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CalculatorChipTest {

	CalculatorChip chip;
	
	@Before
	public void setUp() throws Exception {
		chip = new CalculatorChip();
	}

	@Test
	public void testClear() {
		chip.digit(3);
		assertEquals("3", chip.getOutput());
		assertEquals("3", chip.getVariableA());
		chip.clear();
		assertEquals("0", chip.getOutput());
		assertTrue(chip.getAllClear());
		assertEquals("", chip.getVariableA());
	}
	
	
	@Test
	public void testClear2(){ // Keys input: 3, +, C, 7, =
		chip.digit(3);
		assertFalse(chip.getAllClear()); 
		chip.add();
		chip.clear();
		assertEquals("add", chip.getOperand()); // the operand shouldn't have been cleared
		assertEquals("3", chip.getVariableB()); // variableB shouldn't have been cleared
		assertEquals("", chip.getVariableA()); // variableA should have been cleared
		assertTrue(chip.getAllClear()); // all clear is now set to true
		chip.digit(7);
		chip.equals();
		assertEquals("10", chip.getOutput()); 
	}
	
	@Test
	public void testClear3(){ // Keys input: 3, +, C, C
		chip.digit(3);
		assertFalse(chip.getAllClear()); 
		chip.add();
		chip.clear();
		assertTrue(chip.getAllClear()); // all clear is now set to true
		chip.clear(); // should clear out everything
		assertEquals("", chip.getVariableA()); // variableA should have been cleared
		assertEquals("", chip.getVariableB()); // variableB should have been cleared
		assertEquals("", chip.getOperand()); // operand should have been cleared	
		assertTrue(chip.getAllClear()); // all clear is still true
	}
	
	@Test
	public void testClear4(){ // Keys input: 5, +, C, -, 3, =
		chip.digit(5);
		assertFalse(chip.getAllClear()); 
		chip.add();
		chip.clear();
		assertTrue(chip.getAllClear()); // all clear is now set to true
		chip.subtract();
		assertEquals("subtract", chip.getOperand()); // operand should now be subtract	
		chip.digit(3);
		chip.equals();
		assertEquals("2", chip.getOutput()); 
	}
	
	@Test
	public void testClear5(){ // Keys input: 9, SQRT, C, -, 2, =
		chip.digit(9);
		chip.sqrt();
		chip.clear();
		assertEquals("0", chip.getOutput()); 
		assertEquals("", chip.getVariableA()); 
		assertEquals("", chip.getOperand());  // Output is not cleared, but variableA and variableB are cleared
		assertTrue(chip.getAllClear()); // All clear now set to true
		chip.subtract();
		assertEquals("subtract", chip.getOperand()); // operand should now be subtract	
		assertEquals("0", chip.getVariableB()); 
		assertEquals("", chip.getVariableA()); 
		chip.digit(2);
		assertEquals("2", chip.getVariableA()); 
		chip.equals();
		assertEquals("-2", chip.getOutput()); 
	}

	@Test
	public void testDigit() {
		assertTrue(chip.getAllClear());
		assertEquals("0", chip.getOutput());
		chip.digit(0);
		assertFalse(chip.getAllClear());
		assertEquals("0", chip.getOutput());
		chip.digit(5);
		assertEquals("5", chip.getOutput());
		chip.digit(6);
		chip.digit(0);
		assertEquals("560", chip.getOutput());
		chip.clear();
		assertEquals("0", chip.getOutput());
		chip.setOutput("Error");
		chip.digit(0);
		assertEquals("0", chip.getOutput());
	}

	@Test
	public void testDecimalPoint() {
		assertTrue(chip.getAllClear());
		chip.digit(5);
		assertFalse(chip.getAllClear());
		assertEquals("5", chip.getOutput());
		chip.decimalPoint();
		assertEquals("5.", chip.getOutput());
		assertEquals("5.", chip.getVariableA());
		chip.decimalPoint();
		assertEquals("5.", chip.getOutput());
		chip.digit(6);
		assertEquals("5.6", chip.getOutput());
		assertEquals("5.6", chip.getVariableA());
		chip.clear();
		chip.decimalPoint();
		assertEquals("0.", chip.getOutput());
		chip.clear();
		chip.setOutput("Error");
		chip.decimalPoint();
		assertEquals("0.", chip.getOutput());
	}

	@Test
	public void testChangeSign() {
		chip.changeSign();
		assertEquals("0", chip.getOutput());
		assertFalse(chip.getAllClear());
		chip.decimalPoint();
		chip.changeSign();
		assertEquals("0", chip.getOutput()); // Decimal point drops
		assertFalse(chip.getAllClear());
		chip.digit(3);
		chip.decimalPoint();
		chip.changeSign();
		assertEquals("-3", chip.getOutput()); // Decimal point drops
		chip.decimalPoint();
		chip.digit(0);
		chip.digit(0);
		chip.digit(0);
		chip.changeSign();
		assertEquals("3", chip.getOutput()); // Decimal point and trailing 0s dropped
		chip.digit(0);
		chip.changeSign();		
		assertEquals("-30", chip.getOutput());
		chip.decimalPoint();
		chip.digit(0);
		chip.digit(5);
		chip.changeSign();		
		assertEquals("30.05", chip.getOutput());
		chip.digit(0);
		chip.digit(0);
		chip.changeSign();		
		assertEquals("-30.05", chip.getOutput()); // Trailing 0s dropped
	}
	
	@Test
	public void testtrimInput(){
		assertEquals("5", chip.trimInput("5"));
		assertEquals("-5", chip.trimInput("-5"));
		assertEquals("-5", chip.trimInput("-5.0"));
		assertEquals("50", chip.trimInput("50.0"));
		assertEquals("50.05", chip.trimInput("50.05"));
		assertEquals("50", chip.trimInput("50."));
		assertEquals("-50", chip.trimInput("-50."));
		assertEquals("0", chip.trimInput("0"));
		assertEquals("0", chip.trimInput("0."));
		assertEquals("0", chip.trimInput("0.0000"));
		assertEquals("0.00001", chip.trimInput("0.00001"));
		assertEquals("-0.00001", chip.trimInput("-0.00001"));		
		assertEquals("1.34567890111", chip.trimInput("1.3456789011121314"));
	}
	
	
	@Test
	public void testaddition(){
		assertEquals("35", chip.add("20", "15"));
		assertEquals("36", chip.add("20.55", "15.45"));
		assertEquals("5", chip.add("20", "-15"));
		assertEquals("-5", chip.add("-20", "15"));
		assertEquals("-35", chip.add("-20", "-15"));
		assertEquals("-5", chip.add("-20.0", "15"));
		assertEquals("-4.35", chip.add("-20.05", "15.7"));
	}
	
	@Test
	public void testsubtract(){
		assertEquals("5", chip.subtract("20", "15"));
		assertEquals("-5", chip.subtract("15", "20"));
		assertEquals("5.1", chip.subtract("20.55", "15.45"));
		assertEquals("35", chip.subtract("20", "-15"));
		assertEquals("-35", chip.subtract("-20", "15"));
		assertEquals("-5", chip.subtract("-20", "-15"));
		assertEquals("-35", chip.subtract("-20.0", "15"));
		assertEquals("-35.75", chip.subtract("-20.05", "15.7"));
	}
	
	@Test
	public void testmultiply(){
		assertEquals("300", chip.multiply("20", "15"));
		assertEquals("300", chip.multiply("15", "20"));
		assertEquals("317.4975", chip.multiply("20.55", "15.45"));
		assertEquals("-300", chip.multiply("20", "-15"));
		assertEquals("-300", chip.multiply("-20", "15"));
		assertEquals("300", chip.multiply("-20", "-15"));
		assertEquals("-300", chip.multiply("-20.0", "15"));
		assertEquals("-314.785", chip.multiply("-20.05", "15.7"));
	}
	
	@Test
	public void testdivide(){
		assertEquals("2", chip.divide("20", "10"));
		assertEquals("0.75", chip.divide("15", "20"));
		assertEquals("-2", chip.divide("20", "-10"));
		assertEquals("-2", chip.divide("-20", "10"));
		assertEquals("2", chip.divide("-20", "-10"));
		assertEquals("-2", chip.divide("-20.0", "10"));
		assertEquals("-1.3300970873", chip.divide("-20.55", "15.45"));
		assertEquals("1.33009708737", chip.divide("20.55", "15.45"));
	}
	
	@Test(expected=RuntimeException.class)
	public void testdivideByZero(){
		assertEquals("-2", chip.divide("-20.0", "0"));		
	}
	
	@Test(expected=RuntimeException.class)
	public void testdivideByZero2(){
		assertEquals("-2", chip.divide("-20.0", "0.0"));		
	}


	
	@Test
	public void testoperateEmptyAEmptyB(){
		chip.operateEmptyAEmptyB("add");
		assertEquals("0", chip.getVariableB());
		assertEquals("", chip.getVariableA());
		assertEquals("add", chip.getOperand());
		assertTrue(chip.getAllClear());
	}
	
	@Test
	public void testoperateAEmptyB(){
		chip.digit(5);
		chip.decimalPoint();
		chip.digit(3);
		chip.operateAEmptyB("add");
		assertEquals("5.3", chip.getVariableB());
		assertEquals("5.3", chip.getOutput());
		assertEquals("", chip.getVariableA());
		assertEquals("add", chip.getOperand());
	}
	
	@Test
	public void testoperateEmptyAB(){
		chip.setVariableB("3");
		chip.operateEmptyAB("add");
		assertEquals("3", chip.getOutput());
		assertEquals("", chip.getVariableA());
		assertEquals("add", chip.getOperand());
	}
	
	@Test
	public void testsqrt(){
		assertEquals("3", chip.sqrt("9"));
		assertEquals("3", chip.sqrt("9.0"));
		assertEquals("1.1", chip.sqrt("1.21"));
	}

	@Test(expected=RuntimeException.class)
	public void testsqrtNegativeNumber(){
		chip.sqrt("-1");
	}
	
	@Test(expected=RuntimeException.class)
	public void testsqrtNegativeNumber2(){
		chip.sqrt("-1.3");
	}
	
	@Test
	public void testinvert(){
		assertEquals("0.5", chip.invert("2"));
		assertEquals("2", chip.invert("0.5"));
		assertEquals("-0.5", chip.invert("-2"));
		assertEquals("-2", chip.invert("-0.5"));
	}
	
	@Test(expected=RuntimeException.class)
	public void testinverseZero(){
		chip.invert("0");
	}
	
	@Test(expected=RuntimeException.class)
	public void testinverseZero2(){
		chip.invert("0.0");
	}

	@Test
	public void testpercent(){
		assertEquals("0.02", chip.percent("2", "1"));
		assertEquals("0.005", chip.percent("0.5", "1"));
		assertEquals("-0.02", chip.percent("-2", "1"));
		assertEquals("-0.005", chip.percent("-0.5", "1"));
		assertEquals("5", chip.percent("50", "10"));
		assertEquals("-5", chip.percent("-50", "10"));
	}
	
	@Test
	public void testSimple1(){
		chip.digit(3);
		chip.add();
		chip.digit(5);
		assertEquals("8", chip.equals());
	}
	
	@Test
	public void testSimple2(){
		chip.digit(3);
		chip.subtract();
		chip.digit(5);
		assertEquals("-2", chip.equals());
	}
	
	@Test
	public void testSimple3(){
		chip.digit(6);
		chip.multiply();
		chip.digit(3);
		assertEquals("18", chip.equals());
	}
	
	@Test
	public void testSimple4(){
		chip.digit(6);
		chip.divide();
		chip.digit(3);
		assertEquals("2", chip.equals());
	}
	
	@Test
	public void testSimple5(){
		chip.digit(9);
		assertEquals("3", chip.sqrt());
	}
	
	@Test
	public void testSimple6(){
		chip.digit(2);
		assertEquals("0.5", chip.invert());
	}
	
	@Test
	public void testSimple7(){
		chip.digit(2);
		assertEquals("0.02", chip.percent());
	}
	
	@Test
	public void testPercent2(){
		chip.digit(5);
		chip.percent();
		chip.add();
		chip.digit(5);
		assertEquals("5.05", chip.equals());
		
		chip.clear();
		chip.clear();
		
		chip.digit(5); // 5, +, 10, %, = --> 5.5
		chip.add();
		chip.digit(1);
		chip.digit(0);
		assertEquals("0.5", chip.percent());
		assertEquals("5.5", chip.equals());
	}
	
	@Test
	public void testPercent3(){ // 5, x, %, = 
		chip.digit(5);
		chip.multiply();
		assertEquals("0.25", chip.percent());
		assertEquals("1.25", chip.equals());
		
	}
	
	@Test
	public void testSquareRoot(){ // 9, x, sqrt, =
		chip.digit(9);
		chip.multiply();
		assertEquals("3", chip.sqrt());
		assertEquals("27", chip.equals());
	}
	
	@Test
	public void testSquareRoot2(){ // 18, /, 9, sqrt, =
		chip.digit(1);
		chip.digit(8);
		chip.divide();
		chip.digit(9);
		assertEquals("3", chip.sqrt());
		assertEquals("6", chip.equals());
	}
	
	@Test
	public void testSquareRoot3(){ // 18, /, 16, sqrt, sqrt, =
		chip.digit(1);
		chip.digit(8);
		chip.divide();
		chip.digit(1);
		chip.digit(6);
		assertEquals("4", chip.sqrt());
		assertEquals("2", chip.sqrt());
		assertEquals("9", chip.equals());
	}
	
	@Test
	public void testSquareRoot4(){ // 256, sqrt, =, =
		chip.digit(2);
		chip.digit(5);
		chip.digit(6);
		assertEquals("16", chip.sqrt());
		assertEquals("4", chip.equals());
		assertEquals("2", chip.equals());
	}
	
	@Test
	public void testSquareRoot5(){ // 100, /, 16, sqrt, sqrt, =
		chip.digit(1);
		chip.digit(0);
		chip.digit(0);
		chip.divide();
		chip.digit(1);
		chip.digit(6);
		assertEquals("4", chip.sqrt());
		assertEquals("2", chip.sqrt());
		assertEquals("50", chip.equals());		
	}
	
	@Test
	public void testmultiplyInvert(){ // 2, x, 1/x, =, 0.5
		chip.digit(2);
		chip.multiply();
		assertEquals("0.5", chip.invert());
		assertEquals("1", chip.equals());
		assertEquals("0.5", chip.equals());
	}
	
	@Test
	public void testplusMultiply(){ // 6, +, 3, x, =
		chip.digit(6);
		chip.add();
		chip.digit(3);
		chip.multiply();
		assertEquals("81", chip.equals());
		assertEquals("729", chip.equals());
	}
	
	@Test
	public void testhardCalculation1(){ // 6, +, 3, x, =
		chip.digit(6);
		chip.add();
		chip.digit(3);
		chip.multiply();
		assertEquals("81", chip.equals());
	}
	
	@Test
	public void testEqualsOperation(){ // 9, x, 8, -, =, =
		chip.digit(9);
		chip.multiply();
		chip.digit(8);
		chip.subtract();
		assertEquals("0", chip.equals());
		assertEquals("-72", chip.equals());
	}
	
	@Test
	public void testEqualsOperation2(){ // 3, -, =, =
		chip.digit(3);
		chip.subtract();
		assertEquals("0", chip.equals());
		assertEquals("-3", chip.equals());
	}
	
	@Test
	public void testEqualsOperation3(){ // 9, x, 9, =, sqrt, =
		chip.digit(9);
		chip.multiply();
		chip.digit(9);
		assertEquals("81", chip.equals());
		assertEquals("9", chip.sqrt());
		assertEquals("3", chip.equals());
	}
	
	@Test
	public void testNumerousEquals(){ // 3, -, =, =
		chip.digit(3);
		chip.subtract();
		chip.equals();
		chip.equals();
		assertEquals("-6", chip.equals());
	}
	
	
}
