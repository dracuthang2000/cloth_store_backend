package com.ptithcm.clothing_store.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class StuffDto {
    private Long id;
    private String stuff;
    private Long version;
}
