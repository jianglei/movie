/**
 * Copyright (c) 2011 ScientiaMobile Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Refer to the COPYING file distributed with this package.
 */

package com.umeng.core.utils.wurfl;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sourceforge.wurfl.core.CapabilitiesHolderFactory;
import net.sourceforge.wurfl.core.DefaultDeviceProvider;
import net.sourceforge.wurfl.core.DefaultWURFLManager;
import net.sourceforge.wurfl.core.DefaultWURFLService;
import net.sourceforge.wurfl.core.Device;
import net.sourceforge.wurfl.core.DeviceProvider;
import net.sourceforge.wurfl.core.MarkupResolver;
import net.sourceforge.wurfl.core.WURFLHolder;
import net.sourceforge.wurfl.core.WURFLManager;
import net.sourceforge.wurfl.core.WURFLService;
import net.sourceforge.wurfl.core.WURFLUtils;
import net.sourceforge.wurfl.core.cache.CacheProvider;
import net.sourceforge.wurfl.core.classifiers.FilterChain;
import net.sourceforge.wurfl.core.matchers.MatcherChain;
import net.sourceforge.wurfl.core.matchers.MatcherManager;
import net.sourceforge.wurfl.core.request.DefaultWURFLRequestFactory;
import net.sourceforge.wurfl.core.request.FastWURFLRequestFactory;
import net.sourceforge.wurfl.core.request.UserAgentNormalizerFactory;
import net.sourceforge.wurfl.core.request.UserAgentResolver;
import net.sourceforge.wurfl.core.request.WURFLRequest;
import net.sourceforge.wurfl.core.request.WURFLRequestFactory;
import net.sourceforge.wurfl.core.resource.DefaultWURFLModel;
import net.sourceforge.wurfl.core.resource.WURFLModel;
import net.sourceforge.wurfl.core.resource.WURFLResource;
import net.sourceforge.wurfl.core.resource.WURFLResources;
import net.sourceforge.wurfl.core.web.WurflWebConstants;
import com.umeng.core.utils.wurfl.SpringXMLResourceAdapter;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.context.ServletContextAware;

/**
 * Spring Main Engine Manager: it's self-configured, following the "Convention over Configuration" principle.<br/>
 * <ul> 
 * <li>Wurfl root file default location is "/WEB-INF/wurfl.zip" ({@link WurflWebConstants#WURFL_DEFAULT_LOCATION})<br/>
 * Customize it setting the property 'wurfl' in Spring XML config, pointing to your file path, relative to the webapp context path.<br/>
 * <li>If you have one patch file, you can declare path with property 'wurflPatch'.<br/>
 * If you have two or more patch files, declare paths with property 'wurflPatches', which expects a list of values.<br/>
 * For example:<pre> 
    &lt;property name="wurflPatches"&gt;
        &lt;list&gt;
            &lt;value&gt;/WEB-INF/web_browsers_patch.xml&lt;/value&gt;
            &lt;value&gt;/WEB-INF/web_browsers_patch2.xml&lt;/value&gt;
        &lt;/list&gt;
    &lt;/property&gt;
    </pre> 
 * <p> 
 * <li>All sensible default collaborators are provided.<br/>
 * Nevertheless, you can customize them as you want.<br/>Here are the collaborators you can declare:<br/>
 * wurflModel, matcherManager, markupResolver, capabilitiesHolderFactory, deviceProvider, cacheProvider, filterChain, matcherChain, handlersFactory, userAgentNormalizerFactory, wurflService,
 * also a custom wurflManager.
 * </p> 
 * <li>This Manager implements also the WURFLHolder interface, basically to developers facility.<br/>
 * However, calling {@link #getWURFLManager()} gives a self-reference.<br/>
 * <li>Moreover, it's self-registered in the Servlet Context attributes map, for use outside Spring:
 * the default attribute key for WurfHolder is "net.sourceforge.wurfl.core.WURFLHolder" ({@link WurflWebConstants#WURFL_HOLDER_KEY}).<br/>
 * In future releases we are planning to rename it as  "net.sourceforge.wurfl.core.WURFLManager"<br/>
 * You can also customize the attribute key setting the property 'wurflHolderKey' in Spring XML file.
 * </ul>
 * @see WurflWebConstants#WURFL_DEFAULT_LOCATION  
 * @see WurflWebConstants#WURFL_HOLDER_KEY 
 */
public class SpringWurflManagerByMe implements WURFLManager, WURFLHolder, WurflWebConstants, InitializingBean, ResourceLoaderAware, ServletContextAware {


    private WURFLResource wurfl = null;
    @SuppressWarnings("unchecked")
    private List wurflResources = null;

    private MatcherManager matcherManager;
    private MarkupResolver markupResolver;
    private CapabilitiesHolderFactory capabilitiesHolderFactory;
    private DeviceProvider deviceProvider;
    private CacheProvider cacheProvider;
    private WURFLService wurflService;
    private WURFLRequestFactory wurflRequestFactory;

