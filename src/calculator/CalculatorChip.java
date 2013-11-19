package calculator;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorChip {

	private String output;
	private String variableA;
	private String variableB;
	private String sign;
	private Calculator calculator;
	private boolean allClear;
	
	public CalculatorChip(){
		this.output = "0";
		this.variableA = "";
		this.variableB = "";
		this.allClear = true;
	}
	
	public String clear(){
		this.output = "0";
		this.variableA = "";
		this.variableB = "";
		this.allClear = true;
		return output;
	}
	
	
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
		if (digit >= 0 && digit <= 9){
			if (digit == 0 && this.output.equals("0")){		
				return this.output; // doesn't do anything
			} else if (this.output.equals("0")){
				// only update output and variable if the digit pressed is not zero and the this.output isn't "0"
				String stringDigit = Integer.toString(digit);
				variableA = stringDigit;
				this.output = stringDigit;
				return this.output;				
			} else {
				String stringDigit = Integer.toString(digit);
				variableA = variableA + stringDigit;
				this.output = this.output + stringDigit;
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
		if (!this.output.contains(".")){
			this.output = this.output + ".";
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
		this.output = this.trimInput(this.output);
		if (this.output.equals("0")){
			return this.output; // don't do anything
		} else {
			int signIndex = this.output.indexOf('-');
			if(signIndex > -1){  // it's a negative number
				this.output = this.output.substring(1);
				return this.output;
			} else {
				this.output = "-" + this.output;
				return this.output;
			}			
		}
	}
	
	/**
	 * Removes trailing 0's and decimal points (eg 4., 4.00)
	 * @param input
	 * @return a string of numbers without trailing decimals and 0's
	 */
	public String trimInput(String input){
		int stringLength = input.length();
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
	 * Getter and setter methods
	 * **********
	 */
	public String getOutput(){
		return this.output;
	}
	
	public String getVariableA(){
		return this.variableA;
	}
	
	public boolean getAllClear(){
		return this.allClear;
	}
	
	/**
	 * Setter ONLY FOR UNIT TEST
	 */
	public String setOutput(String text){
		this.output = text;
		return this.output;
	}
	
	
}
