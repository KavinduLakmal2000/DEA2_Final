function submitData(event) {
    event.preventDefault();

    // Get form data
    const position = document.getElementById("position").value;
    const jobType = document.getElementById("jobType").value;
    const email = document.getElementById("email").value;
    const expireDate = document.getElementById("expireDate").value;
    const description = document.getElementById("description").value;
    const requirements = document.getElementById("requirements").value;
    const imageFile = document.getElementById("imageFile").files[0];

    // Validate form data (you can add more validation if needed)

    // Create form data object
    const formData = new FormData();
    formData.append("position", position);
    formData.append("jobType", jobType);
    formData.append("email", email);
    formData.append("expireDate", expireDate);
    formData.append("description", description);
    formData.append("requirements", requirements);
    formData.append("imageFile", imageFile);

    // Send form data to server
    fetch('http://localhost:8091/create', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Network response was not ok (${response.status} ${response.statusText})`);
            }
            return response.text();
        })
        .then(message => {
            alert(message); // Handle success response
            // Redirect or show success message as needed
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error: ' + error.message); // Show detailed error message to the user
        });

}
