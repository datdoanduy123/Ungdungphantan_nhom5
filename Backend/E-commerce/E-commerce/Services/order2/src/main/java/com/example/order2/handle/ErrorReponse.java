    package com.example.order2.handle;
    
    import java.util.Map;
    
    public record ErrorReponse(
            Map<String, String> errors
    ) {
    }
