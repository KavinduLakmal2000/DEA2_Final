document.addEventListener("DOMContentLoaded", () => {
    fetch('http://localhost:8091/templates/hires/AllSeekers')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(users => {
            const tableBody = document.getElementById("userTableBody");

            users.forEach(user => {
                const row = tableBody.insertRow();

                row.innerHTML = `
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.password}</td>
                        <td>${user.phone}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.salaryExpectations}</td>
                        <td>${user.availability}</td>
                        <td><button class="btn btn-danger" onclick="deleteUser(${user.id})">Delete</button></td>
                    `;
            });
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Failed to fetch user data." + error);
        });
});

function deleteUser(id) {
    Swal.fire({
        title: 'Are you sure?',
        text: 'You are about to delete this user. This action cannot be undone.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            fetch(`http://localhost:8091/templates/hires/Seeker/${id}`, {
                method: 'DELETE'
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
                })
                .then(message => {
                    Swal.fire(
                        'Deleted!',
                        message,
                        'success'
                    );
                    location.reload();
                })
                .catch(error => {
                    console.error('Error:', error);
                    Swal.fire(
                        'Error!',
                        'Failed to delete user. Please try again.',
                        'error'
                    );
                });
        }
    });
}


//----------------------------------------------------------------------------- company part -----------------------------------------------------------------


document.addEventListener("DOMContentLoaded", () => {
    fetch('http://localhost:8091/templates/hires/AllCompanies')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(companies => {
            const tableBody = document.getElementById("companyTableBody");

            companies.forEach(company => {
                const row = tableBody.insertRow();

                row.innerHTML = `
                        <td>${company.id}</td>
                        <td>${company.companyName}</td>
                        <td>${company.email}</td>
                        <td>${company.password}</td>
                        <td>${company.phone}</td>
                        <td>${company.contactPersonFirstName}</td>
                        <td>${company.contactPersonLastName}</td>
                        <td>${company.annualRevenue}</td>
                        <td>${company.industry}</td>
                        <td><button class="btn btn-danger" onclick="deleteCompany(${company.id})">Delete</button></td>
                    `;

                tableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Failed to fetch company data." + error);
        });
});

function deleteCompany(id) {
    Swal.fire({
        title: 'Are you sure?',
        text: 'You are about to delete this user. This action cannot be undone.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            fetch(`http://localhost:8091/templates/hires/Company/${id}`, {
                method: 'DELETE'
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
                })
                .then(message => {
                    Swal.fire(
                        'Deleted!',
                        message,
                        'success'
                    );
                    location.reload();
                })
                .catch(error => {
                    console.error('Error:', error);
                    Swal.fire(
                        'Error!',
                        'Failed to delete user. Please try again.',
                        'error'
                    );
                });
        }
    });
}

function logout(){
    window.location.href = "http://localhost:8091/Welcome.html";
}

function home(){
    window.location.href = "http://localhost:8091/Admin.html";
}

function posts(){
    window.location.href = "http://localhost:8091/templates/hires/AdminControl";
}