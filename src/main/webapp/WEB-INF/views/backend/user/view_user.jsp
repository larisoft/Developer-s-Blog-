<%@include file='/WEB-INF/views/backend/header.jsp'%>


<div class='row first_item'>
   
<div class='col-md-9 col-md-offset-2'> 
 
<div class='col-md-1'>
</div>
</div>

<div class='col-md-9 col-md-offset-1'>
 <h1 class='app-title'>${user.getName()}  ${user.getSurname()}</h1>  
<table class='table-responsive table-striped table-bordered'>
<tbody>
<c:choose>
<c:when test="${assetId>0}"> 
<tr> 
<td colspan='2'>
<img class='img img-responsive' src='${pageContext.request.contextPath}/shared/serve_image/${assetId}'/>
 </td> </tr> 
 </c:when>
 </c:choose>
<tr> <td> Sex </td> <td> ${user.getSexForDisplay()} </td> </tr>
<tr> <td> Email </td> <td> ${user.getEmail()} </td> </tr> 
<tr> <td> Phone No:: </td> <td> ${user.getPhone()} </td></tr>

<tr> <td> 
<a href='${pageContext.request.contextPath}/user/edit_user/${user.getId()}' style='float:left'> <button class='btn btn-info '> Edit Info </button> </a>  
	</td>
<td>
<a href='${pageContext.request.contextPath}/user/delete_user/${user.getId()}' style='width:100%; text-align:center;'> <button class='btn btn-danger'> Delete Account </button> </a>   
</td>
<tr> 
<td colspan='2'>
<a href='${pageContext.request.contextPath}/user/edit_user/${user.getId()}' style='width:100%; text-align:center;'> <button class='btn btn-success form-control'> Change Account Picture </button> </a>  </td> </tr>
</td>


<tr> <td colspan='2'>
<h2 style='text-align:center'> About Me</h2>
<p class='app-description'>
 ${user.getAbout()} 
</p>
</tr> 
</tbody>
</table>  

  
</div>
</div>



<%@include file='/WEB-INF/views/backend/footer.jsp'%>