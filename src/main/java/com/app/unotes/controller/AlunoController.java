package com.app.unotes.controller;

import com.app.unotes.dtos.AlunoDTO;
import com.app.unotes.dtos.AlunoLoginDTO;
import com.app.unotes.responsedtos.AlunoResponseDTO;
import com.app.unotes.services.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/aluno")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    //cadastro
    @PostMapping("/auth/cadastro")
    public ResponseEntity<AlunoResponseDTO> cadastroAluno(@RequestBody @Valid AlunoDTO alunoDTO){
        AlunoResponseDTO alunoResponseDTO = alunoService.cadastroAluno(alunoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(alunoResponseDTO);
    }

    //login
    @PostMapping("/auth/login")
    public void login_aluno(@RequestBody @Valid AlunoLoginDTO alunoLoginDTO){
        return;
    }

    //get
    @GetMapping("/{nome_aluno}")
    public void get_aluno(@PathVariable String nome_aluno){
        return;
    }

    //update
    //put
    //delete
    //criar grade
    //criar disciplina
    //criar anotação
    //criar lembrete


}
