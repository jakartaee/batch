/*
 * Copyright 2012, 2022 International Business Machines Corp.
 * 
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership. Licensed under the Apache License, 
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package jakarta.batch.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.enterprise.util.Nonbinding;
import jakarta.inject.Qualifier;

/**
 * Annotation used by batch artifacts and CDI Beans to declare a field
 * or other element which is injectable via a JSL-defined value
 * (possibly leveraging Job XML substitutions).  Note by "batch
 * artifact" we mean an implementation of one of the interfaces in one of the 
 * jakarta.batch.* packages, implementing some portion of a job.
 *
 * For a "batch-managed artifact", (a "batch artifact" loaded by the batch implementation
 * but not as a CDI Bean), this annotation can only be used on a field. 
 *
 * For a CDI Bean, which may be a "batch artifact" or may be some other Bean loaded in 
 * the scope of a batch artifact according to the Jakarta Batch specification, this annotation
 * may also be used on a constructor parameter or method parameter.
 */
@Qualifier
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface BatchProperty {
    
    @Nonbinding
    public String name() default ""; 
}
