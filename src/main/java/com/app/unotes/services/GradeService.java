package com.app.unotes.services;

import com.app.unotes.dtos.GradeDTO;
import com.app.unotes.dtos.GradeUpdateDTO;
import com.app.unotes.entities.Aluno;
import com.app.unotes.entities.Grade;
import com.app.unotes.repository.AlunoRepository;
import com.app.unotes.repository.GradeRepository;
import com.app.unotes.responsedtos.GradeResponseDTO;
import com.app.unotes.tools.GetNullFieldsInAlunoUpdateDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final AlunoRepository alunoRepository;

    //criar grade
    @Transactional
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
    @Transactional
    public GradeResponseDTO getGrade(String nomeGrade, UUID id) throws EntityNotFoundException{

        Grade grade = gradeRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if(!grade.getNome_grade().equals(nomeGrade)){
            throw  new EntityNotFoundException();
        }

        GradeResponseDTO gradeResponseDTO = new GradeResponseDTO(grade.getNome_grade(), grade.getSemestre_grade(), grade.getAno_grade());

        return gradeResponseDTO;

    }

    //ver grades de um aluno
    @Transactional
    public List<GradeResponseDTO> getGradesDeUmAluno(UUID id){

        List<Grade> grades = gradeRepository.findByAlunoId(id);

        return grades.stream()
                .map(GradeResponseDTO::new)
                .toList();
    }

    //atualizar grade
    @Transactional
    public GradeResponseDTO atualizarGrade(GradeUpdateDTO gradeUpdateDTO, UUID user_id){

        Grade grade = gradeRepository.findById(gradeUpdateDTO.id_grade()).orElseThrow(EntityNotFoundException::new);

        BeanUtils.copyProperties(gradeUpdateDTO, grade, GetNullFieldsInAlunoUpdateDTO.getNullPropertyNames(gradeUpdateDTO));

        return new GradeResponseDTO(grade);

    }

    //deletar grade

}
