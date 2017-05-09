package com.EmployeeTrackingSystem.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EmployeeTrackingSystem.Models.DesignationModel;
import com.EmployeeTrackingSystem.Services.CommonService;
import com.EmployeeTrackingSystem.Services.DesignationService;
import com.google.gson.Gson;

/**
 * Servlet implementation class DesignationsServlet
 */
@WebServlet("/designation")
public class DesignationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DesignationsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("Designation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		DesignationModel dm 		= 	new DesignationModel();
		DesignationService ds 		=	new DesignationService();
		CommonService cs 			=	new CommonService();
		String op 					=	request.getParameter("op");
		if(op.equals("Save"))
		{
			dm.setD_name(request.getParameter("designation"));
			dm.setStatus(request.getParameter("status"));
			int	a					=	ds.SaveDesig(dm);
			if(a == 1)
				response.getWriter().write("success");
			else
				response.getWriter().write("failed");
		}
		else if(op.equals("Delete"))
		{
				int id 				=	Integer.parseInt(request.getParameter("id"));
				int a  				=	cs.Delete(id,"designations","d_id");
				if(a==1)
					response.getWriter().write("success");
				else
					response.getWriter().write("failed");
		}
		else if(op.equals("ChangeStatus"))
		{
				int id 				=	Integer.parseInt(request.getParameter("id"));
				String status   	= 	request.getParameter("status");
				int a  				=	cs.ChangeStatus(id, status,"designations","d_id");
				if(a==1)
					response.getWriter().write("success");
				else
					response.getWriter().write("failed");
		}
		else if(op.equals("Edit"))
		{
				int id 				=	Integer.parseInt(request.getParameter("id"));
				dm 					=	ds.SelectById(id);
				String json 		= 	new Gson().toJson(dm);
				response.getWriter().write(json);
				
		}
		if(op.equals("Update"))
		{
			dm.setD_name(request.getParameter("designation"));
			dm.setStatus(request.getParameter("status"));
			dm.setD_id(Integer.parseInt(request.getParameter("id")));
			int	a					=	ds.DesignationUpdate(dm);
			if(a == 1)
				response.getWriter().write("success");
			else
				response.getWriter().write("failed");
		}
		
	}

}
