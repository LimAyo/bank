package com.hotfolder.bank.util;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Parser operating on a configured regular expression to return a matching part or null.
 */
@Component
public class RegexParser implements InitializingBean
{
	private static final int DEFAULT_GROUP = 0;

	private String regex="^.*\\.(csv)$";
	private Pattern pattern;

	@Override
	public void afterPropertiesSet()
	{
		Assert.hasLength(regex);
		pattern = Pattern.compile(regex);
	}

	/**
	 * Parse the value and return the matched part or null
	 * 
	 * @param value
	 * @return matched part or null
	 */
	public String parse(final String value)
	{
		return parse(value, DEFAULT_GROUP);
	}


	/**
	 * Parse the value and return the matched group or null
	 * 
	 * @param value
	 * @param group
	 * @return matched group or null
	 */
	public String parse(final String value, final int group)
	{
		Assert.isTrue(group >= 0);
		String result = null;
		if (!StringUtils.isBlank(value))
		{
			final Matcher matcher = pattern.matcher(value);
			if (matcher.find())
			{
				final String groupExp = matcher.group(group);
				if (!StringUtils.isBlank(groupExp))
				{
					result = groupExp;
				}
			}
		}
		return result;
	}

	/**
	 * @param regex
	 *           the regex to set
	 */
	public void setRegex(final String regex)
	{
		Assert.hasLength(regex);
		this.regex = regex;
	}

	/**
	 * @return the regex
	 */
	protected String getRegex()
	{
		return regex;
	}
}
