package carDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class driver {
	private Connection myCon;
	
	public driver() throws Exception
	{
		try {
		myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/mycardatabase","root","1234");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<entry> getAllEntries()throws Exception{
		List<entry> list = new ArrayList<>();
		
		Statement myStatemt = null;
		ResultSet myRs = null;
		
		try {
			myStatemt = myCon.createStatement();
			myRs = myStatemt.executeQuery("select * from mycardatabase.maintenanceandrepairs");
			while(myRs.next()) 
			{
				entry tempEntry = convertRowToEntry(myRs);
				list.add(tempEntry);
			}
		}finally
		{
			close(myStatemt, myRs);
		}
		return list;
	
	}
	public List<entry> searchEntries(String name) throws Exception
	{
		List<entry> list = new ArrayList<>();
		PreparedStatement myStatemt = null;
		ResultSet myRs = null;
		
		try
		{
			name+="%";
			myStatemt = myCon.prepareStatement("select * from mycardatabase.maintenanceandrepairs where Name like ?");
			
			myStatemt.setString(1, name);
			
			myRs = myStatemt.executeQuery();
			
			while(myRs.next())
			{
				entry tempEntry = convertRowToEntry(myRs);
				list.add(tempEntry);
			}
		}finally
		{
			close(myStatemt, myRs);
		}
		
		return list;
		
		
	}
	
	public void addEntry(entry theEntry) throws Exception
	{
		PreparedStatement myStatemt = null;
		ResultSet myRs = null;
		try
		{
			myStatemt = myCon.prepareStatement("insert into maintenanceandrepairs"+" (i``dmaintenanceandrepairs,name,street,date,cost,comments)"+" values (?,?,?,?,?,?)");
			myStatemt.setInt(1, theEntry.getId());
			myStatemt.setString(2, theEntry.getName());
			myStatemt.setString(3, theEntry.getStreet());
			myStatemt.setString(4, theEntry.getDate());
			myStatemt.setString(5, theEntry.getCost());
			myStatemt.setString(6, theEntry.getComment());
			
			myStatemt.executeUpdate();
		}finally
		{
			close(myStatemt, myRs);
		}
	}
	
	public void updateEntry(entry theEntry) throws SQLException
	{
		PreparedStatement myStatemt = null;
		ResultSet myRs = null;
		try
		{
			myStatemt = myCon.prepareStatement("update maintenanceandrepairs"+" set idmaintenanceandrepairs=?, name=?, street=?, date=?, cost=?, comments=?");
			myStatemt.setInt(1, theEntry.getId());
			myStatemt.setString(2, theEntry.getName());
			myStatemt.setString(3, theEntry.getStreet());
			myStatemt.setString(4, theEntry.getDate());
			myStatemt.setString(5, theEntry.getCost());
			myStatemt.setString(6, theEntry.getComment());
			
			myStatemt.executeUpdate();
		}finally {
			close(myStatemt,myRs);
		}
	}
	
	public void deleteEntry(String s)throws Exception
	{
		PreparedStatement myStatemt = null;
		ResultSet myRs = null;
		try
		{
			myStatemt = myCon.prepareStatement("delete from maintenanceandrepairs where Name like ?");
			myStatemt.setString(1, s);
			myStatemt.executeUpdate();
		}finally {
			close(myStatemt,myRs);
		}
	}
	
	private entry convertRowToEntry(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("idmaintenanceandrepairs");
		String name = myRs.getString("Name");
		String street = myRs.getString("street");
		String date = myRs.getString("date");
		String cost = myRs.getString("cost");
		String comment = myRs.getString("comments");
		
		entry tempEntry = new entry(id, name, street, date, cost, comment);
		
		return tempEntry;
	}

	private static void close(Connection myCon, Statement myStatemt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStatemt != null) {
			
		}
		
		if (myCon != null) {
			myCon.close();
		}
	}

	private void close(Statement myStatemt, ResultSet myRs) throws SQLException {
		close(null, myStatemt, myRs);		
	}

	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
			
		driver drive = new driver();
		//Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/mycardatabase","root","1234");
		System.out.println(drive.searchEntries("Jane"));
		//System.out.println(drive.getAllEntries());
		
		
		/*
		try 
			{
				//Connects to database
				Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/mycardatabase","root","1234");
				//Creates statement
				Statement myStatemt = myCon.createStatement();
				//Executes SQL query
				ResultSet myResultSet = myStatemt.executeQuery("select * from maintenanceandrepairs");
				//Prints the results
				while(myResultSet.next())
				{
					System.out.println(myResultSet.getString("Name"));
				}
				//Adding an element into the table
				String add = "insert into maintenanceandrepairs "
						+ " (idmaintenanceandrepairs, Name, date, cost, comments)"
						+ " values(13, 'Bobby', '03-12-2018', '14', 'car wash')";
				
				myStatemt.executeUpdate(add);
				System.out.println("Inserted data");
				//Updates an element in the table
				String sql = "update maintenanceandrepairs "
						+ "set cost=19 "
						+ "where Name='Bobby'";
				
				myStatemt.executeUpdate(sql);
				String del = "delete from maintenanceandrepairs where Name='Bobby'";
				myStatemt.executeUpdate(del);
				
				
				
			}catch(Exception e)
			{
					e.printStackTrace();
			}
			*/
			
			
		}
}


