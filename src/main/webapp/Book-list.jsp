<%@ include file="header.jsp"%>
<div class="container">
	<h1>Available Books</h1>
	<br> <br>

	<table class="table table-striped">

		<thead>
			<tr>
				<th>ISBN</th>
				<th>Title</th>
				<th>Description</th>
				<th>Date Added</th>
				<th>Action</th>
			</tr>
		</thead>

		<tbody>

			<c:forEach var="book" items="${allBooks}">
				<tr>
					<td><c:out value="${ book.isbn }" /></td>
					<td><c:out value="${ book.title }" /></td>
					<td><c:out value="${ book.descr }" /></td>
					<td><c:out value="${ book.added_to_library }" /></td>

					<td><c:choose>
							<c:when test="${book.rented == false}">
								<a href="/checkout?isbn=<c:out value='${ book.isbn }' /> &patron_id <=c:out value='${ patron_id }'"> 
									<button class="btn btn-primary">Checkout</button>
								</a>
								<br />
							</c:when>
							<c:otherwise>
								<button class="btn btn-primary disabled">Unavailable</button>
								<br />
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>

		</tbody>

	</table>
</div>
<%@ include file="footer.jsp"%>