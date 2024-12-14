<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2>上传页面</h2>
<form action="${pageContext.request.contextPath}/file/upload" enctype="multipart/form-data" method="post">
    上传：<input type="file" name="file"/><br/>
    <input type="submit" value="上传">
</form>
