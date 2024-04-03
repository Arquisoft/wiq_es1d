import Keycloak from 'keycloak-js';
import { getUser, registryUser } from '../backend/dataSource';

const keycloak = new Keycloak({
    url: 'http://localhost:8090',
    realm: 'syg',
    clientId: 'syg-client',
});

keycloak.init({ onLoad: 'login-required' }).then(authenticated => {
        console.log("AUTH-->", authenticated)
    }).catch(error => {
        console.error('Error al inicializar Keycloak', error);
    });

keycloak.onAuthSuccess = function() {
    getUser(keycloak.subject ? keycloak.subject : '')
    .then(user => {
        return
    })
    .catch(error => {
        registryUser({
                id: keycloak.subject ? keycloak.subject : '',
                name: keycloak.tokenParsed?.preferred_username,
                totalGames: 0,
                correctAnswers: 0,
                inCorrectAnswers: 0
            });
    });

};

function logout(){
    keycloak.logout();
}

export { keycloak, logout };