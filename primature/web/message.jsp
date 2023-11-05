<%
    String message = (String) request.getAttribute("message");
    String numCin = (String) request.getAttribute("num_cin");
%>
<script>
    alert("<%= message %>");
    window.location.href = "http://localhost:8080/primature/front?num_cin=<%= numCin %>";
</script>