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
import { useTranslation } from 'react-i18next';
import './Historic.scss';

const Historic: React.FC = () => {
    const [userActive, setUserActive] = useState<User | null>(null);
    const { t } = useTranslation();

    useEffect(() => {
        getUser(keycloak.subject ? keycloak.subject : '').then((user: User) => {
            setUserActive(user)
        })
    }, [])

    return (
        <div id='syg-historic-container' data-testid='syg-historic-container'>
            <Header info='historics' />
            <div id='syg-historic-content' data-testid='syg-historic-content'>
                <span id='syg-historic-user-name' data-testid='syg-historic-user-name'> {t('historics.title')}: <i data-testid='syg-historic-user-active-name'>{userActive?.name}</i></span>
                <div id='syg-historic-user-info' data-testid='syg-historic-user-info'>
                    <div id='primary-info' data-testid='syg-header-primary-info'>
                        <UserInfoCard
                            title={t('historics.totalGames')}
                            info={userActive !== null ? userActive.totalGames : 0}
                            iconItem={<SportsEsportsIcon color='secondary'/>}
                        />

                        <UserInfoCard
                            title={t('historics.correctAnswers')}
                            info={userActive !== null ? userActive.correctAnswers : 0}
                            iconItem={<CheckIcon color='success'/>}
                        />

                        <UserInfoCard
                            title={t('historics.incorrectAnswers')}
                            info={userActive !== null ? userActive.inCorrectAnswers : 0}
                            iconItem={<ClearIcon color='error'/>}
                        />
                    </div>
                    <div id='secondary-info' data-testid='syg-header-secondary-info'>
                        <UserInfoCard
                            title={t('historics.totalQuestions')}
                            info={userActive !== null ? userActive.totalQuestionAnswered : 0}
                            iconItem={<QuestionMarkIcon color='warning'/>}
                        />

                        <UserInfoCard
                            title={t('historics.lastCategory')}
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