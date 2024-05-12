describe('Home', () => {
    beforeEach(() => {
        cy.visit('http://localhost:3000');

        cy.origin('http://localhost:8090', () => {
            cy.get('#kc-content-wrapper').should('exist');
            cy.get('#username').type('alvaro');
            cy.get('#password').type('alvaro');

            cy.get('#kc-login').click();
        })
    });

    it('Home screen', () => {
        cy.get('#syg-header-container').should('exist'); 
        cy.get('#syg-home-content').should('exist');
        cy.get('#syg-home-description').should('exist');
        cy.get('#syg-home-instructions').should('exist');
        cy.get('#syg-home-description-image').should('exist');
        cy.get('#syg-home-instructions-image').should('exist');
        cy.contains('Una vez en la pantalla correcta, podras escoger entre varias categorías, la estandar engloba todas las categorías').should('exist');
    });
});