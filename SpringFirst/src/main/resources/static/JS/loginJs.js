function submitData(event) {
    event.preventDefault();

    const id = document.getElementById("uid").value;
    const password = document.getElementById("password").value;

    const data = {
        "id": id,
        "password": password
    };



    if (id.startsWith("2")) {
        fetch('http://localhost:8091/login/seeker', {
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
                    if (message === "Welcome To the seeking!") {
                        window.location.href = "SeekerProfile.html?id=" + id;
                    }
                });
            })
            .catch(error => {
                console.error('Error:', error);
                Swal.fire({
                    icon: 'error',
                    title: 'Login failed',
                    text: 'An error occurred while logging in.'
                });
            });
    } else if (id.startsWith("1")) {
        fetch('http://localhost:8091/login/company', {
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
                    if (message === "Welcome Company") {
                        window.location.href = "CompanyProfile.html?id=" + id;
                    }
                });
            })
            .catch(error => {
                console.error('Error:', error);
                Swal.fire({
                    icon: 'error',
                    title: 'Login failed',
                    text: 'An error occurred while logging in.'
                });
            });
    }

    else if (id === "admin" && password === "admin"){

        Swal.fire({
            icon: 'success',
            title: 'Success',
            text: ''
        }).then(() => {
            window.location.href = "Admin.html";
            });

    }

    else {
        Swal.fire({
            icon: 'warning',
            title: 'Incorrect ID',
            text: 'Please try again with a valid ID.'
        });
       // window.location.href = "CompanyProfile.html?id=1002";

    }
}
