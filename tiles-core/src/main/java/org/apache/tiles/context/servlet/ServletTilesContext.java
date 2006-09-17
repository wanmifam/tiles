/*
 * $Id$
 *
 * Copyright 1999-2006 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.tiles.context.servlet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tiles.TilesContext;

/**
 * Servlet-bsed implementation of the TilesContext interface.
 *
 * @version $Rev$ $Date$
 */
public class ServletTilesContext implements TilesContext {

    private ServletContext servletContext;
    
    private HttpServletRequest request;
    
    private HttpServletResponse response;
    
    /**
     * <p>The lazily instantiated <code>Map</code> of application scope
     * attributes.</p>
     */
    private Map applicationScope = null;

    /**
     * <p>The lazily instantiated <code>Map</code> of header name-value
     * combinations (immutable).</p>
     */
    private Map header = null;


    /**
     * <p>The lazily instantitated <code>Map</code> of header name-values
     * combinations (immutable).</p>
     */
    private Map headerValues = null;


    /**
     * <p>The lazily instantiated <code>Map</code> of context initialization
     * parameters.</p>
     */
    private Map initParam = null;


    /**
     * <p>The lazily instantiated <code>Map</code> of request
     * parameter name-value.</p>
     */
    private Map param = null;


    /**
     * <p>The lazily instantiated <code>Map</code> of request
     * parameter name-values.</p>
     */
    private Map paramValues = null;

     /**
     * <p>The lazily instantiated <code>Map</code> of request scope
     * attributes.</p>
     */
    private Map requestScope = null;
    
    /**
     * <p>The lazily instantiated <code>Map</code> of session scope
     * attributes.</p>
     */
    private Map sessionScope = null;


    /** Creates a new instance of ServletTilesContext */
    public ServletTilesContext(ServletContext servletContext) {
        initialize(servletContext, null, null);
    }

    /** Creates a new instance of ServletTilesContext */
    public ServletTilesContext(ServletContext servletContext, 
            HttpServletRequest request) {
        initialize(servletContext, request, null);
    }

    /** Creates a new instance of ServletTilesContext */
    public ServletTilesContext(ServletContext servletContext, 
            HttpServletRequest request,
            HttpServletResponse response) {
        initialize(servletContext, request, response);
    }

    public Map getApplicationScope() {

        if ((applicationScope == null) && (servletContext != null)) {
            applicationScope = new ServletApplicationScopeMap(servletContext);
        }
        return (applicationScope);

    }


    public Map getHeader() {

        if ((header == null) && (request != null)) {
            header = new ServletHeaderMap(request);
        }
        return (header);

    }


    public Map getHeaderValues() {

        if ((headerValues == null) && (request != null)) {
            headerValues = new ServletHeaderValuesMap(request);
        }
        return (headerValues);

    }


    public Map getInitParams() {

        if ((initParam == null) && (servletContext != null)) {
            initParam = new ServletInitParamMap(servletContext);
        }
        return (initParam);

    }


    public Map getParam() {

        if ((param == null) && (request != null)) {
            param = new ServletParamMap(request);
        }
        return (param);

    }


    public Map getParamValues() {

        if ((paramValues == null) && (request != null)) {
            paramValues = new ServletParamValuesMap(request);
        }
        return (paramValues);

    }


    public Map getRequestScope() {

        if ((requestScope == null) && (request != null)) {
            requestScope = new ServletRequestScopeMap(request);
        }
        return (requestScope);

    }


    public Map getSessionScope() {

        if ((sessionScope == null) && (request != null)) {
            sessionScope = new ServletSessionScopeMap(request.getSession());
        }
        return (sessionScope);

    }
    
    public void dispatch(String path) throws IOException, Exception {
        RequestDispatcher rd = request.getRequestDispatcher(path);
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            throw new Exception("Error forwarding request.", ex);
        }
    }

    public void include(String path) throws IOException, Exception {
        RequestDispatcher rd = request.getRequestDispatcher(path);
        try {
            rd.include(request, response);
        } catch (ServletException ex) {
            throw new Exception("Error including path.", ex);
        }
    }

    public URL getResource(String path) throws MalformedURLException {
        return servletContext.getResource(path);
    }

    public Locale getRequestLocale() {
        return request.getLocale();
    }

    public ServletContext getServletContext() {
        return servletContext;
    }
    
    public ServletRequest getRequest() {
        return request;
    }
    
    public ServletResponse getResponse() {
        return response;
    }
    
    /**
     * <p>Initialize (or reinitialize) this {@link ServletWebContext} instance
     * for the specified Servlet API objects.</p>
     *
     * @param context The <code>ServletContext</code> for this web application
     * @param request The <code>HttpServletRequest</code> for this request
     * @param response The <code>HttpServletResponse</code> for this request
     */
    public void initialize(ServletContext context,
                           HttpServletRequest request,
                           HttpServletResponse response) {

        // Save the specified Servlet API object references
        this.servletContext = context;
        this.request = request;
        this.response = response;

        // Perform other setup as needed

    }


    /**
     * <p>Release references to allocated resources acquired in
     * <code>initialize()</code> of via subsequent processing.  After this
     * method is called, subsequent calls to any other method than
     * <code>initialize()</code> will return undefined results.</p>
     */
    public void release() {

        // Release references to allocated collections
        applicationScope = null;
        header = null;
        headerValues = null;
        initParam = null;
        param = null;
        paramValues = null;
        requestScope = null;
        sessionScope = null;

        // Release references to Servlet API objects
        servletContext = null;
        request = null;
        response = null;

    }    
}