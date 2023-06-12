let student_form = document.getElementById('student-validation');
window.onload = fetch_Domains;

async function imageUpload(){
    let form_data = new FormData();
    form_data.append('id', sessionStorage.getItem('register_id'));
    form_data.append('file', document.getElementById('image-data').files[0]);
    console.log(form_data);
    let response = await fetch('api/students/image', {
        method: 'POST',
        body: form_data
    }).then(response => {
        response.blob().then(blob => {
            console.log(blob);
            let reader = new FileReader();
            reader.readAsDataURL(blob);
            reader.onloadend = function () {
                let base64String = reader.result;
                document.getElementById('avatar-image').src = "data:image/png;base64"+base64String;
                document.getElementById("upload-success").style.display = "block";

                console.log(base64String);
            }
            const url = URL.createObjectURL(blob);
            console.log(url);
            window.open(url, "_blank");
        })
    }).catch(err => {
        console.log(err);
    });

}

student_form.addEventListener('submit', async (e) => {
  e.preventDefault();
  e.stopPropagation();
  if (student_form.checkValidity() === true) {
    let response = await fetch('api/students/register', {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json;charset=utf-8'
      },
      body: JSON.stringify({
          first_name: document.getElementById('first_name').value,
          last_name: document.getElementById('last_name').value,
          email: document.getElementById('email').value,
          domain: {'domain_id': document.getElementById('domain').value},
          specialization: document.getElementById('specialization').value,
          grad_year: document.getElementById('grad_year').value,
      })
    }).then(
        response => {
            if(response['status'] === 204){
                document.getElementById("login-success").style.display = "none";
                document.getElementById("login-alert").style.display = "block";
                document.getElementById("capacity-exceeded").style.display = "none";

            }else if(response['status'] === 203){
                document.getElementById("login-alert").style.display = "none";
                document.getElementById("login-success").style.display = "none";
                document.getElementById("capacity-exceeded").style.display = "block";

            } else {
                let data = response['status'];
                sessionStorage.setItem('register_id', data);
                document.getElementById("login-alert").style.display = "none";
                document.getElementById("login-success").style.display = "block";
                document.getElementById("capacity-exceeded").style.display = "none";
            }
        }
    );
  }else{
      student_form.classList.add('was-validated');
  }
});

course_form.onsubmit = async (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (course_form.checkValidity() === true) {
      let form_data = new FormData();
      form_data.append('name', document.getElementById('name').value);
      form_data.append('description', document.getElementById('description').value);
      form_data.append('credits', document.getElementById('credits').value);
        // $.ajax({
        //   type: "POST",
        //   url: "api/courses/register",
        //   enctype: 'multipart/form-data',
        //   data: form_data,
        //   processData: false,
        //   contentType: false,
        // }).done(function(response, status) {
        //   console.log(response, status);
        // });
        let response = await fetch('api/courses/register', {
        method: 'POST',
        body: form_data
      });
      let result = await response;
      console.log(result);
    }
    course_form.classList.add('was-validated');
};

async function fetch_Domains(){

    let response = await fetch("api/domains/get");
    let domains = await response.json(); // read response body and parse as JSON
    console.log(domains);
    let domains_option = document.getElementById('domain');
    domains_option.innerHTML = '<option value=""> Choose...</option>';

    for (let i = 0; i < domains.length; i++) {
        domains_option.innerHTML += '<option value="' + domains[i]['domain_id'] + '">' + domains[i]['program'] + '</option>';
    }
}