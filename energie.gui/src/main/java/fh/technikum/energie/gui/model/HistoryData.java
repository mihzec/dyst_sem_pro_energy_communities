package fh.technikum.energie.gui.model;

import java.math.BigDecimal;

public class HistoryData {

    private final BigDecimal communityProduced;
    private final BigDecimal communityUsed;
    private final BigDecimal gridUsed;

    public HistoryData(BigDecimal communityProduced, BigDecimal communityUsed, BigDecimal gridUsed) {
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
