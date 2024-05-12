import { screen, render, fireEvent, act } from "@testing-library/react"
import Aside from "./Aside"
import i18n from '../../translation/i18n';
import { MemoryRouter } from "react-router-dom";

describe('Header', () => {
    beforeAll(() => {
        i18n.changeLanguage('Spain'); 
    });

    test('Aside renders correctly', () => {
        render(
            <MemoryRouter>
                <Aside elements={[]} username={"Alvaro"} />
            </MemoryRouter>
        )
        
        const element = screen.getByTestId('syg-aside-container')
        expect(element).toBeInTheDocument();
    })

    test('Header components renders correctly', () => {
        render(
            <MemoryRouter>
                <Aside elements={[]} username={"Alvaro"} />
            </MemoryRouter>
        )
        
        const asideNavigationMenu = screen.getByTestId('syg-aside-navigation-menu')
        expect(asideNavigationMenu).toBeInTheDocument();

        const asideMenu = screen.getByTestId('syg-aside-login-menu')
        expect(asideMenu).toBeInTheDocument();

        const asideUsernameContent = screen.getByTestId('syg-aside-username')
        expect(asideUsernameContent).toBeInTheDocument();

        const asideUsernameInitial = screen.getByTestId('syg-aside-username-initial')
        expect(asideUsernameInitial).toBeInTheDocument();

        const asideUsername = screen.getByTestId('syg-aside-username-complete')
        expect(asideUsername).toBeInTheDocument();

        const asideLogout = screen.getByTestId('syg-aside-logout')
        expect(asideLogout).toBeInTheDocument();

        const asideLogoutText = screen.getByTestId('syg-aside-logout-text')
        expect(asideLogoutText).toBeInTheDocument();
    })

    test('Aside login user info text', () => {
        render(
            <MemoryRouter>
                <Aside elements={[]} username={"Alvaro"} />
            </MemoryRouter>
        )
        
        const asideUsernameInitial = screen.getByTestId('syg-aside-username-initial')
        expect(asideUsernameInitial).toHaveTextContent("A");

        const asideUsername = screen.getByTestId('syg-aside-username-complete')
        expect(asideUsername).toHaveTextContent("Alvaro");
    })

    test('Aside logout user info text', () => {
        render(
            <MemoryRouter>
                <Aside elements={[]} username={"Alvaro"} />
            </MemoryRouter>
        )
        
        const asideLogoutText = screen.getByTestId('syg-aside-logout-text')
        expect(asideLogoutText).toHaveTextContent("LOGOUT");
    })

    test('Aside change component option', () => {
        const onClickAsideElementMockOption1 = jest.fn();
        const onClickAsideElementMockOption2 = jest.fn();
        render( <MemoryRouter><Aside elements={[
            {
                text:"Juego",
                icon: <></>,
                onClickAsideElement: onClickAsideElementMockOption1
            },
            {
                text:"Historico",
                icon: <></>,
                onClickAsideElement: onClickAsideElementMockOption2
            }
        ]} username={"Alvaro"} /></MemoryRouter>)

        const buttonOption2 = screen.getByText('HISTORICO');

        fireEvent.click(buttonOption2);
      
        expect(onClickAsideElementMockOption2).toHaveBeenCalledTimes(1);

        const buttonOption1 = screen.getByText('JUEGO');

        fireEvent.click(buttonOption1);

        expect(onClickAsideElementMockOption2).toHaveBeenCalledTimes(1);
    })
})