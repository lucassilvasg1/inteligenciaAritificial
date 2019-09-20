package algoritmos;

import algoritmos.model.Tree;
import trabalho1.model.Tabuleiro;

public interface Busca
{

   void setProblema(Tabuleiro problema);

   void setExperado(Tabuleiro solucao);

   boolean executar();

   public Tree getTree();

   Tabuleiro getSolucao();
   
}
