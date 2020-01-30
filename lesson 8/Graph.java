import java.io.*;
import java.util.*;

/**
 * 1 Создать метод для обхода дерева в глубину
 */
class Graph
{
    private int numTops; //количество вершин
    private LinkedList<Integer> adjLists[]; //для присоедения предкa к родителю (что-то типо таблицы смежных вершин)
    private boolean visited[]; //true - если вершину уже посещали, false - если вершину ещё не проходили

    //конструктор
    Graph(int top)
    {
        numTops = top;
        adjLists = new LinkedList[top];
        visited = new boolean[top];

        for (int i = 0; i < top; i++)
            adjLists[i] = new LinkedList<Integer>();
    }

    //связывание вершин (создание ребра между вершинами )
    void addEdge(int parent, int child)
    {
        adjLists[parent].add(child);
    }

    //оюход дерева в глубину (вывод от вершины предка до родителя)
    void goDepth(int top)
    {
        if(top != 0){
            visited[top] = true;
            System.out.print(top + " ");

            Iterator ite = adjLists[top].listIterator();
            while (ite.hasNext())
            {
                int adj = (int) ite.next();
                if (!visited[adj])
                    goDepth(adj);
            }
        }
    }

    public static void main(String args[])
    {
        Graph g = new Graph(7);
        g.addEdge(1, 0);
        g.addEdge(2, 0);
        g.addEdge(3, 1);
        g.addEdge(4, 3);
        g.addEdge(5, 4);
        g.addEdge(6, 0);

        g.goDepth(5);

    }
}
