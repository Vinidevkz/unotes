package com.app.unotes.services;

import com.app.unotes.dtos.DisciplinaDTO;
import com.app.unotes.entities.Disciplina;
import com.app.unotes.entities.Grade;
import com.app.unotes.repository.AlunoRepository;
import com.app.unotes.repository.DisciplinaRepository;
import com.app.unotes.repository.GradeRepository;
import com.app.unotes.responsedtos.DisciplinaResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private final GradeRepository gradeRepository;
    private final DisciplinaRepository disciplinaRepository;

    //criar disciplina
    public DisciplinaResponseDTO criarDisciplina(DisciplinaDTO disciplinaDTO){

        Grade grade = gradeRepository.findById(disciplinaDTO.id_grade()).orElseThrow(EntityNotFoundException::new);

        Disciplina disciplina = new Disciplina();
        disciplina.setNome_disciplina(disciplinaDTO.nome_disciplina());
        disciplina.setNome_professor(disciplinaDTO.nome_professor());
        disciplina.setGrade(grade);

        Disciplina newDisciplina = disciplinaRepository.save(disciplina);

        DisciplinaResponseDTO disciplinaResponseDTO = new DisciplinaResponseDTO(newDisciplina.getNome_disciplina(), newDisciplina.getNome_professor(), newDisciplina.getGrade());

        return disciplinaResponseDTO;


    }

    //ver disciplina especifica
    //ver disciplinas de um aluno
    //atualizar disciplina
    //deletar disciplina

}
