<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="../include/header.jsp" %>

    <section>
       <div class="container">
            <div class="row">
                <div class="col-xs-12 content-wrap">
                    <div class="titlebox">
                        <p>자유게시판</p>
                    </div>
                    
                    <!-- 값을 받기 위해 name을 붙여야한다 -->
                    <form method="post"> <!-- action생략시 마지막으로 요청됐던 url을 재활용한다-->
                        <table class="table">
                            <tbody class="t-control">
                                <tr>
                                    <td class="t-title">NAME</td>
                                    <td><input class="form-control input-sm" name="writer" value="${login}" readonly></td> <!-- session.scope.login인데 이름이 똑같아서 생략 -->
                                </tr>
                                <tr>
                                    <td class="t-title">TITLE</td>
                                    <td><input class="form-control input-sm" name="title"></td>
                                </tr>
                                <tr>
                                    <td class="t-title">COMMENT</td>
                                    <td>
                                    <textarea class="form-control" rows="7" name="content"></textarea>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <div class="titlefoot">
                            <button type="submit" class="btn">등록</button>
                            <button type="button" class="btn" onclick="location.href='${pageContext.request.contextPath}/freeboard/freeList'">목록</button>
                            <!-- button type생략시 submit으로 동작 -->
                        </div>
                    </form>
                    
                </div>
            </div>    
       </div>
    </section>
  
      <%@ include file="../include/footer.jsp" %>
  