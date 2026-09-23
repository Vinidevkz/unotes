package com.app.unotes.controller;

import com.app.unotes.dtos.DisciplinaDTO;
import com.app.unotes.entities.Disciplina;
import com.app.unotes.repository.DisciplinaRepository;
import com.app.unotes.responsedtos.DisciplinaResponseDTO;
import com.app.unotes.services.DisciplinaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/disciplinas")
@RequiredArgsConstructor
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    //criar disciplina
    @PostMapping("/criar_disciplina")
    public ResponseEntity<DisciplinaResponseDTO> criarDisciplina(@Valid @RequestBody DisciplinaDTO disciplinaDTO){

        return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaService.criarDisciplina(disciplinaDTO));

    }


    //ver disciplina especifica
    //ver disciplinas de um aluno
    //atualizar disciplina
    //deletar disciplina


}
