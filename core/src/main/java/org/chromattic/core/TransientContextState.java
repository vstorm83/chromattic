/*
 * Copyright (C) 2003-2009 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.chromattic.core;

import org.chromattic.common.JCR;
import org.chromattic.api.Status;
import org.chromattic.core.bean.SimpleValueInfo;

import javax.jcr.Node;
import javax.jcr.nodetype.NodeType;
import java.util.Map;

/**
 * @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a>
 * @version $Revision$
 */
class TransientContextState extends ContextState {

  /** . */
  private String name;

  /** . */
  private Map<String, Object> properties;

  TransientContextState(String name, NodeType primaryNodeType) {
    super(primaryNodeType);

    //
    this.name = name;
  }

  public String getName() {
    return name;
  }

  void setName(String name) {
    if (name != null) {
      JCR.validateName(name);
    }
    this.name = name;
  }

  String getPath() {
    return null;
  }

  String getId() {
    throw new IllegalStateException();
  }

  Node getNode() {
    throw new IllegalStateException();
  }

  DomainSession getSession() {
    throw new IllegalStateException();
  }

  Status getStatus() {
    return Status.TRANSIENT;
  }

  Object getPropertyValue(String propertyName, SimpleValueInfo type) {
    throw new IllegalStateException();
  }

  <T> T getPropertyValues(String propertyName, SimpleValueInfo simpleType, ListType<T> listType) {
    throw new IllegalStateException();
  }

  void setPropertyValue(String propertyName, SimpleValueInfo type, Object o) {
    throw new IllegalStateException();
  }

  <T> void setPropertyValues(String propertyName, SimpleValueInfo type, ListType<T> listType, T objects) {
    throw new IllegalStateException();
  }

  public String toString() {
    return "ObjectStatus[status=" + Status.TRANSIENT + "]";
  }
}
