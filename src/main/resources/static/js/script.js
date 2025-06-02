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
    if(!name.match(/^[A-Za-z]*\s{1}[A-Za-z]*$/)){
        nameError.innerHTML = "Write full name";
        return false;
    }
    nameError.innerHTML = `<span style ="color:green" class="material-symbols-outlined">
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

        if(!password.match(/[a-z]/)) {
            passwordError.innerHTML = "Must contain one lowercase letter";
            return false;
        }
        if(!password.match(/[A-Z]/)) {
            passwordError.innerHTML = "Must contain one uppercase letter";
            return false;
        }
        if(!password.match(/\d/)) {
            passwordError.innerHTML = "Must contain one numeric value";
            return false;
        }
        if(!password.match(/[@$#!%*?&]/)) {
            passwordError.innerHTML = "Must contain one special character(@$!%*?&).";
            return false;
        }
        if(password.length<8) {
            passwordError.innerHTML = "Password must be 8 characters long";
            return false;
        }

    passwordError.innerHTML =`<span style ="color:green" class="material-symbols-outlined">
check_circle
</span>`;
submitError.innerHTML="";
return true;
}



// method to validate the form
// Once the validation get successful it will send the data to backend to save in DB.
function validateForm() {
    if(!validateName() || !validatePhone() || !validateEmail() || !validatePassword()) {
        submitError.innerHTML = "Please fix above errors";
        return false;
    }
    else {
        const data = {
            studentName: document.getElementById('user-name').value.trim(),
            studentContact: document.getElementById('user-phone').value.trim(),
            studentEmail: document.getElementById('user-email').value.trim(),
            password: document.getElementById('user-password').value.trim(),
        };
        $.ajax('http://localhost:8080/save-form', {
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify(data),
            headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                },
            processData: false,
            success: function(response) {
                console.log("Success", response);
            },
            error: function(xhr, status, error) {
                console.error("Error: ", error);
            }
        });
    }
}
// method to fetch the list from the backend
function showList() {
    console.log("Hello");
    $.ajax('http://localhost:8080/showList', {
    type: 'GET',
    success: function(response) {
    console.log(response);
    $("#itemList").empty();
    response.forEach(function(item) {
    $("#itemList").append(`<li>${item}</li>`);
    });
},
    error:
    function(xhr, status, error) {
    console.error("Error fetching data: " , error);
 }
 });
}