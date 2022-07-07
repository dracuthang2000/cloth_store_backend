package com.ptithcm.clothing_store.model.dto;

import com.ptithcm.clothing_store.model.Enum.EnumSize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SizeDto {
    private Long id;
    private EnumSize size;
    private Long Version;
}
