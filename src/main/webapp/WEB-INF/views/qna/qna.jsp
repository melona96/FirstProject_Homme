<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<br>
<br>
<br>
<section class="bg0 p-t-23 p-b-140">
	<div class="container">
 			<div class="col-lg-6">
                    <div class="card" style="width: 1200px;">
                        <div class="card-header" >
                            <strong class="card-title">Homme's QnA</strong>
                        </div>
                        <div class="card-body">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col" style="width: 100">번호</th>
                                        <th scope="col">제목</th>
                                        <th scope="col">작성자</th>
                                        <th scope="col">날짜</th>
                                        <th scope="col">답변여부</th>
                                    </tr>
                                </thead>
								<tbody>
                                <c:forEach items="${qnaList}" var="qnaVO">
                                	<tr>
                                        <th scope="row">${qnaVO.qseq }</th>
                                        <td><a href="qna_view?qseq=${qnaVO.qseq}"> ${qnaVO.title}</a></td>
                                        <td>${qnaVO.id }</td>
                                        <td><fmt:formatDate value="${qnaVO.indate}" type="date"/></td>
                                        <c:choose>
                                        <c:when test="${qnaVO.rep eq 1}">
                                        	<td>답변완료</td>
                                        </c:when>
                                        <c:otherwise>
                                        	<td><td>
                                        </c:otherwise>
                                        </c:choose>
                                    </tr>
                                </c:forEach>
                                </tbody>
                           	 </table>
                       	 </div>
                       	 
                   <!-- 페이징 기능 구현 -->
                    <div style="display: flex; justify-content: center;">
                   	<ul class="pagination">   
                   		<li>
                   		<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="index">
							<a href="qna_form${pageMaker.makeQuery(index)}">[${index}]</a>
						</c:forEach>
						</li>
                   </ul>
					</div>
					</div>
						 
                    </div>
                    <div style="display: flex; justify-content: flex-start;">&nbsp;&nbsp;&nbsp;
                    	<button type="button" class="btn btn-secondary" style="clear: both;  margin-top: 10px; display:flex; justify-content: flex-start;" onclick="location.href='qna_write_form'">글쓰기</button>
                    </div>
           	</div> 	
    </div>
</section>

<%@ include file="footer.jsp" %>