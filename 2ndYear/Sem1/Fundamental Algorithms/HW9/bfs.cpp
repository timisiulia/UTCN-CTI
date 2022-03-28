#include <stdlib.h>
#include <string.h>
#include "bfs.h"
#include <queue>

int get_neighbors(const Grid *grid, Point p, Point neighb[])
{
    // TODO: fill the array neighb with the neighbors of the point p and return the number of neighbors
    // the point p will have at most 4 neighbors (up, down, left, right)
    // avoid the neighbors that are outside the grid limits or fall into a wall
    // note: the size of the array neighb is guaranteed to be at least 4
    int size = 0;//viitorul numar de vecini
    int axax[] = { -1,0,1,0 };//axa x
    int axay[] = { 0,1,0,-1 };//axa y
    for (int i = 0; i <= 3; i++)//parcurge fiecare vecin
    {
        
        int x = p.row + axax[i];//primeste vecinii pe rand
        int y = p.col + axay[i];//primeste vecinii pe rand
        if (grid->mat[x][y] == 0 && x >=0 && y >= 0 && x<=grid->rows && y <= grid->cols)//verificam daca vecinul e in matrice sau nu e perete 
        {
            neighb[size].row = x;//punem vecinul in vector
            neighb[size].col = y;
            size++;
        }
        
    }
    
    return size;
}

void grid_to_graph(const Grid *grid, Graph *graph)
{
    //we need to keep the nodes in a matrix, so we can easily refer to a position in the grid
    Node *nodes[MAX_ROWS][MAX_COLS];
    int i, j, k;
    Point neighb[4];

    //compute how many nodes we have and allocate each node
    graph->nrNodes = 0;
    for(i=0; i<grid->rows; ++i){
        for(j=0; j<grid->cols; ++j){
            if(grid->mat[i][j] == 0){
                nodes[i][j] = (Node*)malloc(sizeof(Node));
                memset(nodes[i][j], 0, sizeof(Node)); //initialize all fields with 0/NULL
                nodes[i][j]->position.row = i;
                nodes[i][j]->position.col = j;
                ++graph->nrNodes;
            }else{
                nodes[i][j] = NULL;
            }
        }
    }
    graph->v = (Node**)malloc(graph->nrNodes * sizeof(Node*));
    k = 0;
    for(i=0; i<grid->rows; ++i){
        for(j=0; j<grid->cols; ++j){
            if(nodes[i][j] != NULL){
                graph->v[k++] = nodes[i][j];
            }
        }
    }

    //compute the adjacency list for each node
    for(i=0; i<graph->nrNodes; ++i){
        graph->v[i]->adjSize = get_neighbors(grid, graph->v[i]->position, neighb);
        if(graph->v[i]->adjSize != 0){
            graph->v[i]->adj = (Node**)malloc(graph->v[i]->adjSize * sizeof(Node*));
            k = 0;
            for(j=0; j<graph->v[i]->adjSize; ++j){
                if( neighb[j].row >= 0 && neighb[j].row < grid->rows &&
                    neighb[j].col >= 0 && neighb[j].col < grid->cols &&
                    grid->mat[neighb[j].row][neighb[j].col] == 0){
                        graph->v[i]->adj[k++] = nodes[neighb[j].row][neighb[j].col];
                }
            }
            if(k < graph->v[i]->adjSize){
                //get_neighbors returned some invalid neighbors
                graph->v[i]->adjSize = k;
                graph->v[i]->adj = (Node**)realloc(graph->v[i]->adj, k * sizeof(Node*));
            }
        }
    }
}

void free_graph(Graph *graph)
{
    if(graph->v != NULL){
        for(int i=0; i<graph->nrNodes; ++i){
            if(graph->v[i] != NULL){
                if(graph->v[i]->adj != NULL){
                    free(graph->v[i]->adj);
                    graph->v[i]->adj = NULL;
                }
                graph->v[i]->adjSize = 0;
                free(graph->v[i]);
                graph->v[i] = NULL;
            }
        }
        free(graph->v);
        graph->v = NULL;
    }
    graph->nrNodes = 0;
}

