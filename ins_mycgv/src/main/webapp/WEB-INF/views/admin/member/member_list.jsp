<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://localhost:9000/ins_mycgv/resources/css/mycgv.css">
<link rel="stylesheet" href="http://localhost:9000/ins_mycgv/resources/css/am-pagination.css">
<script src="http://localhost:9000/ins_mycgv/resources/js/jquery-3.6.0.min.js"></script>
<script src="http://localhost:9000/ins_mycgv/resources/js/am-pagination.js"></script>
<script>
	$(document).ready(function(){
		
		var pager = jQuery('#ampaginationsm').pagination({
		
		    maxSize: 7,	    		// max page size
		    totals: '${dbCount}',	// total pages	
		    page: '${reqPage}',		// initial page		
		    pageSize: '${pageSize}',			// max number items per page
		
		    // custom labels		
		    lastText: '&raquo;&raquo;', 		
		    firstText: '&laquo;&laquo;',		
		    prevText: '&laquo;',		
		    nextText: '&raquo;',
				     
		    btnSize:'sm'	// 'sm'  or 'lg'		
		});
		
		jQuery('#ampaginationsm').on('am.pagination.change',function(e){
			   jQuery('.showlabelsm').text('The selected page no: '+e.page);
	           $(location).attr('href', "http://localhost:9000/ins_mycgv/admin/member_list.do?rpage="+e.page);         
	    });
		
 	});
</script> 
</head>
<body>
	<!--  header -->
	<jsp:include page="../../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="content">
		<section class="member_list">
			<h1 class="title">관리자-회원관리</h1>
			<table border=1 class="content_layout">
				<tr>
					<th>번호</th>
					<th>아이디</th>	
					<th>성명</th>
					<th>핸드폰번호</th>
					<th>성별</th>
					<th>가입일자</th>
					<th>회원탈퇴</th>
				</tr>
				<c:forEach var="vo" items="${list}">
					<tr>
						<td>${vo.rno }</td>
						<td><a href="member_content.do?id=${vo.id}&rno=${vo.rno}">${vo.id }</a></td>
						<td>${vo.name }</td>
						<td>${vo.hp }</td>
						<td>${vo.gender }</td>
						<td>${vo.mdate }</td>	
						<c:choose>	
							<c:when test="${vo.join_status == 0}">				
								<td><button type="button" disabled>신청</button></td>
							</c:when>
							<c:otherwise>
								<td><button type="button">신청</button></td>
							</c:otherwise>
						</c:choose>						
					</tr>
				</c:forEach>
				<tr>
					<td colspan="7"><div id="ampaginationsm"></div></td>					
				</tr>
			</table>
		</section>
		
	</div>
	
	<!--  footer -->
	<jsp:include page="../../footer.jsp"></jsp:include>
</body>
</html>




