package fh.technikum.energie.gui.model;

import java.math.BigDecimal;

public class HistoryData {

    private BigDecimal communityProduced;
    private BigDecimal communityUsed;
    private BigDecimal gridUsed;

    public HistoryData() {}

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
