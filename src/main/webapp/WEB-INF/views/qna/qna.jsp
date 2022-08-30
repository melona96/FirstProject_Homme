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
                       	 
                    <!--  <tr><td colspan="6" style="text-align: center;"> ${paging} </td></tr>-->
                    <div style="display: flex; justify-content: center;">
                   	<ul class="pagination">    <!-- li태그의 클래스에 disabled를 넣으면 마우스를 위에 올렸을 때 클릭 금지 마크가 나오고 클릭도 되지 않는다.-->    <!-- disabled의 의미는 앞의 페이지가 존재하지 않다는 뜻이다. -->    
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