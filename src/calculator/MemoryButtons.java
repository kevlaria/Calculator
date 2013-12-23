package calculator;

import java.awt.Color;
import java.awt.event.ActionEvent;

public class MemoryButtons extends CalculatorButton{

	private Color orange = new Color(255,225,147);
	
	public MemoryButtons(String text, CalculatorChip calculatorChip) {
		super(text, calculatorChip);

		this.setBackground(orange);
		this.setForeground(Color.BLACK);
	}
	
	public void setNewBackground(Color colour){
		this.setBackground(colour);
	}
	

	@Override
	public String onClick(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("MC")){
			String output = chip.memClear();
			return output;
		} else if (command.equals("M+")){
			String output = chip.memPlus();
			return output;
		} else if (command.equals("M-")){
			String output = chip.memMinus();
			return output;
		} else if (command.equals("MR")){
			String output = chip.memRead();
			return output;
		} else {
			throw new RuntimeException("Unknown button");
		}
	}
}
