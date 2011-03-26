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

package org.chromattic.core.mapper.onetomany.reference;

import org.chromattic.core.mapper.JCRNodePropertyMapper;
import org.chromattic.core.ObjectContext;
import org.chromattic.core.jcr.LinkType;
import org.chromattic.core.bean.SingleValuedPropertyInfo;
import org.chromattic.core.bean.BeanValueInfo;

/**
 * @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a>
 * @version $Revision$
 */
public class JCRNamedReferentPropertyMapper extends JCRNodePropertyMapper {

  /** . */
  private final String propertyName;

  /** . */
  private final LinkType linkType;

  public JCRNamedReferentPropertyMapper(
    SingleValuedPropertyInfo<BeanValueInfo> info,
    String propertyName,
    LinkType linkType) throws ClassNotFoundException {
    super(info);

    //
    this.propertyName = propertyName;
    this.linkType = linkType;
  }

  @Override
  public Object get(ObjectContext context) throws Throwable {
    Class<?> relatedClass = getRelatedClass();
    Object related = context.getRelated(propertyName, linkType);
    if (related == null) {
      return null;
    } else {
      if (relatedClass.isInstance(related)) {
        return related;
      } else {
        throw new ClassCastException("Related with class " + related.getClass().getName() + " is not of class " + relatedClass);
      }
    }
  }

  @Override
  public void set(ObjectContext ctx, Object value) throws Throwable {
    ctx.setRelated(propertyName, value, linkType);
  }
}
