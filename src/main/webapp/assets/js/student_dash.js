
//var id =sessionStorage.getItem('id');
window.onload=fetch_student;

async function fetch_student() {
    let id;
    id = sessionStorage.getItem('id');
    if (!id) {
        location.href = "index.html";
        return;
    }

    let response = await fetch("api/students/get");
    let students = await response.json(); // read response body and parse as JSON
    console.log(students);
    //document.write("<table>");
    let S_table = document.getElementById('table');
    for (let i = 0; i < students.length; i++) {

        //courses_option.innerHTML += '<tr><td>' + courses[i]['course_id'] + '</td><td>' + courses[i]['name'] + '</td></tr>';
        //document.writeln("<tr><td>" + students[i]['student_id'] + "</td><td>" + students[i]['first_name'] + " " + students[i]['last_name'] + "</td><td>" + students[i]['email'] + "</td></tr>");
        //document.write("\n\n");
        //console.log(id);
        console.log(typeof(students[i]['student_id']));
        console.log(typeof(id));

        if(id.toString() === students[i]['student_id'].toString()) {

            S_table.innerHTML += "<tr><td>" + students[i]['student_id'] + "</td><td>" + students[i]['first_name'] + "</td><td>" + students[i]['last_name'] + "</td><td>" + students[i]['email'] + "</td><td>"+ students[i]['domain']['program'] +"</td></tr>";
        }
    }
    //document.write("</table");
}