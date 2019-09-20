package algoritmos;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import algoritmos.model.Node;


public class BuscaGulosaA extends BuscaAbstrata
{

   private Heuristica heuristica;

   private PriorityQueue<Node> fila;

   private Set<Node> visitado = new HashSet<Node>();

   public BuscaGulosaA(Heuristica heuristica)
   {
      super();
      this.heuristica = heuristica;
      this.fila = new PriorityQueue<Node>(new HeuristaComparator());
   }

   @Override
   public boolean executar()
   {

      super.executar();

      getTree().getRootElement().setNivel(0);
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
               o.setNivel(o.getPai().getNivel() + 1);
               
               fila.add(o);
            }
         }
      }

      return false;

   }

   // A HEURISTICA USADA É PELO POSICIONAMENTO 
   // CASO O '1' JÁ ESTEJA NA POSIÇÃO CORRETA, O NÓ VAI PRO COMEÇO DA FILA, QUANTO MAIS ELEMENTOS NA POSIÇÃO CORRETA,
   // MAIS PRIORIDADE ELE VAI TER NA FILA, OU SEJA, ELE VAI FICAR NAS PRIMEIRAS POSIÇÕES
   // A HEURISTICA FOI ESCOLHIDA POR SER MAIS FÁCIL DE VISUALIZAR NA CLASSE PRIORITY QUEUE (MAS QUALQUER OUTRA HEURISTICA FUNCIONARIA)
   private class HeuristaComparator implements Comparator<Node>
   {

      @Override
      public int compare(Node o1, Node o2)
      {
         
         double v1 = heuristica.calculate(o1.getProblema(), experado) + o1.getNivel();
         double v2 = heuristica.calculate(o2.getProblema(), experado) + o2.getNivel();

         return Double.compare(v2, v1);
      }

   }

}
