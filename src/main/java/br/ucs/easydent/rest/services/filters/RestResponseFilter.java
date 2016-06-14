package br.ucs.easydent.rest.services.filters;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

/**
 * Allow the system to serve xhr level 2 from all cross domain site
 * 
 * @author Deisss (LGPLv3)
 * @version 0.1
 */
public class RestResponseFilter implements ContainerResponseFilter {
    /**
     * Add the cross domain data to the output if needed
     * 
     * @param request The container request (input)
     * @param response The container request (output)
     * @return The output request with cross domain if needed
     */
    @Override
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
        response.getHttpHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHttpHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, X-AUTH-TOKEN");
        response.getHttpHeaders().add("Access-Control-Allow-Credentials", "true");
        response.getHttpHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        response.getHttpHeaders().add("Access-Control-Max-Age", "1209600");
        return response;
    }
}