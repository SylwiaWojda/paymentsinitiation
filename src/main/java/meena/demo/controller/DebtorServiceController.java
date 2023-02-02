package meena.demo.controller;

import meena.demo.service.DebtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debtSrvc/v1/")
public class DebtorServiceController {

    @Autowired
    private DebtorService debtorService;

    @PostMapping(value = "sendPain001", produces = MediaType.TEXT_PLAIN_VALUE)
    public String sendPain001(@RequestBody String pain001Req) throws Exception {
       return debtorService.sendPain001(pain001Req);
    }

}
