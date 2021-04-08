<%@ include file="header.jsp"%>
<div class="container">
	<h1>Product List</h1>
	<br> <br>

	<table class="table table-striped">

		<thead>
			<tr>
				<th>ID</th>
				<th>First Name</th>
				<th>Last name</th>
				<th>Username</th>
				<th>Password</th>
				<th>action</th>
			</tr>
		</thead>

		<tbody>

			<c:forEach var="patron" items="${allPatrons}">
				<tr>
					<td><c:out value="${ patron.patron_id }" /></td>
					<td><c:out value="${ patron.first_name }" /></td>
					<td><c:out value="${ patron.last_name }" /></td>
					<td><c:out value="${ patron.username}" /></td>
					<td><c:out value="${ patron.password }" /></td>


					<td><c:choose>
							<c:when test="${patron.account_frozen == true}">
								<a
									href="<%= request.getContextPath() %>/approve?patron_id=<c:out value='${ patron.patron_id }' />">
									<button class="btn btn-primary">Approve Acct</button>
								</a>
								<br />
							</c:when>
							<c:otherwise>
								<a 
									href="<%= request.getContextPath() %>/freeze?patron_id=<c:out value='${ patron.patron_id }' />">
									<button class="btn btn-danger">Freeze Acct</button>
								</a>
								<br />
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>

		</tbody>

	</table>
</div>
<%@ include file="footer.jsp"%>