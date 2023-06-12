
let student_register = document.getElementById('register-student');
let edit_student = document.getElementById('edit-student');

student_register.addEventListener('submit', async (e) => {
        e.preventDefault();
        e.stopPropagation();
        if (student_register.checkValidity() === true) {

                location.href = "dashboard.html";
        }else{
                document.getElementById("login-alert").style.display = "block";
        }

});

edit_student.addEventListener('submit', async (e) => {
        e.preventDefault();
        e.stopPropagation();
        if (edit_student.checkValidity() === true) {

                location.href = "dashboard.html";
        }else{
                document.getElementById("login-alert").style.display = "block";
        }

});