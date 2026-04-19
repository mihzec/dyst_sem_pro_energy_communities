package fh.technikum.energie.server.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "history_data")
public class HistoryData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "timestamp_hour")
    private Timestamp timestamp;

    @Column(name = "community_produces")
    private BigDecimal communityProduced;

    @Column(name = "community_used")
    private BigDecimal communityUsed;

    @Column(name = "grid_used")
    private BigDecimal gridUsed;


    public HistoryData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
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
