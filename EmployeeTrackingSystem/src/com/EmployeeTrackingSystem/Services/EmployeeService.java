package com.EmployeeTrackingSystem.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.EmployeeTrackingSystem.Models.EmployeeModel;
import com.EmployeeTrackingSystem.Utilities.DbConnection;

public class EmployeeService 
{
	Connection 						con	=	DbConnection.getConnection();
	PreparedStatement	p;
	
	//.............Select All....................//
	public ArrayList<EmployeeModel> SelectAll()
	{
		ArrayList<EmployeeModel> 	arr	=	new ArrayList<EmployeeModel>();
		EmployeeModel				em;
		try
		{
			p							=	con.prepareStatement("SELECT e.*,d.d_name,r.r_name FROM employee e INNER JOIN " +
											" designations d ON e.d_id = d.d_id INNER JOIN roles r ON e.r_id = r.r_id");
			ResultSet	rs				=	p.executeQuery();
			while(rs.next())
			{
				em						=	new EmployeeModel();
				em.setEmp_id(rs.getInt(1));
				em.setName(rs.getString(2));
				em.setGender(rs.getString(3));
				em.setDob(rs.getString(4));
				em.setAddrs(rs.getString(5));
				em.setQlfn(rs.getString(6));
				em.setD_id(rs.getInt(7));
				em.setR_id(rs.getInt(8));
				em.setEmail(rs.getString(9));
				em.setMobile(rs.getLong(10));
				em.setReported_to(rs.getInt(11));
				em.setPhoto(rs.getString(12));
				em.setD_name(rs.getString(13));
				em.setR_name(rs.getString(14));
				arr.add(em);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return	arr;
	}
	
	//....................Save Employee..................//
	public int SaveEmployee(EmployeeModel em)
	{
		int 					a	=	0;
		try
		{
			p						=	con.prepareStatement("INSERT INTO employee(emp_name,gender,dob,address,qualification,d_id,r_id,email,mobile,reported_to,photo) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			p.setString(1,em.getName());
			p.setString(2,em.getGender());
			p.setString(3,em.getDob() );
			p.setString(4,em.getAddrs());
			p.setString(5,em.getQlfn() );
			p.setInt(6, em.getD_id());
			p.setInt(7, em.getR_id());
			p.setString(8,em.getEmail() );
			p.setLong(9, em.getMobile());
			p.setInt(10, em.getReported_to());
			p.setString(11,em.getPhoto() );
			a						=	p.executeUpdate();
			if(a > 0)
			{
				int reg_id			=	0;
				ResultSet rs		=	p.getGeneratedKeys();
				if(rs.next())
				{
					reg_id			=	rs.getInt(1);
				}
				p					=	con.prepareStatement("INSERT INTO login (emp_id,username,password,type,status) VALUES(?,?,?,?,?)");
				p.setInt(1, reg_id);
				p.setString(2, em.getEmail());
				p.setString(3, em.getPassword());
				p.setInt(4, em.getR_id());
				p.setString(5,"ACTIVE");
				a					=	p.executeUpdate();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return a;
	}
}
