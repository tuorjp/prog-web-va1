package jp.progweb.va1.dtos;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductCreateDTO {
    private String name;
    private Boolean active;
    private LocalDateTime createdAt;
    private String createdBy;
    private Float price;
}
