package calculator;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorChip {

	private String output;
	private String variableA;
	private String variableB;
	private String operand;
	private boolean allClear;
	private static final int MAX_DIGITS = 14; // max digits to be displayed
	private String lastButtonPressed; // "Number", "Operation", "Equals" or "Memory"
	private String memory;
	private boolean memoryUsed;
	
	public CalculatorChip(){
		this.output = "0";
		this.variableA = "";
		this.variableB = "";
		this.operand = "";
		this.allClear = true;
		this.lastButtonPressed = "";
		this.memory = "0";
		this.memoryUsed = false;
	}
	
	/*******
	 * Clear methods
	 * 	
	 * **********
	 */
	
	/**
	 * Two scenarios:
	 * - Clear
	 * 		Clears out only content in variableA
	 * 		Sets output display to 0
	 * - All clear
	 * 		Clears out content in variableA, variableB and operand
	 * 		Sets output display to 0
	 * @return
	 */
	public String clear(){
		// TODO REMOVE
		this.printStatusBefore();
		
		if (this.allClear){
			this.clearAll();
		} else {
			this.clearSingle();
		}
		
		// TODO REMOVE
		this.printStatusAfter();
		
		return this.output;
	}
	
	/**
	 * On top of all the clearing performed in clearSingle
	 * also clears out this.variableA and this.operand
	 */
	public void clearAll(){
		this.clearSingle();
		this.variableB = "";
		this.operand = "";
		this.lastButtonPressed = "";
	}
	
	/**
	 * sets this.output = 0;
	 * clears out variableB;
	 * sets allClear to true;
	 */
	public void clearSingle(){
		this.output = "0";
		this.variableA = "";
		this.allClear = true;
		if (this.lastButtonPressed.equals("Equals")){ // If the last button pressed was "=", clear out everything like an all clear
			this.variableB = "";
			this.operand = "";
		}
	}
	
	
	/**
	 * Pre-process prior to entry of digits
	 */
	public void newEntryPreprocessing(){
		if (this.output.equals("Error") || this.lastButtonPressed.equals("Equals") 
				|| this.operand.equals("sqrt")){
			this.clearAll();
		}
	
		if (!this.lastButtonPressed.equals("Number")){
			this.resetIfLastButtonPressedNotANumber();
		}	
	}
	
	
	
	/**
	 * Resets if the last button pressed was not a number
	 */
	public void resetIfLastButtonPressedNotANumber(){
		this.output = "0";
		this.variableA = "";
	}
	
	
	/*******
	 * Binary Operations methods
	 * For all binary operations there are 4 scenarios:
	 * 1) variableA == "", variableB == ""
	 * 2) variableA != "", variableB == ""
	 * 3) variableA == "", variableB != ""
	 * 4) variableA != "", variableB != ""
	 * 
	 * **********
	 */
	
	public String add(){
		if (!this.lastButtonPressed.equals("Equals")){
			this.performBinaryCalculation("add");			
		}
		this.lastButtonPressed = "Operation";
		this.operand = "add";
		return this.output;
	}
	
	public String subtract(){
		if (!this.lastButtonPressed.equals("Equals")){
			this.performBinaryCalculation("subtract");			
		}
		this.lastButtonPressed = "Operation";
		this.operand = "subtract";
		return this.output;
	}
	
	public String multiply(){
		if (!this.lastButtonPressed.equals("Equals")){
			this.performBinaryCalculation("multiply");			
		}
		this.operand = "multiply";
		this.lastButtonPressed = "Operation";			
		return this.output;
	}
	
	public String divide(){
		if (!this.lastButtonPressed.equals("Equals")){
			this.performBinaryCalculation("divide");					
		}
		this.operand = "divide";
		this.lastButtonPressed = "Operation";			
		return this.output;
	}
	
	public void performBinaryCalculation(String pressedOperand){

		//TODO REMOVE
		this.printStatusBefore();
		
		//TODO - 3, +, 4, =, - should not continue the calculation
		
		if (this.variableA.equals("") && this.lastButtonPressed.equals("Operation")){
			this.operand = pressedOperand;  // If I press '+' then '-' I merely change the operand, but do no calculations			
		} else {
				if (this.variableA.equals("") && this.variableB.equals("")){

					System.out.println("emptyAemptyB");
					
					this.operateEmptyAEmptyB(pressedOperand);
					
					
				} else if (!this.variableA.equals("") && this.variableB.equals("")){
					System.out.println("AemptyB");
					
					this.operateAEmptyB(pressedOperand);

					
				
				} else if (this.variableA.equals("") && !this.variableB.equals("")){
					System.out.println("emptyAB");

					
					this.operateEmptyAB(pressedOperand);
				} else if (!this.variableA.equals("") && !this.variableB.equals("")){
					System.out.println("AB");

					
					this.operateAB(this.operand, pressedOperand);

				} else {
					throw new RuntimeException("Invalid operand");
				}		
					
		}
		//TODO REMOVE
		this.printStatusAfter();


	}
	

	/**
	 * Performs calculation if both variableA and variableB are empty
	 * NB - no change to this.output
	 * @param operand
	 */
	public void operateEmptyAEmptyB(String pressedOperand){
		this.variableB = "0";
		this.operand = pressedOperand;
	}
	
	/**
	 * Performs calculation if variableA is not empty but variableB is empty
	 * NB - no change to this.output
	 * @param operand
	 */
	public void operateAEmptyB(String pressedOperand){
		this.variableB = this.variableA;
		this.variableA = "";
		this.operand = pressedOperand;
	}
	
	/**
	 * Performs calculation if variableA is empty but variableB is not empty
	 * @param operand
	 */
	public void operateEmptyAB(String pressedOperand){
		this.output = this.variableB;
		this.operand = pressedOperand;
	}
	
	/**
	 * Performs calculation if both variableA and variableB are not empty
	 * @param operand
	 */
	public void operateAB(String operand, String pressedOperand){
		
		// if operand is %, sqrt, invert, we need to update it to the pressedOperand otherwise
		// code will throw an exception
		
		if(operand.equals("percent") || operand.equals("sqrt") || operand.equals("invert")){
			operand = pressedOperand;
		}
		
		
		if(operand.equals("add")){
			this.output = this.add(this.variableA, this.variableB);
			this.operand = pressedOperand;
			this.variableB = this.output;
		} else if (operand.equals("subtract")){
			this.output = this.subtract(this.variableB, this.variableA); // note variableB comes first!
			this.operand = pressedOperand;
			this.variableB = this.output;			
		} else if (operand.equals("multiply")){
			this.output = this.multiply(this.variableA, this.variableB);
			this.operand = pressedOperand;
			this.variableB = this.output;				
		} else if (operand.equals("divide")){
			try {
				this.output = this.divide(this.variableB, this.variableA); // note variableB comes first!
				this.operand = pressedOperand;
				this.variableB = this.output;							
			} catch (RuntimeException exception){
				this.output = "Error";
			}
		} else {
			throw new RuntimeException("Invalid operand");
		}		
	}
	
	
	/**
	 * Performs addition on two numbers (in string format) and returns the result as a string
	 * @param a
	 * @param b
	 * @return result of a + b (as a string)
	 */
	public String add(String a, String b){
		double doubleA = Double.parseDouble(a);
		double doubleB = Double.parseDouble(b);
		double answer = doubleA + doubleB;
		String resultString = Double.toString(answer);
		resultString = this.trimInput(resultString);
		return resultString;
	}
	
	/**
	 * Performs subtraction on two numbers (in string format) and returns the result as a string
	 * @param a
	 * @param b
	 * @return result of a - b (as a string)
	 */
	public String subtract(String a, String b){
		double doubleA = Double.parseDouble(a);
		double doubleB = Double.parseDouble(b);
		double answer = doubleA - doubleB;
		String resultString = Double.toString(answer);
		resultString = this.trimInput(resultString);
		return resultString;
	}
	
	/**
	 * Performs multiplication on two numbers (in string format) and returns the result as a string
	 * @param a
	 * @param b
	 * @return result of a * b (as a string)
	 */
	public String multiply(String a, String b){
		double doubleA = Double.parseDouble(a);
		double doubleB = Double.parseDouble(b);
		double answer = doubleA * doubleB;
		String resultString = Double.toString(answer);
		resultString = this.trimInput(resultString);
		return resultString;
	}
	
	/**
	 * Performs division on two numbers (in string format) and returns the result as a string
	 * @param a
	 * @param b
	 * @return result of a / b (as a string)
	 */
	public String divide(String a, String b){
		double doubleA = Double.parseDouble(a);
		double doubleB = Double.parseDouble(b);
		if (doubleB == 0) {
			throw new RuntimeException("Error");
		} else {		
			double answer = doubleA / doubleB;
			String resultString = Double.toString(answer);
			resultString = this.trimInput(resultString);
			return resultString;			
		} 
	}
	
//	public String trimSignificantPlaces(String number){
//		if (number.length() > MAX_DIGITS){
//			number = number.substring(0, MAX_DIGITS);
//		}
//		return number;
//	}

	/*******
	 * Unary operations methods
	 * **********
	 */
	
	public String sqrt(){
		
		//TODO REMOVE
		this.printStatusBefore();	
		
		this.performUniaryCalculations("sqrt");
		
		//TODO REMOVE
		this.printStatusAfter();
		
		return this.output;
	}
	
	
	
	
	
	/**
	 * Calculates the square root of a number
	 * @param a
	 * @return
	 */
	public String sqrt(String a){
		
		double doubleA = Double.parseDouble(a);
		doubleA = Math.sqrt(doubleA);
		String resultString = Double.toString(doubleA);
		
		if (resultString.equals("NaN")){
			throw new RuntimeException("Error");
		}
		
		resultString = this.trimInput(resultString);
		return resultString;

	}
	
	
	public String invert(){
		//TODO REMOVE
		this.printStatusBefore();	

		this.performUniaryCalculations("invert");		
		
		//TODO REMOVE
		this.printStatusAfter();
	
		return this.output;		
	}
	

	
	/**
	 * calculates the inverse of a number
	 * @param a
	 * @return
	 */
	public String invert(String a){
	
		double doubleA = Double.parseDouble(a);
		if (doubleA == 0){
			throw new RuntimeException("Error");
		}
		doubleA = 1/(doubleA);
		String resultString = Double.toString(doubleA);

		resultString = this.trimInput(resultString);
		return resultString;
	
	}
	
	String percent(){
		//TODO REMOVE
		this.printStatusBefore();	

		this.performUniaryCalculations("percent");		
		
		//TODO REMOVE
		this.printStatusAfter();
	
		return this.output;		

	}
	
	/**
	 * Converts number into a percentage
	 * @param a
	 * @return
	 */
	public String percent(String a, String b){
	
		double doubleA = Double.parseDouble(a);
		double doubleB = Double.parseDouble(b);
		doubleA = doubleA / 100 * doubleB;
		String resultString = Double.toString(doubleA);

		resultString = this.trimInput(resultString);
		return resultString;
	
	}
	
	
	public void performUniaryCalculations(String function){
		String answer = "";
		if (this.lastButtonPressed.equals("Equals")){
			// 1, x, 4, =, sqrt, 
			this.operand = function;
			try {
				
				if (function.equals("sqrt")){
					answer = this.sqrt(this.variableB);					
				} else if (function.equals("invert")){
					answer = this.invert(this.variableB);
				} else if (function.equals("percent")){
					answer = this.percent(this.variableB, "1");
				}
				
				this.variableA = answer;
				this.output = answer;				
			} catch (RuntimeException exception){
				this.output = "Error";
			}
			
		} else if (this.operand.equals("add") || this.operand.equals("subtract") || 
				this.operand.equals("multiply") || this.operand.equals("divide")){
			// 1, x, 4, sqrt
			
			try {
				if (function.equals("sqrt")){
					answer = this.sqrt(this.variableA);					
				} else if (function.equals("invert")){
					answer = this.invert(this.variableA);
				} else if (function.equals("percent")){
					answer = this.percent(this.variableA, this.variableB);
				}
				this.variableA = answer;
				this.output = answer;				
			} catch (RuntimeException exception){
				this.output = "Error";
			}
			
		} else {
			
			try {
				if (function.equals("sqrt")){
					answer = this.sqrt(this.variableA);					
				} else if (function.equals("invert")){
					answer = this.invert(this.variableA);
				}	else if (function.equals("percent")){
					answer = this.percent(this.variableA, "1");
				}
				this.operand = function;			
				this.variableA = answer;
				this.output = answer;
				
			} catch (RuntimeException exception){
				this.output = "Error";
			}
			
		}
		
		this.allClear = false;
		this.lastButtonPressed = "Operation";
	}
	

	/*******
	 * Equals operations methods
	 * **********
	 */
	
	/**
	 * 4 possible states:
	 * 1. VariableA & VariableB are both empty
	 * 2. VariableA is empty, VariableB is not empty
	 * 3. VariableA is not empty, VariableB is empty <-- unlikely
	 * 4. VariableA and VariableB are both not empty
	 * @return
	 */
	public String equals(){
		
		//TODO REMOVE
		this.printStatusBefore();
		
		if (this.operand.equals("sqrt")){
			this.lastButtonPressed = "Equals";
			try {
				if (this.variableB.equals("")){
					this.variableB = this.variableA;
				}
				String answer = this.sqrt(this.variableB);
				this.variableB = answer;
				this.output = answer;				
				
				
				
//				if (this.variableB.equals("")){
//					this.variableB = this.variableA;
//					String answer = this.sqrt(this.variableB);
//					this.variableB = answer;
//					this.output = answer;				
//				} else {
//					String answer = this.sqrt(this.variableA);
//					this.variableA = answer;
//					this.output = answer;									
//					
//				}
				
			} catch (RuntimeException exception){
				this.output = "Error";
				return this.output;
			}
			
		} 
		
		else if (this.lastButtonPressed.equals("Number")){
			
			this.lastButtonPressed = "Equals";

			
			if (!this.variableB.equals("") && !this.variableB.equals("")){
				// eg 3, +, 5, =
				this.performBinaryCalculation(this.operand);
				this.variableB = this.output;
			} else if (!this.variableA.equals("") && this.variableB.equals("") && !this.operand.equals("")){
				// eg -, 3, =
				this.variableB = "0";
				this.performBinaryCalculation(this.operand);
				this.variableB = this.output;		
			} else {
				// Do nothing
			}
		} 
		
		else if (this.lastButtonPressed.equals("Operation") || this.lastButtonPressed.equals("Equals")){
			
			this.lastButtonPressed = "Equals";
			
			if (this.variableA.equals("") && !this.variableB.equals("")){
				// eg. 2, -, =		varA = ""
				this.variableA = this.variableB; // varA = 2, varB = 2

				this.performBinaryCalculation(this.operand);
				// NB varA is left as 2


				
			} else if (!this.variableA.equals("") && !this.variableB.equals("")){
				// eg 2, -, =, =
				this.performBinaryCalculation(this.operand);
								
			} else {
				// Do nothing
			}
		}	


		//TODO REMOVE
		this.printStatusAfter();

		

		return this.output;
	}
		
	
	/*******
	 * NumberButton methods
	 * **********
	 */
	
	/**
	 * Concatenates the input digit to this.output and this.variableA
	 * Handles the following three examples:
	 * 1. if this.output = 0 and digit = 0
	 * 2. if this.output = 0 and digit != 0
	 * 3. if this.ouput != 0
	 * @param digit
	 * @return the current state of this.output
	 */
	public String digit(int digit){
		this.allClear = false;
		
		this.newEntryPreprocessing();
		
		//TODO REMOVE
		this.printStatusBefore();
		
		if (digit >= 0 && digit <= 9){
			if (digit == 0 && this.output.equals("0")){	
				this.lastButtonPressed = "Number";
				this.variableA = "0";

				//TODO REMOVE
				this.printStatusAfter();
				
				return this.output; // doesn't do anything
			} else if (this.output.equals("0")){
				// only update output and variable if the digit pressed is not zero and the this.output isn't "0"
				String stringDigit = Integer.toString(digit);
				this.variableA = stringDigit;
				this.output = stringDigit;
				this.lastButtonPressed = "Number";
				
				//TODO REMOVE
				this.printStatusAfter();

				
				return this.output;				
			} else {
				String stringDigit = Integer.toString(digit);
				variableA = variableA + stringDigit;
				this.output = this.output + stringDigit;
				this.lastButtonPressed = "Number";
	
				//TODO REMOVE
				this.printStatusAfter();
				
				return this.output;					
			}			
		} else {
			// This shouldn't happen - ie input is not a number between 0 - 9
			throw new RuntimeException("Digit isn't 0-9");
		}
	}
	
	/**
	 * Adds a decimal point to this.output, if a decimal hasn't been input already
	 * @return the current state of this.output
	 */
	public String decimalPoint(){
		this.allClear = false;
		this.newEntryPreprocessing();

		
		if (!this.output.contains(".")){
			this.output = this.output + ".";
			this.lastButtonPressed = "Number";
			this.variableA = this.output;
			return this.output;			
		} else {
			return this.output;
		}
	}
	
	/**
	 * Changes the sign of this.output. Considers the following scenarios:
	 * 1. this.output = 0
	 * 2. this.output = 0.
	 * 3. this.output = 3.0
	 * 4. this.output = 3.51
	 * 5. this.output = 3.510
	 * @return the current state of this.output
	 */
	public String changeSign(){
		this.allClear = false;		
		if (!this.output.equals("Error")){
			this.output = this.trimInput(this.output);
			if (this.output.equals("0")){
				return this.output; // don't do anything
			} else {
				int signIndex = this.output.indexOf('-');
				if(signIndex > -1){  // it's a negative number
					this.output = this.output.substring(1);
					this.variableA = this.output;
					this.lastButtonPressed = "Number";
					return this.output;
				} else {
					this.output = "-" + this.output;
					this.variableA = this.output;
					this.lastButtonPressed = "Number";
					return this.output;
				}			
			} 
			
		} else {
			return this.output;
		}
		
	}
	
	/**
	 * Removes trailing 0's and decimal points (eg 4., 4.00)
	 * @param input
	 * @return a string of numbers without trailing decimals and 0's
	 */
	public String trimInput(String input){
				
		int stringLength = input.length();
		
		if (stringLength > MAX_DIGITS){
			input = input.substring(0, MAX_DIGITS);
		}

		stringLength = input.length();

		
		while (stringLength > 1){
			if(  (input.contains(".") && input.endsWith("0")) 
							|| (input.endsWith("."))  ){
				input = input.substring(0, stringLength - 1);
				stringLength = input.length();	
			} else {
				break;
			}
		}
		return input;
	}
	
	/*******
	 * MemoryButton methods
	 * **********
	 */
	
	public String memClear(){
		this.memory = "0";
		this.memoryUsed = false;
		this.lastButtonPressed = "Memory";
		return this.output;
	}
	
	public String memRead(){
		this.output = this.memory;
		this.variableA = this.memory;
		this.lastButtonPressed = "Memory";
		return this.output;
	}
	
	public String memPlus(){
		this.memoryUsed = true;
		if (this.lastButtonPressed.equals("Equals")){
			this.memory = this.add(this.memory,this.variableB);	// Equals stores results in variableB		
		} else {
			
			if (this.variableA.equals("")){this.variableA = "0";}
			
			this.memory = this.add(this.memory,this.variableA);
		}
		this.lastButtonPressed = "Memory";
		return this.output;
	}
	
	public String memMinus(){
		this.memoryUsed = true;
		if (this.lastButtonPressed.equals("Equals")){			
			this.memory = this.subtract(this.memory,this.variableB);	// Equals stores results in variableB		
		} else {
			this.memory = this.subtract(this.memory,this.variableA);
		}
		this.lastButtonPressed = "Memory";
		return this.output;
	}
	
	/*******
	 * Getter and setter methods
	 * **********
	 */
	public String getOutput(){
		return this.output;
	}
	
	public String getVariableA(){
		return this.variableA;
	}
	
	public String getVariableB(){
		return this.variableB;
	}
	
	public boolean getAllClear(){
		return this.allClear;
	}
	
	public boolean getMemoryState(){
		return this.memoryUsed;
	}
	
	public String getOperand(){
		return this.operand;
	}
	
	public String getLastButtonPressed(){
		return this.lastButtonPressed;
	}
	
	/**
	 * Setter ONLY FOR UNIT TEST
	 */
	public void setOutput(String text){
		this.output = text;
	}
	
	public void setVariableB(String text){
		this.variableB = text;
	}
	
	//TODO REMOVE THESE METHODS
	public void printStatusBefore(){
		System.out.println(this.variableA + "<-- variableA before operation");
		System.out.println(this.variableB + "<-- variableB before operation");
		System.out.println(this.lastButtonPressed + "<-- lastButtonPressed before operation");
		System.out.println(this.operand + "<-- operand before operation");
		System.out.println(this.output + "<-- output before operation");
		System.out.println("");
	}
	
	public void printStatusAfter(){
		System.out.println(this.variableA + "<-- variableA after operation");
		System.out.println(this.variableB + "<-- variableB after operation");
		System.out.println(this.lastButtonPressed + "<-- lastButtonPressed after operation");
		System.out.println(this.operand + "<-- operand after operation");
		System.out.println(this.output + "<-- output after operation");
		System.out.println("");

	}
	
}
