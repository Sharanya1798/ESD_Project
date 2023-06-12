package com.example.erp.controller;

import com.example.erp.bean.Domains;
import com.example.erp.bean.Students;
import com.example.erp.service.DomainService;
import com.example.erp.service.StudentService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Path("students")
public class StudentsController {
    StudentService studentService = new StudentService();
    DomainService domainService = new DomainService();
    @POST
    @Path("/register")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerStudent(Students student) throws URISyntaxException {
        System.out.println();
        //List<Courses> courses = new ArrayList<>();
        Domains domain = domainService.getDomainByID(student.getDomain().getDomain_id());
        if(domain!=null){
            student.setDomain(domain);
            int status = studentService.registerStudent(student);
            if(status == 1){
                return Response.status(student.getStudent_id()).build();
            }else if(status == 2){
                return Response.status(203).build();
            } else
            {
                return Response.status(204).build();
            }

        }
        return Response.status(203).build();

    }


    @POST
    @Path("/image")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response imageUpload(@FormDataParam("file") InputStream fileInputStream,
                                @FormDataParam("file") FormDataContentDisposition fileMetaData, @FormDataParam("id") Integer id) throws URISyntaxException {

        // Do this stuff in service
        String upload_path = "/home/sharanya/Desktop/SEM1/ESD/images/"+fileMetaData.getFileName();
        try{
            int read = 0;
            byte[] bytes = new byte[1024];

            OutputStream out = new FileOutputStream(new File(upload_path));
            while ((read = fileInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            System.out.println(id);
            studentService.insertPhotoPath(id, upload_path);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        StreamingOutput fileStream = studentService.fetchPhotoFromPath(upload_path);

        return Response
                .ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition","attachment; filename = "+fileMetaData.getFileName())
                .build();
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginStudent(Students student) throws URISyntaxException {
        List<Students> students= studentService.verifyEmail(student);
        System.out.println(student);
        if(students == null){
            return Response.ok().build();
        }else{
            return Response.ok().entity(students).build();
        }
    }

    StudentService studentService1 = new StudentService();
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents() {
        List<Students> students = studentService1.getStudents();
        return Response.ok().entity(students).build();
    }

    StudentService studentService2= new StudentService();
    @POST
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteStudent(Students roll) throws URISyntaxException {

        if(studentService2.deleteStudent(roll)){
            return Response.ok().build();
        }else{
            return Response.status(203).build();
        }
    }

    @POST
    @Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStudent(Students student) throws URISyntaxException {
        System.out.println(student.getLast_name()+"controller");
        Domains domain = domainService.getDomainByID(student.getDomain().getDomain_id());
        if(domain!=null){
            student.setDomain(domain);
            int status = studentService.updateStudent(student);
            if(status == 1){
                return Response.ok().build();
            }else if(status == 2){
                return Response.status(203).build();
            } else
            {
                return Response.status(204).build();
            }

        }
        return Response.status(203).build();

    }
}
