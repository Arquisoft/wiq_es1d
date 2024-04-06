import React from 'react';
import { Paper, Typography } from '@mui/material';
import './StadisticsGame.scss'
import { useTranslation } from 'react-i18next';

interface ChartAnswersProps{
    correctAnswers: number,
    incorrectAnswers: number
}

function Chart(props: ChartAnswersProps) {
  const { t } = useTranslation();
  
  return (
    <Paper id='stadistic-game-container'>
      <div id='stadistic-game-correct'>
        <span>{t('game.results.correctAnswers')}:</span>
        <div id='stadistic-game-correct-bar' style={{ width: `${props.correctAnswers * 10}px`}}>{props.correctAnswers}</div>
      </div>
      <div id='stadistic-game-incorrect'>
        <span>{t('game.results.incorrectAnswers')}:</span>
        <div id='stadistic-game-incorrect-bar' style={{ width: `${props.incorrectAnswers * 10}px`}}>{props.incorrectAnswers}</div>
      </div>
    </Paper>
  );
}

export default Chart;
