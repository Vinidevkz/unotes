package com.app.unotes.services;

import com.app.unotes.dtos.GradeDTO;
import com.app.unotes.entities.Aluno;
import com.app.unotes.entities.Grade;
import com.app.unotes.repository.AlunoRepository;
import com.app.unotes.repository.GradeRepository;
import com.app.unotes.responsedtos.AlunoResponseDTO;
import com.app.unotes.responsedtos.GradeResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final AlunoRepository alunoRepository;

    //criar grade

    public GradeResponseDTO criarGrade(GradeDTO gradeDTO, UUID id){

        Aluno aluno = alunoRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        Grade grade = new Grade();
        grade.setNome_grade(gradeDTO.nome_grade());
        grade.setSemestre_grade(gradeDTO.semestre_grade());
        grade.setAno_grade(gradeDTO.ano_grade());
        grade.setAluno(aluno);

        Grade novaGrade = gradeRepository.save(grade);

        GradeResponseDTO gradeResponseDTO = new GradeResponseDTO(novaGrade.getNome_grade(), novaGrade.getSemestre_grade(), novaGrade.getAno_grade());

        return gradeResponseDTO;


    }

    //ver grade especifica
    public GradeResponseDTO getGrade(String nomeGrade, UUID id) throws EntityNotFoundException{

        Grade grade = gradeRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if(!grade.getNome_grade().equals(nomeGrade)){
            throw  new EntityNotFoundException();
        }

        GradeResponseDTO gradeResponseDTO = new GradeResponseDTO(grade.getNome_grade(), grade.getSemestre_grade(), grade.getAno_grade());

        return gradeResponseDTO;

    }

    //ver grades de um aluno

    //atualizar grade

    //deletar grade

}
