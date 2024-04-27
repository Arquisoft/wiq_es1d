import React, { useState, useEffect } from 'react';
import LinearProgress from '@mui/joy/LinearProgress';
import {Typography } from '@mui/material';
import './QuestionTimer.scss';

interface QuestionTimerProps {
    seconds: number;
    onTimeout: () => void;
}

const QuestionTimer: React.FC<QuestionTimerProps> = (props: QuestionTimerProps) => {
  const [timeLeft, setTimeLeft] = useState<number>(props.seconds);
  const [progress, setProgress] = useState<number>(100);

  useEffect(() => {
    const timer = setInterval(() => {
      setTimeLeft(prevTime => prevTime - 1);
    }, 1000);

    return () => clearInterval(timer);
  }, [])

  useEffect(() => {
    const percentage = (timeLeft / props.seconds) * 100;
    setProgress(percentage);

    if (timeLeft === 0) {
        props.onTimeout();
    }
  }, [timeLeft])

  return (
    <div id='syg-interval-question-progress' data-testid='syg-interval-question-progress'>
      <LinearProgress determinate={true} variant="outlined" value={progress}  color="primary" thickness={32} data-testid='syg-interval-question-linear-progress'>
        <Typography data-testid='syg-interval-question-text'>
          {timeLeft}
        </Typography>
      </LinearProgress>
    </div>
  );
};

export default QuestionTimer;