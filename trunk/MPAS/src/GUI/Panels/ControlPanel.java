package GUI.Panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import GUI.GUIController;


public class ControlPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	// Variables declaration 
	private  JButton _bFindPath;
	private  JButton _bStop;
	private  JButton _bStep;
	private  JButton _bClearPath;
	private JToggleButton _tbAutoStepMode;
	private JSlider _sAutoStepMode;
	private JLabel _lSec;
	private GUIController _guiController;
	
	// End of variables declaration

	/**
	 * Constructor
	 * @param controller 
	 */
	public ControlPanel(GUIController controller) {
		super();
		this._guiController= controller;
		this.setBorder(BorderFactory.createTitledBorder("Control"));
		_bFindPath = new JButton("Find Path");
		_bStop = new JButton("Stop");
		_bStep = new JButton("Step");
		_tbAutoStepMode = new JToggleButton("Auto");
		_bClearPath = new JButton("Clear Path");
		_sAutoStepMode = new JSlider(1,30,1);
		_sAutoStepMode.setPaintTicks(true);
		_sAutoStepMode.setMajorTickSpacing(5);
	    _lSec = new JLabel(_sAutoStepMode.getValue() * 100 + " msec");
		initComponents();
	}

	/**
	 * initialize all the swing Components
	 */
	private void initComponents() {
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
	            layout.createParallelGroup()
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup()
	                    .addComponent(_bFindPath)
	                    .addComponent(_bStop)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(_bStep)
	                        .addComponent(_tbAutoStepMode)
	                        .addComponent(_sAutoStepMode, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(_lSec))
	                        .addComponent(_bClearPath)))
	    );
		layout.setVerticalGroup(
				layout.createParallelGroup()
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	                    .addComponent(_lSec)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(_bFindPath)
	                        .addComponent(_bStop)
	                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                            .addComponent(_bStep)
	                            .addComponent(_sAutoStepMode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(_tbAutoStepMode))))
	                            .addComponent(_bClearPath))
	    );
		
		_bFindPath.addActionListener(new  ActionListener() {
	    	 public void actionPerformed( ActionEvent evt) {
	    		 _guiController.bFindPathActionPerformed(evt);
	         }
	     });
       _bClearPath.addActionListener(new  ActionListener() {
	    	 public void actionPerformed( ActionEvent evt) {
	    		 _guiController.bClearPathActionPerformed(evt);
	         }
	     });
       
       _bStep.addActionListener(new ActionListener() {			
       		public void actionPerformed(ActionEvent evt) {
       			_guiController.bStepActionPerformed(evt);				
       		}
		});
       _bStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				_guiController.stop();				
			}
		});
       
       _tbAutoStepMode.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {				
				if (!_tbAutoStepMode.isSelected()){
					_guiController.AutoStepActionPerformed();	
				}
			}
		});
       
       _sAutoStepMode.addChangeListener(new ChangeListener() {
			//TODO remove duplicate listeners
			public void stateChanged(ChangeEvent e) {
				JSlider slider = (JSlider) e.getSource();
				int value = slider.getValue();
			    setAutoStepLabel(value *100 + " msec");
			    _guiController.sAutoStepModeActionPerformed();
			}
		});
		
		//added to mainPanel
		/*
		_sAutoStepMode.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				JSlider slider = (JSlider) e.getSource();
				int value = slider.getValue();
			    _lSec.setText(value + " Sec");
			}			
		});
		*/
	    }
	
	public JToggleButton getAutoButton(){
		return this._tbAutoStepMode;
	}


	public JButton getStepButton(){
		return this._bStep;
	}
	
	public int getAutoStepValue(){
		return this._sAutoStepMode.getValue();
	}

	public void setAutoStepLabel(String string) {
		this._lSec.setText(string);
		
	}
}