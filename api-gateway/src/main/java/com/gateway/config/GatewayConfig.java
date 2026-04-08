//package com.gateway.config;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
// /** Optional Java-based route config.
// * Routes are already defined in application.yml.
// * This class is kept for reference — you can use either approach.
// * If you define routes in BOTH yml and here, they will be MERGED.
// * For learning purposes, keep routes in application.yml only.
// */
//@Configuration
//public class GatewayConfig {
//
//    // Routes are configured via application.yml
//    // This bean is intentionally left empty for clarity
//    // Uncomment below if you want to define routes in Java instead:
//
//
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//
//            .route("security-service", r -> r
//                .path("/auth/**")
//                .uri("lb://security-service"))
//
//            .route("quantity-measurement-app", r -> r
//                .path("/api/v1/quantities/**")
//                .uri("lb://quantity-measurement-app"))
//
//            .build();
//    }
//
//}
///*
//```
//
//---
//
//### Important Note — WebFlux vs WebMVC
//
//API Gateway uses **Spring WebFlux** (reactive, non-blocking) under the hood — NOT the traditional Spring MVC. This means:
//
//| | Security-Service / Quantity-App | API Gateway |
//|---|---|---|
//| Stack | Spring MVC (Servlet) | Spring WebFlux (Reactive) |
//| Filter type | `OncePerRequestFilter` | `GatewayFilter` |
//| Request type | `HttpServletRequest` | `ServerWebExchange` |
//| Response type | `HttpServletResponse` | `ServerHttpResponse` |
//
//This is why you must **NOT** add `spring-boot-starter-web` to the gateway `pom.xml` — it will conflict with WebFlux.
//
//---
//
//### How Everything Flows Together
//```
//Frontend (3000)
//    │
//    ▼
//API Gateway (8080)          ← JWT validated HERE
//    │
//    ├── /auth*//**  ──────────► security-service (8081)   [no JWT needed]
//    │
//    └── /api/v1/quantities/** ► quantity-measurement-app (8082) [JWT required]
//
////All services register with:
////    Eureka Server (8761)
////```
////
////---
////
////### Startup Order
////```
////1. eureka-server          (8761)  — first, always
////2. security-service       (8081)  — registers with Eureka
////3. quantity-measurement-app (8082) — registers with Eureka
////4. api-gateway            (8080)  — last, discovers all via Eureka
//
//
//*/