void bfs(Graph* graph, Node* s, Operation* op)
{
    // TOOD: implement the BFS algorithm on the graph, starting from the node s
    // at the end of the algorithm, every node reachable from s should have the color BLACK
    // for all the visited nodes, the minimum distance from s (dist) and the parent in the BFS tree should be set
    // for counting the number of operations, the optional op parameter is received
    // since op can be NULL (when we are calling the bfs for display purposes), you should check it before counting:
    // if(op != NULL) op->count();
    std::queue<Node*>coada;//declaram coada
    s->color = COLOR_GRAY;
    s->dist = 0;//timpul de descoperire
    if (op != NULL) op->count();
    coada.push(s);//adauga la sfarsit
    if (op != NULL) op->count();
    while (!coada.empty())//cat timp coada nu e goala
    {
        if (op != NULL) op->count();
        Node* u = coada.front();//ia primul element din coada
        if (op != NULL) op->count();
        coada.pop();//scot primul element din coada
        u->color = COLOR_BLACK;//il marchez ca vizitat
        if (op != NULL) op->count();
        for (int i = 0; i < u->adjSize; i++)//acum vizitez vecinii nodului u
        {
            if (op != NULL) op->count();
            Node* v = u->adj[i];//luam primul vecin
            if (op != NULL) op->count();
            if (v->color == COLOR_WHITE)//daca e nevizitat
            {
                v->color = COLOR_GRAY;//marcam ca vizitat
                v->parent = u;//atribuim nodului v parintele
                v->dist = u->dist + 1;//creste timpul de descoperire
                coada.push(v);//il punem in coada
                if (op != NULL) op->count();
            }
        }

    }



}
void afis(int k,int l,  int nivel)
{
    for (int i = 0; i < nivel; i++)
        printf(" \t ");
    printf("(%d, %d)\n",k ,l);
}
void pretty_vector_tati(Point arr[], int parinti[], int v[], int n, int poz_rad, int nivel)
{

    if (v[poz_rad] != 0)
    {
        //daca am vizitat nodul, iesim din recursivitate
        return;
    }
    else
    {

        v[poz_rad] = 1;//il marcam ca vizitat
        for (int i = 1; i <= n; i++)
            if (parinti[i] == poz_rad)
                pretty_vector_tati(arr,parinti, v, n, i, nivel + 1);//apelam functia pt fiecare copil al nodului curent
        afis(arr[poz_rad].row, arr[poz_rad].col, nivel);//cand revine din recursivitate, afiseaza


    }
}

