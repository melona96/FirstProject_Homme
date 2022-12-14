<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html lang="KO"><head>
    <meta charset="UTF-8">
    <title>Homme Admin</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="/img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin="">
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&amp;display=swap" rel="stylesheet">
    
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="admin/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="admin/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="admin/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="admin/css/style.css" rel="stylesheet">
</head>
<body>
	<div class="container-xxl position-relative bg-white d-flex p-0">
        <!-- Spinner Start -->
        <div id="spinner" class="bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->


        <!-- Sidebar Start -->
        <div class="sidebar pe-4 pb-3">
            <nav class="navbar bg-light navbar-light">
                <a href="index.html" class="navbar-brand mx-4 mb-3">
                    <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>Homme Admin</h3>
                </a>
                <div class="d-flex align-items-center ms-4 mb-4">
                    <div class="position-relative">
                        <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
                    </div>
                    <div class="ms-3">
                        <h6 class="mb-0">${admin.name}</h6>
                        <span>?????????</span>
                    </div>
                </div>
                <div class="navbar-nav w-100">
                    <a href="admin_main" class="nav-item nav-link"><i class="fa fa-tachometer-alt me-2"></i>????????? ????????????</a>
                    <a href="admin_sales_form" class="nav-item nav-link"><i class="fa fa-chart-bar me-2"></i>?????? ??????</a>
                    <a href="admin_qna_form" class="nav-item nav-link active"><i class="fa fa-laptop me-2"></i>QnA ??????</a>
                    <a href="admin_memberList" class="nav-item nav-link"><i class="fa fa-laptop me-2"></i>?????? ??????</a>
                    <a href="admin_productList" class="nav-item nav-link"><i class="fa fa-laptop me-2"></i>?????? ??????</a>
                </div>
            </nav>
        </div>
        <!-- Sidebar End -->


        <!-- Content Start -->
        <div class="content">
            <!-- Navbar Start -->
            <nav class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
                <a href="index.html" class="navbar-brand d-flex d-lg-none me-4">
                    <h2 class="text-primary mb-0"><i class="fa fa-hashtag"></i></h2>
                </a>
                
                <div class="navbar-nav align-items-center ms-auto">
                    
                	</div>
            </nav>
            <!-- Navbar End -->
    
            <!-- QnA ????????? ??????   -->
            <div class="container-fluid pt-4 px-4" >
                <div class="bg-light text-center rounded p-4">
                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h6 class="mb-0">QnA ??????</h6>      
                    </div>
                    <div class="table-responsive">
                        <table class="table text-start align-middle table-bordered table-hover mb-0">
                            <thead>
                                <tr class="text-dark">
                                    <th scope="col">??????</th>
                                    <th scope="col">??????</th>
                                    <th scope="col">?????????</th>
                                    <th scope="col">??????</th>
                                    <th scope="col">????????????</th>
                                    <th scope="col">??????</th>
                                    <th scope="col">?????????</th>
                                </tr>
                            </thead>
                            <tbody>
                             <c:forEach items="${qnaList}" var="qnaVO">
                                <tr>                 
                                    <td>${qnaVO.qseq }</td>
                                    <td><a href="qna_view?qseq=${qnaVO.qseq}"> ${qnaVO.title} </a></td>
                                    <td>${qnaVO.id }</td>
                                    <td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${qnaVO.indate}"/></td>
                                    <c:choose>
                                    <c:when test="${qnaVO.rep eq 0}">
                                    	<td>X</td>
                                    </c:when>
                                    <c:otherwise>
                                    	<td>O</td>
                                    </c:otherwise>
                                    </c:choose>
                                    
                                    <td><a class="btn btn-sm btn-primary" href="qna_answer_form?qseq=${qnaVO.qseq}">????????????</a></td>
                                    <td><a class="btn btn-sm btn-primary" href="qna_delete?qseq=${qnaVO.qseq}">????????????</a></td>
                                </tr>
                               </c:forEach>
                            </tbody>
                        </table>

                        <div style="display: flex; justify-content: center;">
                   			<ul class="pagination">    <!-- li????????? ???????????? disabled??? ????????? ???????????? ?????? ????????? ??? ?????? ?????? ????????? ????????? ????????? ?????? ?????????.-->    <!-- disabled??? ????????? ?????? ???????????? ???????????? ????????? ?????????. -->    
                   				<li>
                   				<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="index">
									<a href="admin_qna_form${pageMaker.makeQuery(index)}">[${index}]</a>
								</c:forEach>
								</li>
                   			</ul>
						</div>
                    </div>
                </div>
            </div>
            <!-- QnA ????????? ??? -->


           
            <!-- Widgets End -->


            <!-- Footer Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="bg-light rounded-top p-4">
                    <div class="row">
                        <div class="col-12 col-sm-6 text-center text-sm-start">
                            ?? <a href="#">Homme</a>, All Right Reserved. 
                        </div>
                        <div class="col-12 col-sm-6 text-center text-sm-end">
                            <!--/*** This template is free as long as you keep the footer author???s credit link/attribution link/backlink. If you'd like to use the template without the footer author???s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
                            Designed By <a href="https://htmlcodex.com">HTML Codex</a>
                        <br>
                        Distributed By <a class="border-bottom" href="https://themewagon.com" target="_blank">ThemeWagon</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Footer End -->
        </div>
        <!-- Content End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
    </div>

    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/chart/chart.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="lib/tempusdominus/js/moment.min.js"></script>
    <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
    <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>
</html>