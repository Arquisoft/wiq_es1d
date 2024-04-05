import Keycloak from 'keycloak-js';
import { getUser, registryUser } from '../backend/dataSource';
import { User } from '../types/types';

const keycloak = new Keycloak({
    url: 'http://localhost:8090',
    realm: 'syg',
    clientId: 'syg-client',
});

function login(): Promise<User | null> {
    return keycloak.init({ onLoad: 'login-required' }).then(authenticated => {
        if (authenticated) {
            return getUser(keycloak.subject !== undefined ? keycloak.subject : '')
                .then((user: User) => {
                    return user;
                })
                .catch(error => {
                    return registryUser({
                        id: keycloak.subject ? keycloak.subject : '',
                        name: keycloak.tokenParsed?.preferred_username,
                        totalGames: 0,
                        correctAnswers: 0,
                        inCorrectAnswers: 0,
                        totalQuestionAnswered: 0,
                        lastCategoryPlayed: 'Ninguna'
                    }).then(() => {
                        return getUser(keycloak.subject !== undefined ? keycloak.subject : '')
                            .then((registeredUser: User) => {
                                return registeredUser;
                            })
                    })
                })
        } else {
            return null;
        }
    });
}

function logout() {
    keycloak.logout();
}

export { keycloak, login, logout };