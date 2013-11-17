package calculator;

import java.awt.Color;
import java.awt.event.ActionEvent;

public class OperationsButton extends CalculatorButton{

	public OperationsButton(String text, CalculatorChip calculatorChip) {
		super(text, calculatorChip);

		this.setBackground(Color.MAGENTA);
		this.setForeground(Color.BLACK);
	}

	@Override
	public String onClick(ActionEvent e) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
