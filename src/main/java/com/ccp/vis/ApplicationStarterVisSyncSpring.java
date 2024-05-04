package com.ccp.vis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ccp.decorators.CcpStringDecorator;
import com.ccp.dependency.injection.CcpDependencyInjection;
import com.ccp.implementations.cache.gcp.memcache.CcpGcpMemCache;
import com.ccp.implementations.db.crud.elasticsearch.CcpElasticSearchCrud;
import com.ccp.implementations.db.utils.elasticsearch.CcpElasticSearchDbRequest;
import com.ccp.implementations.file.bucket.gcp.CcpGcpFileBucket;
import com.ccp.implementations.http.apache.mime.CcpApacheMimeHttp;
import com.ccp.implementations.json.gson.CcpGsonJsonHandler;
import com.ccp.implementations.main.authentication.gcp.oauth.CcpGcpMainAuthentication;
import com.ccp.implementations.mensageria.sender.gcp.pubsub.CcpGcpPubSubMensageriaSender;
import com.ccp.implementations.mensageria.sender.gcp.pubsub.local.CcpLocalEndpointMensageriaSender;
import com.ccp.implementations.password.mindrot.CcpMindrotPasswordHandler;
import com.ccp.vis.controller.ControllerVisResume;
import com.ccp.web.spring.exceptions.handler.CcpSyncExceptionHandler;


@EnableWebMvc
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
@ComponentScan(basePackageClasses = {
		ControllerVisResume.class, 
		CcpSyncExceptionHandler.class,
})
@SpringBootApplication
public class ApplicationStarterVisSyncSpring {
	
	public static void main(String[] args) {
		boolean localEnviroment = new CcpStringDecorator("c:\\rh").file().exists();
		CcpDependencyInjection.loadAllDependencies
		(
				localEnviroment ? new CcpLocalEndpointMensageriaSender() : new CcpGcpPubSubMensageriaSender()
				,new CcpGcpMainAuthentication()
				,new CcpGsonJsonHandler()
				,new CcpGcpFileBucket()
				,new CcpMindrotPasswordHandler()
				,new CcpElasticSearchDbRequest()
				,new CcpGcpMemCache()
				,new CcpApacheMimeHttp() 
				,new CcpElasticSearchCrud()
		);

		SpringApplication.run(ApplicationStarterVisSyncSpring.class, args);
	}


}
