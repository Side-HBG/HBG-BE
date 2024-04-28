package com.devjin.springstu.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqStream {
    @NotNull
    private String item_id;
}
