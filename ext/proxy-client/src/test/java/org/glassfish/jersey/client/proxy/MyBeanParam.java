package org.glassfish.jersey.client.proxy;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.MatrixParam;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Cookie;

import java.util.List;
import java.util.Set;

public class MyBeanParam {

    @HeaderParam("headerParam")
    String headerParam;

    @PathParam("pathParam")
    String pathParam;

    @QueryParam("queryParam")
    String queryParam;

    @FormParam("formParam1")
    String formParam1;

    @FormParam("formParam2")
    String formParam2;

    @MatrixParam("matrixParam")
    List<String> matrixParam;

    @CookieParam("cookieParam")
    Cookie cookieParam;

    @BeanParam
    MySubBeanParam subBeanParam;

    String queryParam2;


    public MyBeanParam(String headerParam, String pathParam, String queryParam, String formParam1, String formParam2,
                       List<String> matrixParam, Cookie cookieParam, MySubBeanParam subBeanParam) {
        this.headerParam = headerParam;
        this.pathParam = pathParam;
        this.queryParam = queryParam;
        this.formParam1 = formParam1;
        this.formParam2 = formParam2;
        this.matrixParam = matrixParam;
        this.cookieParam = cookieParam;
        this.subBeanParam = subBeanParam;
    }

    public MyBeanParam() {}

    public String getHeaderParam() {
        return headerParam;
    }

    public void setHeaderParam(String headerParam) {
        this.headerParam = headerParam;
    }

    public String getPathParam() {
        return pathParam;
    }

    public void setPathParam(String pathParam) {
        this.pathParam = pathParam;
    }

    public String getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(String queryParam) {
        this.queryParam = queryParam;
    }

    public String getFormParam1() {
        return formParam1;
    }

    public void setFormParam1(String formParam1) {
        this.formParam1 = formParam1;
    }

    public String getFormParam2() {
        return formParam2;
    }

    public void setFormParam2(String formParam2) {
        this.formParam2 = formParam2;
    }

    public List<String> getMatrixParam() {
        return matrixParam;
    }

    public void setMatrixParam(List<String> matrixParam) {
        this.matrixParam = matrixParam;
    }

    public Cookie getCookieParam() {
        return cookieParam;
    }

    public void setCookieParam(Cookie cookieParam) {
        this.cookieParam = cookieParam;
    }

    public MySubBeanParam getSubBeanParam() {
        return subBeanParam;
    }

    public void setSubBeanParam(MySubBeanParam subBeanParam) {
        this.subBeanParam = subBeanParam;
    }

    @QueryParam("queryParam2")
    public String getQueryParam2() {
        return queryParam2;
    }

    @QueryParam("queryParam2")
    public void setQueryParam2(String queryParam2) {
        this.queryParam2 = queryParam2;
    }

}
