package com.abc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class customerCRUD {
	
	public boolean addcustomer(customer c) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.h2.Driver");
		Connection con=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/project", "sa", "SA");
		PreparedStatement ps=con.prepareStatement("insert into customer values(?,?,?,?,?)");
		ps.setString(1,c.getFname());
		ps.setString(2,c.getLname());
		ps.setString(3,c.getDname());
		ps.setString(4,c.getEmailid());
		ps.setString(5,c.getuser_Password());
		if(ps.executeUpdate()!=0)
		  return true;
		else
			return false;
	}
   public ArrayList<customer>SelectAllCustomer() throws ClassNotFoundException, SQLException{
	   Class.forName("org.h2.Driver");
	   Connection cn=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/project","sa","SA");
	   Statement s=cn.createStatement();
	   ResultSet rs=s.executeQuery("Select * from Customer");
	   ArrayList<customer> latestlist=new ArrayList<>();
	   while(rs.next())
	   {
		   customer c=new customer();
		   c.setFname(rs.getString(1));
		   c.setLname(rs.getString(2));
		   c.setDname(rs.getString(3));
		   c.setEmailid(rs.getString(4));
		   latestlist.add(c);
	   }
	  return latestlist; 
	   
   }
   
}
