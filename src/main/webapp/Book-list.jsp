<%@ include file="header.jsp"%>
<div class="container">
	<h1>Product List</h1>
	<br> <br>

	<table class="table table-striped">

		<thead>
			<tr>
				<th>ISBN</th>
				<th>Title</th>
				<th>Description</th>
				<th>Available</th>
				<th>Date Added</th>
				<th>Action</th>
			</tr>
		</thead>

		<tbody>

<!-- comment -->
			<c:forEach var="book" items="${allBooks}">
				<tr>
					<td><c:out value="${ book.isbn }" /></td>
					<td><c:out value="${ book.title }" /></td>
					<td><c:out value="${ book.descr }" /></td>
					<td><c:out value="${ book.rented }" /></td>
					<td><c:out value="${ book.added_to_library }" /></td>
					<td><a href="edit?id=<c:out value='${ book.isbn }' />">
							<button class="btn btn-primary">Edit</button>
					</a>&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="delete?id=<c:out value='${ book.isbn }' />">
							<button class="btn btn-danger">Delete</button>
					</a></td>
				</tr>
			</c:forEach>

		</tbody>

	</table>
</div>
<%@ include file="footer.jsp"%>