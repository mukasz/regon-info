package com.mukasz.regoninfo.controller;

import org.springframework.web.bind.annotation.*;
import com.mukasz.regoninfo.model.SubjectDTO;
import com.mukasz.regoninfo.service.SubjectService;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    // Example for testing nip: 5261040828 - GUS
    @GetMapping("/{nip}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public SubjectDTO getCompanyBy(@PathVariable String nip) {
        return subjectService.getSubjectInfoByNip(nip);
    }

}
