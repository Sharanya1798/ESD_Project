/*
let course_form = document.getElementById('table');
window.onload = fetch_students;
document.getElementById("start").innerHTML =  fetch_students();
*/
//let course_form = document.getElementById('table');
window.onload = fetch_students;
//document.getElementById("start").innerHTML =   fetch_students();

 async function fetch_students() {
     if (!sessionStorage.getItem('id')) {
         location.href = "index.html";
         return;
     }
     let response = await fetch("api/students/get");
     let students = await response.json(); // read response body and parse as JSON
     console.log(students);
     //document.write("<table>");
     let S_table = document.getElementById('start');
     S_table.innerHTML = '';
     for (let i = 0; i < students.length; i++) {

         if(i===0){
             continue;
         }
         //courses_option.innerHTML += '<tr><td>' + courses[i]['course_id'] + '</td><td>' + courses[i]['name'] + '</td></tr>';
         //document.writeln("<tr><td>" + students[i]['student_id'] + "</td><td>" + students[i]['first_name'] + " " + students[i]['last_name'] + "</td><td>" + students[i]['email'] + "</td></tr>");
         //document.write("\n\n");
         S_table.innerHTML += "<tr><td>" + students[i]['student_id'] + "</td><td>" + students[i]['first_name'] + "</td><td>" + students[i]['last_name'] + "</td><td>" + students[i]['email']+ "</td><td>" + students[i]['domain']['program']   + "</td><td>" + `<a onclick='onUpdateR(${students[i]['student_id']})'><span class="fa fa-pencil"></span></a>
<a onclick='onDelete(${students[i]['student_id']})'><span class="fa fa-trash"></span></a>` + "</td></tr>";
        S_table.innerHTML +="<tr></tr>";
     }
     //document.write("</table");
 }

 async function onDelete(std_id) {
     console.log('student_id', std_id);
     console.log("hello");
     let response = await fetch('api/students/delete', {
         method: 'POST',
         headers: {
             'Content-Type': 'application/json;charset=utf-8'
         },
         body: JSON.stringify({
             student_id: std_id
         })
     }).then(
         response => {
             if (response['status'] === 200) {
                 const r = confirm("Deleted Successfully");
                 if (r == true) {
                     fetch_students();
                 }
             } else  {
                 alert('Something went wrong');
             }
         }
     );

 }

async function onUpdateR(std_id) {
    console.log('student_id', std_id);
    sessionStorage.setItem('Student_update', std_id);
    window.location.href="student_update.html";
}

