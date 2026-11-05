<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="includes/header.jsp" %>

<div class="container-fluid">
<div class="row">

	<%@ include file="includes/sidebar.jsp" %>
    <!-- Main content -->
    <main class="col-md-9 ms-sm-auto col-lg-10">
        <div class="page-header">
            <h1 class="h2"><i class="bi bi-book-fill me-2"></i>Add New Book</h1>
        </div>
		
		
		<c:if test="${not empty errorMessage}">
			<div class="alert alert-danger alert-dismissible fade show" role="alert">
					<c:out value="${errorMessage}" />
					<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
		</c:if>
	

        <div class="form-container">
            <form id="add-book-form" action="BookController" method="post">
            	<input type="hidden" name="action" value="addBook"/>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-floating">
                            <input type="text" class="form-control" name="bookTitle" id="bookTitle" placeholder="Book Title">
                            <label for="bookTitle"><i class="bi bi-book me-2"></i>Book Title</label>
                        </div>
                       <div class="text text-danger text-small" id="bookTitleError"></div> 
                    </div>
                    <div class="col-md-6">
                        <div class="form-floating">
                            <input type="text" class="form-control" name="author" id="author" placeholder="Author">
                            <label for="author"><i class="bi bi-person me-2"></i>Author</label>
                        </div>
                        <div class="text text-danger text-small" id="authorError"></div> 
                    </div>
                    
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-floating">
                            <input type="text" class="form-control" name="isbn" id="isbn" placeholder="ISBN">
                            <label for="isbn"><i class="bi bi-hash me-2"></i>ISBN Number</label>
                        </div>
                        <div class="text text-danger text-small" id="isbnError"></div> 
                    </div>
                    <div class="col-md-6">
                        <div class="form-floating">
                            <select class="form-select" name="category" id="category">
                                <option selected disabled value="">Select category</option>
                                <option value="fiction">Fiction</option>
                                <option value="non-fiction">Non-fiction</option>
                                <option value="science">Science</option>
                                <option value="technology">Technology</option>
                                <option value="history">History</option>
                                <option value="biography">Biography</option>
                                <option value="mystery">Mystery</option>
                                <option value="romance">Romance</option>
                            </select>
                            <label for="category"><i class="bi bi-collection me-2"></i>Category</label>
                        </div>
                       <div class="text text-danger text-small" id="categoryError"></div>  
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-floating">
                            <input type="text" class="form-control" name="publisher" id="publisher" placeholder="Publisher">
                            <label for="publisher"><i class="bi bi-building me-2"></i>Publisher</label>
                        </div>
                        <div class="text text-danger text-small" id="publisherError"></div>                         
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-floating">
                            <input type="number" class="form-control" name="availableCopies" id="pages" placeholder="Pages">
                            <label for="pages"><i class="bi bi-file-text me-2"></i>Available Copies</label>
                        </div>
                        <div class="text text-danger text-small" id="availableCopiesError"></div>                                                 
                    </div>
                    <div class="col-md-6">
                        <div class="form-floating">
                            <input type="number" class="form-control" name="totalCopies" id="copies" placeholder="Copies" min="1" value="1">
                            <label for="copies"><i class="bi bi-stack me-2"></i>Total Copies</label>
                        </div>
                         <div class="text text-danger text-small" id="totalCopiesError"></div>                                                
                    </div>
                </div>


                <div class="form-actions">
                    <button type="submit" class="btn-modern">
                        <i class="bi bi-save"></i>Save Book
                    </button>
                </div>
            </form>
        </div>
    </main>
</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
function setBookAddValidation() {
	console.log("Inside validation 1");
	const form = document.getElementById('add-book-form');
	
	if (!form) {
		return;
	}
	
	console.log("Inside validation");
	
	form.addEventListener('submit', function(event) {
		console.log("Inside form");
		const bookTitleError = document.getElementById('bookTitleError');
		const authorError = document.getElementById('authorError');
		const isbnError = document.getElementById('isbnError');
		const categoryError = document.getElementById('categoryError');
		const publisherError = document.getElementById('publisherError');
		const availableCopiesError = document.getElementById('availableCopiesError');
		const totalCopiesError = document.getElementById('totalCopiesError');
		
		bookTitleError.textContent = '';
		authorError.textContent = '';
		isbnError.textContent = '';
		categoryError.textContent = '';
		publisherError.textContent = '';
		availableCopiesError.textContent = '';
		totalCopiesError.textContent = '';
		
		
		const bookTitle = document.getElementById('bookTitle').value.trim();
		const author = document.getElementById('author').value.trim();
		const isbn = document.getElementById('isbn').value.trim();
		const category = document.getElementById('category').value.trim();
		const publisher = document.getElementById('publisher').value.trim();
		const availableCopies = document.getElementById('pages').value.trim();
		const totalCopies = document.getElementById('copies').value.trim();

		let hasError = false;
		console.log("Inside validation");
		if (!bookTitle) {
			bookTitleError.textContent = "Please enter title.";
			hasError = true;
		} 
			
		if (!author) {
			authorError.textContent = "Please enter author.";
			hasError = true;
		} 
		
		if (!isbn) {
			isbnError.textContent = "Please enter isbn.";
			hasError = true;
		} 
		
		if (!category) {
			categoryError.textContent = "Please enter category.";
			hasError = true;
		}
		
		if (!publisher) {
			publisherError.textContent = "Please enter publisher.";
			hasError = true;
		} 
		
		if (!totalCopies) {
			totalCopiesError.textContent = "Please enter the total copies.";
			hasError = true;
		} else {
			totalCopiesNum = Number(totalCopies);
			if (Number.isNaN(totalCopiesNum) || totalCopiesNum < 1) {
				totalCopiesError.textContent = "Total copies should be at least 1";
				hasError = true;
			}
		}
		
		if (!availableCopies) {
			availableCopiesError.textContent = "Please enter the available copies.";
			hasError = true;
		} else {
			availableCopiesNum = Number(availableCopies);
			if (Number.isNaN(availableCopiesNum) || availableCopiesNum < 1) {
				availableCopiesError.textContent = "Available copies should be at least 1";
				hasError = true;
			} else if (availableCopiesNum > totalCopiesNum) {
				availableCopiesError.textContent = "Available copies should be less than or equal to totalCopies";
				hasError = true;
			}
		}
		
		if (hasError) {
			event.preventDefault();
		}
	});	
}

if (document.readyState == 'loading') {
	document.addEventListener('DOMContentLoaded', setBookAddValidation);
} else {
	setBookAddValidation();
}
</script>
</body>
</html>
