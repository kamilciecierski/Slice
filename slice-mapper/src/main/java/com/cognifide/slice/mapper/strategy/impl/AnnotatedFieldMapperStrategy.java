package com.cognifide.slice.mapper.strategy.impl;

/*
 * #%L
 * Slice - Mapper
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


import java.lang.reflect.Field;

import com.cognifide.slice.mapper.annotation.JcrProperty;
import com.cognifide.slice.mapper.strategy.MapperStrategy;

/**
 * AnnotatedFieldMapperStrategy defines a strategy where only fields annotated by {@link JcrProperty} are
 * mapped.
 * 
 */
public class AnnotatedFieldMapperStrategy implements MapperStrategy {

	@Override
	public boolean shouldFieldBeMapped(Field field) {
		return field.isAnnotationPresent(JcrProperty.class);
	}

}
