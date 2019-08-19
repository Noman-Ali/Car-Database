package carDatabase;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddEntry extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField IDtextField;
	private JTextField nameTextField;
	private JTextField streetTextField;
	private JTextField dateTextField;
	private JTextField costTextField;
	private JTextField commentsTextField;
	
	private driver drive;
	private CarDatabase carDatabase;

	private entry oldEntry = null;
	private boolean updateMode = false;
	
	public AddEntry(CarDatabase cd, driver theDrive, entry theOldEntry, boolean theUpdateMode)
	{
	this();	
	
	carDatabase = cd;
	drive = theDrive;
	
	oldEntry = theOldEntry;
	updateMode = theUpdateMode;
	
	if(updateMode)
	{
		setTitle("Update Entry");
		
		populateGui(oldEntry);
	}
	}
	private void populateGui(entry theEntry)
	{
		IDtextField.setText(Integer.toString(theEntry.getId()));
		nameTextField.setText(theEntry.getName());
		streetTextField.setText(theEntry.getStreet());
		dateTextField.setText(theEntry.getDate());
		costTextField.setText(theEntry.getCost());
		commentsTextField.setText(theEntry.getComment());
		
	}
	
	public AddEntry(CarDatabase cd, drive theDrive) {
		this(cd, theDrive, null, false);
	}

	/**
	 * Create the dialog.
	 */
	public AddEntry() {
		setTitle("Add Entry");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblId = new JLabel("ID");
			contentPanel.add(lblId, "2, 2, right, default");
		}
		{
			IDtextField = new JTextField();
			contentPanel.add(IDtextField, "4, 2, fill, default");
			IDtextField.setColumns(10);
		}
		{
			JLabel lblName = new JLabel("Name");
			contentPanel.add(lblName, "2, 4, right, default");
		}
		{
			nameTextField = new JTextField();
			contentPanel.add(nameTextField, "4, 4, fill, default");
			nameTextField.setColumns(10);
		}
		{
			JLabel lblStreet = new JLabel("Street");
			contentPanel.add(lblStreet, "2, 6, right, default");
		}
		{
			streetTextField = new JTextField();
			contentPanel.add(streetTextField, "4, 6, fill, default");
			streetTextField.setColumns(10);
		}
		{
			JLabel lblDate = new JLabel("Date");
			contentPanel.add(lblDate, "2, 8, right, default");
		}
		{
			dateTextField = new JTextField();
			contentPanel.add(dateTextField, "4, 8, fill, default");
			dateTextField.setColumns(10);
		}
		{
			JLabel lblCost = new JLabel("Cost");
			contentPanel.add(lblCost, "2, 10, right, default");
		}
		{
			costTextField = new JTextField();
			contentPanel.add(costTextField, "4, 10, fill, default");
			costTextField.setColumns(10);
		}
		{
			JLabel lblComments = new JLabel("Comments");
			contentPanel.add(lblComments, "2, 12, right, default");
		}
		{
			commentsTextField = new JTextField();
			contentPanel.add(commentsTextField, "4, 12, fill, default");
			commentsTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						saveEntry();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
	}
	public void saveEntry()
	{
		String id = IDtextField.getText();
		String name = nameTextField.getText();
		String street = streetTextField.getText();
		String date = dateTextField.getText();
		String cost = costTextField.getText();
		String comment = commentsTextField.getText();
		int newID = Integer.parseInt(id);
		entry tempEntry = new entry(newID, name, street, date, cost, comment);
		
		if(updateMode) {
			tempEntry = oldEntry;
			tempEntry.setId(newID);
			tempEntry.setName(name);
			tempEntry.setStreet(street);
			tempEntry.setDate(date);
			tempEntry.setCost(cost);
			tempEntry.setComment(comment);
		}
		else
		{
			tempEntry = new entry(newID,name,street,date,cost,comment);
		}
		try {
			if(updateMode)
			{
				drive.updateEntry(tempEntry);
			}
			else
			{
				drive.addEntry(tempEntry);
			}
			setVisible(false);
			dispose();
			
			carDatabase.refreshView();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
