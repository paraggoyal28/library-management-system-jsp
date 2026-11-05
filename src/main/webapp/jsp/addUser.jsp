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
            <h1 class="h2"><i class="bi bi-person-plus me-2"></i>Add New User</h1>
        </div>

        <!-- Info Card -->
        <!-- <div class="info-card">
            <div class="icon">
                <i class="bi bi-info-circle"></i>
            </div>
            <h5>User Registration</h5>
            <p class="mb-0">Register new library members and staff. Choose the appropriate user type and fill in the required information to create a new account.</p>
        </div> -->

        <!-- User Type Selection -->
        <div class="form-container">
            <form id="userForm" action="UserController" method="post">
            	<input type="hidden" name="action" value="addUser" /> 
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-floating">
                            <input type="text" class="form-control" name="firstName" id="firstName" placeholder="First Name">
                            <label for="firstName"><i class="bi bi-person me-2"></i>First Name</label>
                        </div>
                       <div class="text text-danger text-small" id="firstNameError"></div>  
                    </div>
                    <div class="col-md-6">
                        <div class="form-floating">
                            <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Last Name">
                            <label for="lastName"><i class="bi bi-person me-2"></i>Last Name</label>
                        </div>
                       	<div class="text text-danger text-small" id="lastNameError"></div>  
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-floating">
                            <input type="email" class="form-control" name="email" id="email" placeholder="Email">
                            <label for="email"><i class="bi bi-envelope me-2"></i>Email Address</label>
                        </div>
                        <div class="text text-danger text-small" id="emailError"></div> 
                    </div>
                    
                    <div class="col-md-6">
                        <div class="form-floating">
                            <input type="tel" class="form-control" name="phone" id="phone" placeholder="Phone">
                            <label for="phone"><i class="bi bi-telephone me-2"></i>Phone Number</label>
                        </div>
                        <div class="text text-danger text-small" id="phoneError"></div>  
                    </div>
                </div>


                <div class="form-floating">
                    <textarea class="form-control" id="address" name="address" placeholder="Address"></textarea>
                    <label for="address"><i class="bi bi-geo-alt me-2"></i>Address</label>
                </div>
               <div class="text text-danger text-small" id="addressError"></div>  

               
                <div class="form-actions">
                    <button type="submit" class="btn-modern">
                        <i class="bi bi-person-plus"></i>Create User
                    </button>
                </div>
            </form>
        </div>
    </main>

</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
function setUserAddValidation() {
	const form = document.getElementById('userForm');
	
	if (!form) {
		return;
	}
	
	
	form.addEventListener('submit', function(event) {
		const firstNameError = document.getElementById('firstNameError');
		const lastNameError = document.getElementById('lastNameError');
		const emailError = document.getElementById('emailError');
		const phoneError = document.getElementById('phoneError');
		const addressError = document.getElementById('addressError');

		firstNameError.textContent = '';
		lastNameError.textContent = '';
		emailError.textContent = '';
		phoneError.textContent = '';
		addressError.textContent = '';
		
		const firstName = document.getElementById('firstName').value.trim();
		const lastName = document.getElementById('lastName').value.trim();
		const email = document.getElementById('email').value.trim();
		const phone = document.getElementById('phone').value.trim();
		const address = document.getElementById('address').value.trim();

		let hasError = false;

		if (!firstName) {
			firstNameError.textContent = "Please enter first name.";
			hasError = true;
		} 
			
		if (!lastName) {
			lastNameError.textContent = "Please enter last name.";
			hasError = true;
		} 
		
		if (!email) {
			emailError.textContent = "Please enter email.";
			hasError = true;
		} else if(!email.includes("@")) {
			emailError.textContent = "Please enter valid email.";
			hasError = true;
		} 
		
		
		if (!phone) {
			phoneError.textContent = "Please enter phone no.";
			hasError = true;
		} else if (phone.length != 10) {
			phoneError.textContent = "Please enter valid phone no.";
			hasError = true;
		}
		
		
		if (!address) {
			addressError.textContent = "Please enter the total copies.";
			hasError = true;
		}
	
		
		if (hasError) {
			event.preventDefault();
		}
	});	
}

if (document.readyState == 'loading') {
	document.addEventListener('DOMContentLoaded', setUserAddValidation);
} else {
	setUserAddValidation();
}
</script>
</body>
</html>
