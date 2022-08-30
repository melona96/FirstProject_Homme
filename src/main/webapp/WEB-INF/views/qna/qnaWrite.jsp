<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<br>
<br>
<section class="bg0 p-t-23 p-b-140">
	<div class="container">
 			<div class="col-lg-6">
                    <div class="card" style="width: 1200px;">
                       <div class="card">
                            <div class="card-header">
                                <strong>Homme</strong> 문의하기
                            </div>
                            <div class="card-body card-block">
                                <form action="qna_write" method="post" enctype="multipart/form-data" class="form-horizontal">
                                    <div class="row form-group">
                                        <div class="col col-md-3" style="width: 50px;"><label for="title" class=" form-control-label">제목</label></div>
                                        <div class="col-12 col-md-9"><input type="text" id="title" name="title" placeholder="제목" class="form-control"></div>
                                    </div>
                                    
                                    <div class="row form-group">
                                        <div class="col col-md-3"><label for="content" class=" form-control-label" >문의 내용</label></div>
                                        <div class="col-12 col-md-9"><textarea name="content" id="content" rows="25" placeholder="문의 내용을 적어주세요." class="form-control" style="width:861px;"></textarea></div>
                                    </div>
                             </div>
                       </div>         
                    </div>
                    
                       <div class="card-footer">
                            <button type="submit" class="btn btn-primary btn-sm">
                                <i class="fa fa-dot-circle-o"></i> 글쓰기
                            </button>
                            <button type="reset" class="btn btn-danger btn-sm">
                                <i class="fa fa-ban"></i> 취소하기
                            </button>
                            </form>
                   	 </div>   		
  			</div>
	</div>
</section>

<%@ include file="footer.jsp" %>