package live.smoothing.user.rabbitmq;

import live.smoothing.user.rabbitmq.dto.FcmMessage;
import live.smoothing.user.rabbitmq.dto.HookMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageSender {

    private final RabbitTemplate rabbitTemplate;
    private final String hookQueueName = "hook-queue";
    private final String fcmQueueName = "approval-request-queue";

    public void sendMessageToHookQueue(HookMessage message) {

        try {
            rabbitTemplate.convertAndSend(hookQueueName, message);
            log.info(hookQueueName + "에 메세지 전송 성공");
        } catch(Exception ex) {
            log.error(hookQueueName + "에 메세지 전송 실패",  ex);
        }

    }

    public void sendMessageToFcmQueue(FcmMessage message) {

        try {
            rabbitTemplate.convertAndSend(fcmQueueName, message);
            log.info(fcmQueueName + "에 메세지 전송 성공");
        } catch(Exception ex) {
            log.error(fcmQueueName + "에 메세지 전송 실패",  ex);
        }

    }
}
