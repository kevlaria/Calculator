/***
 * Container hierarchy (by method name):
 * 
 * createJFrame
 * 		- createOutputPanel
 * 		- createMainBodyPanel
 * 			- createClearButtonPanel
 * 				- CLEAR button
 * 			- createMainButtonsPanel
 * 				- createOperationButtonPanelSQRT
 * 					- SQRT, %, 1/x, = buttons
 * 				- createOtherButtonsPanel
 * 					- createMemoryButtonPanel
 * 						- MC, MR, M+, M- buttons
 * 					- createNumberAndOperationsPanel
 * 						- createOpetaionButtonPanelDIVIDE
 * 							- Ö, x, -, + buttons
 * 						- createNumbersPanel
 * 							- 0-9, '.' and '+/-' buttons
 * 
 */


package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame{

	private JFrame frame;
	private JButton button;
	private JTextField output;
	private CalculatorChip calculatorChip;
	
	public Calculator(){};
	
	public static void main(String[] args){
		new Calculator().createGui();
		CalculatorChip calculatorChip = new CalculatorChip();
	}
	
	
	public void createGui(){
		
		frame = createJFrame("Calculator"); // Frame for everything
		
		JPanel outputPanel = this.createOutputPanel(); 	// The display
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
		frame.setMinimumSize(new Dimension(280,400));
		frame.setResizable(false);	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
		
	}
	
	/**
	 * Creates the display panel (where the output is displayed)
	 * @return the display panel
	 */
	public JPanel createOutputPanel(){
		JPanel panel = this.createBorderLayoutPanel();
		panel.setBorder(BorderFactory.createBevelBorder(4));
		
		output = new JTextField(9);
		output.setFont(new Font("San Serif", Font.BOLD, 30));
		
		panel.add(output);
		
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
		ClearButton clear = this.createClearButtons("AC");
		panel.add(clear, BorderLayout.EAST);
		return panel;
	}
	
	
	/**
	 * Creates panel for all buttons other than the clear button
	 * @return panel with all buttons other than the clear button
	 */
	public JPanel createMainButtonsPanel(){
		JPanel panel = this.createBorderLayoutPanel();
		JPanel operationsButtonsPanel2 = this.createOperationButtonPanelSQRT(); // for buttons "sqrt", "%", "1/x" and "="
		panel.add(operationsButtonsPanel2, BorderLayout.EAST);
	
		JPanel otherButtonsPanel = this.createOtherButtonsPanel(); 
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
		
		JPanel subPanel = this.createBorderLayoutPanel(); // Houses the Ã, %, 1/x buttons
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
	 * all buttons other than clear and the right hand operations buttons
	 * @return a panel containing memory buttons, number buttons and + - Ö x
	 */
	public JPanel createOtherButtonsPanel(){
		JPanel panel = this.createBorderLayoutPanel();
		JPanel memoryButtonsPanel = this.createMemoryButtonPanel();
		panel.add(memoryButtonsPanel, BorderLayout.NORTH);
		JPanel otherButtonsPanel = this.createNumberAndOperationsPanel();
		panel.add(otherButtonsPanel, BorderLayout.CENTER);
		return panel;
	}
	
	/**
	 * Create panel containing the MC, MR, M+ and M- buttons
	 * @return panel containing the MC, MR, M+ and M- buttons
	 */
	public JPanel createMemoryButtonPanel(){
		JPanel panel = this.createFlowLayoutPanel();
		MemoryButtons mc = this.createMemoryButtons("MC");
		MemoryButtons mr = this.createMemoryButtons("MR");
		MemoryButtons mPlus = this.createMemoryButtons("M+");
		MemoryButtons mMinus = this.createMemoryButtons("M-");
		panel.add(mc);
		panel.add(mr);
		panel.add(mPlus);
		panel.add(mMinus);
		return panel;
	}
	
	
	/**
	 * Creates the panel that includes all the numbers and Ö, x, - and +
	 * @return panel with all the numbers and Ö, x, - and +
	 */
	public JPanel createNumberAndOperationsPanel(){
		JPanel panel = this.createBorderLayoutPanel();
		JPanel operationsPanelDivide = this.createOpetaionButtonPanelDIVIDE();
		JPanel numbersPanel = this.createNumbersPanel();
		panel.add(operationsPanelDivide, BorderLayout.EAST);
		panel.add(numbersPanel, BorderLayout.CENTER);
		return panel;
	}
	
	/**
	 * Creates the panel that includes Ö, x, - and +
	 * @return panel with Ö, x, - and +
	 */
	public JPanel createOpetaionButtonPanelDIVIDE(){
		JPanel panel = this.createBorderLayoutPanel();	

		JPanel topPanel = this.createBorderLayoutPanel(); // Ö and x
		OperationsButton divide = this.createOperationButtons("Ö");
		OperationsButton multiply = this.createOperationButtons("x");
		topPanel.add(divide, BorderLayout.NORTH);
		topPanel.add(multiply, BorderLayout.SOUTH);
		panel.add(topPanel, BorderLayout.NORTH);
		
		JPanel bottomPanel = this.createBorderLayoutPanel(); // - and +	
		OperationsButton minus = this.createOperationButtons("-");
		OperationsButton plus = this.createOperationButtons("+");
		
		bottomPanel.add(minus, BorderLayout.NORTH);
		bottomPanel.add(plus, BorderLayout.SOUTH);

		panel.add(bottomPanel, BorderLayout.CENTER);
		
		return panel;
	}
	
	/**
	 * Creates the panel that includes all numbers
	 * @return panel with numbers 0 - 9 + . + +/-
	 */
	public JPanel createNumbersPanel(){
		JPanel numbersPanel = this.createFlowLayoutPanel();
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
		NumberButtons plusMinus = createNumberButtons("+/-");
		numbersPanel.add(seven);
		numbersPanel.add(eight);
		numbersPanel.add(nine);
		numbersPanel.add(four);
		numbersPanel.add(five);
		numbersPanel.add(six);
		numbersPanel.add(one);
		numbersPanel.add(two);
		numbersPanel.add(three);
		numbersPanel.add(zero);
		numbersPanel.add(decimal);
		numbersPanel.add(plusMinus);		
		return numbersPanel;
	}
	
	
	/**
	 * Method to create NumberButtons (with ActionEvents)
	 * @param text
	 * @return NumberButtons
	 */
	public NumberButtons createNumberButtons(String text){
		NumberButtons button = new NumberButtons(text, calculatorChip);
		button.addActionListener(new NumberButtonListener(button));
		return button;
	}
	
	/**
	 * Method to create OperationButtons (with ActionEvents)
	 * @param text
	 * @return OperationButtons
	 */
	public OperationsButton createOperationButtons(String text){
		OperationsButton button = new OperationsButton(text, calculatorChip);
		button.addActionListener(new OperationButtonListener(button));
		return button;
	}
	
	
	/**
	 * Method to create EqualsButton (with ActionEvents)
	 * @param text
	 * @return EqualsButton
	 */
	public EqualsButton createEqualsButtons(String text){
		EqualsButton button = new EqualsButton(text, calculatorChip);
		button.addActionListener(new OperationButtonListener(button));
		return button;
	}
	
	/**
	 * Method to create ClearButton (with ActionEvents)
	 * @param text
	 * @return ClearButton
	 */
	public ClearButton createClearButtons(String text){
		ClearButton button = new ClearButton(text, calculatorChip);
		button.addActionListener(new OperationButtonListener(button));
		return button;
	}
	
	/**
	 * Method to create MemoryButton (with ActionEvents)
	 * @param text
	 * @return MemoryButton
	 */
	public MemoryButtons createMemoryButtons(String text){
		MemoryButtons button = new MemoryButtons(text, calculatorChip);
		button.addActionListener(new OperationButtonListener(button));
		return button;
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
			button.onClick(e);
		}
	}
	
	class OperationButtonListener implements ActionListener{	
		CalculatorButton button;		
		public OperationButtonListener(CalculatorButton button){
			this.button = button;
		}		
		@Override
		public void actionPerformed(ActionEvent e){
			button.onClick(e);
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
	
	
	
	/**
	 * Creates FlowLayout panel
	 * @return a flow layout panel
	 */
	public JPanel createFlowLayoutPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		return panel;
	}
	
}