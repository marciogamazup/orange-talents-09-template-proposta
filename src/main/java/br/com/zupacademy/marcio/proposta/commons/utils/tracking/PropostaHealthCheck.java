package br.com.zupacademy.marcio.proposta.commons.utils.tracking;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PropostaHealthCheck implements HealthIndicator {

    @Override
    public Health health() {

        Map<String, Object> details = new HashMap<>();
        details.put("versão", "1.2.3");
        details.put("descrição", "Meu primeiro Health Check customizado !");
        details.put("endereço","127.0.0.1");
        return Health.status(Status.UP).withDetails(details).build();
    }
}
