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

import org.chromattic.core.mapper.NodeTypeMapper;
import org.chromattic.core.mapping.NodeTypeMapping;
import org.chromattic.core.mapper.TypeMapperBuilder;
import org.chromattic.core.jcr.info.NodeInfoManager;
import org.chromattic.core.query.QueryManager;
import org.chromattic.spi.instrument.Instrumentor;
import org.chromattic.api.format.ObjectFormatter;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;

/**
 * @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a>
 * @version $Revision$
 */
public class Domain {

  /** . */
  private final Map<String, NodeTypeMapper> typeMapperByNodeType;

  /** . */
  private final Map<Class<?>, NodeTypeMapper> typeMapperByClass;

  /** . */
  private final Instrumentor instrumentor;
  
  /** . */
  final ObjectFormatter objectFormatter;

  /** . */
  final boolean stateCacheEnabled;

  /** . */
  final boolean hasPropertyOptimized;
  /** . */

  final boolean hasNodeOptimized;

  /** . */
  final String rootNodePath;

  /** . */
  final NodeInfoManager nodeInfoManager;

  /** . */
  final QueryManager queryManager;

  public Domain(
    Set<NodeTypeMapping> typeMappings,
    Instrumentor instrumentor,
    ObjectFormatter objectFormatter,
    boolean stateCacheEnabled,
    boolean hasPropertyOptimized,
    boolean hasNodeOptimized,
    String rootNodePath) {

    //
    TypeMapperBuilder builder = new TypeMapperBuilder(typeMappings, instrumentor);

    //
    Map<String, NodeTypeMapper> typeMapperByNodeType = new HashMap<String, NodeTypeMapper>();
    Map<Class<?>, NodeTypeMapper> typeMapperByClass = new HashMap<Class<?>, NodeTypeMapper>();
    for (NodeTypeMapper typeMapper : builder.build()) {
      if (typeMapperByNodeType.containsKey(typeMapper.getNodeTypeName())) {
        throw new IllegalStateException("Duplicate node type name " + typeMapper);
      }
      typeMapperByNodeType.put(typeMapper.getNodeTypeName(), typeMapper);
      typeMapperByClass.put(typeMapper.getObjectClass(), typeMapper);
    }

    //
    this.typeMapperByClass = typeMapperByClass;
    this.typeMapperByNodeType = typeMapperByNodeType;
    this.instrumentor = instrumentor;
    this.objectFormatter = objectFormatter;
    this.stateCacheEnabled = stateCacheEnabled;
    this.hasPropertyOptimized = hasPropertyOptimized;
    this.hasNodeOptimized = hasNodeOptimized;
    this.rootNodePath = rootNodePath;
    this.nodeInfoManager = new NodeInfoManager();
    this.queryManager = new QueryManager();
  }

  public boolean isHasPropertyOptimized() {
    return hasPropertyOptimized;
  }

  public boolean isHasNodeOptimized() {
    return hasNodeOptimized;
  }

  public Instrumentor getInstrumentor() {
    return instrumentor;
  }

  public NodeTypeMapper getTypeMapper(String nodeTypeName) {
    return typeMapperByNodeType.get(nodeTypeName);
  }

  public NodeTypeMapper getTypeMapper(Class<?> clazz) {
    return typeMapperByClass.get(clazz);
  }

  public QueryManager getQueryManager() {
    return queryManager;
  }
}