import React, { useEffect, useState } from 'react';
import Header from '../../components/header/Header';
import { getUser } from '../../backend/dataSource';
import { User } from '../../types/types';
import SportsEsportsIcon from '@mui/icons-material/SportsEsports';
import CheckIcon from '@mui/icons-material/Check';
import ClearIcon from '@mui/icons-material/Clear';
import CategoryIcon from '@mui/icons-material/Category';
import QuestionMarkIcon from '@mui/icons-material/QuestionMark';
import UserInfoCard from '../../components/card/UserInfoCard';
import { keycloak } from '../../secure/keycloak';
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
                    <div id='primary-info'>
                        <UserInfoCard
                            title={'Juegos totales'}
                            info={userActive !== null ? userActive.totalGames : 0}
                            iconItem={<SportsEsportsIcon color='secondary'/>}
                        />

                        <UserInfoCard
                            title={'Total de aciertos'}
                            info={userActive !== null ? userActive.correctAnswers : 0}
                            iconItem={<CheckIcon color='success'/>}
                        />

                        <UserInfoCard
                            title={'Total de fallos'}
                            info={userActive !== null ? userActive.inCorrectAnswers : 0}
                            iconItem={<ClearIcon color='error'/>}
                        />
                    </div>
                    <div id='secondary-info'>
                        <UserInfoCard
                            title={'Preguntas respondidas'}
                            info={userActive !== null ? userActive.totalQuestionAnswered : 0}
                            iconItem={<QuestionMarkIcon color='warning'/>}
                        />

                        <UserInfoCard
                            title={'Última categoria jugada'}
                            info={userActive !== null ? userActive.lastCategoryPlayed.substring(0, 1).toUpperCase() + userActive.lastCategoryPlayed.substring(1) : ''}
                            iconItem={<CategoryIcon color='info'/>}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Historic;