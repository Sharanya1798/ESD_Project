let student_form1= document.getElementById('student-update');

let std_id = sessionStorage.getItem('Student_update');

window.onload=fetch_data;

async function fetch_data() {

    let response = await fetch("api/students/get");
    let students = await response.json(); // read response body and parse as JSON
    console.log(students);
    let i;
    for ( i = 0; i < students.length; i++) {
        console.log(typeof(students[i]['student_id']));
        console.log(typeof(std_id));

        if(std_id.toString() === students[i]['student_id'].toString()) {
            console.log(students[i]['first_name']);
            console.log(students[i]['domain']);

            //student_form.innerHTML = "<tr><td>" + students[i]['student_id'] + "</td><td>" + students[i]['first_name'] + "</td><td>" + students[i]['last_name'] + "</td><td>" + students[i]['email'] + "</td></tr>";
            document.forms['student-update'].elements['first_name'].value=students[i]['first_name'],
            document.forms['student-update'].elements['last_name'].value=students[i]['last_name'],
            document.forms['student-update'].elements['email'].value=students[i]['email'];
            //document.forms['student-update'].elements['domain'].value=students[i]['domain'];
            //document.forms['student-update'].elements['courses'].value=students[i]['courses'];
            break;
        }
    }
    let spec_option=document.getElementById('specialization');
    if(students[i]['specialization'] === "cs"){

        spec_option.innerHTML = '<option value = "cs">Computer Science</option>';
        spec_option.innerHTML += '<option value = "ec">Electronics & Communications</option>';

    }else{
        spec_option.innerHTML = '<option value = "ec">Electronics & Communications</option>';
        spec_option.innerHTML += '<option value = "cs">Computer Science</option>';
    }


    let grad_year=document.getElementById('grad_year');
    grad_year.innerHTML = '<option value="' + students[i]['grad_year'] + '">'+ students[i]['grad_year'] + '</option>';
    grad_year.innerHTML += '<option value = "2020">2020</option>';
    grad_year.innerHTML += '<option value = "2020">2021</option>';
    grad_year.innerHTML += '<option value = "2020">2022</option>';
    grad_year.innerHTML += '<option value = "2020">2023</option>';
    grad_year.innerHTML += '<option value = "2020">2024</option>';
    grad_year.innerHTML += '<option value = "2020">2025</option>';
    grad_year.innerHTML += '<option value = "2020">2026</option>';
    grad_year.innerHTML += '<option value = "2020">2027</option>';

    let response1 = await fetch("api/domains/get");
    let domains = await response1.json(); // read response body and parse as JSON
    console.log(domains);
    let domains_option = document.getElementById('domain');
    domains_option.innerHTML = '<option value="' + students[i]['domain']['domain_id'] + '">'+ students[i]['domain']['program'] + '</option>';
    for (let j = 0; j < domains.length; j++) {
        if(students[i]['domain']['program']=== domains[j]['program']){
            continue;
        }
        domains_option.innerHTML += '<option value="' + domains[j]['domain_id'] + '">' + domains[j]['program'] + '</option>';
    }
}

student_form1.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (student_form1.checkValidity() === true) {
        console.log("a");
        let response = await fetch('api/students/update', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                student_id:parseInt(std_id),
                first_name: document.getElementById('first_name').value,
                last_name: document.getElementById('last_name').value,
                email: document.getElementById('email').value,
                domain: {'domain_id': document.getElementById('domain').value},
                specialization: document.getElementById('specialization').value,
                grad_year: document.getElementById('grad_year').value,
            })
        }).then(
            response => {
                if (response['status'] === 203) {
                    document.getElementById("login-success").style.display = "none";
                    document.getElementById("login-alert").style.display = "block";

                } else {
                    document.getElementById("login-alert").style.display = "none";
                    document.getElementById("login-success").style.display = "block";
                    const r = confirm("Updated Successfully");
                    if (r == true) {
                        location.href = "view.html";
                    }

                }
                if(response['status'] === 204){
                    document.getElementById("login-success").style.display = "none";
                    document.getElementById("login-alert").style.display = "block";
                    document.getElementById("capacity-exceeded").style.display = "none";

                }else if(response['status'] === 203){
                    document.getElementById("login-alert").style.display = "none";
                    document.getElementById("login-success").style.display = "none";
                    document.getElementById("capacity-exceeded").style.display = "block";

                } else {
                    document.getElementById("login-alert").style.display = "none";
                    document.getElementById("login-success").style.display = "block";
                    document.getElementById("capacity-exceeded").style.display = "none";
                }
            }
        );
    } else {
        student_form1.classList.add('was-validated');
    }
});

