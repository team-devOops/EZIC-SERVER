package com.ezic.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentsUpdateRequest {
    private String comment;

    @Builder
    public CommentsUpdateRequest(String comment) {
        this.comment = comment;
    }
}
