    package com.local.product.handle;
    
    import java.util.Map;
    
    public record ErrorReponse(
            Map<String, String> errors
    ) {
    }
