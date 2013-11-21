package calculator;

import java.awt.Color;
import java.awt.event.ActionEvent;

public class ClearButton extends CalculatorButton{

	public ClearButton(String text, CalculatorChip calculatorChip) {
		super(text, calculatorChip);

		this.setBackground(Color.MAGENTA);
		this.setForeground(Color.BLACK);
	}

	@Override
	public String onClick(ActionEvent e) {
		String output = chip.clear();
		return output;
	}
	
}
