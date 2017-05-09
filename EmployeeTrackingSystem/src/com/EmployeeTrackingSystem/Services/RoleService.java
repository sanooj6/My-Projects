package com.EmployeeTrackingSystem.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.EmployeeTrackingSystem.Models.RoleModel;
import com.EmployeeTrackingSystem.Utilities.DbConnection;

public class RoleService 
{
	Connection 		con	=	DbConnection.getConnection();
	PreparedStatement	p;
	
	/*...............Save................*/
	public int SaveRole(RoleModel rm)
	{
		int result				=	0;
		try
		{
			p					=	con.prepareStatement("INSERT INTO roles(r_name,status) VALUES(?,?)");
			p.setString(1, rm.getR_name());
			p.setString(2, rm.getStatus());
			result 				=	p.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	/*.................Select All...................*/
	public ArrayList<RoleModel>SelectAll()
	{
		ArrayList<RoleModel> arr 		= 	new ArrayList<RoleModel>();
		RoleModel	rm;
		try
		{
			p 							=	con.prepareStatement("SELECT * FROM roles");
			ResultSet	rs 				=	p.executeQuery();
			while(rs.next())
			{
				rm						=	new RoleModel();
				rm.setR_id(rs.getInt(1));
				rm.setR_name(rs.getString(2));
				rm.setStatus(rs.getString(3));
				arr.add(rm);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return arr;
		
	}
	
	/*...................Select One.................*/
	public RoleModel SelectById(int id) 
	{
		RoleModel 	rm					=	new RoleModel();
		try
		{
			p 							=	con.prepareStatement("SELECT * FROM roles WHERE r_id=?");
			p.setInt(1, id);
			ResultSet	rs 				=	p.executeQuery();
			while(rs.next())
			{
				rm.setR_id(rs.getInt(1));
				rm.setR_name(rs.getString(2));
				rm.setStatus(rs.getString(3));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return rm;
	}
	
	
	/*............Upate Role.............*/
	public int RoleUpdate(RoleModel rm) 
	{
		int  result     =	0;
		try
		{
			p=con.prepareStatement("UPDATE roles SET r_name=?,status=? WHERE r_id = ?");
			p.setString(1, rm.getR_name());
			p.setString(2, rm.getStatus());
			p.setInt(3, rm.getR_id());
			result       =	p.executeUpdate();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
}
