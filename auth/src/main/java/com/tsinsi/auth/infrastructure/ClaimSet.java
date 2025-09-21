package com.tsinsi.auth.infrastructure;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClaimSet {
    private long uid;
    private String username;
    private int days;
}
