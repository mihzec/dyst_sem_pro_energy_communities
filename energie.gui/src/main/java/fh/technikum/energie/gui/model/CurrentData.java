package fh.technikum.energie.gui.model;

import java.math.BigDecimal;

public class CurrentData {

    private BigDecimal communityPoolOutput;
    private BigDecimal gridPortionOutput;

    public CurrentData() {
    }

    public BigDecimal getGridPortionOutput() {
        return gridPortionOutput;
    }

    public void setGridPortionOutput(BigDecimal gridPortionOutput) {
        this.gridPortionOutput = gridPortionOutput;
    }

    public BigDecimal getCommunityPoolOutput() {
        return communityPoolOutput;
    }

    public void setCommunityPoolOutput(BigDecimal communityPoolOutput) {
        this.communityPoolOutput = communityPoolOutput;
    }


}
