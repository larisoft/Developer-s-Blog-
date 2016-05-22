<%@include file="/WEB-INF/views/backend/header.jsp" %>

<div class='container'>
<div class='row first_item'>
<div class='col-md-6 col-md-offset-2' style='text-align:justify;'>  
<h1 style='text-align:center;'> About this Section</h1>

<p> Whenever you have a bug while developing an application; record it and its solution here. This means that the countless number of developers
that will have the same problem will find the solution to their problem much easier by looking up your record of the bug..  
</p>

<p><em>Bugs</em> are simply error reports. <br><em>Technologies Involved </em> refer to the tools involved in the bug.e.g. C# MVC. <br><em>Error Message:</em> the exact 
error you receive from your application due to the bug. <br><em>Solution:</em> What you did to fix this bug.

</p>
</div>
<div class='col-md-4'></div>
</div>
<div class='row'>
<div class='col-md-6 col-md-offset-2'>
<a href='${pageContext.request.contextPath}/bug/new_bug'> <button class='btn btn-info btn-menu'> <i class='fa fa-plus'></i> New Bug</button> </a> 

<a href='${pageContext.request.contextPath}/bug/view_bugs'> <button class='btn btn-success btn-menu'>View Bugs</button>  </a>
  
<a href='${pageContext.request.contextPath}/bug/view_bugs'>  <button class='btn btn-danger btn-menu'>Delete Bug</button> </a>
 </div>
 
</div>
</div>
<%@include file="/WEB-INF/views/backend/footer.jsp" %>