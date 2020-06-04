package com.mukasz.regoninfo.regonapi.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static org.junit.jupiter.api.Assertions.*;

class XMLParserTest {
    private final String strXML = "<test_wrapper>\n" +
            "  <test_object>\n" +
            "    <test_field_1>test_1</test_field_1>\n" +
            "    <test_field_2>test_2</test_field_2>\n" +
            "  </test_object>\n" +
            "</test_wrapper>";

    private final TestWrapper getTestWrapper() {
        TestObject testObject = new TestObject("test_1", "test_2");
        return new TestWrapper(testObject);
    }

    @Test
    public void parseString_should_return_parsed_object() {

        TestWrapper result = XMLParser.parseString(strXML, TestWrapper.class);
        assertEquals(getTestWrapper(), result);
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "test_wrapper")
@XmlAccessorType(XmlAccessType.FIELD)
class TestWrapper {
    @XmlElement(name = "test_object")
    private TestObject testObject;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "test_object")
@XmlAccessorType(XmlAccessType.FIELD)
class TestObject {
    @XmlElement(name = "test_field_1")
    private String testField1;
    @XmlElement(name = "test_field_2")
    private String testField2;

}