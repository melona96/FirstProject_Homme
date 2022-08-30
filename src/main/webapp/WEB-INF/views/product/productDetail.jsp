<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<br>

	<!-- Product Detail -->
	<section class="sec-product-detail bg0 p-t-65 p-b-60">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-lg-7 p-b-30">
					<div class="p-l-25 p-r-30 p-lr-0-lg">
						<div class="wrap-slick3 flex-sb flex-w">
							<div class="wrap-slick3-dots"></div>
							<div class="wrap-slick3-arrows flex-sb-m flex-w"></div>

							<div class="slick3 gallery-lb">
								<div class="item-slick3" data-thumb="images/${productVO.image}">
									<div class="wrap-pic-w pos-relative">
										<img src="images/${productVO.image}" alt="IMG-PRODUCT">

										<a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="images/${productVO.image}">
											<i class="fa fa-expand"></i>
										</a>
									</div>
								</div>

								<div class="item-slick3" data-thumb="images/${productVO.image2}">
									<div class="wrap-pic-w pos-relative">
										<img src="images/${productVO.image2}" alt="IMG-PRODUCT">

										<a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="images/${productVO.image2}">
											<i class="fa fa-expand"></i>
										</a>
									</div>
								</div>

								<div class="item-slick3" data-thumb="images/${productVO.image3}">
									<div class="wrap-pic-w pos-relative">
										<img src="images/${productVO.image3}" alt="IMG-PRODUCT">

										<a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="images/${productVO.image3}">
											<i class="fa fa-expand"></i>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-md-6 col-lg-5 p-b-30">
					<div class="p-r-50 p-t-5 p-lr-0-lg">
						<h4 class="mtext-105 cl2 js-name-detail p-b-14">
							${productVO.name}
						</h4>

						<span class="mtext-106 cl2">
							${productVO.price}
						</span>

						<p class="stext-102 cl3 p-t-23">
							${productVO.content}
						</p>
						
						<form action="insert_cart" method="post">
						<input type="hidden" name="pseq" value="${productVO.pseq }">
						<!--  -->
						<div class="p-t-33">
							<div class="flex-w flex-r-m p-b-10">
								<div class="size-203 flex-c-m respon6">
									Size
								</div>

								<div class="size-204 respon6-next">
									<div class="rs1-select2 bor8 bg0">
										<select class="js-select2" name="psize">
											<option>사이즈를 선택해주세요.</option>
											<option>S</option>
											<option>M</option>
											<option>L</option>
											<option>XL</option>
										</select>
										<div class="dropDownSelect2"></div>
									</div>
								</div>
							</div>
							
							<div class="flex-w flex-r-m p-b-10">
								<div class="size-204 flex-w flex-m respon6-next">
									<div class="wrap-num-product flex-w m-r-20 m-tb-10">
										<div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
											<i class="fs-16 zmdi zmdi-minus"></i>
										</div>

										<input class="mtext-104 cl3 txt-center num-product" type="number" name="quantity" value="1">

										<div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
											<i class="fs-16 zmdi zmdi-plus"></i>
										</div>
									</div>

									<button type="submit" class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail">
										장바구니에 담기
									</button>
							
								</div>
								
							</div>	
							</form>
						</div>	
					</div>
				</div>
			</div>

			<div class="bor10 m-t-50 p-t-43 p-b-40">
				<!-- Tab01 -->
				<div class="tab01">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item p-b-10">
							<a class="nav-link" data-toggle="tab" href="#reviews" role="tab" style="font-size: 1.5rem;">Reviews(${reviewCnt})</a>
						</li>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content p-t-43">
						<!-- - -->
						<!-- - -->
						<div class="tab-pane fade" id="reviews" role="tabpanel">
							<div class="row">
								<div class="col-sm-10 col-md-8 col-lg-6 m-lr-auto">
									<div class="p-b-30 m-lr-15-sm">
										<!-- Review -->
										<div class="flex-w flex-t p-b-68">
											<c:forEach items="${reviewList}"  var="reviewVO">
											<div class="size-207">
												<div class="flex-w flex-sb-m p-b-17">
													<span class="mtext-107 cl2 p-r-20">
														작성자 : ${reviewVO.id }
													</span>

													<span class="stext-102 cl6">
														<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${reviewVO.regdate}"/>
													</span>
												</div>
												
												<p class="stext-102 cl6">
													${reviewVO.content}
												</p>
												<hr>
											</div>
											</c:forEach>
										</div>
										
										<br>
										<!-- Add review -->
										<form class="w-full" action="review_write" method="post">
											<h5 class="mtext-108 cl2 p-b-7">
												리뷰 남기기
											</h5>

											<div class="row p-b-25">
												<div class="col-12 p-b-5">
													<label class="stext-102 cl3" for="review">리뷰</label>
													<textarea class="size-110 bor8 stext-102 cl2 p-lr-20 p-tb-10" id="review" name="content"></textarea>
												</div>

											</div>

											<input type="hidden" name="pseq" value="${productVO.pseq}">
                               				
											<button class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10" type="submit">
												작성하기
											</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="bg6 flex-c-m flex-w size-302 m-t-73 p-tb-15">

			<span class="stext-107 cl6 p-lr-25">
				Categories: ${productVO.kind }
			</span>
		</div>
	</section>


	<!-- Related Products -->
	
<%@ include file="footer.jsp" %>