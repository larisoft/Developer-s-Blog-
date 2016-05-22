<div class='row first_item'> 
<div class='col-md-9 col-md-offset-1'> 
 <c:forEach items="${assets}" var="asset"> 
 <div style='text-align:center'>
 <img class='img img-thumbnail img-responsive content-img' style='display:inline-block; height:' src='${pageContext.request.contextPath }/shared/serve_image/${asset.getAssetId()}'/>
 </div>
 </c:forEach>
 </div>
</div>