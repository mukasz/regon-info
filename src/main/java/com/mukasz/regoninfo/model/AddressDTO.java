package com.mukasz.regoninfo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {
    private String region;
    private String place;
    private String postalCode;
    private String street;
    private String local;
}
