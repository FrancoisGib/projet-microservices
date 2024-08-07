package com.francoisgib.kubernetesservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageService {
    public void sendLogMessage(String message) {
        log.info(message);
    }
}
