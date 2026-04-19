package fh.technikum.energie.gui.model;

import java.math.BigDecimal;

public class CurrentData {

    private final BigDecimal communityPoolOutput;
    private final BigDecimal gridPortionOutput;

    public CurrentData(BigDecimal communityPoolOutput, BigDecimal gridPortionOutput) {
        this.communityPoolOutput = communityPoolOutput;
        this.gridPortionOutput = gridPortionOutput;
    }

    public BigDecimal getCommunityPoolOutput() {
        return communityPoolOutput;
    }

    public BigDecimal getGridPortionOutput() {
        return gridPortionOutput;
    }
}
