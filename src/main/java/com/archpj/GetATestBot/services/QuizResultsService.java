package com.archpj.GetATestBot.services;

import com.archpj.GetATestBot.database.QuizResultsRepository;
import com.archpj.GetATestBot.models.QuizResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class QuizResultsService {

    private static QuizResultsRepository quizResultsRepository;

    public QuizResultsService(QuizResultsRepository quizResultsRepository) {
        QuizResultsService.quizResultsRepository = quizResultsRepository;
    }

    public void saveQuizResult(QuizResult quizResult) {
        quizResultsRepository.save(quizResult);
    }

    public List<QuizResult> getQuizResults() {
        return quizResultsRepository.findAll();
    }
}
