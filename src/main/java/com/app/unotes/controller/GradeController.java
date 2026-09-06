package com.app.unotes.controller;

import com.app.unotes.dtos.GradeDTO;
import com.app.unotes.responsedtos.GradeResponseDTO;
import com.app.unotes.services.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    //criar grade
    @PostMapping
    public ResponseEntity<GradeResponseDTO> criarGrade(@RequestBody GradeDTO gradeDTO, Authentication authentication){

        UUID id = UUID.fromString(authentication.getName());

        GradeResponseDTO gradeResponseDTO = gradeService.criarGrade(gradeDTO, id);

        return ResponseEntity.ok().body(gradeResponseDTO);

    }

    //ver grade especifica

    //ver grades de um aluno

    //atualizar grade

    //deletar grade


}
