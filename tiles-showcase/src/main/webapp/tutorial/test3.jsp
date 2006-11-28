<%--
/*
 * $Id$
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */
--%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<tiles:insert template='/test/templateLayout.jsp'>
  <tiles:put name="title"  content="My first page" direct="true"/>
  <tiles:put name="header" content="/common/header.jsp" direct="true"/>
  <tiles:put name="footer" content="/common/footer.jsp" />
  <tiles:put name="menu"   content="/basic/menu.jsp" direct="true"/>
   <tiles:put name="body" content='/testAction.do' type="page"/>
</tiles:insert>

<%--
   <tiles:put name="body" content='/testAction2.do'/>
   <tiles:put name="body" direct='true'>
      <bean:insert id='xout2' page='/forwardExampleAction.do'/>
      <bean:write name='xout2' filter='false'/>
    </tiles:put>
--%>