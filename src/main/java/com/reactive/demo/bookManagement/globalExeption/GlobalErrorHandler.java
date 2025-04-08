package com.reactive.demo.bookManagement.globalExeption;

import com.reactive.demo.bookManagement.exceptions.BookNotFoundException;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@Order(-2)
public class GlobalErrorHandler extends AbstractErrorWebExceptionHandler {

    public GlobalErrorHandler(ErrorAttributes errorAttributes,
                            WebProperties properties,
                            ApplicationContext applicationContext,
                            ServerCodecConfigurer configurer) {
        super(errorAttributes, properties.getResources(), applicationContext);
        this.setMessageWriters(configurer.getWriters());
        this.setMessageReaders(configurer.getReaders());
    }


    /**
     * Defines how errors should be routed
     * Catches ALL requests that resulted in errors (RequestPredicates.all())
     * Routes them to  renderErrorResponse method
     */
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(
            RequestPredicates.all(), request -> renderErrorResponse(request, errorAttributes));
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request, ErrorAttributes errorAttributes) {
        Throwable error = errorAttributes.getError(request);
        
        if (error instanceof BookNotFoundException) {
            return handleBookNotFound((BookNotFoundException) error, request);
        }
        
        return handleGenericError(error, request);
    }

    private Mono<ServerResponse> handleBookNotFound(BookNotFoundException ex, ServerRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            LocalDateTime.now().toString(),
            request.path()
        );

        return ServerResponse.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(errorResponse);
    }

    private Mono<ServerResponse> handleGenericError(Throwable error, ServerRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error.getMessage() != null ? error.getMessage() : "Unexpected Error",
            LocalDateTime.now().toString(),
            request.path()
        );

        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(errorResponse);
    }
}