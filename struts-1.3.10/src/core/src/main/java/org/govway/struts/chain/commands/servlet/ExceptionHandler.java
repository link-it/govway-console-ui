/*
 * $Id: ExceptionHandler.java 471754 2006-11-06 14:55:09Z husted $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
/*
 * Modificato da Link.it (https://link.it) per applicazione patch di sicurezza e migrazione a jakarta EE
 * 
 * Copyright (c) 2022-2025 Link.it srl (https://link.it). 
 */
package org.govway.struts.chain.commands.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.govway.struts.action.ActionForm;
import org.govway.struts.action.ActionMapping;
import org.govway.struts.chain.commands.AbstractExceptionHandler;
import org.govway.struts.chain.commands.util.ClassUtils;
import org.govway.struts.chain.contexts.ActionContext;
import org.govway.struts.chain.contexts.ServletActionContext;
import org.govway.struts.config.ActionConfig;
import org.govway.struts.config.ExceptionConfig;
import org.govway.struts.config.ForwardConfig;
import org.govway.struts.config.ModuleConfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * <p>Handle the specified exception.</p>
 *
 * @version $Rev: 471754 $ $Date: 2005-05-07 12:11:38 -0400 (Sat, 07 May 2005)
 *          $
 */
public class ExceptionHandler extends AbstractExceptionHandler {
    // ------------------------------------------------------ Instance Variables
    private static final Log log = LogFactory.getLog(ExceptionHandler.class);

    // ------------------------------------------------------- Protected Methods
    protected ForwardConfig handle(ActionContext context, Exception exception,
        ExceptionConfig exceptionConfig, ActionConfig actionConfig,
        ModuleConfig moduleConfig)
        throws Exception {
        // Look up the remaining properties needed for this handler
        ServletActionContext sacontext = (ServletActionContext) context;
        ActionForm actionForm = (ActionForm) sacontext.getActionForm();
        HttpServletRequest request = sacontext.getRequest();
        HttpServletResponse response = sacontext.getResponse();

        // Handle this exception
        org.govway.struts.action.ExceptionHandler handler =
            (org.govway.struts.action.ExceptionHandler) ClassUtils
            .getApplicationInstance(exceptionConfig.getHandler());

        return (handler.execute(exception, exceptionConfig,
            (ActionMapping) actionConfig, actionForm, request, response));
    }
}
