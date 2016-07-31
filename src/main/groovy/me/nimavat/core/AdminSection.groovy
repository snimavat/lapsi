package me.nimavat.core

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.RUNTIME)
public @interface AdminSection {

	String name()
	String icon() default ""
	int order() default 100
}
