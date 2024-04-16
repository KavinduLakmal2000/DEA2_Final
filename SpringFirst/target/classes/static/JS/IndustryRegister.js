function submitData(event) {
    event.preventDefault();

    // com details

    const Fname = document.getElementById("cpFname").value;
    const Lname = document.getElementById("cpLname").value;
    const Cmail = document.getElementById("mail").value;
    const Uname = document.getElementById("username").value;
    const id = document.getElementById("uid").value;
    const Annual = document.getElementById("annual").value;
    const Cpassword1 = document.getElementById("pass1").value;
    const Cpassword2 = document.getElementById("pass2").value;
    const Cmobile = document.getElementById("phone").value;
    const Industry = document.getElementById("industry").value;
    const comName = document.getElementById("Cname").value;

    // const pdfFile = document.getElementById("pdfFile").files[0];

    if (Cpassword1 !== Cpassword2) {
        Swal.fire({
            icon: 'error',
            title: 'Passwords do not match',
            text: 'Please re-enter your passwords.'
        });
        return false;
    }

    else if (id.startsWith("1")) {
        const data = {
            "companyName": comName,
            "email": Cmail,
            "password": Cpassword1,
            "phone": Cmobile,
            "contactPersonFirstName": Fname,
            "contactPersonLastName": Lname,
            "annualRevenue": Annual,
            "industry": Industry,
            "id": id
        };

        fetch('http://localhost:8091/insert/company', {
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
            text: 'Your ID should start with 1.'
        });
        return false;
    }
}
