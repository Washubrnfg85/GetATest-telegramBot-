package com.archpj.GetATestBot.services;

import com.archpj.GetATestBot.database.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<String> loadQuizQuestions(String specialisation) {
        return quizRepository.loadSpecQuestions(specialisation);
    }

    public String loadCorrectAnswers(String specialisation) {
        List<String> correctAnswers = quizRepository.loadCorrectAnswers(specialisation);
        String result = "";

        for (int i = 0; i < correctAnswers.size(); i++) {
            result += correctAnswers.get(i);
        }

        return result;
    }
}
