package com.mukasz.regoninfo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubjectDTO {
    private String regon;
    private String nip;
    private String name;
    private AddressDTO address;
}
