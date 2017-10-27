package io.tatagulov.badproject.web.dto.boardingPasses;

import io.tatagulov.badproject.web.dto.BaseListRequest;

public class BoardingPassesListRequest extends BaseListRequest {
    private Integer boardingNo;

    public Integer getBoardingNo() {
        return boardingNo;
    }

    public void setBoardingNo(Integer boardingNo) {
        this.boardingNo = boardingNo;
    }
}
