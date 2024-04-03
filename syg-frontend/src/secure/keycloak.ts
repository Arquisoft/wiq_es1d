import Keycloak from 'keycloak-js';

const keycloak = new Keycloak({
    url: 'http://localhost:8090',
    realm: 'syg',
    clientId: 'syg-client',
});


function login(){
    keycloak.init({ onLoad: 'login-required' }).then(authenticated => {
        console.log("AUTH-->", authenticated)
    }).catch(error => {
        console.error('Error al inicializar Keycloak', error);
    });
}

login();

function logout(){
    keycloak.logout();
}

export { keycloak, login, logout };