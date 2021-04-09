<%@ include file="header-lib.jsp"%>
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
				<form action="<%=request.getContextPath()%>/updateBook" method="POST">
				<tr>
					<td><c:out value="${ book.isbn }" /></td>
					<td><input type="text"
									name="bookTitle" class="form-control"
									value="${ book.title }"></td>
					<td><input type="text"
									name="bookTitle" class="form-control"
									value="${ book.descr }"></td>									
					<td><c:out value="${ book.added_to_library }" /></td>

					<td>
									<button class="btn btn-primary" type="submit">Save</button>
								<br /></td>
				</tr>
				</form>
			</c:forEach>

		</tbody>

	</table>
</div>
<%@ include file="footer.jsp"%>