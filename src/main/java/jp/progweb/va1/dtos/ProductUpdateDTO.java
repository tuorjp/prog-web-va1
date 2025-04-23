package jp.progweb.va1.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductUpdateDTO {
    private String name;
    private Boolean active;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
