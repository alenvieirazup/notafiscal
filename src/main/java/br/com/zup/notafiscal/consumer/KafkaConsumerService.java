package br.com.zup.notafiscal.consumer;

import br.com.zup.notafiscal.dto.NovaVendaRequest;
import br.com.zup.notafiscal.model.NotaFiscal;
import br.com.zup.notafiscal.repository.NotaFiscalRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private final NotaFiscalRepository repository;

    public KafkaConsumerService(NotaFiscalRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "teste", groupId = "marketplace")
    public void consume(@Payload NovaVendaRequest request) {
        NotaFiscal notaFiscal = request.toModel();
        repository.save(notaFiscal);
    }



}