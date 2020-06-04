package com.mukasz.regoninfo.model;

import com.mukasz.regoninfo.regonapi.model.subject.Subject;

public class SubjectConverter {
    public static SubjectDTO convertSubjectToDTO(Subject subject) {
        AddressDTO address = AddressDTO.builder()
                .region(buildRegionString(subject.getWojewodztwo(), subject.getPowiat(), subject.getGmina()))
                .place(subject.getMiejscowosc())
                .postalCode(subject.getKodPocztowy())
                .street(subject.getUlica())
                .local(buildLocalString(subject.getNrNieruchomosci(), subject.getNrLokalu()))
                .build();

        return SubjectDTO.builder()
                .name(subject.getNazwa())
                .address(address)
                .nip(subject.getNip())
                .regon(subject.getRegon())
                .build();
    }

    private static String buildRegionString(String wojewodztwo, String powiat, String gmina) {
        return String.format("%s, %s, %s", wojewodztwo, powiat, gmina);
    }

    private static String buildLocalString(String buildingNum, String local) {
        String realLocal = local.isBlank() ? "" : String.format("/%s", local);
        return String.format("%s%s", buildingNum, realLocal);
    }
}
