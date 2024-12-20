package com.ccp.vis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ccp.decorators.CcpStringDecorator;
import com.ccp.dependency.injection.CcpDependencyInjection;
import com.ccp.dependency.injection.CcpInstanceProvider;
import com.ccp.implementations.cache.gcp.memcache.CcpGcpMemCache;
import com.ccp.implementations.db.crud.elasticsearch.CcpElasticSearchCrud;
import com.ccp.implementations.db.utils.elasticsearch.CcpElasticSearchDbRequest;
import com.ccp.implementations.file.bucket.gcp.CcpGcpFileBucket;
import com.ccp.implementations.http.apache.mime.CcpApacheMimeHttp;
import com.ccp.implementations.json.gson.CcpGsonJsonHandler;
import com.ccp.implementations.main.authentication.gcp.oauth.CcpGcpMainAuthentication;
import com.ccp.implementations.mensageria.sender.gcp.pubsub.CcpGcpPubSubMensageriaSender;
import com.ccp.implementations.password.mindrot.CcpMindrotPasswordHandler;
import com.ccp.jn.async.business.factory.CcpJnAsyncBusinessFactory;
import com.ccp.local.testings.implementations.CcpLocalInstances;
import com.ccp.local.testings.implementations.cache.CcpLocalCacheInstances;
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
		CcpInstanceProvider<?> businessInstanceProvider = new CcpJnAsyncBusinessFactory();
		CcpDependencyInjection.loadAllDependencies
		(
				localEnviroment ? CcpLocalInstances.mensageriaSender.getLocalImplementation(businessInstanceProvider) : new CcpGcpPubSubMensageriaSender(),
				localEnviroment ? CcpLocalCacheInstances.map.getLocalImplementation(businessInstanceProvider) : new CcpGcpMemCache(),
				localEnviroment ? CcpLocalInstances.bucket.getLocalImplementation(businessInstanceProvider) : new CcpGcpFileBucket(),
				new CcpElasticSearchDbRequest(),
				new CcpMindrotPasswordHandler()
				,new CcpGcpMainAuthentication()
				,new CcpElasticSearchCrud()
				,new CcpGsonJsonHandler()
				,new CcpApacheMimeHttp() 
		);

		SpringApplication.run(ApplicationStarterVisSyncSpring.class, args);
	}


}
