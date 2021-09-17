package io.nemours.cs.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/people/active-associates")
                        .uri("lb://people-service"))
                .route(r -> r.path("/people/lookup")
                		  .uri("lb://people-service"))
                .route(r -> r.path("/auth/login").and().method(HttpMethod.POST, HttpMethod.OPTIONS)
                		.uri("lb://envoy-service"))
                .route(r -> r.path("/auth/ping")
                		.uri("lb://envoy-service"))
                .route(r -> r.path("/departmentmessage/messages")
                		.uri("lb://envoy-service"))
                .route(r -> r.path("/departmentmessage/departments")
                		.uri("lb://envoy-service"))
                .route(r -> r.path("/pagingpatients")
                		.uri("lb://envoy-service"))
                .build();
    }
}
