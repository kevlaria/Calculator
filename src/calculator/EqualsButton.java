package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class EqualsButton extends CalculatorButton{

	private Color green = new Color(103,254,118);
	
	public EqualsButton(String text, CalculatorChip calculatorChip) {
		super(text, calculatorChip);

		this.setBackground(green);
		this.setForeground(Color.BLACK);
		
		this.setMaximumSize(new Dimension(10, 20));

	}

	@Override
	public String onClick(ActionEvent e) {
		String output = chip.equals();
		return output;
	}
	
}
