package com.frame.properties;

import java.io.IOException;
import java.util.Properties;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class MyPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	/** 开发配置文档 **/
	private Resource[] localeResource = null;
	
	/**
	 * <p>设置本地环境配置</p>
	 * @param localeResource 本地环境配置<br>
	 */
	public void setLocaleResource(Resource[] localeResource) {
		this.localeResource = localeResource;
	}
	
	@Override
	protected void loadProperties(Properties props) throws IOException {
		super.setLocations(localeResource);
		super.loadProperties(props);
	}
}
