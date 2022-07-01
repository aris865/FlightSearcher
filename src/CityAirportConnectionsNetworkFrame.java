// this class creates a graph that shows the connections between the cities that have airports, using JUNG2 
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.algorithms.layout.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import edu.uci.ics.jung.algorithms.shortestpath.DistanceStatistics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CityAirportConnectionsNetworkFrame {

	private ArrayList<Flight> allFlights = new ArrayList<Flight>(CentralRegistry.getFlights()); // Getting the flights list from CentralRegistry 
																								// and copying them at List allFlights
	public CityAirportConnectionsNetworkFrame() {

		int edgeNr = 0 ,count = 0;

		for(int i = 0; i < allFlights.size(); i++ ) { // removing flights that use the exact same 2 airports from allFlights list
			for(int j = 0; j<allFlights.size(); j++)
				if(allFlights.get(i).equals(allFlights.get(j)))
					count++;
			
			if(count>1)
				allFlights.remove(i);
			count = 0;
		}
		
		Graph<String, Integer> g = new SparseMultigraph<String, Integer>();  // creating a graph object
		
		for(Flight f : allFlights) { // creating vertexes ( cities that airports are located at ) and edges ( connections between airports ) 
			
			g.addVertex(f.getAirportA().getCity());
			g.addVertex(f.getAirportB().getCity());
			
			g.addEdge(edgeNr , f.getAirportA().getCity() , f.getAirportB().getCity());
			
			edgeNr++;
		}
		
		Layout<String, Integer> layout = new CircleLayout<String, Integer>(g); // creating a layout object
		layout.setSize(new Dimension(300,300));
		
		BasicVisualizationServer<String, Integer> vv = new BasicVisualizationServer<String, Integer>(layout); // creating a BasicVisualizationServer to show the graph
		vv.setPreferredSize(new Dimension(350,350));
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
		
		String dia = "Diameter = " + DistanceStatistics.diameter(g); // Getting the diameter of the graph 
		
		JFrame frame = new JFrame("City Airport Connections Network");
		JPanel panel = new JPanel(new BorderLayout());
		JTextField text = new JTextField(dia);
		
		panel.add(vv);
		panel.add(text, BorderLayout.PAGE_END);
		
		frame.setContentPane(panel);
		frame.setSize(400,400);
	    frame.setVisible(true); 
	    frame.setLocationRelativeTo(null);
	    
	}	
}