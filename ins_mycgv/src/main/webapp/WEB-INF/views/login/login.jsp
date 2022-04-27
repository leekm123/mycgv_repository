<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://localhost:9000/ins_mycgv/resources/css/mycgv.css">
<script src="http://localhost:9000/ins_mycgv/resources/js/jquery-3.6.0.min.js"></script>
<script src="http://localhost:9000/ins_mycgv/resources/js/mycgv.js"></script>
<script>
	var login_result = "${login_result}";
	var join_result = "${join_result}";
	
	if(login_result == "fail"){
		alert("아이디 또는 패스워드가 다릅니다. 다시 로그인 진행해 주세요.");
	}
	if(join_result == "succ"){
		alert("회원가입에 성공하셨습니다.");
	}
</script>
</head>
<body>
	<!-- header -->
	<jsp:include page="../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="content">
		<section class="login">
			<h1 class="title">로그인</h1>
			<form name="login_form" action="login.do" method="post" class="content_layout">
				<ul>
					<li>
						<label>아이디</label>
						<input type="text" name="id" id="id" placeholder="첫글자는 영문자숫자만 가능!!">
					</li>
					<li>
						<label>패스워드</label>
						<input type="password" name="pass" id="pass">
					</li>
					<li>
						<button type="button" class="btn_style" id="btnLogin">로그인</button>
						<button type="reset" class="btn_style">다시쓰기</button>
					</li>
				</ul>
			</form>
		</section>
		
	</div>
	
	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>