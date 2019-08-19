package carDatabase;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class tableModel extends AbstractTableModel {
	static final int acol = -1;
	private static final int id = 0;
	private static final int name = 1;
	private static final int street = 2;
	private static final int date = 3;
	private static final int cost = 4;
	private static final int comment = 5;
	
	private String[] columnNames = {"ID","Names","Street","Date","Cost","Comment"};
	
	private List<entry> entry;
	public tableModel(List<entry> theEntry)
	{
		entry = theEntry;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return entry.size();
	}
	
	@Override
	public String getColumnName(int col)
	{
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		entry tempEntry = entry.get(row);
		
		switch(col)
		{
		case id:
			return tempEntry.getId();
		case name:
			return tempEntry.getName();
		case street:
			return tempEntry.getStreet();	
		case date:
			return tempEntry.getDate();
		case cost:
			return tempEntry.getCost();
		case comment:
			return tempEntry.getComment();
		default:
			return tempEntry;
		}
	
	}
	
	/*@Override
	public Class getColumnClass(int c)
	{
		return getValueAt(0, c).getClass();
	}*/
}


