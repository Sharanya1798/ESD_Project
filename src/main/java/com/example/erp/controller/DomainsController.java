package com.example.erp.controller;
import com.example.erp.bean.Domains;
import com.example.erp.service.DomainService;
import com.example.erp.service.StudentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Path("domains")
public class DomainsController {

    DomainService domainService = new DomainService();

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDomains() {
        List<Domains> domains = domainService.getDomains();
        return Response.ok().entity(domains).build();
    }
}
