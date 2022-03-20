
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="message">
    <table id="clazz_table" border="1">
        <thead>
        <tr>
            <th>id</th>
            <th>clazzName22</th>
            <th>clazzDesc22</th>
        </tr>
        </thead>
        <tbody id="clazz_table_body">

        </tbody>
    </table>
    <button id="prev_page">next</button>
    <button id="next_page">pre</button>
</div>
</body>
</html>
<script type="text/javascript" src="jquery.min.js"></script>
<script type="text/javascript">
    var start = 0;
    var size = 10;
    $(function ()
    {
        $.ajax({
            url: "http://localhost:8080/zx/test",
            method: "post",
            data: {'start': start, 'size': size},
            xhrFields: {
                withCredentials: true //允许跨域带Cookie
            },
            success: function (result) {
                // $("#message").html(result);
                var result2=eval("("+result+")");
                // $("#message").html(result);
                for (var i = 0; i < result2.length; i++) {
                    var trTemp = $("<tr id='tr" + result2[i].id + "'></tr>");
                    trTemp.append("<td>" + result2[i].id+ "</td>");
                    trTemp.append("<td>" + result2[i].clazzName + "</td>");
                    trTemp.append("<td>" + result2[i].clazzDesc + "</td>");
                    console.log(result2);
                    trTemp.appendTo("#clazz_table_body");
                }
            },
            error: function () {
                window.location.href = "./TestCors.html";
            }
        })
    },
    $("#next_page").click(function pageReq() {
        $.ajax({
                url: "http://localhost:8080/zx/test",
                method: "post",
                data: {'start': start += 10, 'size': size},
                xhrFields: {
                    withCredentials: true //允许跨域带Cookie
                },
                success: function (result) {
                    // $("#message").html(result);
                    $("#clazz_table_body").empty();
                    var result2=eval("("+result+")");
                    // $("#message").html(result);
                    // $("#clazz_table_body").empty();
                    for (var i = 0; i < result2.length; i++) {
                        var trTemp = $("<tr id='tr" + result2[i].id + "'></tr>");
                        trTemp.append("<td>" + result2[i].id+ "</td>");
                        trTemp.append("<td>" + result2[i].clazzName.val + "</td>");
                        trTemp.append("<td>" + result2[i].clazzDesc + "</td>");
                        console.log(result2);
                        trTemp.appendTo("#clazz_table_body");
                    }
                },
                error: function () {
                    window.location.href = "./TestCors.html";
                }
            }
        )
    }),
        $("prev_page").click(function pageReq() {
            var start_tmp = start - 10;
            if (start_tmp <= 0) {
                alert("已经是第一页了哦~~~~~");
                return;
            }
            $.ajax({
                    url: "http://localhost:8080/zx/test",
                    method: "post",
                    data: {'start': start -= 10, 'size': size},
                    xhrFields: {
                        withCredentials: true //允许跨域带Cookie
                    },
                    success: function (result) {
                        var result2=eval("("+result+")");
                        // $("#message").html(result);
                        // $("#clazz_table_body").empty();
                        for (var i = 0; i < result2.length; i++) {
                            var trTemp = $("<tr id='tr" + result2[i].id + "'></tr>");
                            trTemp.append("<td>" + result2[i].id+ "</td>");
                            trTemp.append("<td>" + result2[i].clazzName + "</td>");
                            trTemp.append("<td>" + result2[i].clazzDesc + "</td>");
                            console.log(trTemp.innerHTML);
                            trTemp.appendTo("#clazz_table_body");
                        }
                    },
                    error: function () {
                        window.location.href = "./TestCors.html";
                    }
                }
            )
        }));
</script>