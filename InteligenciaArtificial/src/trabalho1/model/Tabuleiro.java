package trabalho1.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Tabuleiro implements Cloneable
{

   private List<Celula> celulas;

   private int rows;

   private int cols;
   
   // IDEIA DO MOVE ACTION 
   private MoveAction action;

   public enum MoveAction {
      TOP, LEFT, RIGHT, DOWN
   }

   public Tabuleiro(List<Celula> celulas, int rows, int cols)
   {
      super();
      this.celulas = celulas;
      this.rows = rows;
      this.cols = cols;
   }

   public static Tabuleiro build(int[] state, int rows, int cols)
   {
      List<Celula> celulas = new LinkedList<Celula>();
      int c = 0;
      for (int y = 0; y < rows; y++)
      {
         for (int x = 0; x < cols; x++)
         {
            int index = c++;
            // System.out.println(x +"," + y + ": " + index); 
            // MARCOS LUNCIEL 14/09/ 
            Celula cell = new Celula(state[index], x, y);
            celulas.add(cell);
         }

      }
      Tabuleiro board = new Tabuleiro(celulas, rows, cols);
      return board;
   }

   // FIZ PRA NÃO TER CHAMAR APENAS O TIPO DE MOVIMENTO COM A DIRECAO
   public boolean move(Celula cell, MoveAction direction)
   {

      switch (direction)
      {
      case TOP:
         if (cell.y > 0)
         {
            cell.y--;
            cell.setMoved(true);
            Celula cell2 = getCelula(cell.x, cell.y);
            cell2.y++;
            cell2.setMoved(true);
            Collections.sort(celulas);
            return true;
         }
         break;
      case RIGHT:
         if (cell.x < cols - 1)
         {
            cell.x++;
            cell.setMoved(true);
            Celula cell2 = getCelula(cell.x, cell.y);
            cell2.x--;
            cell2.setMoved(true);
            Collections.sort(celulas);
            return true;
         }
         break;
      case DOWN:
         if (cell.y < rows - 1)
         {
            cell.y++;
            cell.setMoved(true);
            Celula cell2 = getCelula(cell.x, cell.y);
            cell2.y--;
            cell2.setMoved(true);
            Collections.sort(celulas);
            return true;
         }
         break;
      case LEFT:
         if (cell.x > 0)
         {
            cell.x--;
            cell.setMoved(true);
            Celula cell2 = getCelula(cell.x, cell.y);
            cell2.x++;
            cell2.setMoved(true);
            Collections.sort(celulas);
            return true;
         }
         break;
      default:
         break;
      }

      return false;

   }

   /**
    * Gera novos sucessores
    */
   public List<Tabuleiro> derivar()
   {
      
      List<Tabuleiro> boards = new ArrayList<Tabuleiro>();

      // Tenta mover o celulas vazia em todas as direções, e cria novas
      // instancias do Tabuleiro
      // apenas onde conseguir fazer o movimento.
      for (MoveAction direction : MoveAction.values())
      {
         try
         {
            Tabuleiro newTabuleiro = (Tabuleiro) this.clone();
            Celula empty = newTabuleiro.getEmptyCelula();
            if (empty == null) throw new IllegalStateException("Celula vazia não encontrada !");
            if (newTabuleiro.move(empty, direction))
            {
               newTabuleiro.action = direction;
               boards.add(newTabuleiro);
            }
         }
         catch (CloneNotSupportedException e)
         {
            e.printStackTrace();
         }

      }

      return boards;
   }
   
   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((celulas == null) ? 0 : celulas.hashCode());
      return result;
   }
   
   // TEM QUE VERIFICAR SE AS CELULAS SÃO IGUALS
   @Override
   public boolean equals(Object obj)
   {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      Tabuleiro other = (Tabuleiro) obj;
      if (celulas == null)
      {
         if (other.celulas != null) return false;
      }
      else if (!celulas.equals(other.celulas)) return false;
      return true;
      
   }

   public List<Celula> getCelulas()
   {
      return celulas;
   }

   public void setCelulas(List<Celula> celulas)
   {
      this.celulas = celulas;
   }

   public int getRows()
   {
      return rows;
   }

   public int getCols()
   {
      return cols;
   }

   public Celula getEmptyCelula()
   {

      for (Celula cell : celulas)
      {
         if (cell.getValue() == 0) return cell;
      }

      return null;
   }

   public Celula getCelula(int value)
   {
      for (Celula cell : celulas)
      {
         if (cell.getValue() == value) return cell;
      }

      return null;
   }

   public Celula getCelula(int x, int y)
   {
      return getCelula(x, y, true);
   }

   public Celula getCelula(int x, int y, boolean ignoreEmpty)
   {

      for (Celula cell : celulas)
      {
         if (cell.x == x && cell.y == y)
         {
            if (ignoreEmpty)
            {
               if (cell.getValue() != 0) return cell;
            }
            else
            {
               return cell;
            }
         }
      }

      return null;
   }
   
   @Override
   protected Object clone() throws CloneNotSupportedException
   {
      Tabuleiro board = (Tabuleiro) super.clone();

      List<Celula> celulas = board.getCelulas();
      List<Celula> listClone = new LinkedList<Celula>();

      for (Celula cell : celulas)
      {
         Celula clone = (Celula) cell.clone();
         clone.setMoved(false);
         listClone.add(clone);
      }

      board.setCelulas(listClone);

      return board;
   }

   public void print()
   {
      System.out.println(toString());
   }

   // FAZER NO TOO STRING PRA NÃO TER QUE FICAR PRINTANDO VÁRIOS FILHOS (PQ SÃO MUITOS) VER NO CTRL + SHIFT + I
   // LUCAS SILVA 15/09
   @Override
   public String toString()
   {
      StringBuilder sb = new StringBuilder();

      sb.append("+-----------+").append('\n');
      int c = 1;
      for (Celula cell : celulas)
      {
         sb.append("|");
         if (cell.getValue() != 0)
         {
            sb.append((cell.isMoved() ? "*" : " "));
            sb.append(cell.getValue());
            sb.append((cell.isMoved() ? "*" : " "));
         }
         else
         {
            if (action != null)
            {
               if (action == MoveAction.TOP) sb.append(" \u25B2 ");
               if (action == MoveAction.DOWN) sb.append(" \u25BC ");
               if (action == MoveAction.LEFT) sb.append(" \u25C0 ");
               if (action == MoveAction.RIGHT) sb.append(" \u25B6 ");

            }
            else
            {
               sb.append("   ");
            }

         }

         if (c % cols == 0) sb.append("|\n");
         c++;
      }
      sb.append("+-----------+");
      return sb.toString();
   }

}
