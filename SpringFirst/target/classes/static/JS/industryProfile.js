document.addEventListener("DOMContentLoaded", () => {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const id = urlParams.get('id');
    const form = document.getElementById("companyForm");

    fetch(`http://localhost:8091/companyRead/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(companyData => {
            form.elements["comID"].value = companyData.id;
            form.elements["annualRevenue"].value = companyData.annualRevenue;
            form.elements["industry"].value = companyData.industry;
            form.elements["CPFname"].value = companyData.contactPersonFirstName;
            form.elements["CPLname"].value = companyData.contactPersonLastName;
            form.elements["phone"].value = companyData.phone;
            form.elements["companyName"].value = companyData.companyName;
            form.elements["email"].value = companyData.email;
            form.elements["password1"].value = companyData.password;
        })
        .catch(error => {
            console.error('Error:', error);
            Swal.fire({
                icon: 'error',
                title: 'Failed to fetch company data',
                text: 'An error occurred while fetching company data. Please try again.'
            });
        });
});

function updateProfile() {
    const id = document.getElementById("comID").value;
    const companyName = document.getElementById("companyName").value;
    const email = document.getElementById("email").value;
    const phone = document.getElementById("phone").value;
    const CPFname = document.getElementById("CPFname").value;
    const CPLname = document.getElementById("CPLname").value;
    const annualRevenue = document.getElementById("annualRevenue").value;
    const industry = document.getElementById("industry").value;
    const password1 = document.getElementById("password1").value;

    const data = {
        "id": id,
        "companyName": companyName,
        "email": email,
        "phone": phone,
        "contactPersonFirstName": CPFname,
        "contactPersonLastName": CPLname,
        "annualRevenue": annualRevenue,
        "industry": industry,
        "password": password1
    };

    fetch('http://localhost:8091/update/company', {
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
                title: 'Failed to update company profile',
                text: 'An error occurred while updating company profile. Please try again.'
            });
        });
}

function deleteProfile() {
    const id = document.getElementById("comID").value;

    fetch(`http://localhost:8091/delete/company/${id}`, {
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
                title: 'Failed to delete profile',
                text: 'An error occurred while deleting profile. Please try again.'
            });
        });
}

function continueToDashboard() {
    window.location.href = "Welcome.html";
}
