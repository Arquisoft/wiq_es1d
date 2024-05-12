describe('Historics', () => {
    beforeEach(() => {
        cy.visit('http://localhost:3000');

        cy.origin('http://localhost:8090', () => {
            cy.get('#kc-content-wrapper').should('exist');
            cy.get('#username').type('alvaro');
            cy.get('#password').type('alvaro');

            cy.get('#kc-login').click();
        })
    });

    it('Historic screen', () => {
        cy.contains('.syg-aside-navigation-menu-element', 'HISTORICO').click();
        cy.get('#syg-header-container').should('exist');
        cy.get('#syg-historic-container').should('exist');
        cy.get('#syg-historic-user-name').should('exist');
        cy.get('#primary-info').should('exist');
        cy.get('#secondary-info').should('exist');
        cy.contains('[data-testid="syg-historic-card-header-title"]', 'Juegos totales').should('exist');
        cy.contains('[data-testid="syg-historic-card-header-title"]', 'Total de aciertos').should('exist');
        cy.contains('[data-testid="syg-historic-card-header-title"]', 'Total de fallos').should('exist');
        cy.contains('[data-testid="syg-historic-card-header-title"]', 'Preguntas respondidas').should('exist');
        cy.contains('[data-testid="syg-historic-card-header-title"]', 'Última categoria jugada').should('exist');
    });
});