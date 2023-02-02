package meena.demo.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void logTemplate(String source, String destination, String description, String payload){
        logger.info(String.format("SOURCE=%s, DESTINATION=%s, DESCRIPTION=%s, PAYLOAD='%s'",source, destination, description, payload));
    }

    public void logStr(String log){
        logger.info(log);
    }
}