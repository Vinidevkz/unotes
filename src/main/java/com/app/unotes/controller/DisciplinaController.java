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
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @GetMapping("/{id_disciplina}")
    public ResponseEntity<DisciplinaResponseDTO> verDisciplina(@PathVariable UUID id_disciplina){
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.verDisciplina(id_disciplina));
    }

    //ver disciplinas de um aluno
    @GetMapping("/minhas_disciplinas")
    public ResponseEntity<List<DisciplinaResponseDTO>> verDisciplinasDeUmAluno(Authentication authentication){
        UUID id_aluno = UUID.fromString(authentication.getName());

        return ResponseEntity.ok().body(disciplinaService.verDisciplinasDeUmAluno(id_aluno));
    }

    //atualizar disciplina
    //deletar disciplina


}
