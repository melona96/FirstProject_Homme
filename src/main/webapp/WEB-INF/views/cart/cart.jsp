<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>


	<!-- Shoping Cart -->
	<form class="bg0 p-t-75 p-b-85" action="insert_order" method="post">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
					<div class="m-l-25 m-r--38 m-lr-0-xl">
						<div class="wrap-table-shopping-cart">
							<table class="table-shopping-cart">
								<tr class="table_head">
									<th class="column-1">상품</th>
									<th class="column-2"></th>
									<th class="column-3">가격</th>
									<th class="column-3">사이즈</th>
									<th class="column-4">수량</th>
									<th class="column-5">금액</th>
								</tr>
								
								<c:forEach items="${cartList}" var="cartVO">
								<input type="hidden" name="cseq" value="${cartVO.cseq }">
								<tr class="table_row">
									<td class="column-1">
										<div class="how-itemcart1">
											<img src="images/${cartVO.image }" alt="IMG">
										</div>
									</td>
									<td class="column-2"> ${cartVO.pname }</td>
									<td class="column-3"><fmt:formatNumber value="${cartVO.price }" pattern="#,###" />₩</td>
									<td class="column-3"><input type="hidden" name="psize" value="${cartVO.psize }">${cartVO.psize }</td>
									<td class="column-4">
										<div class="wrap-num-product flex-w m-l-auto m-r-0">
											<div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
												<i class="fs-16 zmdi zmdi-minus"></i>
											</div>

											<input class="mtext-104 cl3 txt-center num-product" type="number" name="quantity" value="${cartVO.quantity}">

											<div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
												<i class="fs-16 zmdi zmdi-plus"></i>
											</div>
										</div>
									</td>
									<td class="column-5"><fmt:formatNumber value="${cartVO.price * cartVO.quantity}" pattern="#,###" />₩</td>
								</tr>
								</c:forEach>
								
								
							</table>
						</div>

						<div class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-40 p-lr-15-sm">
							<div class="flex-w flex-m m-r-20 m-tb-5">
								
								<div class="flex-c-m stext-101 cl2 size-118 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-5">
									장바구니 업데이트
								</div>
							</div>

							
							<div class="flex-c-m stext-101 cl2 size-119 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10">
								<a href="delete_cart_list">장바구니 비우기</a>
							</div>
						</div>
					</div>
				</div>

				<div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
					<div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
						<h4 class="mtext-109 cl2 p-b-30">
							결제
						</h4>

						<div class="flex-w flex-t bor12 p-b-13">
							<div class="size-208">
								<span class="stext-110 cl2">
									총 금액:
								</span>
							</div>

							<div class="size-209">
								<span class="mtext-110 cl2">
									<fmt:formatNumber value="${totalPrice}" pattern="#,###" />₩
								</span>
							</div>
						</div>

						

						<div class="flex-w flex-t p-t-27 p-b-33">
							<div class="size-208">
								<span class="mtext-101 cl2">
									결제 금액:
								</span>
							</div>

							<div class="size-209 p-t-1">
								<span class="mtext-110 cl2">
									<fmt:formatNumber value="${totalPrice}" pattern="#,###" />₩
								</span>
							</div>
						</div>

						
						<button type="submit" class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">
							구매하기
						</button>
					</div>
				</div>
			</div>
		</div>
	</form>
		
<%@ include file="footer.jsp" %>