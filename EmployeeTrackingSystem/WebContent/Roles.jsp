<%@page import="com.EmployeeTrackingSystem.Models.RoleModel"%>
<%@page import="com.EmployeeTrackingSystem.Services.RoleService"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Menu.jsp"></jsp:include>
           
<div id="content">
 <div class="outer">
    <div class="inner bg-light lter" style="min-height: 500px !important;">
                            
<div class="row">
  <div class="col-lg-12" id="addForm" style="display: none;">
    <div class="box dark">
        <header>
            <div class="icons"><i class="fa fa-edit"></i></div>
            <h5>Add New Role</h5>
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
        <div id="div-1" class="body" style="padding-top:50px;">
            <form class="form-horizontal" id="roleForm">

                <div class="form-group">
                    <label for="text1" class="control-label col-lg-3">Role</label>

                    <div class="col-lg-7">
                        <input type="text" id="role" name="role" placeholder="Role" class="form-control validate[required, minSize[3]]">
                    </div>
                </div>
                <!-- /.form-group -->
				<div class="form-group">
                    <label for="text1" class="control-label col-lg-3 ">Status</label>

                    <div class="col-lg-7">
                        <select id="status" name="status" class="form-control validate[required]">
                            <option value="">--Select--</option>
                        	<option value="Active">Active</option>
                        	<option value="Inactive">Inactive</option>
                        </select>
                    </div>
                </div>
                
                <!-- /.form-group -->
				<div class="form-group">
                    <div class="col-lg-3"> </div>
					<div class="col-lg-7">
					   <a href="javascript:;" class="btn btn-warning" id="clearBtn" style="float: left;">Clear</a>
                       <input type="submit" class="btn btn-primary" id="op" name="op" style="float: right;" value="Save">
                    </div>
                </div>
                <input type="hidden" id="id" name="id">
            </form>
        </div>
    </div>
</div>

<!--END TEXT INPUT FIELD-->
  <div class="col-lg-12" id="tableDiv">
        <div class="box">
            <header>
                <div class="icons"><i class="fa fa-table"></i></div>
                <h5>Roles</h5>
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
            	RoleService					rs	=	new RoleService();
            	ArrayList<RoleModel>	arr	=	rs.SelectAll();
            %>
            <div id="collapse4" class="body">
                <table id="roleTable" class="table table-bordered table-condensed table-hover table-striped">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                       <%
                    		for(RoleModel rm : arr)
                    		{
                    	%>
                    		<tr>
                                <td><%=rm.getR_id() %></td>
                                <td><%=rm.getR_name()%></td>
                                <td><%=rm.getStatus() %></td>
                                <td>
                                		<a class="btn btn-default btn-xs" href="javascript:" title="Edit" onclick="EditRole(<%=rm.getR_id()%>);"> <!--  -->
				                    		<i class="fa fa-pencil-square-o"></i>
				                    	</a> 
				                    	<a href="javascript:" title="Delete" class="btn btn-default btn-xs" onclick="Delete(<%=rm.getR_id()%>,'role');">
				                    		<i class="fa fa-trash-o"></i>
				                    	</a> 
				                    	<% if(rm.getStatus().equals("Active")){ %>
				                    	<a href="javascript:" title="Disable" class="btn btn-default btn-xs" onclick="ChangeStatus(<%=rm.getR_id()%>,'role','Inactive');">
				                    		<i class="fa fa-ban"></i>
				                    	</a>
				                    	<%}else{ %>
				                    	<a href="javascript:" title="Enable" class="btn btn-default btn-xs" onclick="ChangeStatus(<%=rm.getR_id()%>,'role','Active');">
				                    		<i class="fa fa-check"></i>
				                    	</a>
                    	<%}%>
                                
                                </td>
                            </tr>
                    	<%		
                    		}
                    	%> 
                            
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
          $('#roleTable').dataTable();
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