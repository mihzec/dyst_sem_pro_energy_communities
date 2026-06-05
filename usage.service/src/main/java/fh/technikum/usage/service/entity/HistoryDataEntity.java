package fh.technikum.usage.service.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "history_data")
public class HistoryDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp_hour", unique = true)
    private LocalDateTime timestampHour;

    @Column(name = "community_produced")
    private BigDecimal communityProduced;

    @Column(name = "community_used")
    private BigDecimal communityUsed;

    @Column(name = "grid_used")
    private BigDecimal gridUsed;


    public HistoryDataEntity() {
    }

    public HistoryDataEntity(LocalDateTime timestampHour) {
        this.timestampHour = timestampHour;
        this.communityProduced = BigDecimal.ZERO;
        this.communityUsed = BigDecimal.ZERO;
        this.gridUsed = BigDecimal.ZERO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestampHour() {
        return timestampHour;
    }

    public void setTimestampHour(LocalDateTime timestampHour) {
        this.timestampHour = timestampHour;
    }

    public BigDecimal getCommunityProduced() {
        return communityProduced;
    }

    public void setCommunityProduced(BigDecimal communityProduced) {
        this.communityProduced = communityProduced;
    }

    public BigDecimal getCommunityUsed() {
        return communityUsed;
    }

    public void setCommunityUsed(BigDecimal communityUsed) {
        this.communityUsed = communityUsed;
    }

    public BigDecimal getGridUsed() {
        return gridUsed;
    }

    public void setGridUsed(BigDecimal gridUsed) {
        this.gridUsed = gridUsed;
    }
}
