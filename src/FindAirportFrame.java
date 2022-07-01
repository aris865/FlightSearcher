import java.awt.event.*;
import javax.swing.*;

public class FindAirportFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panel = new JPanel();
	private JTextField text = new JTextField("Please enter CITY name");
	private JButton btn = new JButton("Find");
	private JButton btn1 = new JButton("Visualize Network");
	
	public FindAirportFrame() {
	
		panel.add(text);
		panel.add(btn);
		panel.add(btn1);
		
		this.setContentPane(panel);
		
		ButtonListener listener = new ButtonListener();
		btn.addActionListener(listener);
		
		ButtonListener listener1 = new ButtonListener();
		btn1.addActionListener(listener1);
		
		this.setVisible(true);
		this.setSize(300, 200);
		this.setTitle("Find Airport");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(btn)) {
				if(CentralRegistry.getAirport(text.getText()) != null) 
					new AirportPageFrame(CentralRegistry.getAirport(text.getText()));
				else {
					JPanel error = new JPanel();
					
					JOptionPane.showMessageDialog(error, text.getText() + " does not have an airport");
				}
			}
			else if(e.getSource().equals(btn1))
				new CityAirportConnectionsNetworkFrame();
			
		} 
	}
}