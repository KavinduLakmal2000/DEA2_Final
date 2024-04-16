function submitData(event) {
    event.preventDefault();

    const username = document.getElementById("username").value;
    const password1 = document.getElementById("pass1").value;
    const password2 = document.getElementById("pass2").value;
    const id = document.getElementById("uid").value;
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const email = document.getElementById("mail").value;
    const mobile = document.getElementById("phone").value;
    const salaryExpectations = document.getElementById("salaryExpectations").value;
    const availability = document.querySelector('input[name="availability"]:checked').value;

    if (password1 !== password2) {
        Swal.fire({
            icon: 'error',
            title: 'Passwords do not match',
            text: 'Please re-enter your passwords.'
        });
        return false;
    }

    else if (id.startsWith("2")) {
        const data = {
            "id": id,
            "username": username,
            "password": password1,
            "firstName": firstName,
            "lastName": lastName,
            "email": email,
            "salaryExpectations": salaryExpectations,
            "availability": availability,
            "phone": mobile
        };

        fetch('http://localhost:8091/insert/seeker', {
            method: 'POST',
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
                }).then(() => {
                    if (message === "Registration successful") {
                        window.location.href = "Login.html";
                    }
                });
            })
            .catch(error => {
                console.error('Error:', error);
                Swal.fire({
                    icon: 'error',
                    title: 'Registration failed',
                    text: 'An error occurred while registering. Please try again.'
                });
            });
    } else {
        Swal.fire({
            icon: 'warning',
            title: 'Incorrect ID',
            text: 'Your ID should start with 2.'
        });
        return false;
    }
}
