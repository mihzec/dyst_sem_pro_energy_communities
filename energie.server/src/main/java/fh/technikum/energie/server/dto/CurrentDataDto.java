package fh.technikum.energie.server.dto;

import java.math.BigDecimal;

public class CurrentDataDto {

    private final BigDecimal communityPoolOutput;
    private final BigDecimal gridPortionOutput;

    public CurrentDataDto(BigDecimal communityPoolOutput, BigDecimal gridPortionOutput) {
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
