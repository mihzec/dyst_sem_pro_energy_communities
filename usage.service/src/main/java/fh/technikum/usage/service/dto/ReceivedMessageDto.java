package fh.technikum.usage.service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ReceivedMessageDto(
        LocalDateTime timestamp,
        BigDecimal energyValue
) {
}
