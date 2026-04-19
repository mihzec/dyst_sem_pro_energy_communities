package fh.technikum.energie.server.dto;

import java.math.BigDecimal;

public class HistoryDataDto {

    private BigDecimal communityProduced;

    private BigDecimal communityUsed;

    private BigDecimal gridUsed;

    public HistoryDataDto(BigDecimal communityProduced, BigDecimal communityUsed, BigDecimal gridUsed) {
        this.communityProduced = communityProduced;
        this.communityUsed = communityUsed;
        this.gridUsed = gridUsed;
    }

    public BigDecimal getCommunityProduced() {
        return communityProduced;
    }

    public BigDecimal getCommunityUsed() {
        return communityUsed;
    }

    public BigDecimal getGridUsed() {
        return gridUsed;
    }
}
