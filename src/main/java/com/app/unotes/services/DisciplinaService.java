package com.app.unotes.services;

import com.app.unotes.dtos.DisciplinaDTO;
import com.app.unotes.entities.Disciplina;
import com.app.unotes.entities.Grade;
import com.app.unotes.repository.AlunoRepository;
import com.app.unotes.repository.DisciplinaRepository;
import com.app.unotes.repository.GradeRepository;
import com.app.unotes.responsedtos.DisciplinaResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private final GradeRepository gradeRepository;
    private final DisciplinaRepository disciplinaRepository;

    //criar disciplina
    @Transactional
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

    //ver disciplina específica
    @Transactional
    public DisciplinaResponseDTO verDisciplina(UUID id_disciplina){
        Disciplina disciplina = disciplinaRepository.findById(id_disciplina).orElseThrow(EntityNotFoundException::new);

        return new DisciplinaResponseDTO(disciplina);
    }

    //ver disciplinas de um aluno
    @Transactional
    public List<DisciplinaResponseDTO> verDisciplinasDeUmAluno(UUID id_aluno){

        List<Disciplina> disciplinas = Collections.singletonList((Disciplina) disciplinaRepository.findAllByAlunoId(id_aluno).orElseThrow(EntityNotFoundException::new));

        return disciplinas.stream().map(DisciplinaResponseDTO::new).toList();

    }

    //atualizar disciplina
    //deletar disciplina

}
