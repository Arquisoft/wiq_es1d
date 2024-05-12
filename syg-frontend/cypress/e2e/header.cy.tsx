describe('Header', () => {
    beforeEach(() => {
        cy.visit('http://localhost:3000');

        cy.origin('http://localhost:8090', () => {
            cy.get('#kc-content-wrapper').should('exist');
            cy.get('#username').type('alvaro');
            cy.get('#password').type('alvaro');

            cy.get('#kc-login').click();
        })
    });

    it('Header translate', () => {
        cy.contains('Una vez en la pantalla correcta, podras escoger entre varias categorías, la estandar engloba todas las categorías').should('exist');

        cy.contains('.syg-header-language-switch-element', 'Frances').click();
        cy.contains('Une fois sur le bon écran, vous pouvez choisir entre plusieurs catégories, la standard regroupe toutes les catégories').should('exist');

        cy.contains('.syg-header-language-switch-element', 'Allemand').click();
        cy.contains('Sobald Sie auf dem richtigen Bildschirm sind, können Sie zwischen mehreren Kategorien wählen, die Standardkategorie umfasst alle Kategorien').should('exist');

        cy.contains('.syg-header-language-switch-element', 'Englisch').click();
        cy.contains('Once on the correct screen, you can choose between several categories, the standard one includes all the categories').should('exist');

        cy.contains('.syg-header-language-switch-element', 'Spain').click();
        cy.contains('Una vez en la pantalla correcta, podras escoger entre varias categorías, la estandar engloba todas las categorías').should('exist');
    });
});