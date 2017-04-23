<%--
  Created by IntelliJ IDEA.
  User: nikolay
  Date: 21.11.15
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<spring:url value="/resources/js/flotr2.min.js" var="js" />

<t:page notShow="true">
  <jsp:body>
    <style type="text/css">
      body {
        margin: 0;
        padding: 0;
      }
      #container {
        /*width : 600px;*/
        /*height: 384px;*/
        margin: 8px auto;
      }
    </style>
    <div id="container"></div>
    <script type="text/javascript" src="${js}"></script>
    <script type="text/javascript">
      (function () {

        var cont = $('#container');
        cont.height($(window).height() - 50);
        cont.width($(window).width() - 50);
        var container = document.getElementById('container');


        Flotr.draw(container, ${graphic.toJSON()}, {
          xaxis : {
            min: ${graphic.minX},
            max: ${graphic.maxX},
            ticks: ${graphic.ticks}
          },
          yaxis : {

            max : ${graphic.maxY + 1},
            min : ${graphic.minY - 1}
          }
        });

        function ticksFn(n) {
          var date = new Date(n);
          return date.toString();
        }

      })();
    </script>

  </jsp:body>
</t:page>
