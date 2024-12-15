<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.0.js"></script>
    <script>
        $(document).ready(function() {
            $("#showUsersButton").click(function() {
                $.ajax({
                    url: "${pageContext.request.contextPath}/test/getAllUser",
                    type: "GET",
                    success: function(data) {
                        $("#users").empty();
                        data.forEach(function(user) {
                            $("#users").append("<li>" + user.username + "</li>");
                        });
                    },
                    error: function() {
                        alert("加载用户失败");
                    }
                });
            });
        });
    </script>
</head>
<body>
    <h1>欢迎回来！</h1>
    <button id="showUsersButton">显示所有用户</button>
    <ul id="users"></ul>
</body>
</html>
