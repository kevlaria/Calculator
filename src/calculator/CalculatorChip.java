package calculator;

public class CalculatorChip {

	private String output;
	private String variableA;
	private String variableB;
	private String operand;
	private boolean allClear;
	private static final int MAX_DIGITS = 13; // max digits to be displayed
	private String lastButtonPressed; // "Number", "Operation", "Unary Operation" "Equals" or "Memory"
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
	 * Implements the clear button
	 * Two scenarios:
	 * - Clear
	 * - All clear
	 * @return the current value of this.output
	 */
	public String clear(){
		if (this.allClear){
			this.clearAll();
		} else {
			this.clearSingle();
		}
		return this.output;
	}
	
	/**
	 * On top of all the clearing performed in clearSingle
	 * also clears out this.variableB, this.operand and lastButtonPressed
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
		if (this.lastButtonPressed.equals("Equals") || this.lastButtonPressed.equals("UnaryOperation")){ 
			// If the last button pressed was "=", "sqrt", "%" or "1/x", clear out everything like an all clear
			this.variableB = "";
			this.operand = "";
		}
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
	
	
	/**
	 * Implements the add button
	 * @return the current value of this.output
	 */
	public String add(){
		if (!this.lastButtonPressed.equals("Equals")){
			this.performBinaryCalculation("add");			
		}
		this.lastButtonPressed = "Operation";
		this.operand = "add";
		return this.output;
	}
	
	/**
	 * Implements the subtract button
	 * @return the current value of this.output
	 */
	public String subtract(){
		if (!this.lastButtonPressed.equals("Equals")){
			this.performBinaryCalculation("subtract");			
		}
		this.lastButtonPressed = "Operation";
		this.operand = "subtract";
		return this.output;
	}
	
	/**
	 * Implements the multiply button
	 * @return the current value of this.output
	 */
	public String multiply(){
		if (!this.lastButtonPressed.equals("Equals")){
			this.performBinaryCalculation("multiply");			
		}
		this.operand = "multiply";
		this.lastButtonPressed = "Operation";			
		return this.output;
	}
	
