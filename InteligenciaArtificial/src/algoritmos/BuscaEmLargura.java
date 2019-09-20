package algoritmos;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import algoritmos.model.Node;

public class BuscaEmLargura extends BuscaAbstrata
{

   private Queue<Node> fila = new LinkedList<Node>();

   private Set<Node> visitado = new HashSet<Node>();

   @Override
   public boolean executar()
   {

      super.executar();

      fila.add(getTree().getRootElement());

      while (!fila.isEmpty())
      {
         Node atual = fila.remove();

         if (isSolution(atual))
         {
            System.out.println("SOLUÇÃO ENCONTRADA.....");
            construirArvoreSolucao(atual);
            return true;
         }

         if (!visitado.contains(atual))
         {
            visitado.add(atual);
            List<Node> filhos = abrir(atual, false);
            for (Node o : filhos)
            {
               fila.add(o);
            }
         }

      }

      return false;

   }

}
