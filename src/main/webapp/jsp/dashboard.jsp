<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp" %>

<div class="container-fluid">
<div class="row">
	<%@ include file="includes/sidebar.jsp" %>
    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
        <div class="page-header">
            <h1 class="h2"><i class="bi bi-speedometer2 me-2"></i>Dashboard</h1>
        </div>
		
        <!-- Statistics Cards -->
        <div class="row mb-4">
            <div class="col-md-3">
                <div class="card text-white stat-card primary mb-3">
                    <div class="card-body">
                        <i class="bi bi-book-fill stat-icon"></i>
                        <h5 class="card-title">Total Books</h5>
                        <p class="card-text">120</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card text-white stat-card warning mb-3">
                    <div class="card-body">
                        <i class="bi bi-arrow-right-circle-fill stat-icon"></i>
                        <h5 class="card-title">Books Assigned</h5>
                        <p class="card-text">45</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card text-white stat-card success mb-3">
                    <div class="card-body">
                        <i class="bi bi-arrow-left-circle-fill stat-icon"></i>
                        <h5 class="card-title">Books Returned</h5>
                        <p class="card-text">40</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card text-white stat-card info mb-3">
                    <div class="card-body">
                        <i class="bi bi-people-fill stat-icon"></i>
                        <h5 class="card-title">Users</h5>
                        <p class="card-text">30</p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Recent Books Table -->
        <h4 class="section-title">Currently Issued Books</h4>
        <div class="table-container">
            <div class="table-responsive">
                <table class="table table-hover align-middle">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Category</th>
                            <th>ISBN</th>
						    <th>Due Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><span class="badge bg-primary">1</span></td>
                            <td><strong>The Great Gatsby</strong></td>
                            <td>F. Scott Fitzgerald</td>
                            <td><span class="badge bg-info">Fiction</span></td>
                            <td><code>9780743273565</code></td>
						    <td><span class="badge bg-warning">12-08-2025</span></td>
                        </tr>
                        <tr>
                            <td><span class="badge bg-primary">2</span></td>
                            <td><strong>Clean Code</strong></td>
                            <td>Robert C. Martin</td>
                            <td><span class="badge bg-success">Technology</span></td>
                            <td><code>9780132350884</code></td>
						    <td><span class="badge bg-warning">15-08-2025</span></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