void print_bfs_tree(Graph *graph)
{
    //first, we will represent the BFS tree as a parent array
    int n = 0; //the number of nodes
    int *p = NULL; //the parent array
    Point *repr = NULL; //the representation for each element in p

    //some of the nodes in graph->v may not have been reached by BFS
    //p and repr will contain only the reachable nodes
    int *transf = (int*)malloc(graph->nrNodes * sizeof(int));
    for(int i=0; i<graph->nrNodes; ++i){
        if(graph->v[i]->color == COLOR_BLACK){
            transf[i] = n;
            ++n;
        }else{
            transf[i] = -1;
        }
    }
    if(n == 0){
        //no BFS tree
        free(transf);
        return;
    }

    int err = 0;
    p = (int*)malloc(n * sizeof(int));
    repr = (Point*)malloc(n * sizeof(Node));
    for(int i=0; i<graph->nrNodes && !err; ++i){
        if(graph->v[i]->color == COLOR_BLACK){
            if(transf[i] < 0 || transf[i] >= n){
                err = 1;
            }else{
                repr[transf[i]] = graph->v[i]->position;
                if(graph->v[i]->parent == NULL){
                    p[transf[i]] = -1;
                }else{
                    err = 1;
                    for(int j=0; j<graph->nrNodes; ++j){
                        if(graph->v[i]->parent == graph->v[j]){
                            if(transf[j] >= 0 && transf[j] < n){
                                p[transf[i]] = transf[j];
                                err = 0;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
    free(transf);
    transf = NULL;

    if (!err) {
        // TODO: pretty print the BFS tree
        // the parrent array is p (p[k] is the parent for node k or -1 if k is the root)
        // when printing the node k, print repr[k] (it contains the row and column for that point)
        // you can adapt the code for transforming and printing multi-way trees from the previous labs
        int vect_viz[1000] = { 0 };
        int rad = 0;
        for (int i = 0; i < n; i++)//cauta radacina
        {
            if (p[i] == -1)//daca e radacina
            {
                rad = i;
            }
        }
        pretty_vector_tati(repr, p, vect_viz,n, rad, 0);
    }


    if(p != NULL){
        free(p);
        p = NULL;
    }
    if(repr != NULL){
        free(repr);
        repr = NULL;
    }
}

int shortest_path(Graph *graph, Node *start, Node *end, Node *path[])
{
    // TODO: compute the shortest path between the nodes start and end in the given graph
    // the nodes from the path, should be filled, in order, in the array path
    // the number of nodes filled in the path array should be returned
    // if end is not reachable from start, return -1
    // note: the size of the array path is guaranteed to be at least 1000
    return -1;
}
void legatura (Node* u, Node* v)
{
    if (u->adjSize == 0)//are vecini?
    {

        u->adj = (Node**)malloc(sizeof(Node*));//ii aloc memorie nodului
        u->adj[0] = v;//ia valoarea nodului adiacent
        u->adjSize++;//crestem size-ul pentru urmatorul
        
    }
    else
    {
        //daca am vecini, realoc memorie de pe ultima pozitie pentru viitorul nod de adiacenta
        u->adj = (Node**)realloc(u->adj, (u->adjSize+1) * sizeof(Node*));
        u->adj[u->adjSize] = v;
        u->adjSize++;
    }
    //la fel
    if (v->adjSize == 0)
    {
        v->adj = (Node**)malloc(sizeof(Node*));
        v->adj[0] = u;
        v->adjSize++;
        
    }
    else
    {
        v->adj = (Node**)realloc(v->adj, (v->adjSize+1) * sizeof(Node*));
        v->adj[v->adjSize] = u;
        v->adjSize++;
    }
}


void performance()
{
    int n, i;
    Profiler p("bfs");

    // vary the number of edges
    for(n=1000; n<=4500; n+=100){
        Operation op = p.createOperation("bfs-edges", n);
        Graph graph;
        graph.nrNodes = 100;
        //initialize the nodes of the graph
        int adiacenta[100][100] = { 0 };
        
        graph.v = (Node**)malloc(graph.nrNodes * sizeof(Node*));
        for(i=0; i<graph.nrNodes; ++i){
            graph.v[i] = (Node*)malloc(sizeof(Node));//vect de parinti
            memset(graph.v[i], 0, sizeof(Node));
        }
        // TODO: generate n random edges
        // make sure the generated graph is connected
        for (int i = 1; i < graph.nrNodes - 1; i++) //ca sa fie conex leg primul nod cu toate celelalte
        {
            adiacenta[0][i] = adiacenta[i][0] = 1;
            legatura(graph.v[0], graph.v[i]);
        }
        for (int i = graph.nrNodes - 1; i < n; i++)//generez restul de muchii distincte 
        {
            int x = rand() % graph.nrNodes;//luam doua noduri random
            int y = rand() % graph.nrNodes;

            legatura(graph.v[x], graph.v[y]);//facem legatura intre ele
            adiacenta[x][y] = adiacenta[y][x] = 1;//il marcam ca si creat
        }
        bfs(&graph, graph.v[0], &op);
        free_graph(&graph);

    }

    // vary the number of vertices
    for(n=100; n<=200; n+=10){
        int adiacenta[300][300] = { 0 };
        Operation op = p.createOperation("bfs-vertices", n);
        Graph graph;
        graph.nrNodes = n;
        //initialize the nodes of the graph
        graph.v = (Node**)malloc(graph.nrNodes * sizeof(Node*));
        for(i=0; i<graph.nrNodes; ++i){
            graph.v[i] = (Node*)malloc(sizeof(Node));
            memset(graph.v[i], 0, sizeof(Node));
        }
        // TODO: generate 4500 random edges
        // make sure the generated graph is connected
        for (int i = 1; i < graph.nrNodes - 1; i++) //ca sa fie conex leg primul nod cu toate celelalte
        {
            adiacenta[0][i] = adiacenta[i][0] = 1;
            legatura(graph.v[0], graph.v[i]);
        }
        for (int i = graph.nrNodes - 1; i < 4500; i++)//generez n-nr de noduri  muchii distincte
        {
            int x = rand() % graph.nrNodes;
            int y = rand() % graph.nrNodes;

            legatura(graph.v[x], graph.v[y]);
            adiacenta[x][y] = adiacenta[y][x] = 1;
        }

        bfs(&graph, graph.v[0], &op);
        free_graph(&graph);
    }

    p.showReport();
}
