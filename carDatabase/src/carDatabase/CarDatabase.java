package carDatabase;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import java.util.List;


public class CarDatabase extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterName;
	private JTable table;
	
	private driver drive;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarDatabase frame = new CarDatabase();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CarDatabase() {
		
		try {
			drive = new driver();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		setTitle("Car Maintenance Log");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		txtEnterName = new JTextField();
		panel.add(txtEnterName);
		txtEnterName.setColumns(16);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					String name = txtEnterName.getText();
					
					List<entry> entry = null;
					
					if(name != null && name.trim().length() > 0)
					{
						entry = drive.searchEntries(name);
					}
					else
					{
						entry = drive.getAllEntries();
					}
					tableModel model = new tableModel(entry);
					table.setModel(model);
					/*for(entry temp : entry)
					{
						System.out.println(temp);
					}*/
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		panel.add(btnSearch);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEntry dialog = new AddEntry(CarDatabase.this,drive,null, false);
				
				dialog.setVisible(true);
			}
		});
		btnAdd.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delEntry dialog = new delEntry(CarDatabase.this,drive);
				dialog.setVisible(true);
			}
		});
		panel_1.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				
				entry tempEntry = (entry) table.getValueAt(row,tableModel.acol);
				AddEntry dialog = new AddEntry(CarDatabase.this,drive,tempEntry,true);
				
				dialog.setVisible(true);
				
			}
		});
		panel_1.add(btnUpdate);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

	public void refreshView() {
		// TODO Auto-generated method stub
		try {
			List<entry> entry = drive.getAllEntries();
			
			tableModel model = new tableModel(entry);
			
			table.setModel(model);
		}
		catch(Exception e)
		{
			
		}
		
	}

}