	/**
	 * Implements the divide button
	 * @return the current value of this.output
	 */
	public String divide(){
		if (!this.lastButtonPressed.equals("Equals")){
			this.performBinaryCalculation("divide");					
		}
		this.operand = "divide";
		this.lastButtonPressed = "Operation";			
		return this.output;
	}
	
	
	/**
	 * Perform calculations for +, -, x, /
	 * @param pressedOperand
	 */
	public void performBinaryCalculation(String pressedOperand){

		if (this.variableA.equals("") && this.lastButtonPressed.equals("Operation")){
			this.operand = pressedOperand;  // If I press '+' then '-' I merely change the operand, but do no calculations			
		} else {
				if (this.variableA.equals("") && this.variableB.equals("")){
					this.operateEmptyAEmptyB(pressedOperand);
					
				} else if (!this.variableA.equals("") && this.variableB.equals("")){
					this.operateAEmptyB(pressedOperand);
					
				
				} else if (this.variableA.equals("") && !this.variableB.equals("")){
					
					this.operateEmptyAB(pressedOperand);

				} else if (!this.variableA.equals("") && !this.variableB.equals("")){
									
					this.operateAB(this.operand, pressedOperand);

				} else { // this should not happen
					throw new RuntimeException("Invalid operand");
				}					
		}
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
		} else { //This shouldn't happen
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
	

	/*******
	 * Unary operations methods ('sqrt', '1/x', '%')
	 * **********
	 */
	
	/**
	 * Implements the square root button
	 * @return the current value of this.output
	 */
	public String sqrt(){
		this.preUnaryCalculations("sqrt");
		return this.output;
	}
	
	/**
	 * Implements the 1/x button
	 * @return the current value of this.output
	 */
	public String invert(){
		this.preUnaryCalculations("invert");				
		return this.output;		
	}
	
	/**
	 * Implements the % button
	 * @return the current value of this.output
	 */
	String percent(){
		this.preUnaryCalculations("percent");		
		return this.output;		
	}

	
	/**
	 * Conducts preparatory steps for unary calculations based on the last button pressed and current operand
	 * @param function
	 */
	public void preUnaryCalculations(String function){
		if (this.lastButtonPressed.equals("Operation")){
			// eg. 5, x, % - varA is empty hence need to copy varB to varA
			this.variableA = this.variableB;
			this.performUnaryCalculation(function);
			
		} else if (this.lastButtonPressed.equals("Equals")){
			// eg. 8, x, 2, = - answer is in varB so need to copy to varA
			// After computation, need to update this.operand + copy answer to varB
			this.variableA = this.variableB;
			this.performUnaryCalculation(function);
			this.operand = function;
			this.variableB = this.variableA;
			
		} else {		// lastButtonPressed = "Memory", "Number" or "Unary Operations"
			
			if (this.operand.equals("add") || this.operand.equals("subtract") 
					|| this.operand.equals("multiply") || this.operand.equals("divide")){
				// don't change operand
				this.performUnaryCalculation(function);
			} else {
				this.performUnaryCalculation(function);
				this.operand = function;
			}	
		}
	}
	
	/**
	 * Perform unary calculations (sqrt, 1/x, %)
	 * Performs calculation on variableA
	 * Returns answer to variableA
	 * Retains original operand
	 * @param function
	 */	
	public void performUnaryCalculation(String function){
		String answer = "";
		try {
			if (function.equals("sqrt")){
				answer = this.sqrt(this.variableA);					
			} else if (function.equals("invert")){
				answer = this.invert(this.variableA);
			} else if (function.equals("percent")){	
				if (this.variableB.equals("")){
					this.variableB = "1";
					answer = this.percent(this.variableA, this.variableB);
					this.variableB = "";  // reset variableB
				} else {
					answer = this.percent(this.variableA, this.variableB);					
				}
			}
			this.variableA = answer;
			this.output = answer;				
		} catch (RuntimeException exception){
			this.output = "Error";
		}
		
		this.allClear = false;
		this.lastButtonPressed = "UnaryOperation";
	}
	
	
	/**
	 * Calculates the square root of a number
	 * @param a
	 * @return the result of the calculation
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
	
	
	/**
	 * calculates the inverse of a number
	 * @param a
	 * @return the result of the calculation
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
	
	
	
	/**
	 * Converts number into a percentage
	 * @param a
	 * @param b
	 * @return the result of the calculation
	 */
	public String percent(String a, String b){
	
		double doubleA = Double.parseDouble(a);
		double doubleB = Double.parseDouble(b);
		doubleA = doubleA / 100 * doubleB;
		String resultString = Double.toString(doubleA);

		resultString = this.trimInput(resultString);
		return resultString;
	
	}

			

	/*******
	 * Equals operations methods
	 * **********
	 */
	
	/**
	 * Implements the enter key. Functionality depends on what the last button that was pressed 
	 * @return the current value of this.output
	 */
	public String equals(){
		
		if (this.getLastButtonPressed().equals("Number")){
			this.equalsNumber();
		} else if (this.getLastButtonPressed().equals("Operation")){
			this.equalsOperation();
		} else if (this.getLastButtonPressed().equals("UnaryOperation")){
			this.equalsUnaryOperation();
		} else if (this.getLastButtonPressed().equals("Equals")){
			this.equalsEquals();
		} else {
			// Do nothing
		}
		this.lastButtonPressed = "Equals";
		return this.output;
	}
	
	/**
	 * Performs the equals function if lastButtonPressed = "Number"
	 */
	public void equalsNumber(){
		if (this.operand.equals("add") || this.operand.equals("subtract") 
				|| this.operand.equals("multiply") || this.operand.equals("divide")){
			if (!this.variableA.equals("") && !this.variableB.equals("")){
				// eg. 9 x 8
				this.performBinaryCalculation(this.operand);
			} else if (!this.variableA.equals("") && this.variableB.equals("")){
				// eg. -2
				this.variableB = "0";
				this.performBinaryCalculation(this.operand);
			} else {
				// shouldn't happen
				throw new RuntimeException("Invalid equals function");
			}		
		} else {
			// eg. 9, 
			// do nothing
		}
	}
	
	/**
	 * Performs the equals function if lastButtonPressed = "Operation" (+, -, x, /)
	 */
	public void equalsOperation(){
		if (!this.variableA.equals("") && !this.variableB.equals("")){
			// eg. 9 x 8 -
			this.variableA = this.variableB;
			this.performBinaryCalculation(this.operand);
		} else if (this.variableA.equals("") && !this.variableB.equals("")){
			// eg. 3 - 
			this.variableA = this.variableB;
			this.performBinaryCalculation(this.operand);
		} else if (this.variableA.equals("") && this.variableB.equals("")){
			// +
			// Do nothing
		}	
		else {
			// shouldn't happen
			throw new RuntimeException("Invalid equals function");
		}			
	}
	
	/**
	 * Performs the equals function if lastButtonPressed = "UnaryOperation" (%, sqrt, 1/x)
	 */
	public void equalsUnaryOperation(){
		if (!this.variableA.equals("") && !this.variableB.equals("")){
			
			if (this.operand.equals("add") || this.operand.equals("subtract") 
					|| this.operand.equals("multiply") || this.operand.equals("divide")){
				// eg. 8, x, 9, sqrt
				this.performBinaryCalculation(this.operand);
			} else {
				// eg. 9, x, 9, =, sqrt, = 
				this.performUnaryCalculation(this.operand);
			}

		} else if (!this.variableA.equals("") && this.variableB.equals("")){
			// eg. 3, sqrt 
			this.performUnaryCalculation(this.operand);
		} else if (this.variableA.equals("") && this.variableB.equals("")){
			// sqrt
			// Do nothing
		}	
		else {
			// shouldn't happen
			throw new RuntimeException("Invalid equals function");
		}					
	}

	/**
	 * Performs the equals function if lastButtonPressed = "Equals" (%, sqrt, 1/x)
	 */
	public void equalsEquals(){
		if (!this.variableA.equals("") && !this.variableB.equals("")){
			
			if (this.operand.equals("add") || this.operand.equals("subtract") 
					|| this.operand.equals("multiply") || this.operand.equals("divide")){
				// eg. 8, x, 9, =
				this.performBinaryCalculation(this.operand);
			} else {
				// eg. 81, sqrt, = 
				this.performUnaryCalculation(this.operand);
			}

		} else if (this.variableA.equals("") && !this.variableB.equals("")){
			// 8, x, 8, =, clear
			this.variableA = this.variableB;
			if (this.operand.equals("add") || this.operand.equals("subtract") 
					|| this.operand.equals("multiply") || this.operand.equals("divide")){
				// eg. 8, x, 9, =
				this.performBinaryCalculation(this.operand);
			} else {
				// eg. 81, sqrt, = 
				this.performUnaryCalculation(this.operand);
			}
			
		} else if (!this.variableA.equals("") && this.variableB.equals("")){
			if (this.operand.equals("add") || this.operand.equals("subtract") 
					|| this.operand.equals("multiply") || this.operand.equals("divide")){
				// eg. 8, x, 9, =
				this.performBinaryCalculation(this.operand);
			} else if (this.operand.equals("invert") || this.operand.equals("sqrt") 
					|| this.operand.equals("percent")){
				// eg. 256, sqrt, =, 
				this.performUnaryCalculation(this.operand);
			} else {
				// eg. 8, =
				// Do nothing
				
			}
			
		} else if (this.variableA.equals("") && this.variableB.equals("")){
			// 8, =
			// Do nothing	
		} else {
			// Shouldn't happen
			throw new RuntimeException("Invalid equals function");
		}

	}
	
	
	
	/*******
	 * MemoryButton methods: MC, MR, M+, M-
	 * **********
	 */
	
	/**
	 * Clears the memory
	 * @return the current state of this.output
	 */
	public String memClear(){
		this.memory = "0";
		this.memoryUsed = false;
		this.lastButtonPressed = "Memory";
		return this.output;
	}
	

	/**
	 * Returns the value in the memory
	 * @return the current state of this.output
	 */
	public String memRead(){
		this.output = this.memory;
		this.variableA = this.memory;
		this.lastButtonPressed = "Memory";
		return this.output;
	}
	
	/**
	 * Adds whatever has been input to the number in memory, and stores it.
	 * @return the current state of this.output
	 */
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
	
	/**
	 * Subtracts whatever has been input from the number in memory, and stores it.
	 * @return the current state of this.output
	 */

	public String memMinus(){
		this.memoryUsed = true;

		if (this.lastButtonPressed.equals("Equals")){
			this.memory = this.subtract(this.memory,this.variableB);	// Equals stores results in variableB	
		} else {
			if (this.variableA.equals("")){this.variableA = "0";}
			this.memory = this.subtract(this.memory,this.variableA);
			this.variableA = "";
		}
		this.lastButtonPressed = "Memory";
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
		if (digit >= 0 && digit <= 9){
			if (digit == 0 && this.output.equals("0")){	
				this.lastButtonPressed = "Number";
				this.variableA = "0";
				return this.output; // doesn't do anything
			} else if (this.output.equals("0")){
				// only update output and variable if the digit pressed is not zero and the this.output isn't "0"
				String stringDigit = Integer.toString(digit);
				this.variableA = stringDigit;
				this.output = stringDigit;
				this.lastButtonPressed = "Number";
				return this.output;				
			} else {
				String stringDigit = Integer.toString(digit);
				variableA = variableA + stringDigit;
				this.output = this.output + stringDigit;
				this.lastButtonPressed = "Number";
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
	 * Trims result to number of digits set in constant MAX_DIGITS
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
}
