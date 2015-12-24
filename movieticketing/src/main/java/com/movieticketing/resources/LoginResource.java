package com.movieticketing.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movieticketing.bo.LoginBO;
import com.movieticketing.common.LoginDetails;
import com.movieticketing.common.ResultBean;

/**
 * Created by nagal_000 on 12/2/2015.
 */
@Component
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)

public class LoginResource {

    @Autowired
    LoginBO loginBo;

    @POST
    @Path("/validate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validateLogin(LoginDetails login) {
        System.out.println("Login: " + login);
        ResultBean result = loginBo.validateLogin(login);
        return Response.status(result.getStatus()).entity(result).build();

    }

    @GET
    public Response getHey(){
        System.out.println("Get Hey");
        ResultBean result = new ResultBean();
        result.setStatus(200);
        return Response.status(result.getStatus()).entity(result).build();
    }

}
