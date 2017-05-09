<%@page import="com.EmployeeTrackingSystem.Models.DesignationModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.EmployeeTrackingSystem.Services.DesignationService"%>
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
            <h5>Add New Designation</h5>
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
            <form class="form-horizontal" id="desigForm">

                <div class="form-group">
                    <label for="text1" class="control-label col-lg-3">Designation</label>

                    <div class="col-lg-7">
                        <input type="text" id="designation" name="designation" placeholder="Designation" class="form-control validate[required, minSize[3]]">
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
            	DesignationService	ds 			=	new DesignationService();
            	ArrayList<DesignationModel>	arr	=	ds.SelectAll();
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
                    	<%
                    		for(DesignationModel dm : arr)
                    		{
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