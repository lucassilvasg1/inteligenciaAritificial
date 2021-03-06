package trabalho1.model;

public class Celula implements Comparable<Celula>, Cloneable
{

   private int value;

   public int x;

   public int y;

   private boolean moved = false;

   public Celula(int value, int x, int y)
   {
      super();
      this.value = value;
      this.x = x;
      this.y = y;
   }

   public int getValue()
   {
      return value;
   }

   public void setValue(int value)
   {
      this.value = value;
   }

   public int getX()
   {
      return x;
   }

   public void setX(int x)
   {
      this.x = x;
   }

   public int getY()
   {
      return y;
   }

   public void setY(int y)
   {
      this.y = y;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + value;
      result = prime * result + x;
      result = prime * result + y;
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      Celula other = (Celula) obj;
      if (value != other.value) return false;
      if (x != other.x) return false;
      if (y != other.y) return false;
      return true;
   }

   public void setMoved(boolean moved)
   {
      this.moved = moved;
   }

   public boolean isMoved()
   {
      return moved;
   }

   @Override
   public Object clone() throws CloneNotSupportedException
   {
      return super.clone();
   }

   // COMPARAR AS CELULAS PARA QUANDO FOR ORDENAR, OLHAR PARA A POSIÇÃO E NÃO PARA O VALOR
   @Override
   public int compareTo(Celula o)
   {
      int result = Integer.compare(getY(), o.getY());
      if (result == 0)
      {
         result = Integer.compare(getX(), o.getX());
      }
      return result;
   }

   @Override
   public String toString()
   {
      return "[" + x + "," + y + "]=" + value;
   }

}
