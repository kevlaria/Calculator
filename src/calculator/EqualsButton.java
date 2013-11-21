package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class EqualsButton extends CalculatorButton{

	public EqualsButton(String text, CalculatorChip calculatorChip) {
		super(text, calculatorChip);

		this.setBackground(Color.MAGENTA);
		this.setForeground(Color.BLACK);
		
		this.setPreferredSize(new Dimension(60, 120));

	}

	@Override
	public String onClick(ActionEvent e) {
		String output = chip.equals();
		return output;
	}
	
}
