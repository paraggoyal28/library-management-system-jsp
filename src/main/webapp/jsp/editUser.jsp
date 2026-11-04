<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ include file="includes/header.jsp" %>
<div class="container-fluid">
<div class="row">
    <!-- Sidebar -->
    <%@ include file="includes/sidebar.jsp" %>

    <!-- Main content -->
	<main class="col-md-9 ms-sm-auto col-lg-10">
        <div class="page-header">
            <h1 class="h2"><i class="bi bi-person-plus me-2"></i>Update User</h1>
        </div>=

		<c:if test="${not empty successMessage}">
			<div class="alert alert-success alert-dismissible fade show" role="alert">
					<c:out value="${successMessage}" />
					<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
		</c:if>
        
        <c:if test="${not empty errorMessage}">
			<div class="alert alert-danger alert-dismissible fade show" role="alert">
					<c:out value="${errorMessage}" />
					<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
		</c:if>
        
        <!-- User Type Selection -->
        <div class="form-container">
            <form id="userForm" action="UserController" method="post">
            	<input type="hidden" name="action" value="updateUser" />
            	<input type="hidden" name="userId" value="${user.userId}" /> 
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-floating">
                            <input type="text" class="form-control" name="firstName" value="${user.firstName}" id="firstName" placeholder="First Name">
                            <label for="firstName"><i class="bi bi-person me-2"></i>First Name</label>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-floating">
                            <input type="text" class="form-control" name="lastName" value="${user.lastName}" id="lastName" placeholder="Last Name">
                            <label for="lastName"><i class="bi bi-person me-2"></i>Last Name</label>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-floating">
                            <input type="email" class="form-control" name="email" value="${user.email}" id="email" placeholder="Email" readonly="readonly">
                            <label for="email"><i class="bi bi-envelope me-2"></i>Email Address</label>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-floating">
                            <input type="tel" class="form-control" name="phone" value="${user.phone_no}" id="phone" placeholder="Phone">
                            <label for="phone"><i class="bi bi-telephone me-2"></i>Phone Number</label>
                        </div>
                    </div>
                </div>


                <div class="form-floating">
                    <textarea class="form-control" id="address"  name="address" placeholder="Address">${user.address}</textarea>
                    <label for="address"><i class="bi bi-geo-alt me-2"></i>Address</label>
                </div>

               
                <div class="form-actions">
                    <button type="submit" class="btn-modern">
                        <i class="bi bi-person-plus"></i>Update User
                    </button>
                </div>
            </form>
        </div>
    </main>

</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
