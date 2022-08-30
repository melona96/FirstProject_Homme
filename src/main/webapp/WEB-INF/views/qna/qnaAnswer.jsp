<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<br>
<br>
<section class="bg0 p-t-23 p-b-140">
	<div class="container">
 		<div class="col-lg-6">
                        <div class="card" style="width: 1200px;">
                            <div class="card-header">
                                <strong>Homme</strong> 문의 내용
                            </div>
                            <div class="card-body card-block">
                                <form action="qna_answer" method="post" enctype="multipart/form-data" class="form-horizontal">
                                <input type="hidden" name="qseq" value="${qnaVO.qseq}">
                                <input type="hidden" name="title" value="${qnaVO.title}">
                                <input type="hidden" name="id" value="${qnaVO.id}">
                                    <div class="row form-group">
                                        <div class="col col-md-3"><label class=" form-control-label">제목</label></div>
                                        <div class="col-12 col-md-9">
                                            <p class="form-control-static">${qnaVO.title }</p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row form-group">
                                        <div class="col col-md-3"><label class=" form-control-label">작성자</label></div>
                                        <div class="col-12 col-md-9">
                                            <p class="form-control-static">${qnaVO.id }</p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row form-group">
                                        <div class="col col-md-3"><label for="textarea-input" class=" form-control-label">문의 내용</label></div>
                                        <div class="col-12 col-md-9">
                                        <textarea name="content" id="content" rows="25" class="form-control" readonly>${qnaVO.content }</textarea></div>
                                    </div>
                                    <hr>
                                    
                                    <div class="row form-group">
                                        <div class="col col-md-3"><label for="textarea-input" class=" form-control-label">답변 내용</label></div>
                                        <div class="col-12 col-md-9">
                                        <textarea name="reply" id="reply" rows="25" class="form-control" placeholder="답변 내용을 적어주세요"></textarea></div>
                                    </div>
                                    <button type="submit" class="btn btn-secondary" style="float: left;  margin-top: 10px; width: 120px;">답글달기</button>  
                                   </form>
							</div>
						</div>		 
			</div>                       
 		</div>  	 
</section>
<%@ include file="footer.jsp" %>