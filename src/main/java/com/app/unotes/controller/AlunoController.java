package com.app.unotes.controller;

import com.app.unotes.dtos.AlunoDTO;
import com.app.unotes.dtos.AlunoLoginDTO;
import com.app.unotes.dtos.AlunoUpdateDTO;
import com.app.unotes.responsedtos.AlunoDataResponseDTO;
import com.app.unotes.responsedtos.AlunoResponseDTO;
import com.app.unotes.services.AlunoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.UUID;

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
    public ResponseEntity<AlunoResponseDTO> loginAluno(@RequestBody @Valid AlunoLoginDTO alunoLoginDTO) throws EntityNotFoundException {
        AlunoResponseDTO alunoResponseDTO = alunoService.loginAluno(alunoLoginDTO);

        return ResponseEntity.ok().body(alunoResponseDTO);
    }

    //get
    @GetMapping("/{nome_aluno}")
    public ResponseEntity<AlunoDataResponseDTO> getAluno(@PathVariable("nome_aluno") String nome_aluno, Authentication authentication) throws EntityNotFoundException {

        UUID id = UUID.fromString(authentication.getName());

        AlunoDataResponseDTO alunoDataResponseDTO = alunoService.getAluno(nome_aluno, id);

        return ResponseEntity.ok().body(alunoDataResponseDTO);

    }

    //update
    @PutMapping("/update")
    public ResponseEntity<AlunoDataResponseDTO> updateAluno(@RequestBody AlunoUpdateDTO alunoUpdateDTO, Authentication authentication) throws EntityNotFoundException{

        UUID id = UUID.fromString(authentication.getName());
        AlunoDataResponseDTO alunoDataResponseDTO = alunoService.updateAluno(alunoUpdateDTO, id);

        return ResponseEntity.ok().body(alunoDataResponseDTO);
    }

    //delete
    @DeleteMapping("/deletar_perfil")
    public ResponseEntity<Void> deletarAluno(Authentication authentication) throws EntityNotFoundException{

        UUID id = UUID.fromString(authentication.getName());
        alunoService.deleteAluno(id);

        return ResponseEntity.noContent().build();

    }

}
