package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public abstract class CalculatorButton extends JButton {

	protected CalculatorChip chip;
	private Color foreground;
	private Color background;
	
	public CalculatorButton(String text, CalculatorChip calculatorChip){
		this.setText(text);
		this.chip = calculatorChip;
		this.setBackground(background);
		this.setForeground(foreground);
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(50, 50));
		this.setFont(new Font("San Serif", Font.BOLD, 15));
	}
	
   public abstract String onClick(ActionEvent e);
	
}
