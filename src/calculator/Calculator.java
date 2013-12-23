package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;


public class Calculator extends JFrame{

	private JFrame frame;
	private JTextField display;
	private CalculatorChip chip;
	private String displayText; // The text that's to be displayed on the display
	private ClearButton clearButton;
	private MemoryButtons memReadButton; 
	
	private Color orange = new Color(255,225,147);
	private Color red = new Color(245,116,62);
	private Color cyan = new Color(222,252,255);

	
	public Calculator(){
		this.chip = new CalculatorChip();
		this.displayText = "";
	}
	
	public static void main(String[] args){

		new Calculator().createGui();
	}	
	
	public void createGui(){
		
		frame = createJFrame("Calculator"); // Frame for everything	
		JPanel outputPanel = this.createDisplayPanel(); 	// The display
		frame.add(outputPanel, BorderLayout.NORTH);
		JPanel mainBody = this.createMainBodyPanel(); // Container for all the buttons
		frame.add(mainBody, BorderLayout.CENTER);	
		frame.setVisible(true);
	}
	
	
	/**
	 * Creates the frame of the calculator
	 * @param title
	 * @return the frame of the calculator
	 */
	public JFrame createJFrame(String title){
		frame = new JFrame(title);
		frame.setLayout(new BorderLayout());
		frame.setMinimumSize(new Dimension(253,375));
		frame.setResizable(false);	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;		
	}
	
	/**
	 * Creates the display panel, including the textField (where the output is displayed)
	 * @return the display panel
	 */
	public JPanel createDisplayPanel(){
		JPanel panel = this.createBorderLayoutPanel();
		
		display = new JTextField(9);
		display.setFont(new Font("San Serif", Font.BOLD, 30));
		displayText = chip.getOutput();   // The text that's to be displayed on the display
		display.setText(this.displayText);
		display.setEditable(false);
		display.setBackground(cyan);
		display.setHorizontalAlignment(JTextField.TRAILING);
		display.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		panel.add(display);
		
		return panel;
	}
	
	/**
	 * Creates the main body panel - ie everything except for the output display
	 * @return returns the main body panel
	 */
	public JPanel createMainBodyPanel(){
		JPanel panel = this.createBorderLayoutPanel();
		JPanel clearButtonPanel = this.createClearButtonPanel(); // Just the clear button
		panel.add(clearButtonPanel, BorderLayout.NORTH);
		
		JPanel mainButtonsPanel = this.createMainButtonsPanel(); // all buttons other than clear
		panel.add(mainButtonsPanel, BorderLayout.CENTER);
		return panel;
	}
	
	/**
	 * Create panel for just the clear button
	 * @return Panel for just the clear button
	 */
	public JPanel createClearButtonPanel(){
		JPanel panel = this.createBorderLayoutPanel();
		clearButton = this.createClearButtons("AC");
		panel.add(clearButton, BorderLayout.EAST);

		return panel;
	}
	
	
	/**
	 * Creates panel for all buttons other than the clear button
	 * @return panel with all buttons other than the clear button
	 */
	public JPanel createMainButtonsPanel(){
		JPanel panel = this.createBorderLayoutPanel();
		JPanel operationsButtonsPanel2 = this.createOperationButtonPanelSQRT(); // for buttons "sqrt", "%", "1/x" and "="
		panel.add(operationsButtonsPanel2, BorderLayout.LINE_END);

		JPanel otherButtonsPanel = this.createOtherButtonsGridPanel(); 

		panel.add(otherButtonsPanel, BorderLayout.CENTER);
		return panel;
	}
	

	/**
	 * Creates panel for buttons "sqrt", "%", "1/x" and "="
	 * @return Panel for buttons "sqrt", "%", "1/x" and "="
	 */
	public JPanel createOperationButtonPanelSQRT(){
		JPanel panel = this.createBorderLayoutPanel();
 		panel.setPreferredSize(new Dimension(50, 250));
		EqualsButton equals = this.createEqualsButtons("=");
		panel.add(equals, BorderLayout.CENTER);
		
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(3,1,1,1));
		
