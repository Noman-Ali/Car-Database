package carDatabase;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class delEntry extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField delTtextField;
	private driver drive;
	private CarDatabase carDatabase;
	private String del;

	public delEntry(CarDatabase cd,driver theDriver)
	{
		this();
		drive = theDriver;
		carDatabase = cd;
	}

	/**
	 * Create the dialog.
	 */
	public delEntry() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				JLabel lblEnterName = new JLabel("Enter name:");
				panel.add(lblEnterName);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				delTtextField = new JTextField();
				panel.add(delTtextField);
				delTtextField.setColumns(10);
				
			}
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						try {
							delAction();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
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

	protected void delAction() throws Exception {
		// TODO Auto-generated method stub
		String del = delTtextField.getText();
		
		drive.deleteEntry(del);
		setVisible(false);
		dispose();
		carDatabase.refreshView();
		
	}

}
