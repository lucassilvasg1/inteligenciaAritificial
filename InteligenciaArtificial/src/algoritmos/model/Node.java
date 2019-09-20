package algoritmos.model;

import java.util.ArrayList;
import java.util.List;

import trabalho1.model.Tabuleiro;

public class Node
{

   public Tabuleiro problema;

   public List<Node> filho;

   private int nivel;

   private Node pai;

   public Node()
   {
      super();
   }

   public Node(Tabuleiro data)
   {
      this();
      setProblema(data);
   }

   public List<Node> getChildren()
   {
      if (this.filho == null)
      {
         return new ArrayList<Node>();
      }
      return this.filho;
   }

   public void setChildren(List<Node> filho)
   {
      this.filho = filho;
   }

   public int getNumberOfChildren()
   {
      if (filho == null)
      {
         return 0;
      }
      return filho.size();
   }

   public void inserirFilho(Node child)
   {
      if (filho == null)
      {
         filho = new ArrayList<Node>();
      }
      filho.add(child);
      child.pai = this;
   }

   public void inserirFilhoEm(int index, Node child) throws IndexOutOfBoundsException
   {
      if (index == getNumberOfChildren())
      {
         inserirFilho(child);
         return;
      }
      else
      {
         filho.get(index);
         filho.add(index, child);
      }
   }

   public void removerFilho(int index) throws IndexOutOfBoundsException
   {
      filho.remove(index);
   }

   public Tabuleiro getProblema()
   {
      return this.problema;
   }

   public void setProblema(Tabuleiro data)
   {
      this.problema = data;
   }

   public void setPai(Node pai)
   {
      this.pai = pai;
   }

   public Node getPai()
   {
      return pai;
   }

   @Override
   public String toString()
   {
      return problema.toString();
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((problema == null) ? 0 : problema.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      Node other = (Node) obj;
      if (problema == null)
      {
         if (other.problema != null) return false;
      }
      else if (!problema.equals(other.problema)) return false;
      return true;
   }

   public int getNivel()
   {
      return nivel;
   }

   public void setNivel(int nivel)
   {
      this.nivel = nivel;
   }

}