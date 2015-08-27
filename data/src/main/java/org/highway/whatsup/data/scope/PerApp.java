package org.highway.whatsup.data.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by engeng on 7/29/15.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApp {}
