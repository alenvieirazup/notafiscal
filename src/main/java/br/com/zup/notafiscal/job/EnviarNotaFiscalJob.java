package br.com.zup.notafiscal.job;

import br.com.zup.notafiscal.dto.NotaFiscalResponse;
import br.com.zup.notafiscal.model.NotaFiscal;
import br.com.zup.notafiscal.repository.NotaFiscalRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class EnviarNotaFiscalJob {

    private final JavaMailSender javaMailSender;
    private final TransactionTemplate transactionTemplate;
    private final NotaFiscalRepository repository;


    public EnviarNotaFiscalJob(JavaMailSender javaMailSender, TransactionTemplate transactionTemplate, NotaFiscalRepository repository) {
        this.javaMailSender = javaMailSender;
        this.transactionTemplate = transactionTemplate;
        this.repository = repository;
    }

    @Scheduled(fixedDelay = 30 * 1000, initialDelay = 10 * 1000)
    public void executa() {

        Boolean pendente = true;
        while (pendente) {
            pendente = transactionTemplate.execute((status) -> {

                List<NotaFiscal> geradas = repository.findTop5ByStatusOrderByCriadoEmAsc("GERADA");
                if (geradas.isEmpty()) {
                    return false;
                }

                geradas.forEach(gerada -> {

                    NotaFiscalResponse notaParaEnviar = new NotaFiscalResponse(gerada);
                    String xml = notaFiscalToXml(notaParaEnviar);
                    System.out.println(xml);
                    sendEmail(gerada.getEmail(), "nota@fiscal.com", "Sua Nota Fiscal",
                            "Olá! Segue sua nota fiscal em anexo", xml);
                    gerada.setStatus("GERADA_ENVIADA");

                });

                return true;
            });
        }
    }

    public void sendEmail(String to, String from, String subject, String content, String contentAttachment) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(content);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.addAttachment("nota.xml",
                    new ByteArrayResource(contentAttachment.getBytes(StandardCharsets.UTF_8)), MediaType.TEXT_XML_VALUE);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String notaFiscalToXml(NotaFiscalResponse notaFiscal) {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            return xmlMapper.writeValueAsString(notaFiscal);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}

