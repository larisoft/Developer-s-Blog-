<%@include file="/WEB-INF/views/backend/header.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="springForm"%> 

<div class="container">
<div class='row first_item'>


<div class='col-md-9 col-md-offset-1'> 
<!-- create new app -->
<div style='float:right; margin-bottom:10px;'>
 <a class='my-links' href='${pageContext.request.contextPath}/bug/new_bug'><i class='fa fa-plus'></i> New Bug </a> 
</div>
 </div>	

</div>
<div class='row'>
 
<div class='col-md-9 col-md-offset-1'>


<!--  apps table -->
<table class='  table-striped table-bordered' id="apps_table">
<thead>
<tr> <th> S/N </th> <th> Title </th> <th> Views </th> <th> Action </th>  </tr>
<%int counter = 1; %>
</thead>
<tbody>
<c:forEach items="${bugs}" var="bug">  
<tr> <td> <%=counter++%> </td> <td><c:out value="${bug.getTitle()}"/> </td> <td> ${bug.getViews()} </td>  <td> 
      <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i class='fa fa-bars'></i>  </a>
                    <ul class="dropdown-menu alert-dropdown"> 
                        	
                         <li>
                            <a href="${pageContext.request.contextPath}/bug/view_bug/${bug.getId()}"><span class="label label-success">View</span></a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/bug/edit_bug/${bug.getId()}"><span class="label label-warning">Edit</span></a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/bug/delete_bug/${bug.getId()}"><span class="label label-danger">Delete</span></a>
                        </li> 
                    </ul>
                </li>
</tds></tr>


</c:forEach>
</tbody>
</table> 
</div>
</div>
</div>


<%@include file="/WEB-INF/views/backend/footer.jsp" %>

 
    <script src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/datatables/TableTools.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/datatables/jquery.dataTables.columnFilter.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/datatables/lodash.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/datatables/responsive/js/datatables.responsive.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/select2/select2.min.js"></script>  

<script type='text/javascript'>

jQuery(document).ready(function($)
	{
		

		var datatable = $("#apps_table").dataTable();
		
		$(".dataTables_wrapper select").select2({
			minimumResultsForSearch: -1
		});
	});

</script>