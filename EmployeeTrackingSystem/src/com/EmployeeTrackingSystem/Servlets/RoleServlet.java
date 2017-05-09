package com.EmployeeTrackingSystem.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EmployeeTrackingSystem.Models.RoleModel;
import com.EmployeeTrackingSystem.Services.CommonService;
import com.EmployeeTrackingSystem.Services.RoleService;
import com.google.gson.Gson;

/**
 * Servlet implementation class RoleServlet
 */
@WebServlet("/role")
public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Roles.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RoleModel 			rm		=	new RoleModel();
		RoleService			rs		=	new RoleService();
		CommonService		cs		=	new CommonService();
		String 				op		=	request.getParameter("op");
		
		if(op.equals("Save"))
		{
			rm.setR_name(request.getParameter("role"));
			rm.setStatus(request.getParameter("status"));
			int	a					=	rs.SaveRole(rm);
			if(a == 1)
				response.getWriter().write("success");
			else
				response.getWriter().write("failed");
		}
		else if(op.equals("Delete"))
		{
				int id 				=	Integer.parseInt(request.getParameter("id"));
				int a  				=	cs.Delete(id,"roles","r_id");
				if(a==1)
					response.getWriter().write("success");
				else
					response.getWriter().write("failed");
		}
		else if(op.equals("ChangeStatus"))
		{
				int id 				=	Integer.parseInt(request.getParameter("id"));
				String status   	= 	request.getParameter("status");
				int a  				=	cs.ChangeStatus(id, status,"roles","r_id");
				if(a==1)
					response.getWriter().write("success");
				else
					response.getWriter().write("failed");
		}
		else if(op.equals("Edit"))
		{
				int id 				=	Integer.parseInt(request.getParameter("id"));
				rm 					=	rs.SelectById(id);
				String json 		= 	new Gson().toJson(rm);
				response.getWriter().write(json);
				
		}
		if(op.equals("Update"))
		{
			rm.setR_name(request.getParameter("role"));
			rm.setStatus(request.getParameter("status"));
			rm.setR_id(Integer.parseInt(request.getParameter("id")));
			int	a					=	rs.RoleUpdate(rm);
			if(a == 1)
				response.getWriter().write("success");
			else
				response.getWriter().write("failed");
		}
	}

}
