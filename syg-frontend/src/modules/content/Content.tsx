import React, { useEffect, useState } from 'react';
import { Route, Routes, useNavigate } from 'react-router-dom';
import Historic from '../../modules/historic/Historic';
import Aside from '../../components/aside/Aside';
import Game from '../../modules/game/Game';
import { login, logout } from '../../secure/keycloak';
import { User } from '../../types/types';
import HomeIcon from '@mui/icons-material/Home';
import GamesIcon from '@mui/icons-material/Games';
import QueryStatsIcon from '@mui/icons-material/QueryStats';
import BarChartIcon from '@mui/icons-material/BarChart';
import Ranking from '../ranking/Ranking';
import Home from '../home/Home';
import './Content.scss';

function Content() {
    const navigate = useNavigate();
    const [userActive, setUserActive] = useState<User | null>(null);
  
    useEffect(() => {
        login().then(user =>{
            setUserActive(user)
        });
    }, []);

    useEffect(() => {
      if(userActive === null){
        logout();
        navigate('/');
      }
  }, [userActive]);
  
    function handleHomeClick() {
      navigate('/');
    };

    function handleGameClick() {
      navigate('/game');
    };
  
    function handleHistoricClick() {
      navigate('/historic');
    };

    function handleRankingClick() {
      navigate('/ranking');
    };
  
    return (
        userActive ? (
        <div id='syg-container' className="content" data-testid='syg-container'>
          <Aside
            elements={[
              { text: 'Home', icon: <HomeIcon/>, onClickAsideElement: handleHomeClick },
              { text: 'Juego', icon: <GamesIcon/>, onClickAsideElement: handleGameClick },
              { text: 'Historico', icon: <QueryStatsIcon/>, onClickAsideElement: handleHistoricClick },
              { text: 'Ranking', icon: <BarChartIcon/>, onClickAsideElement: handleRankingClick },
            ]}
            username={userActive?.name}
          />
          <div id='syg-content-container' data-testid='syg-content-container'>
            <Routes>
              <Route path="/" element={<Home/>} />
              <Route path="/game" element={<Game />} />
              <Route path="/historic" element={<Historic />} />
              <Route path="/ranking" element={<Ranking />} />
            </Routes>
          </div>
        </div>
      ) : null
    );
}

export default Content;