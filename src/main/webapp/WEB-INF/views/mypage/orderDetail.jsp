<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<br><br><br>
<section class="bg0 p-t-23 p-b-140">
	<div class="container">
		<div class="col-lg-6">
    		<div class="card" style="width: 1200px">
   			 <div class="col-lg-6">
   			 <br>
   			 	<ul class="main-menu">
						<li class="active-menu">
							<a href="mypage_form">주문 내역</a>
						</li>

						<li>
							<a href="cart_list">장바구니</a>
						</li>

						<li>
							<a href="change_pwd_form">비밀번호 변경</a>
						</li>
						
						<li>
							<a href="member_delete">회원탈퇴</a>
						</li>
					</ul>
				<br>
                        
                           
    
    			<div class="col-lg-6">
                        <div class="card" style="width: 1160px;">
                            <div class="card-header">
                                <strong class="card-title">주문내역 상세보기</strong>
                            </div>
                            <div class="card-body">
                                <table class="table">
                                    <thead>
                                        <tr>
                                          <th scope="col">주문번호</th>
                                          <th scope="col">상품</th>
                                          <th scope="col">사이즈</th>
                                          <th scope="col">수랑</th>
                                          <th scope="col">가격</th>
                                          <th scope="col">주문일</th>
                                          
                                      </tr>
                                  </thead>
                                  <tbody>
                                  <c:forEach items="${orderList}"  var="orderVO">
                                    <tr>
                                    	<td>${orderVO.oseq }</td>
                                        <td>${orderVO.pname }</td>
                                        <td>${orderVO.psize }</td>
                                        <td>${orderVO.quantity }</td>
                                        <td>${orderVO.price }</td>
                                        <td><fmt:formatDate value="${orderVO.indate}" type="date"/></td>
                                    </tr>

                                   </c:forEach>
                                   <td>결제금액 : ${orderPrice }</td>
                                </tbody>
                                
                            </table>
                        </div>
                    </div>
                </div>
                <br><br><br>
    
    
			</div>
		</div>
    </div>
</section>

<%@ include file="footer.jsp" %>