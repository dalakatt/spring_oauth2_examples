package de.synyx.cl.oauth.examples.service.c;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ServiceCController {

    @GetMapping("/api")
    public Map<String, Boolean> api() {
        Map<String, Boolean> map = new HashMap<>();
        map.put("rightA", true);
        map.put("rightB", false);
        map.put("rightC", true);
        return map;
    }

    @GetMapping("/info")
    public Map<String, Object> getUserInfo(Principal principal) {

        Map<String, String> map = new HashMap<>();
        if (principal instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) principal;
            map.put("clientId", jwtToken.getTokenAttributes().get("azp").toString());
        }

        map.put("name", principal.getName());

        return Collections.unmodifiableMap(map);
    }
}
