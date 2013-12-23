package calculator;

import java.awt.Color;
import java.awt.event.ActionEvent;

public class ClearButton extends CalculatorButton{

	private Color red = new Color(245,116,62);
	
	public ClearButton(String text, CalculatorChip calculatorChip) {
		super(text, calculatorChip);

		this.setBackground(red);
		this.setForeground(Color.BLACK);
	}

	@Override
	public String onClick(ActionEvent e) {
		String output = chip.clear();
		return output;
	}
	
}
