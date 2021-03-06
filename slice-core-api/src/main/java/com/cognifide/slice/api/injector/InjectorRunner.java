package com.cognifide.slice.api.injector;

/*-
 * #%L
 * Slice - Core
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2012 Cognifide Limited
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.osgi.framework.BundleContext;

import com.cognifide.slice.api.context.ContextScope;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class InjectorRunner {

	private final String injectorName;

	private final List<Module> modules = new ArrayList<Module>();

	private boolean started = false;

	private final BundleContext bundleContext;

	private final ContextScope contextScope;

	public InjectorRunner(final BundleContext bundleContext, final String injectorName,
			final ContextScope contextScope) {
		this.bundleContext = bundleContext;
		this.injectorName = injectorName;
		this.contextScope = contextScope;
	}

	public BundleContext getBundleContext() {
		return bundleContext;
	}

	public String getInjectorName() {
		return injectorName;
	}

	public void installModule(final Module newModule) {
		if (started) {
			throw new IllegalStateException("Installing new modules is not allowed after Injector was stared");
		}
		modules.add(newModule);
	}

	public void installModules(final List<Module> newModules) {
		if (started) {
			throw new IllegalStateException("Installing new modules is not allowed after Injector was stared");
		}
		modules.addAll(newModules);
	}

	public void start() {
		final Injector injector = Guice.createInjector(modules);

		final Dictionary<String, String> injectorAttr = new Hashtable<String, String>();
		injectorAttr.put("name", injectorName);

		bundleContext.registerService(Injector.class.getName(), injector, injectorAttr).getReference();

		started = true;
	}

	public ContextScope getContextScope() {
		return contextScope;
	}

}
