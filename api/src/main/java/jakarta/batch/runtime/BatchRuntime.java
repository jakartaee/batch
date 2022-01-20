/*
 * Copyright 2012, 2020, 2019 International Business Machines Corp.
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
package jakarta.batch.runtime;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ServiceLoader;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;

import jakarta.batch.operations.JobOperator;

/**
 * BatchRuntime represents the Jakarta Batch Runtime.
 * It provides factory access to the JobOperator interface.
 *
 */
public class BatchRuntime {

    private final static String sourceClass = BatchRuntime.class.getName();
    private final static Logger logger = System.getLogger(sourceClass);

	/**
	* The getJobOperator factory method returns
	* an instance of the JobOperator interface.
	*
	* @return JobOperator instance.
	*/
	public static JobOperator getJobOperator() {
		JobOperator operator = null;
		if (System.getSecurityManager() == null) {
			for (JobOperator provider : ServiceLoader.load(JobOperator.class)) {
				if (logger.isLoggable(Level.DEBUG)) {
					logger.log(Level.DEBUG, "Loaded JobOperator with class: " + provider.getClass().getCanonicalName());
				}
				operator = provider;
				break;
			}
		} else {
			operator = AccessController.doPrivileged(new PrivilegedAction<JobOperator>() {
				public JobOperator run() {

					ServiceLoader<JobOperator> loader = ServiceLoader.load(JobOperator.class);
					JobOperator returnVal = null;
					for (JobOperator provider : loader) {
						if (logger.isLoggable(Level.DEBUG)) {
							logger.log(Level.DEBUG, "Loaded JobOperator with class: " + provider.getClass().getCanonicalName());
						}
						// Use first one
						returnVal = provider;
						break;
					}

					return returnVal;
				}
			});
		}

		if (operator == null) {
			if (logger.isLoggable(Level.WARNING)) {
				logger.log(Level.WARNING, "The ServiceLoader was unable to find an implementation for JobOperator. Check classpath for META-INF/services/jakarta.batch.operations.JobOperator file.");
			}
		}
		return operator;
	}
}
