function applyForJob() {
    Swal.fire({
        icon: 'success',
        title: 'You successfully applied for the job!',
        text: ' You will receive an email shortly.',
    });
}


let id;

document.addEventListener('DOMContentLoaded', function() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    id = urlParams.get('id');

});

function myProfileS(){
    window.location.href = "http://localhost:8091/SeekerProfile.html?id=" + id;
}

function logout(){
    window.location.href = "http://localhost:8091/Welcome.html";
}
