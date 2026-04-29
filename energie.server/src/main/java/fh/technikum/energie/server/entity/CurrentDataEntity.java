package fh.technikum.energie.server.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "current_data")

public class CurrentDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp_hour")
    private LocalDateTime timestampHour;

    @Column(name = "community_depleted")
    private BigDecimal communityDepleted;

    @Column(name = "grid_portion")
    private BigDecimal gridPortion;


    public CurrentDataEntity() {
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

    public BigDecimal getCommunityDepleted() {
        return communityDepleted;
    }

    public void setCommunityDepleted(BigDecimal communityDepleted) {
        this.communityDepleted = communityDepleted;
    }

    public BigDecimal getGridPortion() {
        return gridPortion;
    }

    public void setGridPortion(BigDecimal gridPortion) {
        this.gridPortion = gridPortion;
    }

}
