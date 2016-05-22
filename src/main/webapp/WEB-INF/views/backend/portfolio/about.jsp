<%@include file="/WEB-INF/views/backend/header.jsp" %>

<div class='container'>
<div class='row first_item'>
<div class='col-md-6 col-md-offset-2' style='text-align:justify;'>  
<h1 style='text-align:center;'> About this Section</h1>

 
<p><em>Apps </em> refer to applications developed by the owner of this blog. This section will help the developer showcase his 
applications to potential visitors. 
<em>Platforms</em> are the operating systems for which these apps are developed. <em>Categories </em> refer to the different application categories.
</p> 
</div>
<div class='col-md-4'></div>
</div>
<div class='row'>
<div class='col-md-6 col-md-offset-2'>
<a href='${pageContext.request.contextPath}/portfolio/view_apps'> <button class='btn btn-success btn-menu'>Manage Apps</button>  </a>
 
<a href='${pageContext.request.contextPath}/portfolio/view_categories'> <button class='btn btn-info btn-menu'> Manage Categories</button> </a> 

<a href='${pageContext.request.contextPath}/portfolio/view_platforms'>  <button class='btn btn-danger btn-menu'>Manage Platforms</button> </a>
 </div>
 
</div>
</div>
<%@include file="/WEB-INF/views/backend/footer.jsp" %>