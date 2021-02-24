package algoritmos;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import algoritmos.model.Node;

public class BuscaEmLargura extends BuscaAbstrata
{
   private Long numeroVisitados = 0L;
   private Queue<Node> fila = new LinkedList<Node>();

   private Set<Node> visitado = new HashSet<Node>();

   @Override
   public boolean executar()
   {

      super.executar();
      this.numeroVisitados = 0L;

      fila.add(getTree().getRootElement());

      while (!fila.isEmpty())
      {
         Node atual = fila.remove();
         this.numeroVisitados++;
         if (isSolution(atual))
         {
            System.out.println("SOLUÇÃO ENCONTRADA.....");
            construirArvoreSolucao(atual);
            System.out.println("NÚMERO DE NÓS VISITADOS BUSCA EM LARGURA: " + this.numeroVisitados);
            return true;
         }

         if (!visitado.contains(atual))
         {
            visitado.add(atual);
            List<Node> filhos = abrir(atual, false);
            for (Node o : filhos)
            {
               o.setNivel(o.getPai().getNivel() + 1);
               fila.add(o);
            }
         }

      }

      return false;

   }

}
