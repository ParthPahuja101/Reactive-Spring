## Global Exception Handling

##### Spring provides a way to create centralised exception handling using. @ControllerAdvice (for annotation-based controllers), But here since we are using Functional Web Framework, We will be using GlobalErrorHandler which extends AbstractErrorWebExceptionHandler and provides access to  Spring's built-in error handling mechanism.

##### AbstractErrorWebExceptionHandler: Spring's base class for WebFlux error handling

##### Example -
```

@Component
@Order(-2)     // Sets high priority for this error handler (lower numbers = higher priority) 
public class GlobalErrorHandler extends AbstractErrorWebExceptionHandler {

    
    // Initializes the error handler with necessary dependencies
    // Sets up message readers/writers for JSON serialization/deserialization
    public GlobalErrorHandler(ErrorAttributes errorAttributes,
                            WebProperties properties,
                            ApplicationContext applicationContext,
                            ServerCodecConfigurer configurer) {
        super(errorAttributes, properties.getResources(), applicationContext);
        this.setMessageWriters(configurer.getWriters());
        this.setMessageReaders(configurer.getReaders());
    }
    
    
    // Sets up the routing function for handling errors
    // Catches all requests and routes them to the error handler
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(
            RequestPredicates.all(), request -> renderErrorResponse(request, errorAttributes));
    }

    // Extracts the exact exception that was throes and returns a response accordingly
    private Mono<ServerResponse> renderErrorResponse(ServerRequest request, ErrorAttributes errorAttributes) {
        Throwable error = errorAttributes.getError(request);
    }

    
}
```