    private UserAgentNormalizerFactory userAgentNormalizerFactory;
    //private HandlersFactory handlersFactory;
    private FilterChain filterChain;
    private MatcherChain matcherChain;

    private UserAgentResolver userAgentResolver;
    private WURFLManager wurflManager;
    private WURFLUtils wurflUtils;
    private WURFLModel model;
    private ResourceLoader resourceLoader;
    private boolean ok;
    //private ServletContext servletContext;
    private final transient Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * default value: WurflWebConstants.WURFL_HOLDER_KEY
     */
    private String wurflHolderKey = WURFL_HOLDER_KEY;


    public void setWurflHolderKey(String wurflHolderKey) {
        Validate.notEmpty(wurflHolderKey);
        this.wurflHolderKey = wurflHolderKey;
    }

    public void setMarkupResolver(MarkupResolver markupResolver) {
        this.markupResolver = markupResolver;
    }

    public void setCapabilitiesHolderFactory(CapabilitiesHolderFactory capabilitiesHolderFactory) {
        this.capabilitiesHolderFactory = capabilitiesHolderFactory;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void setWurflUtils(WURFLUtils wurflUtils) {
        this.wurflUtils = wurflUtils;
    }

    public void setWurflManager(WURFLManager wurflManager) {
        this.wurflManager = wurflManager;
    }

    public void setWurflService(WURFLService wurflService) {
        this.wurflService = wurflService;
    }

    public void setWurflRequestFactory(WURFLRequestFactory wurflRequestFactory) {
        this.wurflRequestFactory = wurflRequestFactory;
    }

    public void setUserAgentNormalizerFactory(UserAgentNormalizerFactory userAgentNormalizerFactory) {
        this.userAgentNormalizerFactory = userAgentNormalizerFactory;
    }

    public void setUserAgentResolver(UserAgentResolver userAgentResolver) {
        this.userAgentResolver = userAgentResolver;
    }

    public void setMatcherManager(MatcherManager matcherManager) {
        this.matcherManager = matcherManager;
    }

    public void setDeviceProvider(DeviceProvider deviceProvider) {
        this.deviceProvider = deviceProvider;
    }

    public void setCacheProvider(CacheProvider cacheProvider) {
        this.cacheProvider = cacheProvider;
    }

    public void setWurflModel(WURFLModel delegate) {
        this.model = delegate;
    }

/*    public void setHandlersFactory(HandlersFactory handlersFactory) {
        this.handlersFactory = handlersFactory;
    }*/

    public void setFilterChain(FilterChain filterChain) {
        this.filterChain = filterChain;
    }

    public void setMatcherChain(MatcherChain matcherChain) {
        this.matcherChain = matcherChain;
    }

    public void setWurfl(String path) {
//        Validate.notNull(wurfl);
        this.wurfl = new SpringXMLResourceAdapter(path);

    }

    public void setWurflPatch(Resource wurflPatch) {
        Validate.notNull(wurflPatch);
        setWurflPatches(new Resource[]{wurflPatch});
    }

    @SuppressWarnings("unchecked")
    public void setWurflPatches(Resource[] wurflPatches) {
        Validate.notNull(wurflPatches);
        Validate.notEmpty(wurflPatches, "wurfl patches path cannot be empty");

        List wurflResources = new ArrayList();
        for (int i = 0; i < wurflPatches.length; i++) {
            SpringXMLResourceAdapter resourceAdapter = new SpringXMLResourceAdapter(wurflPatches[i]);
            wurflResources.add(resourceAdapter);
        }
        this.wurflResources = wurflResources;
    }

    /**
     * Implementation to respect WURFLHolder contract.
     *
     * @return this
     */
    public WURFLManager getWURFLManager() {
        if (!ok) try {
            afterPropertiesSet();
        } catch (Exception e) {
            logger.error("cannot initialize: " + e);
            e.printStackTrace();
        }
        return this;
    }

    public WURFLUtils getWURFLUtils() {
        if (!ok) try {
            afterPropertiesSet();
        } catch (Exception e) {
            logger.error("cannot initialize: " + e);
            e.printStackTrace();
        }
        return wurflUtils;
    }

    public void setServletContext(ServletContext servletContext) {
        servletContext.setAttribute(wurflHolderKey, this);
        if (logger.isInfoEnabled()) {
            logger.info("Exported WURFLHolder in Servlet Context with attribute key '" + wurflHolderKey + "'");
        }
    }

    /**
     * Invoked by Spring when all the collaborators (if any) are set.
     *
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {

        if (model == null) {
            if (wurfl == null) {
                Resource resource = resourceLoader.getResource(WURFL_DEFAULT_LOCATION);
                wurfl = new SpringXMLResourceAdapter(resource);
            }        
            if (wurflResources == null) {
                model = new DefaultWURFLModel(wurfl);
            } else {
                model = new DefaultWURFLModel(wurfl, new WURFLResources(wurflResources));
            }
        } else {
            if (logger.isInfoEnabled()) {
                logger.info("model is custom: " + model.getClass().getName());
            }
        }
        
        if (filterChain != null) {
            if (logger.isInfoEnabled()) {
                logger.info("filterChain is custom: " + filterChain.getClass().getName());
            }
        }
        if (matcherChain != null) {
            if (logger.isInfoEnabled()) {
                logger.info("matcherChain is custom: " + matcherChain.getClass().getName());
            }
        }
/*        if (handlersFactory != null) {
            if (logger.isInfoEnabled()) {
                logger.info("handlersFactory is custom: " + handlersFactory.getClass().getName());
            }
        }*/
        if (matcherManager == null) {
            if (filterChain != null && matcherChain != null) {
                matcherManager = new MatcherManager(model, filterChain, matcherChain);
            } /*else if (handlersFactory != null) {
                if (filterChain == null) filterChain = new FilterChainFactory(handlersFactory).create();
                if (matcherChain == null) matcherChain = new MatcherChainFactory(handlersFactory).create();
                matcherManager = new MatcherManager(model, filterChain, matcherChain);
            }*/ else
                matcherManager = new MatcherManager(model);
        } else {
            if (logger.isInfoEnabled()) {
                logger.info("matcherManager is custom: " + matcherManager.getClass().getName());
            }
        }

        if (markupResolver != null) {
            if (logger.isInfoEnabled()) {
                logger.info("markupResolver is custom: " + markupResolver.getClass().getName());
            }
        }
        if (capabilitiesHolderFactory != null) {
            if (logger.isInfoEnabled()) {
                logger.info("capabilitiesHolderFactory is custom: " + capabilitiesHolderFactory.getClass().getName());
            }
        }
        if (deviceProvider == null) {
            if (markupResolver != null) {
                if (capabilitiesHolderFactory != null) {
                    deviceProvider = new DefaultDeviceProvider(model, capabilitiesHolderFactory, markupResolver);
                } else {
                    deviceProvider = new DefaultDeviceProvider(model, markupResolver);
                }
            } else {
                if (capabilitiesHolderFactory != null) {
                    deviceProvider = new DefaultDeviceProvider(model, capabilitiesHolderFactory);
                } else {
                    deviceProvider = new DefaultDeviceProvider(model);
                }
            }
        }
        if (cacheProvider != null) {
            if (logger.isInfoEnabled()) {
                logger.info("cacheProvider is custom: " + cacheProvider.getClass().getName());
            }
        }
        if (wurflService == null) {
            if (cacheProvider == null) {
                wurflService = new DefaultWURFLService(matcherManager, deviceProvider);
            } else {
                wurflService = new DefaultWURFLService(matcherManager, deviceProvider, cacheProvider);
            }
        } else {
            if (logger.isInfoEnabled()) {
                logger.info("wurflService is custom: " + wurflService.getClass().getName());
            }
        }

        if (userAgentResolver != null) {
            if (logger.isInfoEnabled()) {
                logger.info("userAgentResolver is custom: " + userAgentResolver.getClass().getName());
            }
        }
        if (userAgentNormalizerFactory != null) {
            if (logger.isInfoEnabled()) {
                logger.info("userAgentNormalizerFactory is custom: " + userAgentNormalizerFactory.getClass().getName());
            }
        }
        if (wurflRequestFactory == null) {
            if (userAgentResolver != null) {
                if (userAgentNormalizerFactory == null) {
                    wurflRequestFactory = new FastWURFLRequestFactory(userAgentResolver);
                } else
                    wurflRequestFactory = new DefaultWURFLRequestFactory(userAgentResolver, userAgentNormalizerFactory.create());
            } else {
                if (userAgentNormalizerFactory != null) {
                    wurflRequestFactory = new DefaultWURFLRequestFactory(userAgentNormalizerFactory.create());
                }
            }
        } else {
            if (logger.isInfoEnabled()) {
                logger.info("wurflRequestFactory is custom: " + wurflRequestFactory.getClass().getName());
            }
        }

        if (wurflManager == null) {
            if (wurflRequestFactory == null) {
                wurflManager = new DefaultWURFLManager(wurflService);
            } else {
                wurflManager = new DefaultWURFLManager(wurflService, wurflRequestFactory);
            }
        } else {
            if (logger.isInfoEnabled()) {
                logger.info("wurflManager is custom: " + wurflManager.getClass().getName());
            }
        }
        if (wurflUtils == null) wurflUtils = new WURFLUtils(model, deviceProvider);
        ok = true;
    }

    public Device getDeviceForRequest(HttpServletRequest request) {
        return wurflManager.getDeviceForRequest(request);
    }

    public Device getDeviceForRequest(WURFLRequest request) {
        return wurflManager.getDeviceForRequest(request);
    }

    public Device getDeviceForRequest(String userAgent) {
        return wurflManager.getDeviceForRequest(userAgent);
    }
}
