// Name: Chanelle Mosquera

public class Graph {

   private int rows, cols;
   private int numVertices;
   
   // holds the relationship between two vertices
   public int[][] adj;
   
   // indicates whether vertice at specified row/col is a wall or not
   private int[][] maze;
   
   // arrays are used when inserting edges
   // keeps track of which vertices have been visited and which are currently in queue
   //    and what are the vertices' predecessors and the cost of the path from the starting vertex
   //    Note: I used an array that acts as a queue because initially, i was under the 
   //          impression that we were not allowed to use the Queue class
   private boolean[] visited, inQueue; 
   private int[] from, cost;
   
   // contains vertices in path
   int path[];
   
   public Graph(int[][] maze){
      this.maze = maze;
      rows = maze.length;
      cols = maze[0].length;
      
      numVertices = rows*cols;
      adj = new int[numVertices][numVertices];
      visited = new boolean[numVertices];
      inQueue = new boolean[numVertices];
      from = new int[numVertices];
      cost = new int[numVertices];
      
      // initially, none of the vertices have any connection with each other
      for (int i = 0; i < numVertices; i++) {
         for (int j = 0; j < numVertices; j++) {
            adj[i][j] = 0;
         }
      }   
      // initialize default value for visited[] inQueue[] from[] cost[] 
      for (int i = 0; i < numVertices; i++) {
         visited[i] = false;
         inQueue[i] = false;
         from[i] = -1;
         cost[i] = -1;
      }
   }
   
   // returns the vertex number
   public int vertexID(int row, int col) {
      return row*cols + col;
   }
   
   // returns row of vertex
   public int row(int vertex) {
      return vertex/cols;
   }
   
   // returns col of vertex
   public int col(int vertex) {
      return vertex%cols;
   }

   // Fills adj[][] 
   // checks for any edges between vertices
   // each vertice has at most 4 edges, up, down, left, right
   // if a vertex is along the border of the maze, and no vertex exists in 
   //    specified direction it will be set to -1
   public void insertEdges() {
      int UP, DOWN, LEFT, RIGHT;
   
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
           // if tile is a wall, they do NOT have any edges
            // if not a wall, check if its adjacent tiles are walls or not
            if (maze[i][j] == 0) {
               if (i > 0) {            // UP
                  UP = maze[i-1][j];
               } else UP = -1;
               if (i < rows-1) {       // DOWN
                  DOWN = maze[i+1][j];             
               } else DOWN = -1;            
               if (j > 0) {            // LEFT
               LEFT = maze[i][j-1];         
               } else LEFT = -1;             
               if (j < cols -1) {      // RIGHT
                  RIGHT = maze[i][j+1];            
               } else RIGHT = -1;
            }
            else {
               UP = -1;
               DOWN = -1; 
               LEFT = -1; 
               RIGHT = -1;            
            }
            
            // if the adjacent vertices are not a wall, modify adj[][] indicating edge exists 
            // don't need to worry about nonexistant adj vertice because it would be set to -1 above
            //    therefore not passing these if statement             
            if (UP == 0) adj[vertexID(i,j)][vertexID(i-1,j)] = 1;
            if (DOWN == 0) adj[vertexID(i,j)][vertexID(i+1,j)] = 1;
            if (LEFT == 0) adj[vertexID(i,j)][vertexID(i, j-1)] = 1;
            if (RIGHT == 0) adj[vertexID(i,j)][vertexID(i, j+1)] = 1;
         }
      }
   }
   
   public boolean findShortestPath(int start, int end) {
      boolean empty = false; // determines whether inQueue has an item set to True or not
      int cheapestVertex = -1; // keeps track of cheapest vertex in the array inQueue[]
      boolean exists = false;
      
      int curVertex = start; // at vertex 'start', vertex has been visited and has itself as a predecessor
      visited[start] = true; // and has a path that costs 0
      from[start] = start;
      cost[start] = 0;
      
      // loop for as long as there are objects set to true in the queue array
      while (!empty) {
         // check if the current vertex has an edge with any of the other vertices
         for (int i = 0; i < numVertices; i++) {
            if (adj[curVertex][i] == 1) { // check adj[][] if an edge exists
            
               // if vertex 'i' has already been visited BUT this cost is less than the cost of other path, 
               //    reassign its values
               if ((visited[i] == true) && (cost[i] > cost[curVertex] + 1)) { 
                  from[i] = curVertex;
                  cost[i] = cost[curVertex] + 1;
                  inQueue[i] = true;
               }
               else if (visited[i] == false) { // otherwise if its never been visited
                  visited[i] = true;
                  from[i] = curVertex;
                  cost[i] = cost[curVertex] + 1;
                  inQueue[i] = true;
               }
            }
         }        
          
         // find next cheapest vertex in queue array 
         int min = Integer.MAX_VALUE;
         empty = true; 
         // the following for loop will set 'empty' back to false if there is an item in inQueue[]
         for (int i = 0; i < inQueue.length; i++) {
            if ((inQueue[i] == true) && (cost[i] < min)) {
               empty = false;
               min = cost[i];
               cheapestVertex = i;
            }
         }     
         curVertex = cheapestVertex;
         inQueue[curVertex] = false;
      }
      
      // use from[] to track down the path it took to get to 'end'
      // and fill path[] with vertices in the path
      if (visited[end] == true) {
         exists = true;
         path = new int[cost[end] + 1];
         int cnt = 0;
         while (start != end) {
            path[cnt] = end;        
            end = from[end];
            cnt ++;
         }
         // add 'start' into the path
         path[cnt] = start;
      }
      return exists;
   }

   // returns null if not path exists
   public int[] shortestPath() {      
      return path;
   }   
}
