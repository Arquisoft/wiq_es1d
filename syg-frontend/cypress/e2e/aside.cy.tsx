describe('Aside', () => {
    beforeEach(() => {
        cy.visit('http://localhost:3000');

        cy.origin('http://localhost:8090', () => {
            cy.get('#kc-content-wrapper').should('exist');
            cy.get('#username').type('alvaro');
            cy.get('#password').type('alvaro');

            cy.get('#kc-login').click();
        })
    });

    it('Aside navigation', () => {
        cy.contains('.syg-aside-navigation-menu-element', 'JUEGO').click();
        cy.get('#syg-header-container').should('exist');
        cy.get('#syg-game-content').should('exist');

        cy.contains('.syg-aside-navigation-menu-element', 'HISTORICO').click();
        cy.get('#syg-header-container').should('exist');
        cy.get('#syg-historic-container').should('exist');

        cy.contains('.syg-aside-navigation-menu-element', 'RANKING').click();
        cy.get('#syg-header-container').should('exist');
        cy.get('#syg-ranking-container').should('exist');

        cy.contains('.syg-aside-navigation-menu-element', 'HOME').click();
        cy.get('#syg-header-container').should('exist');
        cy.get('#syg-home-container').should('exist');
    });
});