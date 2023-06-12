let login_form = document.getElementById('login-validation');

login_form.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (login_form.checkValidity() === true) {
        let response = await fetch('api/students/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                email: document.getElementById('email').value,
            })
        });

        let result = await response.json();
        console.log(result);
        console.log(result[0]["student_id"]);
        if(result[0]["student_id"]===1){
            sessionStorage.setItem('id', result[0]["student_id"]);
            location.href = "Temp.html";
        }else {
            if(result[0]["student_id"]){
                sessionStorage.setItem('id', result[0]["student_id"]);
                location.href = "Student_dashboard.html";
            }else{
                console.log("email not found");
                document.getElementById("login-alert").style.display = "block";
            }
        }
    }
});