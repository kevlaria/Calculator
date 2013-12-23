package calculator;

import java.awt.Color;
import java.awt.event.ActionEvent;

public class OperationsButton extends CalculatorButton{

	private Color green = new Color(103,254,118);
	
	public OperationsButton(String text, CalculatorChip calculatorChip) {
		super(text, calculatorChip);

		this.setBackground(green);
		this.setForeground(Color.BLACK);
	}

	@Override
	public String onClick(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("+")){
			String output = chip.add();
			return output;
		} else if (command.equals("-")){
			String output = chip.subtract();
			return output;
		} else if (command.equals("x")){
			String output = chip.multiply();
			return output;
		} else if (command.equals("Ö")){
			String output = chip.divide();
			return output;
		} else if (command.equals("Ã")){
			String output = chip.sqrt();
			return output;
		} else if (command.equals("1/x")){
			String output = chip.invert();
			return output;
		} else if (command.equals("+/-")){
			String output = chip.changeSign();
			return output;
		} else if (command.equals("%")){
			String output = chip.percent();
			return output;
		}	
		else {
			throw new RuntimeException("Unknown button");
		}
	}
	
}
