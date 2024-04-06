import React, { useEffect, useState } from 'react';
import Header from '../../components/header/Header';
import { Button } from '@mui/material';
import { Answer, Category, Question, User } from '../../types/types';
import { getCategories, getQuestions, getQuestionsByCategory, getUser, updateUser } from '../../backend/dataSource';
import LinearProgress from '@mui/joy/LinearProgress';
import { Typography } from '@mui/material';
import { keycloak } from '../../secure/keycloak';
import Chart from './StadisticsGame';
import './Game.scss';
import { useTranslation } from 'react-i18next';

const Game: React.FC = () => {
    const { t } = useTranslation();
    const [categories, setCategories] = useState<Category[]>([]);
    const [categorySelected, setCategorySelected] = useState<Category | undefined>(undefined);
    const [questions, setQuestions] = useState<Question[]>([]);
    const [idQuestion, setIdQuestion] = useState<number>(0);
    const [timeLeft, setTimeLeft] = useState<number>(0);
    const [progress, setProgress] = useState<number>(100);
    const [totalCorrectAnswers, setTotalCorrectAnswers] = useState<number>(0);
    const [totalIncorrectAnswers, setTotalIncorrectAnswers] = useState<number>(0);
    const [isGameFinished, setIsGameFinished] = useState<boolean>(false);
    const [userActive, setUserActive] = useState<User | null>(null);

    useEffect(()=>{
        getUser(keycloak.subject ? keycloak.subject : '').then((user: User) => {
            setUserActive(user)
        })
        getCategories().then((categories: Category[])=>{
            setCategories(categories)
        })
    }, [])

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

    function handleStartGame(category?: Category) {
        if(category !== undefined){
            setCategorySelected(category)
            getQuestionsByCategory(category.id).then((questions: Question[]) => {
                setQuestions(questions)
            })
        }
        else{
            getQuestions().then((questions: Question[]) => {
                setQuestions(questions)
            })
        }
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
                    id: userActive?.id,
                    name: userActive?.name,
                    totalGames: userActive?.totalGames !== undefined ? userActive?.totalGames + 1 : 0,
                    correctAnswers: userActive?.correctAnswers !== undefined ? userActive?.correctAnswers + totalCorrectAnswers : 0,
                    inCorrectAnswers: userActive?.inCorrectAnswers !== undefined ? userActive?.inCorrectAnswers + totalIncorrectAnswers : 0,
                    totalQuestionAnswered: userActive?.totalQuestionAnswered !== undefined ? userActive?.totalQuestionAnswered + (totalCorrectAnswers+totalIncorrectAnswers) : 0,
                    lastCategoryPlayed: categorySelected !== undefined ? categorySelected.name : userActive?.lastCategoryPlayed
                } as User);
            }
        }
    }

    return (
        <div id='syg-game-container'>
            <Header info='game' />
            <div id='syg-game-content'>
                {categories.length > 0 && (questions === undefined || questions.length < 1) ? (
                    <div id='syg-game-content-start-game'>
                        <h2>{t('game.mode.title')}</h2>
                        <div id='syg-game-content-start-game-options'>
                            <Button
                                className='syg-game-start-game-button'
                                onClick={() => handleStartGame()}
                            >
                                {t('game.mode.standard')}
                            </Button>
                            {categories.map((category)=>(
                                <Button
                                className='syg-game-start-game-button'
                                onClick={() => handleStartGame(category)}
                            >
                               {t(`game.mode.${category.name}`)}
                            </Button>
                            ))}
                        </div>
                    </div>
                ) : (
                    questions !== undefined && questions.length > 0 && isGameFinished === false ? (
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
                            <h2>{t('game.results.tittle')}</h2>
                            <Chart
                                correctAnswers={totalCorrectAnswers}
                                incorrectAnswers={totalIncorrectAnswers}
                            />
                        </div>
                    )
                )}
            </div>
        </div>
    );
};

export default Game;