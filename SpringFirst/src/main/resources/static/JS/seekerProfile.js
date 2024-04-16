document.addEventListener("DOMContentLoaded", () => {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const id = urlParams.get('id');
    const form = document.getElementById("companyForm");

    fetch(`http://localhost:8091/userRead/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(companyData => {
            form.elements["UID"].value = companyData.id;
            form.elements["username"].value = companyData.username;
            form.elements["email"].value = companyData.email;
            form.elements["password"].value = companyData.password;
            form.elements["phone"].value = companyData.phone;
            form.elements["firstName"].value = companyData.firstName;
            form.elements["lastName"].value = companyData.lastName;
            form.elements["salaryExpectations"].value = companyData.salaryExpectations;
            form.elements["availability"].value = companyData.availability;
        })
        .catch(error => {
            console.error('Error:', error);
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Failed to fetch company data.',
                footer: error
            });
        });
});

function updateProfile() {
    const id = document.getElementById("UID").value;
    const username = document.getElementById("username").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const phone = document.getElementById("phone").value;
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const salaryExpectations = document.getElementById("salaryExpectations").value;
    const availability = document.getElementById("availability").value;

    const data = {
        "id": id,
        "username": username,
        "email": email,
        "password": password,
        "phone": phone,
        "firstName": firstName,
        "lastName": lastName,
        "salaryExpectations": salaryExpectations,
        "availability": availability
    };

    fetch('http://localhost:8091/update/user', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(message => {
            Swal.fire({
                icon: 'success',
                title: 'Success',
                text: message
            });
        })
        .catch(error => {
            console.error('Error:', error);
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Failed to update user profile.',
                footer: error
            });
        });
}

function deleteProfile() {
    const id = document.getElementById("UID").value;
    fetch(`http://localhost:8091/delete/users/${id}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(message => {
            Swal.fire({
                icon: 'success',
                title: 'Success',
                text: message
            }).then(() => {
                window.location.href = "Welcome.html";
            });
        })
        .catch(error => {
            console.error('Error:', error);
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Failed to delete profile.',
                footer: error
            });
        });
}

function continueToDashboard() {
    window.location.href = "Welcome.html";
}
