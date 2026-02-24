package grace.consulting.desafiograceconsulting.config.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        long startedAtMillis = System.currentTimeMillis();
        try {
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            log.error("Request failed", exception);
        } finally {
            String principal = SecurityContextHolder.getContext().getAuthentication() == null
                    ? "anonymous"
                    : String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());

            log.info("{} {} -> {} ({} ms) user={}",
                    request.getMethod(),
                    request.getRequestURI(),
                    response.getStatus(),
                    System.currentTimeMillis() - startedAtMillis,
                    principal
            );
        }
    }
}