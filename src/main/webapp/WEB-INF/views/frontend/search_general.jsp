
 <%@include file="/WEB-INF/views/frontend/header.jsp" %>
<div class="container">
  <div class="row">
    
    <div class="col-md-12"> 
      
      <div class="panel">
        <div class="panel-body"> 
   
   <div class='row'>
   <div class='section-head'> <p class='section-title col-md-offset-2'>Result(s) Found in Articles</p></div>     
   </div>
   <c:forEach items="${articles}" var="article"> 
          <div class="row">    
            <br>
            <div class="col-md-2 col-sm-3 text-center">
              <a class="story-title" href="#"><img alt="" src="${pageContext.request.contextPath}/shared/serve_image/${article.getAuthorAssetId(userService)}" style="width:100px;height:100px" class="img-circle"></a>
            </div>
            <div class="col-md-10 col-sm-9">
              <h3>${article.getTitle()}</h3>
              <div class="row">
                <div class="col-xs-9">
                  <h4><span class="label label-default">${article.getAuthorForDisplay(userService)}</span></h4><h4>
                  <small style="font-family:courier,'new courier';" class="text-muted">${article.getAge()} Ago <a href="${pageContext.request.contextPath}/view/article/${article.getFriendlyUrl()}" class="text-muted">Read More</a></small>
                  </h4></div>
                <div class="col-xs-3"></div>
              </div>
              <br> 
            </div>
          </div>
          <hr>
      </c:forEach>    
      <div class='row' style='margin:10px;'>
       <a href="${pageContext.request.contextPath}/search/blog/${query}/1" class="btn pull-right btn-primary">Search  Apps for '${query}' <i class="glyphicon glyphicon-chevron-right  "  ></i> </a>
      </div>
          
        <div class='row'>
   <div class='section-head'> <p class='section-title col-md-offset-2'>Result(s) found in Apps</p></div>     
   </div>
   <c:forEach items="${apps}" var="app"> 
          <div class="row">    
            <br>
            <div class="col-md-2 col-sm-3 text-center">
              <a class="story-title" href="#"><img alt="" src="${pageContext.request.contextPath}/shared/serve_image/${app.getAuthorAssetId(userService)}" style="width:100px;height:100px" class="img-circle"></a>
            </div>
            <div class="col-md-10 col-sm-9">
              <h3>${app.getTitle()}</h3>
              <div class="row">
                <div class="col-xs-9">
                  <h4><span class="label label-default">${app.getAuthorForDisplay(userService)}</span></h4><h4>
                  <small style="font-family:courier,'new courier';" class="text-muted">${app.getAge()} Ago <a href="${pageContext.request.contextPath}/view/app/${app.getFriendlyUrl()}" class="text-muted">Read More</a></small>
                  </h4></div>
                <div class="col-xs-3"></div>
              </div>
              <br> 
            </div>
          </div>
          <hr>
      </c:forEach>   
 
          
       <div class='row' style='margin:10px;'>
       <a href="${pageContext.request.contextPath}/search/app/${query}/1" class="btn pull-right btn-primary">Search  Apps for '${query}' <i class="glyphicon glyphicon-chevron-right  "  ></i> </a>
       </div>
          
     
                                                                                       
	   <div class='row'>
   <div class='section-head'> <p class='section-title col-md-offset-2'>Result(s) found in Bugs</p></div>     
   </div>
   <c:forEach items="${bugs}" var="bug"> 
          <div class="row">    
            <br>
            <div class="col-md-2 col-sm-3 text-center">
            </div>
            <div class="col-md-10 col-sm-9">
              <h3>${bug.getTitle()}</h3>
              <div class="row">
                <div class="col-xs-9">
                  <h4><span class="label label-default">${bug.getAuthorForDisplay(userService)}</span></h4><h4>
                  <small style="font-family:courier,'new courier';" class="text-muted">${bug.getAge()} Ago <a href="${pageContext.request.contextPath}/view/bug/${bug.getFriendlyUrl()}" class="text-muted">Read More</a></small>
                  </h4></div>
                <div class="col-xs-3"></div>
              </div>
              <br> 
            </div>
          </div>
          <hr>
      </c:forEach> 
                                                    
       <div class='row' style='margin:10px;'>
       <a href="${pageContext.request.contextPath}/search/bug/${query}/1" class="btn pull-right btn-primary">Search Bug Solutions for '${query}'<i class="glyphicon glyphicon-chevron-right disabled" disabled></i> </a>
       </div>     
                                                
   	</div> 
  </div>
</div>
          </div>
      </div>                                         
 <%@include file="/WEB-INF/views/frontend/footer.jsp" %>