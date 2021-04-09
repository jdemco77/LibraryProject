<%@ include file="header.jsp"%>
<div class="container">
	<h1>My Books</h1>
	<br> <br>

	<table class="table table-striped">

		<thead>
			<tr>
				<th>ISBN</th>
				<th>Title</th>
				<th>Date Rented</th>
				<th>Due Date</th>
				<th>Action</th>
			</tr>
		</thead>

		<tbody>

			<c:forEach var="book" items="${allBooks}">
				<c:forEach var="userCheckouts" items="${userCheckouts}">
					<c:if test="${book.isbn eq userCheckouts.isbn}" >
						<tr>
							<td><c:out value="${ book.isbn }" /></td>
							<td><c:out value="${ book.title }" /></td>
							<td><c:out value="${ userCheckouts.checkoutdate }" /></td>
							<td><c:out value="${ userCheckouts.duedate }" /></td>
							<td><c:choose>
									<c:when test="${userCheckouts.returned == null}">
										<a href="<%= request.getContextPath() %>/return?isbn=<c:out value='${ book.isbn }' />&patron_id=<c:out value='${ userCheckouts.patron_id }' />">
											<button class="btn btn-primary">Return</button>
										</a>
										<br />
									</c:when>
									<c:otherwise>
										<button class="btn btn-primary disabled">Returned</button>
										<br />
									</c:otherwise>
								</c:choose></td>
						</tr>
					</c:if>
				</c:forEach>
			</c:forEach>

		</tbody>

	</table>
</div>
<%@ include file="footer.jsp"%>