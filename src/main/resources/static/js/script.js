const nameError = document.getElementById("name-error");
const phoneError = document.getElementById("phone-error");
const emailError = document.getElementById("email-error");
const passwordError = document.getElementById("password-error");
const messageError = document.getElementById("message-error");
const submitError = document.getElementById("submit-error");

//method for validation of name field
function validateName() {
    let name  = document.getElementById('user-name').value.trim();
    if(name.length == 0) {
        nameError.innerHTML = "Name is required";
        return false;
    }
    if(!name.match(/^[A-Za-z]+(?:\s[A-Za-z]+)+$/)){
        nameError.innerHTML = "Write full name";
        return false;
    }
    nameError.innerHTML = `<span style ="color:green;" class="material-symbols-outlined">
check_circle
</span>`;

submitError.innerHTML="";
    return true;
}

//method for validation of contact field
function validatePhone() {
    let phone = document.getElementById("user-phone").value.trim();
    if(phone.length == 0) {
        phoneError.innerHTML = "Phone no. is required";
        return false;
    }
    if(!phone.match(/^[6789]\d{9}$/)) {
        phoneError.innerHTML = "Invalid Number";
        return false;
    }

    phoneError.innerHTML = `<span style ="color:green" class="material-symbols-outlined">
check_circle
</span>`;

submitError.innerHTML="";
return true;
}

//method for validation of Email field
function validateEmail(){
    let email = document.getElementById("user-email").value.trim();
if(email.length == 0){
    emailError.innerHTML = "Email is required";
    return false;
}
if(!email.match(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/ )){
    emailError.innerHTML = "Email Invalid";
    return false;
}
emailError.innerHTML = `<span style ="color:green" class="material-symbols-outlined">
check_circle
</span>`;
submitError.innerHTML="";
return true;
}

//method for validation of Password field
function validatePassword() {
    let password  = document.getElementById("user-password").value.trim();
    if(password.length == 0) {
        passwordError.innerHTML = "Password is required";
        return false;
    }
    if (!/(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$#!%*?&]).{8,}/.test(password)) {
        passwordError.innerHTML = "Password must contain at least 8 characters, including one uppercase, one lowercase, one number, and one special character.";
        return false;
    }

   passwordError.innerHTML = `<span style ="color:green" class="material-symbols-outlined">
   check_circle
   </span>`;
submitError.innerHTML="";
return true;
}



// Method to validate the form
// Once the validation get successful it will send the data to backend to save in DB.
function validateForm() {

    if(!validateName() || !validatePhone() || !validateEmail() || !validatePassword()) {
        submitError.innerHTML = "Please fix above errors";
        return false;
    }
    else {
        const data = {
            name: document.getElementById('user-name').value.trim(),
            contact: document.getElementById('user-phone').value.trim(),
            email: document.getElementById('user-email').value.trim(),
            password: document.getElementById('user-password').value.trim(),
        };

        $.ajax('http://localhost:8080/form/save', {
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify(data),
            headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                },
            processData: false,
            success: function(response) {
				if(response.message == "Data saved successfully")
                alert("Data saved successfully.");
                else
                alert("Duplicate Data found. Retry!!");
            },
            error: function(xhr, status, error) {
                console.error(`Error ${xhr.status}: ${xhr.responseText}`);
                alert("!!Error in saving data");
            }
        });
    }
}

// Method to fetch the list from the backend
function showList(){
    $.ajax('http://localhost:8080/form/showList', {
    type: 'GET',
    success: function(response) {
    $("#table-body").empty();
    if(response.length == 0) {
    $("#table-body").append(`<tr><td colspan="4"> No student data available</tr>`);
    } else {
    response.forEach(function(student) {
	$("#table-body").append(
	`<tr>
	<td>${student.id}</td>
	<td>${student.name}</td>
	<td>${student.contact}</td>
	<td>${student.email}</td>
	</tr>`);
    });
    }
},
    error:
    function(xhr, status, error) {
    console.error("Error fetching data: " , error);
    $("#table-body").append(`<tr><td colspan="4">Error fetching data. Please try again.</td></tr>`);
 }
 });
}