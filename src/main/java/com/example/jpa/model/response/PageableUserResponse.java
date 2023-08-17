package com.example.jpa.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableUserResponse {
    private List<UserResponse> users;
    private int lastPageNumber;
    private long totalElements;
    private boolean hasNextPage;
}
