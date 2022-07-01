import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class AirportPageFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Airport airport;
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JPanel panel4 = new JPanel();
	private JPanel panel5 = new JPanel(new BorderLayout());
	private JButton btn = new JButton("Find Flights");
	private JButton btn1 = new JButton("Back to Search Screen");
	private JTextField text;
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private JTextField text4 = new JTextField(20);
	private JList<String> companies = new JList<String>();
	private JTextArea area5 = new JTextArea(20,40);
	private JTextArea area6 = new JTextArea(20,40);
	
	public AirportPageFrame(Airport anAirport) {
	
        this.airport = anAirport;
		
		text = new JTextField(this.airport.getName(), 20);
		text1 = new JTextField(this.airport.getCodename(), 20);
		text2 = new JTextField(this.airport.getCity(), 20);
		text3 = new JTextField(this.airport.getCountry(), 20);
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(String st: CentralRegistry.RemoveAndOrder(this.airport.getCompanies()))
			
			listModel.addElement(st);
		
		companies.setModel(listModel);	
		
		panel1.add(text);
		panel1.add(text1);
		panel1.add(text2);
		panel1.add(text3);
		panel1.add(companies);
		
		this.add(panel1, BorderLayout.PAGE_START);
		
		panel2.add(text4);
		panel2.add(btn);
		
		ButtonListener listener = new ButtonListener();
		btn.addActionListener(listener);
		
		panel3.add(area5);
		panel3.add(area6);
		
		panel4.add(btn1);

		
		panel5.add(panel2 , BorderLayout.PAGE_START);
		panel5.add(panel3, BorderLayout.CENTER);
		panel5.add(panel4, BorderLayout.PAGE_END);
		
		this.add(panel5, BorderLayout.CENTER);
		

		ButtonListener listener1 = new ButtonListener();
		btn1.addActionListener(listener1);
		
		this.setVisible(true);
		this.setSize(1000, 500);
		this.setResizable(false);
		this.setTitle("Airport Page");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {	
			
			if(e.getSource().equals(btn)){
				if(text2.getText().equals(text4.getText())) {
				    JPanel error = new JPanel();
				    JOptionPane.showMessageDialog(error, "Arrival and departure city cannot be the same");
				}
				else {
					ArrayList <String> details = new ArrayList<>();
					
					details = CentralRegistry.getDirectFlightsDetails(CentralRegistry.getAirport(text2.getText()), 
							CentralRegistry.getAirport(text4.getText()));
					
					area5.append("DIRECT FLIGHTS DETAILS \n");
					
					if(details != null)
						for(String det : details)
							area5.append(det + "\n");
					
					
					ArrayList <String> indirectDetails = new ArrayList<String>();
					
					indirectDetails = CentralRegistry.getInDirectFlightsDetails(CentralRegistry.getAirport(text2.getText()), 
							CentralRegistry.getAirport(text4.getText()));
					
					area6.append("INDIRECT FLIGHTS through... \n");
					
					if(indirectDetails != null)
						for(String det : indirectDetails)
							area6.append(det + "\n");

					// the below try and catch block writes to a text file
					try {
						
						String destination = text2.getText() + "To" + text4.getText() + ".txt"; 
						FileWriter writer = new FileWriter(destination);
						
						writer.write("CITY: " + text2.getText() + ", " + text3.getText() + System.lineSeparator() 
						+ "Airport: " + text.getText() + " (" + text1.getText() + ")" + System.lineSeparator() + 
						System.lineSeparator() + "DESTINATION: " + text4.getText() + System.lineSeparator() + 
						System.lineSeparator() + "DIRECT FLIGHTS DETAILS: " + System.lineSeparator());
						
						for(String det : details)
							writer.write(det + System.lineSeparator());
							
						writer.write("\n" + "INDIRECT FLIGHTS through... \n");
						
						if(indirectDetails != null)
							for(String det : indirectDetails)
								writer.write(det + System.lineSeparator());
						
						writer.close();
					}
					catch(IOException exc) {
						
						System.out.println("Error occured");
						
					}
				}
			}
			else if(e.getSource().equals(btn1))
				new FindAirportFrame();
		}	
	}
}