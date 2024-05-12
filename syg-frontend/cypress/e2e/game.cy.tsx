describe('Game', () => {
    beforeEach(() => {
        cy.visit('http://localhost:3000');

        cy.origin('http://localhost:8090', () => {
            cy.get('#kc-content-wrapper').should('exist');
            cy.get('#username').type('alvaro');
            cy.get('#password').type('alvaro');

            cy.get('#kc-login').click();
        })
    });

    it('Game screen', () => {
        cy.contains('.syg-aside-navigation-menu-element', 'JUEGO').click();
        cy.get('#syg-header-container').should('exist');
        cy.get('#syg-game-content').should('exist');
        cy.get('#syg-game-content-start-game').should('exist');
        cy.contains('Escoga el modo de juego').should('exist');
        cy.get('#syg-game-content-start-game-options').should('exist');
    });

    it('Play game screen', () => {
        cy.contains('.syg-aside-navigation-menu-element', 'JUEGO').click();
        cy.contains('.syg-game-start-game-button', 'Deportes').click();

        cy.get('.syg-game-question-time-limit').should('exist');
        cy.get('.syg-game-question-title').should('exist');
        cy.get('.syg-game-question-answers-content').should('exist');

        cy.get('[data-testid="syg-game-question-answer-answer-number-0"]').click();
        cy.get('[data-testid="syg-game-question-answer-answer-number-0"]').click();
        cy.get('[data-testid="syg-game-question-answer-answer-number-0"]').click();
        cy.get('[data-testid="syg-game-question-answer-answer-number-0"]').click();
        cy.get('[data-testid="syg-game-question-answer-answer-number-0"]').click();
        cy.get('[data-testid="syg-game-question-answer-answer-number-0"]').click();
        cy.get('[data-testid="syg-game-question-answer-answer-number-0"]').click();
        cy.get('[data-testid="syg-game-question-answer-answer-number-0"]').click();
        cy.get('[data-testid="syg-game-question-answer-answer-number-0"]').click();
        cy.get('[data-testid="syg-game-question-answer-answer-number-0"]').click();
        cy.get('[data-testid="syg-game-question-answer-answer-number-0"]').click();
        cy.get('[data-testid="syg-game-question-answer-answer-number-0"]').click();
        cy.get('[data-testid="syg-game-question-answer-answer-number-0"]').click();
        cy.get('[data-testid="syg-game-question-answer-answer-number-0"]').click();
        cy.get('[data-testid="syg-game-question-answer-answer-number-0"]').click();


        cy.get('#syg-game-finish').should('exist');
        cy.get('#stadistic-game-correct').should('exist');
        cy.get('#stadistic-game-correct-bar').should('exist');
        cy.get('#stadistic-game-incorrect').should('exist');
        cy.get('#stadistic-game-incorrect-bar').should('exist');

    });
});