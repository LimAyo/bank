package com.hotfolder.bank.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;


/** Manages Spring intergration Configuration.
 * @author Ayoub Lim
 */
@Configuration
@ImportResource("classpath:integrationconf/custom-hot-folder-spring.xml")
@PropertySource("classpath:integrationconf/integration.properties")
@ComponentScan(basePackages={"com.hotfolder.bank.integration"})
public class IntegrationConf
{

}
