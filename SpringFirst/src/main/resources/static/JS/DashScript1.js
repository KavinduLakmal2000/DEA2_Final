let id;

document.addEventListener('DOMContentLoaded', function() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    id = urlParams.get('comId');
    const idLabel = document.getElementById('idLabel');

    if(id === null){
        idLabel.innerText = "";
    }
    else{
        idLabel.innerText = "Company ID: "+id;
    }

});


document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('idInput').value = id;
});

function NewAd() {
    const redirectUrl = "http://localhost:8091/templates/hires/create?comId=" + id;
    window.location.href = redirectUrl;
}

function myProfile(){
    window.location.href = "http://localhost:8091/CompanyProfile.html?id=" + id;
}

function logout(){
    window.location.href = "http://localhost:8091/Welcome.html";
}

function DashBoard(){
    window.location.href = "http://localhost:8091/templates/hires/JobsByCom?comId=" + id;
}