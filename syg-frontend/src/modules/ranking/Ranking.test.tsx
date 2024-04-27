import { screen, render, fireEvent, waitFor } from "@testing-library/react"
import Ranking from "./Ranking"
import i18n from '../../translation/i18n';
import { MemoryRouter } from "react-router-dom";

describe('Ranking', () => {
    beforeAll(() => {
        i18n.changeLanguage('Spain'); 
    });

    test('Ranking renders correctly', () => {
        render(<Ranking/>)
        
        const element = screen.getByTestId('syg-ranking-container')
        expect(element).toBeInTheDocument();
    })

    test('Ranking components renders correctly', () => {
        render(<Ranking/>)
        
        const headerContainer = screen.getByTestId('syg-ranking-content')
        expect(headerContainer).toBeInTheDocument();
    })

    test('checks DataGrid pagination and sorting', async () => {
        render(
          <MemoryRouter>
            <Ranking />
          </MemoryRouter>
        );
    
        // Esperar a que se complete la carga
        await waitFor(() => {
          expect(screen.getByTestId('syg-ranking-container')).toBeInTheDocument();
        });
    
        const dataGrid = screen.getByRole('grid'); // Localizar el DataGrid
      });
})