import React, { useEffect, useState } from 'react';
import { getUser, getUsers } from '../../backend/dataSource';
import { User } from '../../types/types';
import { keycloak } from '../../secure/keycloak';
import { DataGrid, GridColDef } from '@mui/x-data-grid';
import Header from '../../components/header/Header';
import { useTranslation } from 'react-i18next';
import './Ranking.scss';

const Ranking: React.FC = () => {
    const { t } = useTranslation();
    const [userActive, setUserActive] = useState<User | null>(null);
    const [allUsers, setAllUsers] = useState<User[]>([]);

    useEffect(() => {
        getUser(keycloak.subject ? keycloak.subject : '').then((user: User) => {
            setUserActive(user)
        })
        getUsers().then((users: User[]) => {
            setAllUsers(users);
        })
    }, [])

    function getRows(){
        const rows: any[] = []
        allUsers.forEach(user => {
            rows.push({
                id: user.id,
                username: user.name, 
                totalGames: user.totalGames,
                correctAnswers: user.correctAnswers,
                inorrectAnswers: user.inCorrectAnswers,
                totalQuestionsAnswered: user.totalQuestionAnswered,
            });
        });
        return rows;
    }

    function getColumns(): GridColDef[]{
        return[
            { field: 'username', headerName: t('ranking.username'), width: 200, headerAlign: 'center', align:'center' },
            { field: 'totalGames', headerName: t('ranking.games'), width: 190, headerAlign: 'center', align:'center' },
            { field: 'correctAnswers', headerName: t('ranking.correctAnswer'), width: 190, headerAlign: 'center', align:'center' },
            { field: 'inorrectAnswers', headerName: t('ranking.incorrectAnswer'), width: 190, headerAlign: 'center', align:'center' },
            { field: 'totalQuestionsAnswered', headerName: t('ranking.questionsAnswered'), width: 190, headerAlign: 'center', align:'center' },
        ]

    }

    return (
        <div id='syg-ranking-container' data-testid='syg-ranking-container'>
            <Header info='ranking' />
            <div id='syg-ranking-content' data-testid='syg-ranking-content'>
                <DataGrid
                    className='syg-ranking-table'
                    rows={getRows()}
                    columns={getColumns()}
                    rowSelection={false}
                    getRowClassName={(params) =>
                        params.row.id === userActive?.id ? 'active' : ''
                    }
                    initialState={{
                        sorting: {
                            sortModel: [{ field: 'username', sort: 'asc' }],
                        },
                        pagination: {
                            paginationModel: { page: 0, pageSize: 10 },
                        }
                    }}
                    pageSizeOptions={[5, 10, 25]}
                />
            </div>
        </div>
    );
};

export default Ranking;