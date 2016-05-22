<%@include file="/WEB-INF/views/backend/header.jsp"%>

<div class='container'>

<%@include file="/WEB-INF/views/backend/shared/view_assets.jsp" %>


<div class='row first_item'> 
 
<div class='col-md-9 col-md-offset-1'>
 <h1 class='app-title'>${app.getTitle()}</h1>
 
   
<table class='table-responsive table-striped table-bordered'>
<tbody>

<tr> <td colspan='2'>
<p class='app-description'>
 ${app.getDescriptionForDisplay()} 
</p>
<tr> 
<td> Source Code </td> <td>
<c:choose>
 <c:when test="${app.getSource_url().trim().length()>0}" >
 <a href='<c:out value="${app.getSource_url()}"/>'> Click to View Source</a> 
 </c:when>
 <c:otherwise>
  <p class='text-unavailable'> <c:out value="Closed Source"/> </p>
 </c:otherwise>
 </c:choose>
  </td> 
  </tr>
 
<tr> <td> Platform </td> <td> ${platform} </td> </tr>
<tr> <td> Version </td> <td> ${app.getVersion()} </td> </tr> 
<tr> <td> Tags: </td> <td> ${app.getTags()} </td>
<tr> <td> <a href='${pageContext.request.contextPath}/portfolio/edit_app/${app.getId()}' style='float:left'> <button class='btn btn-info '> Edit App </button> </a> 
</td> <td><a href='${app.getApp_url()}'> <button class='btn btn-danger'> Delete App </button> </a>  </td>
 <tr> <td colspan='2'> <a href='${app.getApp_url()}'> <button class='btn btn-portfolio form-control'> Get App </button> </a> </td> </td> </tr>
 
</tbody>
</table>  

  
</div>
</div>
</div>


<%@include file="/WEB-INF/views/backend/footer.jsp" %>