package NewGUI.Panels;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class ControlPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	// Variables declaration 
	private  JButton bFindPath;
	private  JButton bStop;
	private  JButton bStep;
	private  JButton bClearPath;
	// End of variables declaration

	/**
	 * Constructor
	 */
	public ControlPanel() {
		super();
		initComponents();

	}

	/**
	 * initialize all the swing Components
	 */
	private void initComponents() {
		this.setBorder(BorderFactory.createTitledBorder("Control"));
		bFindPath = new JButton("Find Path");
		bStop = new JButton("Stop");
		bStep = new JButton("Step");
		bClearPath = new JButton("Clear Path");
		this.add(bFindPath);
		this.add(bStop);
		this.add(bStep);
		this.add(bClearPath);
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				   layout.createSequentialGroup()
				      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				    		  .addComponent(bFindPath)
						      .addComponent(bStop)
						      .addComponent(bStep)
						      .addComponent(bClearPath))
				);
				layout.setVerticalGroup(
				   layout.createSequentialGroup()
				      .addComponent(bFindPath)
				      .addComponent(bStop)
				      .addComponent(bStep)
				      .addComponent(bClearPath)
				);
	}
	
}