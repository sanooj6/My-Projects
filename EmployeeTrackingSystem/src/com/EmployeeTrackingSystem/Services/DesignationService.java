package com.EmployeeTrackingSystem.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.EmployeeTrackingSystem.Models.DesignationModel;
import com.EmployeeTrackingSystem.Utilities.DbConnection;

public class DesignationService 
{
	Connection  con     = 	DbConnection.getConnection();
	PreparedStatement p;
	
	/*...............Designation Insert.............*/
	public int SaveDesig(DesignationModel dm)
	{
		int result				=	0;
		try
		{
			p					=	con.prepareStatement("INSERT INTO designations(d_name,status) VALUES(?,?)");
			p.setString(1, dm.getD_name());
			p.setString(2, dm.getStatus());
			result 				=	p.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	/*.................Select All...................*/
	public ArrayList<DesignationModel>SelectAll()
	{
		ArrayList<DesignationModel> arr = new ArrayList<DesignationModel>();
		DesignationModel	dm;
		try
		{
			p 							=	con.prepareStatement("SELECT * FROM designations");
			ResultSet	rs 				=	p.executeQuery();
			while(rs.next())
			{
				dm						=	new DesignationModel();
				dm.setD_id(rs.getInt(1));
				dm.setD_name(rs.getString(2));
				dm.setStatus(rs.getString(3));
				arr.add(dm);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return arr;
		
	}

	public DesignationModel SelectById(int id) 
	{
		DesignationModel 	dm			=	new DesignationModel();
		try
		{
			p 							=	con.prepareStatement("SELECT * FROM designations WHERE d_id=?");
			p.setInt(1, id);
			ResultSet	rs 				=	p.executeQuery();
			while(rs.next())
			{
				dm.setD_id(rs.getInt(1));
				dm.setD_name(rs.getString(2));
				dm.setStatus(rs.getString(3));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dm;
	}
	
	/*............Upate Designation.............*/
	public int DesignationUpdate(DesignationModel dm) 
	{
		int  result     =	0;
		try
		{
			p=con.prepareStatement("UPDATE designations SET d_name=?,status=? WHERE d_id = ?");
			p.setString(1, dm.getD_name());
			p.setString(2, dm.getStatus());
			p.setInt(3, dm.getD_id());
			result       =	p.executeUpdate();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
}
