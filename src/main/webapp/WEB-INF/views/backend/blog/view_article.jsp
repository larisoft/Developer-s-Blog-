<%@include file="/WEB-INF/views/backend/header.jsp"%>

<div class='container'>

 

<div class='row'> 

<div class='col-md-9 col-md-offset-1'>
 <h1 class='app-title'>${article.getTitle()}</h1>
 
   
<table class='table-responsive table-striped table-bordered'>
<tbody>

<tr> <td colspan='2'>
<p class='app-description'>
 ${article.getContent()} 
</p>
</tr>   
 
<tr> <td> Views </td> <td> ${article.getViews()} </td> </tr>
<tr> <td> Date Published </td> <td> ${article.getDate()} </td> </tr> 
<tr> <td> Tags: </td> <td> ${article.getTags()} </td>

<tr> <td> <a href='${pageContext.request.contextPath}/blog/edit_article/${article.getId()}' style='float:left'> <button class='btn btn-info '> Edit Article </button> </a> 
</td> <td><a href='${pageContext.request.contextPath}/blog/delete_article/${article.getId()}'> <button class='btn btn-danger'> Delete Article </button> </a>  </td>
 <tr> <td colspan='2'> <a href='${pageContext.request.contextPath}/home/view_article/${article.getId()}'> <button class='btn btn-portfolio form-control'> View Live</button> </a> </td> </td> </tr>
 
</tbody>
</table>  

  
</div>
</div>
</div>

<%@include file="/WEB-INF/views/backend/footer.jsp" %>