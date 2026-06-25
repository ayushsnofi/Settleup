package com.ayush.settleUp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GroupResponse {
    
    private Long id;
    private String name;
    private String createdBy;
}
