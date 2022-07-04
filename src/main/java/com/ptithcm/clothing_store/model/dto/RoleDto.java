package com.ptithcm.clothing_store.model.dto;

import com.ptithcm.clothing_store.model.Enum.EnumRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleDto {
    private Long id;
    private EnumRole role;
    private Long version;
}
