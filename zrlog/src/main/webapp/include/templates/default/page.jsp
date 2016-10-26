<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" media="screen" href="${templateUrl}/css/jquery-tab.css" />
<script src="${templateUrl}/js/jquery-tab.js"></script>
<c:choose>
	<c:when test="${empty requestScope.data}">
		<c:set var="pageLevel" value="1" scope="request"/>
		<jsp:include page="404.jsp"></jsp:include>
	</c:when>
	<c:otherwise>
	<c:if test="${not empty tipsType}">
    <h2 class="category-title">
    	${tipsType}目录：${tipsName}<br/>
    	以下是与${tipsType} “${tipsName}” 相关联的文章
    </h2>
    </c:if>
    
    <article class="entry2"  style="height:400px">
 
		<div class="container">
		      <div class="tab-group">
		      
		      <c:if test="${not empty requestScope.data}">
				<c:forEach var="tabs" items="${requestScope.data.tabs}" varStatus="status">
					<section id="tab${status.index+1}" title="${tabs.typeName}">
			           <ul class="liebiao"  nmeId="${tabs.typeId}" >
				           <c:if  test="${status.index==0} ">
				              <c:forEach var="firstTab" items="${requestScope.data.firstTab}">
				               <li><a href="${firstTab.url }" target="_blank">${firstTab.name }</a></li>
				              </c:forEach>
				           </c:if>
			           
			          <!--    <li style="list-style:none;"><a href="www.baidu.com">推酷</a></li>
			             <li><a href="http://www.baidu.com" target="_blank">推酷</a></li>
			             <li><a href="http://www.baidu.com">推酷33333333333</a></li>
			             <li><a href="www.baidu.com">推酷</a></li>
			             <li><a href="www.baidu.com">推酷</a></li>
			             <li><a href="www.baidu.com">推酷</a></li>
			             <li><a href="www.baidu.com">推酷</a></li>
			             <li><a href="www.baidu.com">推酷</a></li>
			             <li><a href="www.baidu.com">推酷</a></li>
			              <li><a href="www.baidu.com">推酷</a></li>
			             <li><a href="www.baidu.com">推酷</a></li>
			             <li><a href="www.baidu.com">推酷</a></li>
			             <li><a href="www.baidu.com">推酷</a></li> -->
			           </ul>
			        </section>
				</c:forEach>
			</c:if>
		      
		   </div>
		</div>          
	</article>
    
    
    
	<c:if test="${not empty requestScope.data}">
		<c:forEach var="log" items="${requestScope.data.rows}">
		<article class="entry">
		<h2 class="post-title"><a rel="bookmark" href="${rurl}post/${log.alias}">${log.title}</a></h2>
		  <div class="content"><p>${log.digest}</p></div>
			  <div class="meta">
			  <p class="category"><a rel="tag" href="${rurl}post/sort/${log.typeAlias}">${log.typeName}</a> </p>
			  <p class="published">/<time datetime="${log.releaseTime}">&nbsp;${log.releaseTime.year+1900}-${log.releaseTime.month+1}-${log.releaseTime.date}</time></p>
			  <p class="commentlink"><a href="${rurl}post/${log.alias}#comment" class="comments_invite">${_res.commentView} [${log.commentSize}]</a></p>
		  </div>
		</article>
		</c:forEach>
	</c:if>
</c:otherwise>
</c:choose>

<script type="text/javascript">
$(function(){
	  // Calling the plugin
	  $('.tab-group').tabify();
	  
	  
	  //给第一个tab初始化
	  var str=$('.tab-nav .active').children().attr('href');
	  var typeId=$(str).children().attr('nmeid');
	  $.ajax({
		  url: "getAdaptTab",
		  data:{
			  "typeId":typeId
		  },
		  dataType:"json",
		  success: function(data){
			  //alert(JSON.stringify(data.adaptTab));
			  var htmstr='';
			  for(key in data.adaptTab){
				  var obj=data.adaptTab[key];
				 // alert(JSON.stringify(obj));
				  htmstr=htmstr+"<li><a href='"+obj.url+"' target='_blank'>"+obj.name+"</a></li>";
			  }
			  
			 // alert(htmstr);
			  $('[nmeid='+typeId+']').html(htmstr);
	      }
	  
	  });
	  
	  
	  $('.tab-nav li').click(function(){
		  var str=$(this).children().attr('href');
		  //str.substr(1,str.length-1);
		 // alert(str.substr(1,str.length-1));
		 var typeId=$(str).children().attr('nmeid');
		 //alert(typeId);
		  $.ajax({
			  url: "getAdaptTab",
			  data:{
				  "typeId":typeId
			  },
			  dataType:"json",
			  success: function(data){
				  //alert(JSON.stringify(data.adaptTab));
				  var htmstr='';
				  for(key in data.adaptTab){
					  var obj=data.adaptTab[key];
					 // alert(JSON.stringify(obj));
					  htmstr=htmstr+"<li><a href='"+obj.url+"' target='_blank'>"+obj.name+"</a></li>";
				  }
				  
				 // alert(htmstr);
				  $('[nmeid='+typeId+']').html(htmstr);
		      }
		  
		  });
		 
		 
		 
		 
	  });
	  
	})           
</script>

<jsp:include page="pager.jsp"></jsp:include>
<jsp:include page="plugs.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>
