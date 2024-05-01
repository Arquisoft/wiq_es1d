import React from 'react';
import { Paper } from '@mui/material';
import './StadisticsGame.scss'
import { useTranslation } from 'react-i18next';

interface ChartAnswersProps{
    correctAnswers: number,
    incorrectAnswers: number
}

function Chart(props: ChartAnswersProps) {
  const { t } = useTranslation();
  
  return (
    <Paper id='stadistic-game-container' data-testid='stadistic-game-container'>
      <div id='stadistic-game-correct' data-testid='stadistic-game-correct'>
        <span data-testid='stadistic-game-correct-info'>{t('game.results.correctAnswers')}:</span>
        <div id='stadistic-game-correct-bar' style={{ width: `${props.correctAnswers * 10}px`}} data-testid='stadistic-game-correct-bar'>{props.correctAnswers}</div>
      </div>
      <div id='stadistic-game-incorrect' data-testid='stadistic-game-incorrect'>
        <span data-testid='stadistic-game-incorrect-info'>{t('game.results.incorrectAnswers')}:</span>
        <div id='stadistic-game-incorrect-bar' style={{ width: `${props.incorrectAnswers * 10}px`}} data-testid='stadistic-game-incorrect-bar'>{props.incorrectAnswers}</div>
      </div>
    </Paper>
  );
}

export default Chart;
