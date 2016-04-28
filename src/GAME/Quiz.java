package GAME;

import java.util.ArrayList;

/**
 * Created by MisterY on 26.04.2016.
 */
public class Quiz {
    private String question;
    private String answer;
    private static ArrayList<Quiz> quizesList = new ArrayList<Quiz>();
    public static void addQuiz(Quiz quiz) {
        quizesList.add(quiz);
    }
    public static void setQuiz(int id, Quiz quiz) {
        quizesList.get(id).setQuestion(quiz.getQuestion());
        quizesList.get(id).setAnswer(quiz.getAnswer());
    }

    public static void deleteQuizefromList(int id) {
        quizesList.remove(id);
    }
    public static Quiz getQuizesList(int id) {
        if(id >= quizesList.size() )
        {return null;}
        return quizesList.get(id);
    }
    public Quiz(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public static Quiz getRandomQuiz() {
        int quizId = (int) Math.random()*quizesList.size();
        return quizesList.get(quizId);
    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


}
