<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%request.setCharacterEncoding("utf-8"); 
response.setCharacterEncoding("utf-8");%>
    <%
    
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
            <base href="<%=basePath%>">
        <!--//    echarts CDN-->
        <script src='https://cdn.bootcss.com/echarts/3.7.0/echarts.simple.js'></script>
        <!--//    下载wordcloud.js文件
        //    https://github.com/ecomfe/echarts-wordcloud-->
        <script src="${pageContext.request.contextPath}/plug-ins/js/echarts-wordcloud.min.js"></script>
        <script src="${pageContext.request.contextPath}/plug-ins/js/jquery-1.10.2.min.js"></script>
    </head>
    <body>
        <style>
            html, body{
                width: 100%;
                height: 100%;
                margin: 0;
            }
            #main{
                width: 600px;
                height: 500px;
                background: rgba(70, 120, 200, 0.2)
            }
        </style>
        <div id='main'></div>
        <script>
        var chart = echarts.init(document.getElementById('main'));
		var postURL = "/lunwen/ShowServlet";
		var mydata = new Array();
		$.ajaxSettings.async = false;
		$.post(postURL, {}, function(rs) {
			var dataList = JSON.parse(rs);
			for (var i = 0; i < dataList.length; i++) {
				var d = {};
				d['name'] = dataList[i].name;
				d['value'] = dataList[i].value;
				mydata.push(d);
			}
		});
		$.ajaxSettings.async = true;
		var option = {
			tooltip : {},
			series : [ {
				type : 'wordCloud',
				gridSize : 2,
				sizeRange : [ 20, 50 ],
				rotationRange : [ -90, 90 ],
				shape : 'pentagon',
				width : 800,
				height : 600,
				drawOutOfBound : false,
				textStyle : {
					normal : {
						color : function() {
							return 'rgb('
									+ [ Math.round(Math.random() * 160),
											Math.round(Math.random() * 160),
											Math.round(Math.random() * 160) ]
											.join(',') + ')';
						}
					},
					emphasis : {
						shadowBlur : 10,
						shadowColor : '#333'
					}
				},
				data : mydata
			} ]
		};
		chart.setOption(option);
		chart.on('click', function(params) {
			var url = "ClickServlet?name=" + params.name;
			window.location.href = url;
		});
 
            
        </script>
        <div>
		<table class="table table-hover">
			<thead>
				<tr>
					<td style="font-size: 20px;">论文链接</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list2}" var="data" varStatus="vs">
					<tr>
						<td><a href="${data.link}">${data.title}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
        
    </body>
</html>