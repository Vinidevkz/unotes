package com.app.unotes.controller;

import com.app.unotes.dtos.GradeDTO;
import com.app.unotes.responsedtos.GradeResponseDTO;
import com.app.unotes.services.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    //criar grade
    @PostMapping("/criar_grade")
    public ResponseEntity<GradeResponseDTO> criarGrade(@RequestBody GradeDTO gradeDTO, Authentication authentication){

        UUID id = UUID.fromString(authentication.getName());

        GradeResponseDTO gradeResponseDTO = gradeService.criarGrade(gradeDTO, id);

        return ResponseEntity.status(HttpStatus.CREATED).body(gradeResponseDTO);

    }

    //ver grade especifica
    @GetMapping("/{nomeGrade}")
    public ResponseEntity<GradeResponseDTO> verGrade(@PathVariable String nomeGrade, Authentication authentication){

        UUID id = UUID.fromString(authentication.getName());

        GradeResponseDTO gradeResponseDTO = gradeService.getGrade(nomeGrade, id);

        return ResponseEntity.ok().body(gradeResponseDTO);

    }

    //ver grades de um aluno
    @GetMapping("/minhas_grades")
    public ResponseEntity<List<GradeResponseDTO>> verGradesDeUmAluno(Authentication authentication){

        UUID id = UUID.fromString(authentication.getName());

        List<GradeResponseDTO> grades = gradeService.getGradesDeUmAluno(id);

        return ResponseEntity.ok().body(grades);

    }

    //atualizar grade

    //deletar grade


}
