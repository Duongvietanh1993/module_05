package org.ra.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest {
    @NotEmpty(message = "Không được để trống")
    private String fullName;
    private String username;
    @Size(min = 3,message = "Nhiều hơn 3 kí tự")
    private String password;
}
