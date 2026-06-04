package fh.technikum.energy.producer.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MessageDto(
        LocalDateTime timestamp,
        BigDecimal energyValue
) {
}
