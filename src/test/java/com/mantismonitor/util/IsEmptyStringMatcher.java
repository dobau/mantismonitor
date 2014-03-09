package com.mantismonitor.util;

import org.mockito.ArgumentMatcher;
import org.springframework.util.StringUtils;

public class IsEmptyStringMatcher extends ArgumentMatcher<String> {

	@Override
	public boolean matches(Object argument) {
		return StringUtils.isEmpty((String) argument);
	}

}
