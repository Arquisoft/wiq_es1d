import React from 'react';
import { Route, Routes, useNavigate } from 'react-router-dom';
import Home from './modules/home/Home';
import Login from './modules/login/Login';
import Historic from './modules/historic/Historic';
import Aside from './components/aside/Aside';
import './App.scss';
import Game from './modules/game/Game';

function App() {
  const navigate = useNavigate();

  function handleGameClick() {
    navigate('/game');
  };

  function handleHistoricClick() {
    navigate('/historic');
  };

  return (
    <div id='syg-container' className="App">
      <Aside
        elements={[
          { text: 'Game', onClickAsideElement: handleGameClick },
          { text: 'Historico', onClickAsideElement: handleHistoricClick },
        ]}
        username={"Álvaro"}
      />
      <div id='syg-content-container'>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/game" element={<Game />} />
          <Route path="/historic" element={<Historic />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
