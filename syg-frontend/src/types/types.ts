export interface User{
    id: number;
    name: string;
    password: string;
    totalGames: number;
    correctAnswers: number;
    inCorrectAnswers: number;
}

export interface Question{
    id: string;
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