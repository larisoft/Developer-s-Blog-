
 <%@include file="/WEB-INF/views/frontend/header.jsp" %>
<div class="container">
  <div class="row"> 
    <div class="col-md-12">  
      <div class="panel">
        <div class="panel-body"> 
   
   <div class='row'>
   <div class='section-head'> <p class='section-title col-md-offset-2'>App Results for '${query}' </p></div>     
   </div>
   <c:forEach items="${contents}" var="content"> 
          <div class="row">    
            <br>
            <div class="col-md-2 col-sm-3 text-center">
              <a class="story-title" href="#"><img alt="" src="${pageContext.request.contextPath}/shared/serve_image/${content.getAuthorAssetId(userService)}" style="width:100px;height:100px" class="img-circle"></a>
            </div>
            <div class="col-md-10 col-sm-9">
              <h3>${content.getTitle()}</h3>
              <div class="row">
                <div class="col-xs-9">
                        <h4> <a href='${pageContext.request.contextPath}/user/${content.getAuthorUsername(userService)}'> <span class="label label-default">${content.getAuthorForDisplay(userService)}</span> </a></h4><h4>
                     <small style="font-family:courier,'new courier';" class="text-muted">${content.getAge()} Ago
                   <a href='${pageContext.request.contextPath}/view/${module}/${content.getFriendlyUrl()}' class='text-muted'>Read More</a></small>
                  </h4></div>
                <div class="col-xs-3"></div>
              </div>
              <br> 
            </div>
          </div>
          <hr>
      </c:forEach> 	   
      
      <c:choose>
      <c:when test="${has_more}">
       <div class='row' style='margin:10px;'>
       <a href="${pageContext.request.contextPath}/search/${module}/${query}/${next_page}"  class="btn pull-right btn-primary">More Results for '${query}' <i class="glyphicon glyphicon-chevron-right"></i> </a>
       </div>
      </c:when> 
       
      <c:otherwise>
       <div class='row' style='margin:10px;'>
       <a href="#"  class="btn pull-right disabled" disabled> More Results for '${query}' <i class="glyphicon glyphicon-chevron-right"></i> </a>
       </div>
      </c:otherwise> 
      </c:choose>
      
   
                                                
   	</div> 
  </div>
</div>
          </div>
      </div>                                         
 <%@include file="/WEB-INF/views/frontend/footer.jsp" %>