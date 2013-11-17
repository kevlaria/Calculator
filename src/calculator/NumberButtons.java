package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class NumberButtons extends CalculatorButton{


	public NumberButtons(String text, CalculatorChip calculatorChip) {
		super(text, calculatorChip);
		this.setBackground(Color.BLUE);
		this.setForeground(Color.BLACK);
	}
	
	@Override
	public String onClick(ActionEvent e) {
		String command = e.getActionCommand();
		System.out.println(command);
				
		return command;
	}
	
	
	

	
	
}
