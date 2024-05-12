describe('Ranking', () => {
    beforeEach(() => {
        cy.visit('http://localhost:3000');

        cy.origin('http://localhost:8090', () => {
            cy.get('#kc-content-wrapper').should('exist');
            cy.get('#username').type('alvaro');
            cy.get('#password').type('alvaro');

            cy.get('#kc-login').click();
        })
    });

    it('Ranking screen', () => {
        cy.contains('.syg-aside-navigation-menu-element', 'RANKING').click();
        cy.get('#syg-header-container').should('exist');
        cy.get('#syg-ranking-content').should('exist');
        cy.get('.syg-ranking-table').should('exist');
        cy.get('.MuiToolbar-root').should('exist');
        cy.contains('.MuiDataGrid-cell', 'alvaro').should('exist');
    });
});