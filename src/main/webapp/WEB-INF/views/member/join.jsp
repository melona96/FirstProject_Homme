<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="author" content="Kodinger">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>Project Homme</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="css/my-login.css">
	<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script> 
  	<script type="text/javascript" src="member/member.js"></script>
  	<script type="text/javascript" src="product/product.js"></script>
  	<script type="text/javascript" src="member/findIdAndPassword.js"></script>
  	<script type="text/javascript" src="mypage/mypage.js"></script>
  	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</head>
<body class="my-login-page">
	<section class="h-100">
		<div class="container h-100">
			<div class="row justify-content-md-center h-100">
				<div class="card-wrapper">
					<div class="card fat">
						<div class="card-body">
							<h4 class="card-title">회원가입</h4>
							<form method="POST" class="my-login-validation" novalidate="" action="join" >
								<div class="form-group">
									<label for="name">이름</label>
									<input id="name" type="text" class="form-control" name="name" required autofocus>
									<div class="invalid-feedback">
										이름을 입력해주세요.
									</div>
								</div>

								<div class="form-group">
									<label for="user_id">아이디</label>
									<input id="id" type="text" class="form-control" name="id" value="${id}" required >
									<div class="invalid-feedback">
										아이디를 입력해주세요.
									</div>
								</div>

								<div class="form-group">
									<label for="pwd">비밀번호</label>
									<input id="pwd" type="password" class="form-control" name="pwd" required data-eye>
									<div class="invalid-feedback">
										비밀번호를 입력해주세요.
									</div>
								</div>

								<!-- API를 활용한 주소 찾기 기능 -->
								<div class="form-group">
									<label for="addtest">주소</label>
									<input type="text" id="postcode" name="postcode" placeholder="우편번호">
									<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기" ><br>
									<!--  
									<input type="text" id="roadAddress" placeholder="도로명주소">
									 -->
									<input type="text" id="jibunAddress" placeholder="지번주소" name="jibunAddress">
									<span id="guide" style="color:#999;display:none"></span>
									<input type="text" id="detailAddress" placeholder="상세주소" name="detailAddress">
									<!-- 
									<input type="text" id="extraAddress" placeholder="참고항목"> 
									 -->
									<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
									<script>
									    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
									    function execDaumPostcode() {
									        new daum.Postcode({
									            oncomplete: function(data) {
									                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
									
									                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
									                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
									                var roadAddr = data.roadAddress; // 도로명 주소 변수
									                var extraRoadAddr = ''; // 참고 항목 변수
									
									                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
									                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
									                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
									                    extraRoadAddr += data.bname;
									                }
									                // 건물명이 있고, 공동주택일 경우 추가한다.
									                if(data.buildingName !== '' && data.apartment === 'Y'){
									                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
									                }
									                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
									                if(extraRoadAddr !== ''){
									                    extraRoadAddr = ' (' + extraRoadAddr + ')';
									                }
									
									                // 우편번호와 주소 정보를 해당 필드에 넣는다.
									                document.getElementById('postcode').value = data.zonecode;
									                // document.getElementById("roadAddress").value = roadAddr;
									                document.getElementById("jibunAddress").value = data.jibunAddress;
									                
									                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
									                if(roadAddr !== ''){
									                    document.getElementById("extraAddress").value = extraRoadAddr;
									                } else {
									                    document.getElementById("extraAddress").value = '';
									                }
									
									                var guideTextBox = document.getElementById("guide");
									                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
									                if(data.autoRoadAddress) {
									                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
									                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
									                    guideTextBox.style.display = 'block';
									
									                } else if(data.autoJibunAddress) {
									                    var expJibunAddr = data.autoJibunAddress;
									                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
									                    guideTextBox.style.display = 'block';
									                } else {
									                    guideTextBox.innerHTML = '';
									                    guideTextBox.style.display = 'none';
									                }
									            }
									        }).open();
									    }
									</script>
								<div class="invalid-feedback">
										주소를 입력해주세요.
									</div>
								</div>
															
								
								<div class="form-group">
									<label for="email">전화번호</label>
									<input id="phone" type="text" class="form-control" name="phone" required>
									<div class="invalid-feedback">
										전화번호를 입력해주세요
									</div>
								</div>
								
								<div class="form-group">
									<div class="custom-checkbox custom-control">
										<input type="checkbox" name="agree" id="agree" class="custom-control-input" required="">
										<label for="agree" class="custom-control-label">약관에 동의합니다. <a href="#">약관 보기</a></label>
										<div class="invalid-feedback">
											약관에 동의하셔야 회원가입이 가능합니다.
										</div>
									</div>
								</div>

								<div class="form-group m-0">
									<button type="submit" class="btn btn-primary btn-block">
										가입하기
									</button>
								</div>
								<div class="mt-4 text-center">
									이미 회원이신가요?<a href="login_form">로그인</a>
								</div>
							</form>
						</div>
					</div>
					<div class="footer">
						Copyright &copy; 2022 &mdash; Homme
					</div>
				</div>
			</div>
		</div>
	</section>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="js/my-login.js"></script>
</body>
</html>