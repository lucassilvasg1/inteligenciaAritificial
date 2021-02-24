package trabalho1.model;

import java.util.List;

import algoritmos.Heuristica;

public class TabuleiroHeuristicaA implements Heuristica
{

   @Override
   public double calculate(Tabuleiro Problema, Tabuleiro solucao)
   {
      double c = 0;

      List<Celula> cells1 = (solucao).getCelulas();
      List<Celula> cells2 = (Problema).getCelulas();

      for (int i = 0; i < cells1.size(); i++)
      {
         if (cells1.get(i).getValue() != cells2.get(i).getValue())
         {
            c++;
         }
      }

      return c;
   }

}
