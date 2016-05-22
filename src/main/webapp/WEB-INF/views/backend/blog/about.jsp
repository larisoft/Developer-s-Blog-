<%@include file="/WEB-INF/views/backend/header.jsp" %>

<div class='container'>
<div class='row first_item'>
<div class='col-md-6 col-md-offset-2' style='text-align:justify;'>  
<h1 style='text-align:center;'> About this Section</h1>

<p> You can publish tutorials, for yourself and your potential readers through this section.  
</p>

</div>
<div class='col-md-4'></div>
</div>
<div class='row'>
<div class='col-md-6 col-md-offset-2'>
<a href='${pageContext.request.contextPath}/blog/new_article'> <button class='btn btn-info btn-menu'> <i class='fa fa-plus'></i> New Article</button> </a> 

<a href='${pageContext.request.contextPath}/blog/view_articles/1'> <button class='btn btn-success btn-menu'>View Articles</button>  </a>
  
<a href='${pageContext.request.contextPath}/blog/view_articles/1'>  <button class='btn btn-danger btn-menu'>Delete Article</button> </a>
 </div>
 
</div>
</div>
<%@include file="/WEB-INF/views/backend/footer.jsp" %>