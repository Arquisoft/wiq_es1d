import React, { useEffect, useState } from 'react';
import Header from '../../components/header/Header';
import { getUser } from '../../backend/dataSource';
import { User } from '../../types/types';
import SportsEsportsIcon from '@mui/icons-material/SportsEsports';
import CheckIcon from '@mui/icons-material/Check';
import ClearIcon from '@mui/icons-material/Clear';
import UserInfoCard from '../../components/card/UserInfoCard';
import {keycloak} from '../../secure/keycloak';
import './Historic.scss';

const Historic: React.FC = () => {
    const [userActive, setUserActive] = useState<User | null>(null);

    useEffect(() => {
        getUser(keycloak.subject ? keycloak.subject : '').then((user: User) => {
            setUserActive(user)
        })
    }, [])

    return (
        <div id='syg-historic-container'>
            <Header info='Historicos' />
            <div id='syg-historic-content'>
                <span id='syg-historic-user-name'> Datos de juego de <i>{userActive?.name}</i></span>
                <div id='syg-historic-user-info'>
                    <UserInfoCard
                        title={'Número de juegos totales'}
                        info={userActive !== null ? userActive.totalGames : 0}
                        iconItem={<SportsEsportsIcon color='secondary'/>}
                    />

                    <UserInfoCard
                        title={'Número total de aciertos'}
                        info={userActive !== null ? userActive.correctAnswers : 0}
                        iconItem={<CheckIcon color='success'/>}
                    />

                    <UserInfoCard
                        title={'Número total de fallos'}
                        info={userActive !== null ? userActive.inCorrectAnswers : 0}
                        iconItem={<ClearIcon color='error'/>}
                    />
                </div>
            </div>
        </div>
    );
};

export default Historic;