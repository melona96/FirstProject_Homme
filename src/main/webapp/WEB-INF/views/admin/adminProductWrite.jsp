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
                        <span>관리자</span>
                    </div>
                </div>
                <div class="navbar-nav w-100">
                    <a href="admin_main" class="nav-item nav-link"><i class="fa fa-tachometer-alt me-2"></i>관리자 대쉬보드</a>
                    <a href="admin_sales_form" class="nav-item nav-link"><i class="fa fa-chart-bar me-2"></i>판매 실적</a>
                    <a href="admin_qna_form" class="nav-item nav-link"><i class="fa fa-laptop me-2"></i>QnA 관리</a>
                    <a href="admin_memberList" class="nav-item nav-link"><i class="fa fa-laptop me-2"></i>회원 관리</a>
                    <a href="admin_productList" class="nav-item nav-link active"><i class="fa fa-laptop me-2"></i>상품 관리</a>
                </div>
            </nav>
        </div>
        <!-- Sidebar End -->


        <!-- Content Start -->
        <div class="content" style="width: 1500px;">
            <!-- Navbar Start -->
            <nav class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
                <a href="index.html" class="navbar-brand d-flex d-lg-none me-4">
                    <h2 class="text-primary mb-0"><i class="fa fa-hashtag"></i></h2>
                </a>
                
                <div class="navbar-nav align-items-center ms-auto">
                </div>
            </nav>
            <!-- Navbar End -->
    
            <!-- QnA 리스트 시작   -->
            <form action="insert_product_action" method="post" enctype="multipart/form-data">
            <div class="container-fluid pt-4 px-4">
                <div class="bg-light text-center rounded p-4">
                	
                    <div class="d-flex align-items-center justify-content-between mb-4">
                    <!--         
                    </div>
                    
                    <div class="container-fluid pt-4 px-4">
                <div class="row g-4">
                    <div class="col-sm-12 col-xl-6">
                        <div class="bg-light rounded h-100 p-4">
                            
                        </div>
                    </div>
                    <div class="col-sm-12 col-xl-6">
                        <div class="bg-light rounded h-100 p-4">
                          
                        </div>
                    </div>
                    -->
                    
                    <div class="col-sm-12 col-xl-6">
                        <div class="bg-light rounded h-100 p-4">
                            <h6 class="mb-4">Homme 상품 등록</h6>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="floatingInput" name="name"
                                    placeholder="상품명을 입력해주세요.">
                                <label for="floatingInput">상품명</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="floatingPassword" name="price"
                                    placeholder="가격을 입력해주세요.">
                                <label for="floatingPassword">가격</label>
                            </div>
                            <div class="form-floating mb-3">
                                <select class="form-select" id="floatingSelect"
                                    aria-label="Floating label select example" name="kind">
                                    <option selected>상품 종류</option>
                                    <option value="suits">수트</option>
                                    <option value="casual">캐주얼</option>
                                    <option value="vintage">빈티지</option>
                                    <option value="sporty">스포티</option>
                                </select>
                                <label for="floatingSelect">상품 종류</label>
                            </div>
                            <div class="form-floating mb-3">
                                <select class="form-select" id="floatingSelect" name="psize"
                                    aria-label="Floating label select example">
                                    <option selected>상품 사이즈</option>
                                    <option value="S">S</option>
                                    <option value="M">M</option>
                                    <option value="L">L</option>
                                    <option value="XL">XL</option>
                                </select>
                                <label for="floatingSelect">상품 사이즈</label>
                            </div>
                            <div class="form-floating">
                                <textarea class="form-control" placeholder="상품 설명을 적어주세요." name="content"
                                    id="floatingTextarea" style="height: 200px;"></textarea>
                                <label for="floatingTextarea">상품 설명</label>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-sm-12 col-xl-6">
                        <div class="bg-light rounded h-100 p-4">
                            <h6 class="mb-4">상품 이미지 선택</h6>
                            <div class="mb-3">
                                <label for="formFile" class="form-label">대표 이미지</label>
                                <input class="form-control" type="file" id="formFile" name="file1">
                            </div>
                            <div class="mb-3">
                                <label for="formFileMultiple" class="form-label">상품 이미지2</label>
                                <input class="form-control" type="file" id="formFileMultiple" name="file2">
                            </div>
                            <div class="mb-3">
                                <label for="formFileMultiple" class="form-label">상품 이미지3</label>
                                <input class="form-control" type="file" id="formFileMultiple" name="file3">
                            </div>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
            	<button type="submit" class="btn btn-sm btn-primary"> </button>  
            </form>
            <!-- QnA 리스트 끝 -->

            <!-- Footer Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="bg-light rounded-top p-4">
                    <div class="row">
                        <div class="col-12 col-sm-6 text-center text-sm-start">
                            © <a href="#">Homme</a>, All Right Reserved. 
                        </div>
                        <div class="col-12 col-sm-6 text-center text-sm-end">
                            <!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
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