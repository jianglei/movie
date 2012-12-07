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

import net.sourceforge.wurfl.core.resource.ResourceData;
import net.sourceforge.wurfl.core.resource.WURFLResource;
import net.sourceforge.wurfl.spring.SpringXMLResourceAdapter;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.core.io.Resource;

/**
 * Decorator of WURFLResource to build from Spring Resource.
 *
 * @author Fantayeneh Asres Gizaw
 * @author Filippo De Luca
 * @author Ermanno Franco
 * @version $Id$
 */
public abstract class SpringResourceAdapter implements WURFLResource {

    /**
     * Delegate WURFLResource
     */
    protected final WURFLResource delegate;

    /**
     * Build resource from Spring Resource.
     *
     * @param resource The Spring Resource to build resource from.
     */
    public SpringResourceAdapter(Resource resource) {
        delegate = createDelegate(resource);
    }

    /**
     * Create delegate WURFLResource from Spring Resource.
     *
     * @param resource The Spring Resource from that build WURFLResource.
     * @return WURFLResource delegate.
     */
    protected abstract WURFLResource createDelegate(String path);
    
    
    /**
     * Build resource from Spring Resource.
     *
     * @param resource The Spring Resource to build resource from.
     */
    public SpringResourceAdapter(String path) {
        delegate = createDelegate(path);
    }

    /**
     * Create delegate WURFLResource from Spring Resource.
     *
     * @param resource The Spring Resource from that build WURFLResource.
     * @return WURFLResource delegate.
     */
    protected abstract WURFLResource createDelegate(Resource resource);

    // Delegate methods ***************************************************

    /**
     * {@inheritDoc}
     */
    public final ResourceData getData() {
        return delegate.getData();
    }

    /**
     * {@inheritDoc}
     */
    public final String getInfo() {
        return delegate.getInfo();
    }

    /**
     * {@inheritDoc}
     */
    public final void release() {
        delegate.release();
    }

    // Commons methods ****************************************************

    public String toString() {
        return new ToStringBuilder(this).append(delegate).toString();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getClass()).append(delegate)
                .toHashCode();
    }

//    public boolean equals(Object obj) {
//        EqualsBuilder eb = new EqualsBuilder();
//
//        eb.appendSuper(getClass().isInstance(obj));
//        if (eb.isEquals()) {
//            SpringXMLResourceAdapter other = (SpringXMLResourceAdapter) obj;
//            eb.append(delegate, other.delegate);
//        }
//
//        return eb.isEquals();
//    }

}