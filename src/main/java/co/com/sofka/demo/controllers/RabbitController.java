package co.com.sofka.demo.controllers;



import co.com.sofka.demo.services.RabbitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rabbit")
public class RabbitController {

    private final RabbitService service;

    public RabbitController(RabbitService service) {
        this.service = service;
    }

    @GetMapping(value = "/direct/producer")
    public String producerPisoUno(@RequestParam String messageData) {
        service.sendMessage("direct-exchange", null, messageData);
        return "Message sent to the RabbitMQ Successfully";
    }

    @GetMapping(value = "/topic/producer-impar")
    public String producerPisosImpares(@RequestParam String messageData) {
        service.sendMessage("topic-exchange", "imp", messageData);
        return "Message sent to the RabbitMQ Successfully";
    }

}
