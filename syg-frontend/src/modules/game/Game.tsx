import React, { useEffect, useState } from 'react';
import Header from '../../components/header/Header';
import { Button } from '@mui/material';
import { Answer, Question, User } from '../../types/types';
import { getQuestions, updateUser } from '../../backend/dataSource';
import LinearProgress from '@mui/joy/LinearProgress';
import { Typography } from '@mui/material';
import './Game.scss';

const Game: React.FC = () => {
    const [questions, setQuestions] = useState<Question[]>([]);
    const [idQuestion, setIdQuestion] = useState<number>(0);
    const [timeLeft, setTimeLeft] = useState<number>(0);
    const [progress, setProgress] = useState<number>(100);
    const [totalCorrectAnswers, setTotalCorrectAnswers] = useState<number>(0);
    const [totalIncorrectAnswers, setTotalIncorrectAnswers] = useState<number>(0);
    const [isGameFinished, setIsGameFinished] = useState<boolean>(false);

    useEffect(() => {
        if (questions !== undefined && questions.length > 0) {
            setTimeLeft(questions[idQuestion].timeLimit)
            const timer = setInterval(() => {
                setTimeLeft(prevTime => prevTime - 1);
            }, 1000);

            return () => clearInterval(timer);
        }
    }, [questions])

    useEffect(() => {
        if (questions !== undefined && questions.length > 0) {
            const percentage = (timeLeft / questions[idQuestion].timeLimit) * 100;
            setProgress(percentage);

            if (timeLeft === 0) {
                handleAnswerQuestion();
            }
        }
    }, [timeLeft])

    useEffect(() => {
        if (questions !== undefined && questions.length > 0) {
            setTimeLeft(questions[idQuestion].timeLimit)
            setProgress(100)
        }
    }, [idQuestion])

    useEffect(() => {
        handlePostUpdateStats();
    }, [totalCorrectAnswers, totalIncorrectAnswers])

    function handleStartGame() {
        getQuestions().then((questions: Question[]) => {
            setQuestions(questions)
        })
    }

    function handleAnswerQuestion(answer?: Answer){
        if(answer !== undefined){
            if(answer.isCorrect){
                setTotalCorrectAnswers(totalCorrectAnswers + 1)
            }
            else{
                setTotalIncorrectAnswers(totalIncorrectAnswers + 1)
            }
        }
        else{
            setTotalIncorrectAnswers(totalIncorrectAnswers + 1)
        }

    }

    function handlePostUpdateStats(){
        if(totalCorrectAnswers > 0 || totalIncorrectAnswers > 0){
            if((idQuestion + 1) <  questions.length){
                setIdQuestion(idQuestion + 1);
            }
            else{
                setIsGameFinished(true);
                updateUser({
                    id: 1,
                    name: "alvaroActualizado",
                    password: "admin",
                    totalGames: 4,
                    correctAnswers: totalCorrectAnswers,
                    inCorrectAnswers: totalIncorrectAnswers,
                } as User);
            }
        }
    }

    return (
        <div id='syg-game-container'>
            <Header info='Juego' />
            <div id='syg-game-content'>
                {questions === undefined || questions.length < 1 ? (
                    <div id='syg-game-content-start-game'>
                        <h2>Escoga el modo de juego</h2>
                        <div id='syg-game-content-start-game-options'>
                            <Button
                                className='syg-game-start-game-button'
                                onClick={handleStartGame}
                            >
                                Modo estandar
                            </Button>
                            <Button
                                className='syg-game-start-game-button'
                                onClick={handleStartGame}
                            >
                                Animales
                            </Button>
                            <Button
                                className='syg-game-start-game-button'
                                onClick={handleStartGame}
                            >
                                plantas
                            </Button>
                            <Button
                                className='syg-game-start-game-button'
                                onClick={handleStartGame}
                            >
                                Deportes
                            </Button>
                        </div>
                    </div>
                ) : (
                    isGameFinished === false ? (
                        <div className='syg-game-question-content'>
                            <div className='syg-game-question-time-limit'>
                                <LinearProgress determinate={true} variant="outlined" value={progress} color="primary" thickness={32} >
                                    <Typography>
                                        {timeLeft}
                                    </Typography>
                                </LinearProgress>
                            </div>
                            <div className='syg-game-question-title'>
                                {questions[idQuestion].text}
                            </div>
                            <div className='syg-game-question-answers-content'>
                                {questions[idQuestion].answers.map((answer: Answer, index: number) => (
                                    <Button
                                        key={index}
                                        className={`syg-game-question-answer answer-number-${index}`}
                                        onClick={() => handleAnswerQuestion(answer)}
                                    >
                                        {answer.text}
                                    </Button>
                                ))}
                            </div>
                        </div>
                    ):(
                        <div id='syg-game-finish'>
                            <h2>Resumen de la partida</h2>
                            <span>Respuestas correctas: {totalCorrectAnswers}</span>
                            <span>Respuestas incorrectas: {totalIncorrectAnswers}</span>
                        </div>
                    )
                )}
            </div>
        </div>
    );
};

export default Game;