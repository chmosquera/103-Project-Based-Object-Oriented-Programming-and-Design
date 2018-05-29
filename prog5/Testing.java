public class Testing {

   public static void main(String[] args) {
   
      int[][] maze =  {{-1,-1,-1,-1,-1},
                       {-1, 0, 0, 0,-1},
                       {-1, 0, 0, 0,-1},   
                       {-1, 0, 0, 0,-1},   
                       {-1, 0, 0, 0,-1},   
                       {-1, 0, 0, 0,-1}
                       };
      int rows = maze.length;
      int cols = maze[0].length;
      int numVertices = rows * cols;
      System.out.println("rows = " + rows + "   cols = " + cols + "   numVertices = " + numVertices);

      // create graph
      Graph mazeGraph = new Graph(maze);  
           
      // test Graph.vertexID(int, int);
      for (int i = 0; i < rows; i ++) {
         for (int j = 0; j < cols; j++) {
            System.out.println("vertex ID of vertex at row " + i + " and col " + j + 
                               " is " + mazeGraph.vertexID(i,j));
         }
      }
      // test insertEdges()
      mazeGraph.insertEdges();
      for (int i = 0; i < numVertices; i++) {
         for (int j = 0; j < numVertices; j++) {
         //System.out.println(i + " and " + j);
            if (mazeGraph.adj[i][j] == 1) System.out.println("Vertex " + i + " and vertex " + j + " have an edge");
         }
      }
      
      // test shortestPath(int, int)
      int[] path = mazeGraph.shortestPath(6, 27);
      System.out.println(toString(path));
   }
   
   public static String toString(int[] data) {
      String s = "[";
      for (int i = 0; i < data.length; i ++) {
         s = s.concat(data[i] + ", ");
      }
      s = s.concat("]");
      return s;
   }

}
