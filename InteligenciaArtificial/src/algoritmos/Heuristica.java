package algoritmos;

import trabalho1.model.Tabuleiro;

public interface Heuristica
{
   public double calculate(Tabuleiro problema, Tabuleiro solucao);
}
