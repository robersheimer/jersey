package org.glassfish.jersey.client.proxy;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.MatrixParam;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author Richard Obersheimer
 */
public class RequestParametersTest {

    private static final MultivaluedMap<String, Object> EMPTY_HEADERS = new MultivaluedHashMap<>();
    private static final Form EMPTY_FORM = new Form();
    private static final String baseURL = "http://example.com";

    private WebTarget getExampleTarget() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(baseURL);
        return webTarget;
    }

    private WebTarget getExampleTargetWithPathParam() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(baseURL + "/{pathParam}");
        return webTarget;
    }

    private RequestParameters getEmptyRequestParameters(WebTarget webTarget) {
        RequestParameters requestParameters = new RequestParameters(webTarget,
                EMPTY_HEADERS, Collections.<Cookie>emptyList(), EMPTY_FORM);
        return requestParameters;
    }

    @Test
    public void testAddQueryParameter() throws NoSuchFieldException, IntrospectionException,
            InvocationTargetException, IllegalAccessException {

        WebTarget webTarget = getExampleTarget();
        RequestParameters requestParameters = getEmptyRequestParameters(webTarget);

        MyGetBeanParam getBeanParam = new MyGetBeanParam();
        Annotation ann = getBeanParam.getClass().getDeclaredField("queryParam").getAnnotations()[0];
        Map<Class, Annotation> anns = new HashMap<>();
        anns.put(QueryParam.class, ann);

        requestParameters.addParameter("testQuery", anns);
        String uri = requestParameters.getNewTarget().getUriBuilder().build().toString();

        assertEquals(baseURL + "/?queryParam=testQuery", uri);
    }

    @Test
    public void testAddPathParameter() throws NoSuchFieldException, IntrospectionException,
            InvocationTargetException, IllegalAccessException {

        WebTarget webTarget = getExampleTargetWithPathParam();
        RequestParameters requestParameters = getEmptyRequestParameters(webTarget);

        MyGetBeanParam getBeanParam = new MyGetBeanParam();
        Annotation ann = getBeanParam.getClass().getDeclaredField("pathParam").getAnnotations()[0];
        Map<Class, Annotation> anns = new HashMap<>();
        anns.put(PathParam.class, ann);

        requestParameters.addParameter("testPath", anns);
        String uri = requestParameters.getNewTarget().getUriBuilder().build().toString();

        assertEquals(baseURL + "/testPath", uri);
    }

    @Test
    public void testAddHeaderParameter() throws NoSuchFieldException, IntrospectionException,
            InvocationTargetException, IllegalAccessException {

        WebTarget webTarget = getExampleTarget();
        RequestParameters requestParameters = getEmptyRequestParameters(webTarget);

        MyGetBeanParam getBeanParam = new MyGetBeanParam();
        Annotation ann = getBeanParam.getClass().getDeclaredField("headerParam").getAnnotations()[0];
        Map<Class, Annotation> anns = new HashMap<>();
        anns.put(HeaderParam.class, ann);

        requestParameters.addParameter("testHeader", anns);
        MultivaluedHashMap<String, Object> headers = requestParameters.getHeaders();
        LinkedList<String> headerList = new LinkedList<>();
        headerList.add("testHeader");

        assertEquals(headerList, headers.get("headerParam"));
    }

    @Test
    public void testAddMatrixParameter() throws NoSuchFieldException, IntrospectionException,
            InvocationTargetException, IllegalAccessException {

        WebTarget webTarget = getExampleTarget();
        RequestParameters requestParameters = getEmptyRequestParameters(webTarget);

        MyGetBeanParam getBeanParam = new MyGetBeanParam();
        Annotation ann = getBeanParam.getClass().getDeclaredField("matrixParam").getAnnotations()[0];
        Map<Class, Annotation> anns = new HashMap<>();
        anns.put(MatrixParam.class, ann);

        requestParameters.addParameter("testMatrix", anns);
        String uri = requestParameters.getNewTarget().getUriBuilder().build().toString();

        assertEquals(baseURL + "/;matrixParam=testMatrix", uri);
    }

    @Test
    public void testAddCookieParameter() throws NoSuchFieldException, IntrospectionException,
            InvocationTargetException, IllegalAccessException {

        WebTarget webTarget = getExampleTarget();
        RequestParameters requestParameters = getEmptyRequestParameters(webTarget);

        MyGetBeanParam getBeanParam = new MyGetBeanParam();
        Annotation ann = getBeanParam.getClass().getDeclaredField("cookieParam").getAnnotations()[0];
        Map<Class, Annotation> anns = new HashMap<>();
        anns.put(CookieParam.class, ann);

        Cookie cookie = new Cookie("cookieParam", "testCookie");
        requestParameters.addParameter(cookie, anns);
        List<Cookie> cookies = requestParameters.getCookies();

        assertEquals(cookie, cookies.get(0));
    }

    @Test
    public void testAddFormParameter() throws NoSuchFieldException, IntrospectionException,
            InvocationTargetException, IllegalAccessException {

        WebTarget webTarget = getExampleTarget();
        RequestParameters requestParameters = getEmptyRequestParameters(webTarget);

        MyBeanParam beanParam = new MyBeanParam();
        Annotation ann = beanParam.getClass().getDeclaredField("formParam1").getAnnotations()[0];
        Map<Class, Annotation> anns = new HashMap<>();
        anns.put(FormParam.class, ann);

        requestParameters.addParameter("testForm", anns);
        Form form = requestParameters.getForm();
        LinkedList<String> formList = new LinkedList<>();
        formList.add("testForm");

        assertEquals(formList, form.asMap().get("formParam1"));
    }

    @Test
    public void testAddBeanParameter() throws NoSuchFieldException, IntrospectionException,
            InvocationTargetException, IllegalAccessException {

        WebTarget webTarget = getExampleTarget();
        RequestParameters requestParameters = getEmptyRequestParameters(webTarget);

        MyGetBeanParam getBeanParam = new MyGetBeanParam();
        Annotation ann = getBeanParam.getClass().getDeclaredField("queryParam").getAnnotations()[0];
        Map<Class, Annotation> anns = new HashMap<>();
        anns.put(BeanParam.class, ann);

        getBeanParam.setQueryParam("testQuery");

        requestParameters.addParameter(getBeanParam, anns);
        String uri = requestParameters.getNewTarget().getUriBuilder().build().toString();

        assertEquals(baseURL + "/?queryParam=testQuery", uri);
    }
}