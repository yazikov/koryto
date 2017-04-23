<%@ tag import="java.util.ArrayList" %>
<%@ tag import="java.util.List" %>
<%@ tag import="java.util.HashMap" %>
<%@ tag import="java.util.LinkedHashMap" %>
<%@ tag import="java.util.Map" %>
<%@ tag import="java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="pageNumber" required="false" type="java.lang.Long" %>
<%@ attribute name="size" required="false" type="java.lang.Integer" %>
<%@ attribute name="count" required="false" type="java.lang.Long" %>
<%@ attribute name="pageCount" required="false" type="java.lang.Long" %>

<%
    String url = (String) request.getAttribute("javax.servlet.forward.request_uri");
    String baseURL = url;
    if (request.getQueryString() != null && !request.getQueryString().isEmpty()) {
        String decodedQueryString = URLDecoder.decode(request.getQueryString(), "UTF-8");
        baseURL = baseURL + "?" + decodedQueryString;
    }
    if (baseURL.contains("?")) {
        if (baseURL.contains("&")) {
            baseURL = baseURL.replaceAll("&page=" + pageNumber, "").replaceAll("page=" + pageNumber + "&", "");
            baseURL = baseURL + "&page=";
        } else {
            baseURL = baseURL.replaceAll("page=" + pageNumber, "");
            baseURL = baseURL + "page=";
        }
    } else {
        baseURL = url + "?page=";
    }
    request.setAttribute("baseURL", baseURL);
    String first = baseURL + 1;
    String last = baseURL + pageCount;
    String next = baseURL + (pageNumber + 1);
    String prev = baseURL + (pageNumber - 1);

    Long start = 1l;
    if (pageNumber > 2) {
        start = pageNumber - 2;
    }
    if ((pageNumber == pageCount - 1 || pageNumber.equals(pageCount)) && pageCount > 5) {
        start = pageCount - 4;
    }
    Long end = start + 4 < pageCount ? start + 4 : pageCount;

    Map<Long, String> pages = new LinkedHashMap<>();

    for (long i = start; i <= end; i++) {
        pages.put(i, baseURL + i);
    }

%>

<c:if test="${pageCount > 1}">
<div class="pagination_container">
    <ul class="pagination pagination-lg">
        <c:set var="disabled" value='${(pageNumber == 1) ? "disabled" : ""}' />
        <li class="${disabled}">
            <a href='<%= pageNumber == 1 ? "#" : first %>' aria-label="First">
                <span aria-hidden="true"><<</span>
            </a>
        </li>
        <li class="${disabled}">
            <a href='<%= pageNumber == 1 ? "#" :  prev %>' aria-label="Previous">
                <span aria-hidden="true"><</span>
            </a>
        </li>

        <c:forEach items="<%= pages %>" var="p">
            <c:set var="active" value='${p.key == pageNumber ? "active" : ""}' />
            <li class="${active}">
                <a href="${p.value}"><c:out value="${p.key}" /></a>
            </li>
        </c:forEach>

        <c:set var="disabled" value='${(pageNumber == pageCount) ? "disabled" : ""}' />
        <li class="${disabled}">
            <a href='<%= pageNumber.equals(pageCount) ? "#" : next %>' aria-label="Next">
                <span aria-hidden="true">></span>
            </a>
        </li>
        <li class="${disabled}">
            <a href='<%= pageNumber.equals(pageCount) ? "#" : last %>' aria-label="Last">
                <span aria-hidden="true">>></span>
            </a>
        </li>
    </ul>
</div>
</c:if>