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
	$(document).ready(function(){
		
		//중복체크 처리
		$("#idCheck").click(function(){
			var id = $("#id").val();
			
			if(id == ""){
				alert("아이디를 입력해주세요");
				$("#id").focus();
			}else{
				//id값을 서버로 전송하여 중복체크 처리한 후 결과 송신
				//alert(id);
				$.ajax({
					url:"idcheck.do?id="+id,
					success : function(result){
						if(result == 1){
							//alert("중복된 아이디가 존재합니다. 다시 입력해주세요");
							$("#idcheck_msg").text("중복된 아이디가 존재합니다. 다시 입력해주세요");
							$("#idcheck_msg").css("font-size","9px").css("color","red");						
							$("#id").val("").focus();
						}else{
							//alert("사용이 가능한 아이디입니다.");
							$("#idcheck_msg").text("사용이 가능한 아이디입니다.");
							$("#idcheck_msg").css("font-size","9px").css("color","blue");
							$("#pass").focus();
						}
					}
				});
			}			
			
		});
	});
</script>
</head>
<body>
	<!--  header -->
	<jsp:include page="../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="content">
		<section class="join">
			<h1 class="title">회원가입</h1>
			<form name="join_form" action="join.do" method="post" class="content_layout">
				<ul>
					<li>
						<label>아이디</label>
						<input type="text" name="id" class="i1" id="id">
						<button type="button" class="btn_style" id="idCheck">중복체크</button>
						<div id="idcheck_msg"></div>
					</li>
					<li>
						<label>패스워드</label>
						<input type="password" name="pass" class="i1" id="pass">
					</li>
					<li>
						<label>패스워드 확인</label>
						<input type="password" name="cpass" class="i1" id="cpass" onblur="passCheck()">
						<div id="msg"></div>
					</li>
					<li>
						<label>이름</label>
						<input type="text" name="name" class="i1" id="name">
					</li>
					<li>
						<label>성별</label>
						<input type="radio" name="gender" value="남자"><span>남자</span>
						<input type="radio" name="gender" value="여자"><span>여자</span>
						<input type="radio" name="gender" value="기타"><span>기타</span>
						
					</li>
					<li>
						<label>이메일</label>
						<input type="text" name="email1" class="i2" id="email1"> @
						<input type="text" name="email2" class="i2" id="email2">
						<select id="email3" onchange="emailCheck()">
							<option value="choice">선택</option>
							<option value="naver.com">네이버</option>
							<option value="gmail.com">구글</option>
							<option value="korea.com">코리아</option>
							<option value="daum.net">다음</option>
						</select>
					</li>
					<li>
						<label>핸드폰</label>
						<select name="hp1" id="hp1">
							<option value="choice">선택</option>
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="017">017</option>
							<option value="016">016</option>
						</select>
						- <input type="text" name="hp2" class="i2" id="hp2">
						- <input type="text" name="hp3" class="i2" id="hp3">
					</li>
					<li>
						<label>취미</label>
						<input type="checkbox" name="hobby" value="영화보기"><span>영화보기</span>
						<input type="checkbox" name="hobby" value="독서"><span>독서</span>
						<input type="checkbox" name="hobby" value="등산"><span>등산</span>
						<input type="checkbox" name="hobby" value="게임"><span>게임</span>
						<input type="checkbox" name="hobby" value="캠핑"><span>캠핑</span>
					</li>
					<li>
						<label>소개</label>
						<textarea name="intro" id="intro"></textarea>
					</li>				
					<li>
						<button type="button" class="btn_style" id="btnJoin">가입하기</button>
						<button type="reset" class="btn_style">다시쓰기</button>
					</li>
				</ul>
			</form>
		</section>		
	</div>
	
	<!--  footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>