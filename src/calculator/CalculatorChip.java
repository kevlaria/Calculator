package calculator;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorChip {

	private String output;
	private String variableA;
	private String variableB;
	private Calculator calculator;
	
	public CalculatorChip(){
		output = "";
		variableA = "";
		variableB = "";
	}
	
	public String clear(){
		this.output = "";
		this.variableA = "";
		this.variableB = "";
		return output;
	}
	
	public String digit(int digit){
		String stringDigit = Integer.toString(digit);
		variableA = variableA + stringDigit;
		return stringDigit;
	}
	
	
	
	public String getOutput(){
		return this.output;
	}
	
	
}
