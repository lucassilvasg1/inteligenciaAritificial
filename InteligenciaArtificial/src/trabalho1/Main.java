package trabalho1;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import algoritmos.Busca;
import algoritmos.BuscaEmLargura;
import algoritmos.BuscaGulosaA;
import algoritmos.TipoBusca;
import algoritmos.model.Node;
import algoritmos.model.Tree;
import trabalho1.model.Tabuleiro;
import trabalho1.model.TabuleiroHeuristicaA;


public class Main
{
   static int[] inicial = { 2, 0, 4, 3, 1, 6, 7, 5, 8 };

   static int[] experado = { 1, 2, 3, 4, 5, 6, 7, 8, 0 };

   public static void main(String[] args)
   {

      while (true)
      {
         System.out.println();
         System.out.println("=====================================");
         System.out.println("Quebra Cabeça");
         System.out.println("=====================================");
         System.out.println("Escolha o Algoritmo:");
         TipoBusca[] tiposBusca = TipoBusca.values();
         for (int i = 0; i < tiposBusca.length; i++)
         {
            System.out.println((i + 1) + ") " + tiposBusca[i]);
         }
         System.out.println("q) Sair");

         TipoBusca buscaType = null;
         try
         {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            String string = in.readLine();
            if (string.contains("q")) System.exit(0);
            int option = Integer.parseInt(string);
            if ((option < 1) || (option > TipoBusca.values().length))
            {
               System.out.println("Opção Inválida / SAIR !");

            }
            buscaType = TipoBusca.values()[option - 1];

            run(buscaType);

         }
         catch (Exception ex)
         {
            ex.printStackTrace();
            System.exit(-1);
         }
      }

   }

   public static void run(TipoBusca tipoBusca)
   {
      Busca busca = null;
      switch (tipoBusca)
      {
      case BuscaEmLargura:
         busca = new BuscaEmLargura();
         break;
      case BuscaGulosaA:
         busca = new BuscaGulosaA(new TabuleiroHeuristicaA());
         break;
      default:
         break;
      }

      busca.setProblema(Tabuleiro.build(inicial, 3, 3));
      busca.setExperado(Tabuleiro.build(experado, 3, 3));

      if (busca.executar())
      {
         Tree tree = busca.getTree();
         List<Node> lista = tree.convertToList();

         for (Node node : lista)
         {
            System.out.println(node.problema);
         }
         
         System.out.println("NÍVEL DA ÁRVORE: " + lista.get(lista.size() - 1).getNivel());  
         
      }
   }

}
