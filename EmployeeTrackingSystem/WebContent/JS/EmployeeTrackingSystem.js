	$(document).ready(function() {
		
		//..................Designation form submit function...............//
		 $("#desigForm").validationEngine('attach');
		 $('#desigForm').submit(function(){ 
			 if($("#desigForm").validationEngine('validate'))
			 {
				 var Dat    =	getAllformvalues('desigForm');
				 $.ajax({
		                url:'designation',
		                type: 'POST',
		                data: Dat,
		                dataType: "text",
		            success: function(result)
		            {
		            	var text = ($('#op').val()=="Update")?"Updated Successfully !":"Successfully Saved !";
		            	if(result == "success")
		            	{
		            		swal({ 
		             		   title: "Success",
		             		    text: text,
		             		     type: "success" 
		             		   },
		             		   function(){
		             		     window.location.href = window.location.href;
		             		 });
			            }
		            	else 
		                {
		                   sweetAlert("",'Failed to Save, Please try again..!', "error");
		                } 
		            }         
		          });
			 	}
			 return false;
		 });
		 //...................................................................//
		 
		//..................Role form submit function...............//
		 $("#roleForm").validationEngine('attach');
		 $('#roleForm').submit(function(){ 
			 if($("#roleForm").validationEngine('validate'))
			 {
				 var Dat    =	getAllformvalues('roleForm');
				 $.ajax({
		                url:'role',
		                type: 'POST',
		                data: Dat,
		                dataType: "text",
		            success: function(result)
		            {
		            	var text = ($('#op').val()=="Update")?"Updated Successfully !":"Successfully Saved !";
		            	if(result == "success")
		            	{
		            		swal({ 
		             		   title: "Success",
		             		    text: text,
		             		     type: "success" 
		             		   },
		             		   function(){
		             		     window.location.href = window.location.href;
		             		 });
			            }
		            	else 
		                {
		                   sweetAlert("",'Failed to Save, Please try again..!', "error");
		                } 
		            }         
		          });
			 	}
			 return false;
		 });
		 
		 
		 //....................Employee Form.......................//
		 $("#empForm").validationEngine('attach');
		 $('#empForm').submit(function(){ 
			 if($("#empForm").validationEngine('validate'))
			 {
				 var formData    =   new FormData(this);
				 formData.append('op',$('#op').val());
				 $.ajax({
		                url:'employee',
		                type: 'POST',
		                data: formData,
		                dataType: "json",
		                mimeType:"multipart/form-data",
		                contentType: false,
		                cache: false,
		                processData:false,
		            success: function(result)
		            {
		            	var text = ($('#op').val()=="Update")?"Updated Successfully !":"Successfully Saved !";
		            	if(result == "success")
		            	{
		            		swal({ 
		             		   title: "Success",
		             		    text: text,
		             		     type: "success" 
		             		   },
		             		   function(){
		             		     window.location.href = window.location.href;
		             		 });
			            }
		            	else 
		                {
		                   sweetAlert("",'Failed to Save, Please try again..!', "error");
		                } 
		            }         
		          });
			 	}
			 return false;
		 });
		 
	});//Document Ready
	
	//////////////GET ALL VALUES IN FORM/////////////////
	function getAllformvalues(formid) 
	{
		
	    var inputValues             =   {};
	    jQuery('#' + formid + ' input').each(function () {
	        var type                =   jQuery(this).attr('type');
	        var name                =   jQuery(this).attr('name');
	        var id                  =   jQuery(this).attr('id');
	        var type                =   jQuery(this).attr("type");
	        if ((type == "checkbox" || type == "radio") ) {
	        	if($(this).prop('checked'))
	             inputValues[name]   =   jQuery(this).val();
	        } else if (type != "button" ) {
	            inputValues[name]   =   jQuery(this).val();
	        }
	    });
	    
	    
	    jQuery('select').each(function () {
	        var name = jQuery(this).attr('name');
	        inputValues[name]       =   jQuery(this).val();
	
	    });
	    jQuery('textarea').each(function () {
	        var name                =   jQuery(this).attr('name');
	        var id                  =   jQuery(this).attr('id');
	        inputValues[name]       =   jQuery(this).val();
	    });
	    return inputValues;
	}
	
	
	//////////////////////DELETE FUNCTION////////////////////////////
	function Delete(id,url) 
	{
		swal({
			title: "Are you sure?",
	        text: "You want to delete this record..!",
	        type: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "Yes, please delete!",
	        closeOnConfirm: false,
	    }, function(){   
	    	
			 $.ajax({
	            url				: url,
	            dataType		: "text",
	            type			: 'POST',
	            data:{'id'    	:  id	,	
	            	  'op'      :  'Delete'
	                 },
	            success: function(result)
	            {
	               if(result == "success") {
	                   swal({ 
	            		   title: "Success",
	            		    text: "Successfully deleted.!",
	            		     type: "success" 
	            		   },
	            		   function(){
	            		     window.location.href = window.location.href;
	            		 });
	                }
	                else 
	                {
	                   sweetAlert("",'Failed to add, Please try again..!', "error");
	                } 
	            },
	             error: function(jqXHR, textStatus, errorThrown) 
	             {
	            	 sweetAlert("",'Something went wrong , Please try again..!', "error");
	             }          
	        }); 
			 
	        
	    });
	}
	//////////////////////CHANGE STATUS FUNCTION////////////////////////////
	function ChangeStatus(id,url,status) 
	{
		$.ajax({
	        url				: url,
	        dataType		: "text",
	        type			: 'POST',
	        data:{'id'    	:  id	,	
	        	  'op'      :  'ChangeStatus',
	        	  'status'  :  status
	             },
	        success: function(result)
	        {
	           if(result == "success") {
	              window.location.href = window.location.href;
	           }
	           else 
	           {
	        	   sweetAlert("",'Failed to add, Please try again..!', "error");
	           } 
	        },
	         error: function(jqXHR, textStatus, errorThrown) 
	         {
	        	 sweetAlert("",'Something went wrong , Please try again..!', "error");
	         }          
	    }); 
	}
	
	////////////////////////Edit Designation///////////////
	function EditDesig(id)
	{
		$.ajax({
	        url: 'designation', 
	        type: 'POST',
	        data:  {
	        	'op':"Edit",
	        	'id':id
	        },
	        dataType: "json",
	    success: function(result)
	    {
	    	$('#addForm').show(500);
	  	  	$('#tableDiv').hide();
	    	$('#op').val('Update');
	    	$('#designation').val(result.d_name);
	    	$('#status').val(result.status);
	    	$('#id').val(result.d_id)
	    },
	     error: function(jqXHR, textStatus, errorThrown) 
	     {
	    	 sweetAlert("",'Something went wrong , Please try again..!', "error");
	     }          
	    });
	}
	
	
////////////////////////Edit Role///////////////
	function EditRole(id)
	{
		$.ajax({
	        url: 'role', 
	        type: 'POST',
	        data:  {
	        	'op':"Edit",
	        	'id':id
	        },
	        dataType: "json",
	    success: function(result)
	    {
	    	$('#addForm').show(500);
	  	  	$('#tableDiv').hide();
	    	$('#op').val('Update');
	    	$('#role').val(result.r_name);
	    	$('#status').val(result.status);
	    	$('#id').val(result.r_id)
	    },
	     error: function(jqXHR, textStatus, errorThrown) 
	     {
	    	 sweetAlert("",'Something went wrong , Please try again..!', "error");
	     }          
	    });
	}
	
	
	//view selected image before upload
	//parameter :- input: 'this' file field,
	//viewId: id of view token
	//	         width: width of image
	//	          height:height of image
	function viewImage(input,viewId,image_width,image_height) 
	{

		if (input.files && input.files[0]) {
		    var reader          = new FileReader();
	
		    reader.onload       = function (e) {
		        $('#'+viewId)
		            .attr('src', e.target.result)
		            .width(image_width)
		            .height(image_height);
		    };
		    reader.readAsDataURL(input.files[0]);
		}
	}
