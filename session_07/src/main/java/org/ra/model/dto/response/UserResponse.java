package org.ra.model.dto.response;

import lombok.*;
import org.ra.model.entity.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String fullName;
    private String username;
    private Boolean status;

    public UserResponse(User user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.username = user.getUsername();
        this.status = user.getStatus();
    }
}
