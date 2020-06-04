package com.mukasz.regoninfo.regonapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class RegonApiObject implements Serializable {
    @XmlElement private String ErrorCode;
    @XmlElement private String ErrorMessageEn;
    @XmlElement private String ErrorMessagePl;
}
