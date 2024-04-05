import React, { useEffect, useState } from 'react';
import { Navigate, Route, Routes, useNavigate } from 'react-router-dom';
import Home from '../../modules/home/Home';
import Historic from '../../modules/historic/Historic';
import Aside from '../../components/aside/Aside';
import Game from '../../modules/game/Game';
import { login } from '../../secure/keycloak';
import { User } from '../../types/types';
import GamesIcon from '@mui/icons-material/Games';
import QueryStatsIcon from '@mui/icons-material/QueryStats';
import './Content.scss';

function Content() {
    const navigate = useNavigate();
    const [userActive, setUserActive] = useState<User | null>(null);
  
    useEffect(() => {
        login().then(user =>{
            setUserActive(user)
        });
    }, []);
  
    function handleGameClick() {
      navigate('/game');
    };
  
    function handleHistoricClick() {
      navigate('/historic');
    };
  
    return (
        userActive ? (
        <div id='syg-container' className="content">
          <Aside
            elements={[
              { text: 'Juego', icon: <GamesIcon/>, onClickAsideElement: handleGameClick },
              { text: 'Historico', icon: <QueryStatsIcon/>, onClickAsideElement: handleHistoricClick },
            ]}
            username={userActive?.name}
          />
          <div id='syg-content-container'>
            <Routes>
              <Route path="/" element={<Home/>} />
              <Route path="/game" element={<Game />} />
              <Route path="/historic" element={<Historic />} />
            </Routes>
          </div>
        </div>
      ) : null
    );
}

export default Content;