    package com.local.customer.handle;
    
    import java.util.Map;
    
    public record ErrorReponse(
            Map<String, String> errors
    ) {
    }
