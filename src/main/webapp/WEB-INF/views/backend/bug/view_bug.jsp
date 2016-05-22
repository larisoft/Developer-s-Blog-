<%@include file="/WEB-INF/views/backend/header.jsp"%>

<div class='row first_item'>
 

<div class='col-md-9 col-md-offset-2'> 
 <c:choose>
 <c:when test="${assets.size()>3}">
<img data-toggle="modal" data-target="#pic1Modal" class='img img-thumbnail img-responsive screen-shot' src="${pageContext.request.contextPath}/portfolio/serve_image/${assets.get(0).getId()}"> 
<img data-toggle="modal" data-target="#pic2Modal" class='img img-thumbnail img-responsive screen-shot' src=" ${pageContext.request.contextPath}/portfolio/serve_image/${assets.get(1).getId()}"> 
<img data-toggle="modal" data-target="#pic3Modal" class='img img-thumbnail img-responsive screen-shot' src=" ${pageContext.request.contextPath}/portfolio/serve_image/${assets.get(2).getId()}"> 
<img data-toggle="modal" data-target="#pic4Modal"class='img img-thumbnail img-responsive screen-shot' src=" ${pageContext.request.contextPath}/portfolio/serve_image/${assets.get(3).getId()}">   	 	
 
 </c:when>
 </c:choose>
 </div>
 <div class='col-md-1'></div>

<div class='col-md-9 col-md-offset-1'>
 <h1 class='app-title'>${bug.getTitle()}</h1>
 
   
<table class='table-responsive table-striped table-bordered'>
<tbody>

<tr> <td colspan='2'>
<p class='app-description'>
 ${bug.getError_message()} 
</p>
</tr>   
 
<tr> <td> Views </td> <td> ${bug.getViews()} </td> </tr>
<tr> <td> Date Published </td> <td> ${bug.getDate()} </td> </tr> 
<tr> <td> Technologies </td> <td> ${bug.getTechnologies_involved()} </td>

<tr> <td> <a href='${pageContext.request.contextPath}/bug/edit_bug/${bug.getId()}' style='float:left'> <button class='btn btn-info '> Edit Bug </button> </a> 
</td> <td><a href='${pageContext.request.contextPath}/bug/delete_bug/${bug.getId()}'> <button class='btn btn-danger'> Delete Bug </button> </a>  </td>
 <tr> <td colspan='2'> <a href='${pageContext.request.contextPath}/bug/view_bug/${bug.getId()}'> <button class='btn btn-portfolio form-control'> View Bug</button> </a> </td> </td> </tr>
 
</tbody>
</table>  

  
</div>
</div>


<%@include file="/WEB-INF/views/backend/footer.jsp" %>