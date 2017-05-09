
<%@page import="com.EmployeeTrackingSystem.Models.EmployeeModel"%>
<%@page import="com.EmployeeTrackingSystem.Services.EmployeeService"%>
<%@page import="com.EmployeeTrackingSystem.Models.RoleModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.EmployeeTrackingSystem.Services.RoleService"%>
<%@page import="com.EmployeeTrackingSystem.Services.DesignationService"%>
<%@page import="com.EmployeeTrackingSystem.Models.DesignationModel"%>
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Menu.jsp"></jsp:include>
           
 <% 
 	DesignationService 				ds 	=	new DesignationService();
 	RoleService 	   				rs 	= 	new RoleService();
 	EmployeeService					es	=	new EmployeeService();
 	
 	ArrayList<DesignationModel>		dar	=	ds.SelectAll();
 	ArrayList<RoleModel>			rar	=	rs.SelectAll();
 	ArrayList<EmployeeModel>		ear	=	es.SelectAll();
 
 %>
 
 
<div id="content">
 <div class="outer">
    <div class="inner bg-light lter" style="min-height: 500px !important;">
                            
<div class="row">
  <div class="col-lg-12" id="addForm" style="display: none;">
    <div class="box dark">
        <header>
            <div class="icons"><i class="fa fa-edit"></i></div>
            <h5>Add New Employee</h5>
            <!-- .toolbar -->
            <div class="toolbar">
              <nav style="padding: 8px;">
                 <a href="javascript:;" class="btn btn-default btn-sm full-box">
                      <i class="fa fa-expand"></i>
                  </a>
                   <a href="javascript:;" class="btn btn-danger btn-xs" id="closeBtn">
                      Close
                  </a>
              </nav>
            </div>            <!-- /.toolbar -->
        </header>
        <div id="div-1" class="body col-lg-12" style="padding-top:50px;">
            <form class="form-horizontal" id="empForm">
				<div class="col-lg-10">
                <div class="form-group">
                    <label for="text1" class="control-label col-lg-4">Name</label>

                    <div class="col-lg-8">
                        <input type="text" id="name" name="name" placeholder="Name" class="form-control validate[required, minSize[3]]">
                    </div>
                    
                </div>
                <div class="form-group">
                    <label for="text1" class="control-label col-lg-4">Gender</label>

                    <div class="col-lg-8">
                       <input type="radio" class="validate[required]" name="gender" id="male" value="Male">Male
                       <input type="radio" class="validate[required]" name="gender" id="female" value="Female">Female
                    </div>
                </div>
                 <div class="form-group">
                    <label for="text1" class="control-label col-lg-4">Date of Birth</label>

                    <div class="col-lg-8">
                      <input type="text" id="dob" name="dob" placeholder="DOB" class="form-control validate[required, minSize[3]]">
                    </div>
                </div>
                 <div class="form-group">
                    <label for="text1" class="control-label col-lg-4">Address</label>

                    <div class="col-lg-8">
                      <textarea type="text" id="addrs" name="addrs" placeholder="" class="form-control validate[required, minSize[5]]"></textarea>
                    </div>
                </div>
                <!-- /.form-group -->
				<div class="form-group">
                    <label for="text1" class="control-label col-lg-4 ">Qualification</label>

                    <div class="col-lg-8">
                        <select id="qlfn" name="qlfn" class="form-control validate[required]">
                            <option value="">--Select--</option>
                        	<option value="MCA">MCA</option>
                        	<option value="B Tech">B Tech</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="text1" class="control-label col-lg-4 ">Designation</label>

                    <div class="col-lg-8">
                        <select id="desig" name="desig" class="form-control validate[required]">
                            <option value="">--Select--</option>
                        	<% 	for(DesignationModel dm:dar) 
	                        	{
	                        		if(dm.getStatus().equals("Active"))
	                        		{
	                        %>
	                        			<option value="<%=dm.getD_id() %>"><%=dm.getD_name() %></option>
	                        <% 		}
	                        	}
                        	%>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="text1" class="control-label col-lg-4 ">Role</label>

                    <div class="col-lg-8">
                        <select id="role" name="role" class="form-control validate[required]">
                            <option value="">--Select--</option>
                        	<% 	for(RoleModel rm:rar) 
	                        	{
	                        		if(rm.getStatus().equals("Active"))
	                        		{
	                        %>
	                        			<option value="<%=rm.getR_id() %>"><%=rm.getR_name() %></option>
	                        <% 		}
	                        	}
                        	%>
                        </select>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="text1" class="control-label col-lg-4 ">Reported To</label>

                    <div class="col-lg-8">
                        <select id="reported_to" name="reported_to" class="form-control validate[required]">
                            <option value="">--Select--</option>
                        	<% 	for(EmployeeModel em:ear) 
	                        	{
	                        		if(em.getR_id() == 1 || em.getR_id() == 2 || em.getR_id() ==3)
	                        		{
	                        %>
	                        			<option value="<%=em.getEmp_id()%>"><%=em.getName()+" ("+em.getR_name()+")" %></option>
	                        <% 		}
	                        	}
                        	%>
                        </select>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="text1" class="control-label col-lg-4">Email</label>

                    <div class="col-lg-8">
                      <input type="text" id="email" name="email" placeholder="Email" class="form-control validate[required,custom[email]]">
                    </div>
                </div>
                <div class="form-group">
                    <label for="text1" class="control-label col-lg-4">Mobile</label>

                    <div class="col-lg-8">
                      <input type="text" id="mobile" name="mobile" placeholder="Mobile Number" class="form-control validate[required, minSize[10],maxSize[10],custom[integer]]">
                    </div>
                </div>
                <div class="form-group">
                    <label for="text1" class="control-label col-lg-4">Password</label>

                    <div class="col-lg-8">
                      <input type="text" id="password" name="password" placeholder="Password" class="form-control validate[required, minSize[5]]">
                    </div>
                </div>
                <!-- /.form-group -->
				<div class="form-group">
                    <div class="col-lg-4"> </div>
					<div class="col-lg-8">
					   <a href="javascript:;" class="btn btn-warning" id="clearBtn" style="float: left;">Clear</a>
                       <input type="submit" class="btn btn-primary" id="op" name="op" style="float: right;" value="Save">
                    </div>
                </div>
                <input type="hidden" id="id" name="id">
               </div>
               <div class="col-lg-2">
               	<img alt="" src="Uploads/Employee_Pics/default.jpg" style="height: 150px; width: 120px; border:1px solid teal;" id="logP">
                <input type="file" name="photo" id="photo" onchange="viewImage(this,'logP',120,150);" class="validate[required]" style="width: 95px;">
               </div>
            </form>
        </div>
    </div>
