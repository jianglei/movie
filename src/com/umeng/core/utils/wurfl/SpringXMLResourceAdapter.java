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

import java.io.File;
import java.io.IOException;

import net.sourceforge.wurfl.core.resource.WURFLResource;
import net.sourceforge.wurfl.core.resource.WURFLResourceException;
import net.sourceforge.wurfl.core.resource.XMLResource;
import com.umeng.core.utils.wurfl.SpringResourceAdapter;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

/**
 * Subclass of SpringWURFLResource building XMLResource as delegate.
 *
 * @author Fantayeneh Asres Gizaw
 * @author Filippo De Luca
 * @author Ermanno Franco
 * @version $Id$
 */
public class SpringXMLResourceAdapter extends SpringResourceAdapter {

    /**
     * Build resource by Spring Resource
     *
     * @param resource Spring Resource
     */
    public SpringXMLResourceAdapter(Resource resource) {
        super(resource);
    }
    
    public SpringXMLResourceAdapter(String path) {
        super(path);
    }
    
    /**
     * Create XMLREsource as delegate.
     *
     * @param resource The Resource to create delegate from.
     * @return WURFLResource delegate.
     */
    protected final WURFLResource createDelegate(Resource resource) {

        try {

            if (resource instanceof InputStreamResource) {
                return new XMLResource(resource.getInputStream());
            } else {
                return new XMLResource(resource.createRelative("")+resource.getFilename());
            }
        } catch (IOException e) {
            throw new WURFLResourceException(this, e);
        }

    }

    /**
     * Create XMLREsource as delegate.
     *
     * @param resource The Resource to create delegate from.
     * @return WURFLResource delegate.
     */
    protected final WURFLResource createDelegate(String path) {

       return new XMLResource(new File(path));

    }

}
