package org.glassfish.jersey.client.proxy;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.Response;

public class MyResourceWithBeanParam implements MyResourceWithBeanParamIfc {

    @Override
    public String echoQuery(MyGetBeanParam bean) {
        return bean.getQueryParam();
    }

    @Override
    public String echoHeader(@BeanParam MyGetBeanParam bean) {
        return bean.getHeaderParam();
    }

    @Override
    public String echoPath(@BeanParam MyGetBeanParam bean) {
        return bean.getPathParam();
    }

    @Override
    public String echoCookie(@BeanParam MyGetBeanParam bean) {
        return bean.getCookieParam().getValue();
    }

    @Override
    public String echoMatrix(@BeanParam MyGetBeanParam bean) {
        return bean.getMatrixParam().toString();
    }

    @Override
    public String echoSubBean(@BeanParam MyGetBeanParam bean) {
        return bean.getSubBeanParam().getSubQueryParam().toString();
    }

    @Override
    public String echo(MyBeanParam bean) {
        return ("HEADER=" + bean.getHeaderParam() + ",PATH=" + bean.getPathParam() + ",FORM="
                + bean.getFormParam1() + "," + bean.getFormParam2() + ",QUERY=" + bean.getQueryParam()
                + ",MATRIX=" + bean.getMatrixParam().size() + ",COOKIE=" + bean.getCookieParam().getValue()
                + ",SUB=" + bean.getSubBeanParam().getSubQueryParam().size()
                + ",Q2=" + bean.getQueryParam2());
    }
}
