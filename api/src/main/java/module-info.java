/*
 * Copyright 2022 International Business Machines Corp. and others
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
module jakarta.batch {

    // Optional - note this transitively requires jakarta.inject
    requires static jakarta.cdi;

    exports jakarta.batch.api;
    exports jakarta.batch.api.chunk;
    exports jakarta.batch.api.chunk.listener;
    exports jakarta.batch.api.listener;
    exports jakarta.batch.api.partition;
    exports jakarta.batch.operations;
    exports jakarta.batch.runtime;
    exports jakarta.batch.runtime.context;

    uses jakarta.batch.operations.JobOperator;
}
