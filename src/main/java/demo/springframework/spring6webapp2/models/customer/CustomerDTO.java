package demo.springframework.spring6webapp2.models.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerDTO {

    private UUID id;
    private Integer version;

    @NotBlank
    @NotNull
    private String customerName;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}