		panel.add(subPanel, BorderLayout.NORTH);
		OperationsButton sqrt = this.createOperationButtons("Ã");
		OperationsButton percent = this.createOperationButtons("%");
		OperationsButton reciprocal = this.createOperationButtons("1/x");
		subPanel.add(sqrt, BorderLayout.NORTH);
		subPanel.add(percent, BorderLayout.CENTER);
		subPanel.add(reciprocal, BorderLayout.SOUTH);
		return panel;
		
	}
	
	
	/**
	 * Creates the panel for all the rest of the buttons
	 * @return panel with all the rest of the buttons
	 */
	public JPanel createOtherButtonsGridPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5,4,1,1));
		
		MemoryButtons mc = this.createMemoryButtons("MC");
		memReadButton = this.createMemoryButtons("MR"); // special as may change colour depending on state of chip.memoryUsed
		MemoryButtons mPlus = this.createMemoryButtons("M+");
		MemoryButtons mMinus = this.createMemoryButtons("M-");
		
		OperationsButton divide = this.createOperationButtons("Ö");
		OperationsButton multiply = this.createOperationButtons("x");
		OperationsButton minus = this.createOperationButtons("-");
		OperationsButton plus = this.createOperationButtons("+");
		
		NumberButtons seven = createNumberButtons("7");
		NumberButtons eight = createNumberButtons("8");
		NumberButtons nine = createNumberButtons("9");
		NumberButtons four = createNumberButtons("4");
		NumberButtons five = createNumberButtons("5");
		NumberButtons six = createNumberButtons("6");
		NumberButtons one = createNumberButtons("1");
		NumberButtons two = createNumberButtons("2");
		NumberButtons three = createNumberButtons("3");
		NumberButtons zero = createNumberButtons("0");
		NumberButtons decimal = createNumberButtons(".");
		OperationsButton plusMinus = createOperationButtons("+/-");
		
		panel.add(mc);
		panel.add(memReadButton);
		panel.add(mPlus);
		panel.add(mMinus);

		panel.add(seven);
		panel.add(eight);
		panel.add(nine);
		panel.add(divide);

		panel.add(four);
		panel.add(five);
		panel.add(six);
		panel.add(multiply);

		panel.add(one);
		panel.add(two);
		panel.add(three);
		panel.add(minus);

		panel.add(zero);
		panel.add(decimal);
		panel.add(plusMinus);
		panel.add(plus);
	
		return panel;
	}
	
	/*******
	 * Button creation methods
	 * **********
	 */
	
	/**
	 * Method to create NumberButtons (with ActionEvents)
	 * @param text
	 * @return NumberButtons
	 */
	public NumberButtons createNumberButtons(String text){
		NumberButtons button = new NumberButtons(text, chip);
		button.addActionListener(new NumberButtonListener(button));
		return button;
	}
	
	/**
	 * Method to create OperationButtons (with ActionEvents)
	 * @param text
	 * @return OperationButtons
	 */
	public OperationsButton createOperationButtons(String text){
		OperationsButton button = new OperationsButton(text, chip);
		button.addActionListener(new OperationButtonListener(button));
		return button;
	}
	
	
	/**
	 * Method to create EqualsButton (with ActionEvents)
	 * @param text
	 * @return EqualsButton
	 */
	public EqualsButton createEqualsButtons(String text){
		EqualsButton button = new EqualsButton(text, chip);
		button.addActionListener(new OperationButtonListener(button));
		return button;
	}
	
	/**
	 * Method to create ClearButton (with ActionEvents)
	 * @param text
	 * @return ClearButton
	 */
	public ClearButton createClearButtons(String text){
		ClearButton button = new ClearButton(text, chip);
		button.addActionListener(new OperationButtonListener(button));
		return button;
	}
	
	/**
	 * Method to create MemoryButton (with ActionEvents)
	 * @param text
	 * @return MemoryButton
	 */
	public MemoryButtons createMemoryButtons(String text){
		MemoryButtons button = new MemoryButtons(text, chip);
		button.addActionListener(new OperationButtonListener(button));
		return button;
	}

	/*******
	 * Button alteration methods
	 * **********
	 */
		
	/**
	 * Sets the text on the clear button
	 */
	public void setClearButtonText(){
		if(chip.getAllClear()){
			clearButton.setText("AC");
		} else {
			clearButton.setText("C");
		}
	}
	
	/**
	 * Sets the colour on the MemRead button
	 */
	public void setMemReadButtonColour(){
		if(chip.getMemoryState()){
			memReadButton.setNewBackground(red);
		} else {
			memReadButton.setNewBackground(orange);
		}
	}

	/*******
	 * Panel creation methods
	 * **********
	 */
	
	/**
	 * Creates borderLayout Panels
	 * @return a border layout panel
	 */
	public JPanel createBorderLayoutPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		return panel;
	}
	
	
	
	/*******
	 * ActionListener methods
	 * **********
	 */
	
	class NumberButtonListener implements ActionListener{	
		CalculatorButton button;		
		public NumberButtonListener(CalculatorButton button){
			this.button = button;
		}		
		@Override
		public void actionPerformed(ActionEvent e){
			displayText = button.onClick(e);
			display.setText(displayText);
			setClearButtonText();
			setMemReadButtonColour();
		}
	}
	
	class OperationButtonListener implements ActionListener{	
		CalculatorButton button;		
		public OperationButtonListener(CalculatorButton button){
			this.button = button;
		}		
		@Override
		public void actionPerformed(ActionEvent e){
			displayText = button.onClick(e);
			display.setText(displayText);
			setClearButtonText();
			setMemReadButtonColour();
		}
	}
	
}