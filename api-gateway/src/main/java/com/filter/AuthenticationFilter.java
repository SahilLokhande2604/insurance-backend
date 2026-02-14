//package com.filter;
//
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//
//import com.security.JwtUtil;
//
//import reactor.core.publisher.Mono;
//
//@Component
//public class AuthenticationFilter implements GlobalFilter, Ordered {
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange,
//                             GatewayFilterChain chain) {
//
//        String authHeader = exchange.getRequest()
//                .getHeaders()
//                .getFirst("Authorization");
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//
//            String token = authHeader.substring(7);
//            String username = JwtUtil.extractUsername(token);
//
//            ServerHttpRequest modifiedRequest = exchange.getRequest()
//                    .mutate()
//                    .header("X-Username", username)
//                    .build();
//
//            return chain.filter(exchange.mutate()
//                    .request(modifiedRequest)
//                    .build());
//        }
//
//        return chain.filter(exchange);
//    }
//
//    @Override
//    public int getOrder() {
//        return -1;
//    }
//}
//
