package com.tsinsi.auth.infrastructure;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClaimSet {
    long uid;
    String username;
    int days;
}
