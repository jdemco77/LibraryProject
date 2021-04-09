<%@ include file="header-lib.jsp"%>
<div class="container">
	<h1>Past Check-Out List</h1>
	<br> <br>

	<table class="table table-striped">

		<thead>
			<tr>
				<th>Checkout ID</th>
				<th>Patron ID</th>
				<th>ISBN</th>
				<th>Date checked out</th>
				<th>Date returned</th>
			<!--	<th>Action</th> -->
			</tr>
		</thead>

		<tbody>

			<c:forEach var="BookCheckout" items="${allcheckouts}">
				<tr>
					<td><c:out value="${ BookCheckout.checkout_id }" /></td>
					<td><c:out value="${ BookCheckout.patron_id }" /></td>
					<td><c:out value="${ BookCheckout.isbn }" /></td>
					<td><c:out value="${ BookCheckout.due_date }" /></td>
					<td><c:out value="${ BookCheckout.returned }" /></td>

					<td><c:choose>
							<c:when test="${book.checkedout == true}">
								<a href="<%= request.getContextPath() %>/pastCheckout?checkedout<c:out value='${ BookCheckout.checkout_id }' />">
									
								</a>
								<br />
							</c:when>
						</c:choose></td>
				</tr>
			</c:forEach>

		</tbody>

	</table>
</div>
<%@ include file="footer.jsp"%>