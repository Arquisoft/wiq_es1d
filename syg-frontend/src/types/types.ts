export interface User{
    id: string;
    name: string;
    totalGames: number;
    correctAnswers: number;
    inCorrectAnswers: number;
    totalQuestionAnswered: number;
    lastCategoryPlayed: string;
}

export interface Question{
    id: number;
    text: string;
    timeLimit: number;
    category: Category;
    answers: Answer[];
}

export interface Category{
    id: number;
    name: string;

}

export interface Answer{
    id: number;
    text: string;
    isCorrect: boolean;
}