</div>

<!--END TEXT INPUT FIELD-->
  <div class="col-lg-12" id="tableDiv">
        <div class="box">
            <header>
                <div class="icons"><i class="fa fa-table"></i></div>
                <h5>Designations</h5>
                <!-- .toolbar -->
            <div class="toolbar">
              <nav style="padding: 8px;">
                 <a href="javascript:;" class="btn btn-primary" id="addBtn">
                      Add New
                  </a>
              </nav>
            </div>  
            </header>
            <%
            	/* DesignationService	ds 			=	new DesignationService();
            	ArrayList<DesignationModel>	arr	=	ds.SelectAll(); */
            %>
            <div id="collapse4" class="body">
                <table id="desigTable" class="table table-bordered table-condensed table-hover table-striped">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Designation</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    	<%-- <%
                    		/* for(DesignationModel dm : arr)
                    		{ */
                    	%>
                    		<tr>
                                <td><%=dm.getD_id() %></td>
                                <td><%=dm.getD_name()%></td>
                                <td><%=dm.getStatus() %></td>
                                <td>
                                		<a class="btn btn-default btn-xs" href="javascript:" title="Edit" onclick="EditDesig(<%=dm.getD_id()%>);"> <!--  -->
				                    		<i class="fa fa-pencil-square-o"></i>
				                    	</a> 
				                    	<a href="javascript:" title="Delete" class="btn btn-default btn-xs" onclick="Delete(<%=dm.getD_id()%>,'designation');">
				                    		<i class="fa fa-trash-o"></i>
				                    	</a> 
				                    	<% if(dm.getStatus().equals("Active")){ %>
				                    	<a href="javascript:" title="Disable" class="btn btn-default btn-xs" onclick="ChangeStatus(<%=dm.getD_id()%>,'designation','Inactive');">
				                    		<i class="fa fa-ban"></i>
				                    	</a>
				                    	<%}else{ %>
				                    	<a href="javascript:" title="Enable" class="btn btn-default btn-xs" onclick="ChangeStatus(<%=dm.getD_id()%>,'designation','Active');">
				                    		<i class="fa fa-check"></i>
				                    	</a>
                    	<%}%>
                                
                                </td>
                            </tr>
                    	<%		
                    		}
                    	%> --%>
                            
                    </tbody>               
                  </table>
            </div>
        </div>
    </div>
</div>
</div>
<!-- /.row -->
<!--End Datatables-->
<!-- /.inner -->
       </div>
       <!-- /.outer -->
   </div>
   <!-- /#content -->
    <script>
        $(function() {
          $('#desigTable').dataTable();
          $('#addBtn').click(function(){
        	  $('#addForm').show(500);
        	  $('#tableDiv').hide();
        	  $('#op').val('Save');
          });
          $('#closeBtn').click(function(){
        	  $('#addForm').hide();
        	  $('#tableDiv').show(500);
          });
        });
    </script>            
      <jsp:include page="Footer.jsp"></jsp:include>