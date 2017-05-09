package com.EmployeeTrackingSystem.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EmployeeTrackingSystem.Models.EmployeeModel;
import com.EmployeeTrackingSystem.Services.EmployeeService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Employee.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String encoding			=	"ISO-8859-1";
		EmployeeService 	es	=	new EmployeeService();
		EmployeeModel		em	=	new EmployeeModel();
		MultipartRequest	m 	= 	null;
		String	op				=	"";
		String Path 			=	request.getServletContext().getRealPath("/");
		Path 					=	Path.substring(0,Path.indexOf("."));
		Path					=	Path.replace("\\", "/");
		String BasePath			=	request.getContextPath();
		BasePath 				=	BasePath.replace("/", "");
		String FullPath 		=	Path+BasePath+"/WebContent/Uploads/Employee_Pics/"; 
		FullPath				=	FullPath.replace("www.ets.com", "EmployeeTrackingSystem");
		if(request.getContentType().contains("multipart/form-data"))
		{
			m					=	new MultipartRequest(request,FullPath,5242880,encoding,new DefaultFileRenamePolicy());
			op 					=	m.getParameter("op");
		}else{
			op 					=	request.getParameter("op");
		}
		
		if(op.equals("Save"))
		{
			em.setName(m.getParameter("name"));
			em.setGender(m.getParameter("gender"));
			em.setDob(m.getParameter("dob"));
			em.setAddrs(m.getParameter("addrs"));
			em.setQlfn(m.getParameter("qlfn"));
			em.setD_id(Integer.parseInt(m.getParameter("desig")));
			em.setR_id(Integer.parseInt(m.getParameter("role")));
			em.setEmail(m.getParameter("email"));
			em.setMobile(Long.parseLong(m.getParameter("mobile")));
			try{em.setReported_to(Integer.parseInt(m.getParameter("reported_to")));}
			catch(Exception e){em.setReported_to(0);}
			em.setPhoto(m.getFilesystemName("photo"));
			em.setPassword(m.getParameter("password"));
			int 			a	=	es.SaveEmployee(em);
			if(a > 0)
				response.getWriter().write( "success");
			else
				response.getWriter().write("failed");
			
		}
	}

}
