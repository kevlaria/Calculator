package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class NumberButtons extends CalculatorButton{


	public NumberButtons(String text, CalculatorChip chip) {
		super(text, chip);
		this.setBackground(Color.GREEN);
		this.setForeground(Color.BLACK);
	}
	
	@Override
	public String onClick(ActionEvent e) {
		String command = e.getActionCommand();
		int digit = Integer.parseInt(command);
		String output = chip.digit(digit);
		return output;
	}
	
	
	

	
	
}
