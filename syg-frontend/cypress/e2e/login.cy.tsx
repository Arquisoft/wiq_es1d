describe('Login methods', () => {
  it('Login success', () => {
    cy.visit('http://localhost:3000')

    cy.origin('http://localhost:8090', () => {
      cy.get('#kc-content-wrapper').should('exist');
      cy.get('#username').type('alvaro');
      cy.get('#password').type('alvaro');

      cy.get('#kc-login').click();
    })

    cy.get('#syg-aside-container').should('exist');
    cy.get('#syg-content-container').should('exist');
    cy.get('#syg-aside-username-complete').should('contain', 'alvaro'); 
  })

  it('Bad login not success', () => {
    cy.visit('http://localhost:3000')

    cy.origin('http://localhost:8090', () => {
      cy.get('#kc-content-wrapper').should('exist');
      cy.get('#username').type('not exist username');
      cy.get('#password').type('random passord');

      cy.get('#kc-login').click();
    })

    cy.origin('http://localhost:8090', () => {
      cy.get('#syg-aside-container').should('not.exist');
      cy.get('#syg-content-container').should('not.exist');
      cy.get('#kc-content-wrapper').should('exist');
      cy.get('#input-error ').should('exist');  
    })
  })

  it('Login register and success', () => {
    cy.visit('http://localhost:3000')
    
    cy.origin('http://localhost:8090', () => {
      cy.get('#kc-content-wrapper').should('exist');
      cy.contains('Register').click();
      cy.get('#username').type('juan');
      cy.get('#password').type('juan');
      cy.get('#password-confirm').type('juan');
      cy.get('#email').type('juan@gmail.com');
      cy.get('#firstName').type('Juan');
      cy.get('#lastName').type('Díaz');

      cy.contains('.pf-c-button', 'Register').click();
    })

    cy.get('#syg-aside-container').should('exist');
    cy.get('#syg-content-container').should('exist');
    cy.get('#syg-aside-username-complete').should('contain', 'juan'); 
  })

  it('Login success and logout', () => {
    cy.visit('http://localhost:3000')
    
    cy.origin('http://localhost:8090', () => {
      cy.get('#kc-content-wrapper').should('exist');
      cy.get('#username').type('alvaro');
      cy.get('#password').type('alvaro');

      cy.get('#kc-login').click();
    })

    cy.get('#syg-aside-container').should('exist');
    cy.get('#syg-content-container').should('exist');
    cy.get('#syg-aside-username-complete').should('contain', 'alvaro'); 

    cy.get('#syg-aside-logout').click();
    
    cy.origin('http://localhost:8090', () => {
      cy.get('#syg-aside-container').should('not.exist');
      cy.get('#syg-content-container').should('not.exist');
      cy.get('#kc-content-wrapper').should('exist');
    })
  })
})