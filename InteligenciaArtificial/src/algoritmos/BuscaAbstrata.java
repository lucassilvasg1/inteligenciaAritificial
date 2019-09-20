package algoritmos;

import java.util.ArrayList;
import java.util.List;

import algoritmos.model.Node;
import algoritmos.model.Tree;
import trabalho1.model.Tabuleiro;


public abstract class BuscaAbstrata implements Busca
{

   protected Tabuleiro problema;

   protected Tabuleiro experado;

   protected Tabuleiro solucao;

   protected Tree tree;
   
   public Integer nivel = 0;
   
   public BuscaAbstrata()
   {
      tree = new Tree();
   }
   
   @Override
   public void setProblema(Tabuleiro problema)
   {
      tree.setRootElement(new Node(problema));
      this.problema = problema;
   }

   @Override
   public void setExperado(Tabuleiro experado)
   {
      this.experado = experado;
   }

   public void setSolucao(Tabuleiro solucao)
   {
      this.solucao = solucao;
   }

   @Override
   public Tabuleiro getSolucao()
   {
      return solucao;
   }

   public Tree getTree()
   {
      return tree;
   }

   protected boolean isSolution(Node node)
   {
      if (experado.equals(node.problema))
      {
         setSolucao(node.problema);
         return true;
      }
      return false;
   }

   protected void construirArvoreSolucao(Node found)
   {
      if (found == tree.getRootElement()) return;
      Node parent = found.getPai();
      if (parent == null) return;
      parent.inserirFilho(found);
      construirArvoreSolucao(parent);
   }

   protected List<Node> abrir(Node current, boolean storeChilds)
   {
      List<Tabuleiro> derivates = current.getProblema().derivar();
      List<Node> list = new ArrayList<Node>();
      for (Tabuleiro problema : derivates)
      {
         Node node = new Node(problema);
         node.setPai(current);
         if (storeChilds) current.inserirFilho(node);
         list.add(node);
      }

      return list;
   }

   @Override
   public boolean executar()
   {

      if (experado.equals(problema))
      {
         return true;
      }

      return false;
   }

}
