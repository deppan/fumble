package com.tsinsi.auth.configuration.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClaimSet {
    long uid;
    String username;
    int days;
}
