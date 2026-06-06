package fh.technikum.current.percentage.service.dto;

import java.time.LocalDateTime;

public record UpdateMessageDto(
        LocalDateTime timestamp_msg,
        LocalDateTime timestamp_hour,
        double communityProduced,
        double communityUsed,
        double gridUsed
) {
}
