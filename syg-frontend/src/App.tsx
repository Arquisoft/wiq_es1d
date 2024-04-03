import React from 'react';
import { Navigate, Route, Routes, useNavigate } from 'react-router-dom';
import Home from './modules/home/Home';
import Historic from './modules/historic/Historic';
import Aside from './components/aside/Aside';
import Game from './modules/game/Game';
import {keycloak} from './secure/keycloak';
import './App.scss';

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
                <Route path="/" element={keycloak.authenticated ? <Home /> : <Navigate to="/home" />} />
                <Route path="/game" element={keycloak.authenticated ? <Game /> : <Navigate to="/home" />} />
                <Route path="/historic" element={keycloak.authenticated ? <Historic /> : <Navigate to="/home" />} />
              </Routes>
            </div>
          </div>

  );
}

export default App;
