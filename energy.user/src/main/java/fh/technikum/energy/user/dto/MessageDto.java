package fh.technikum.energy.user.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MessageDto(
        String type,
        String association,
        BigDecimal kwh,
        LocalDateTime dateTime
) {
}
