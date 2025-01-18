// package com.example.servicemanagementsystem.configs;

// import java.io.IOException;
// import java.nio.file.DirectoryStream.Filter;
// import java.util.HashMap;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.core.Ordered;
// import org.springframework.core.annotation.Order;
// import org.springframework.stereotype.Component;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.ServletRequest;
// import jakarta.servlet.ServletResponse;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @SuppressWarnings("rawtypes")
// @Component
// @Order(Ordered.HIGHEST_PRECEDENCE)
// public class SimpleCorsFilter implements Filter {

//     @Value("${app.client.url}")
//     private String clientUrl = "";

//     public SimpleCorsFilter() {

//     }

//     public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//             throws IOException, ServletException {
//         HttpServletResponse response = (HttpServletResponse) res;
//         HttpServletRequest request = (HttpServletRequest) res;
//         Map<String, String> map = new HashMap<>();
//         String originHeader = request.getHeader("origin");
//         response.setHeader("Acces-control-Allow-Origin", originHeader);
//         response.setHeader("Acces-control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
//         response.setHeader("Acces-control-max-Age", "3600");
//         response.setHeader("Acces-control-Allow-Headers", "*");

//         if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//             response.setStatus(HttpServletResponse.SC_OK);
//         } else {
//             chain.doFilter(req, res);
//         }

//     }

//     @Override
//     public boolean accept(Object entry) throws IOException {

//         throw new UnsupportedOperationException("Unimplemented method 'accept'");
//     }

// }
