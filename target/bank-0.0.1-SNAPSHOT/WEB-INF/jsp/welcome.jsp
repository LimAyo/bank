<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Bank</a>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="starter-template">
			<h1>Transactions</h1>
			<table class="table table-dark">
				<thead>
				<tr>
					<th scope="col">Transaction ID</th>
					<th scope="col">Transaction Date</th>
					<th scope="col">Amount</th>
					<th scope="col">Description</th>
					<th scope="col">Start Date</th>
					<th scope="col">End Date</th>
					<th scope="col">File name</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${transactions}" var="transaction">
				<tr>
					<th scope="row">${transaction.transactionID}</th>
					<td><fmt:formatDate pattern = "yy/MM/dd" value = "${transaction.transactionDate}" /></td>
					<td>${transaction.amount}</td>
					<td>${transaction.description}</td>
					<td><fmt:formatDate pattern = "yy/MM/dd" value = "${transaction.startDate}" /></td>
					<td><fmt:formatDate pattern = "yy/MM/dd" value = "${transaction.endDate}" /></td>
					<td>${transaction.fileName}</td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>

	</div>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
