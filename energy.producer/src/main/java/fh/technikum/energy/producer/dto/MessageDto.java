package fh.technikum.energy.producer.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MessageDto(
        String type,
        String association,
        BigDecimal kwh,
        LocalDateTime datetime
) {
}