package org.example.java_demo.model.api;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record PageResponse<T> (
    List<T> content,
    long totalCount,
    int totalPages,
    int currentPage

) {

}
