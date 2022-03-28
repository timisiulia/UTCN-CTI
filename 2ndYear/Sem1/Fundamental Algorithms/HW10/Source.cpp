#include <stdlib.h>
#include <string.h>
#include <queue>
#include <iostream>
#include <algorithm>
#include "Profiler.h"


Profiler p("Adancime");



typedef struct _Node //declaram structura pentru nod
{
    int key;
    int adjSize;//cate noduri adiacente are
    struct _Node** adj;//nodurile adiacente cu el

    int color;
    int descoperire;
    int finalizare;
    int  parent;
}Node;

typedef struct // structura pentru graf
{
    int nrNodes;
    Node** v;
}Graph;


enum { WHITE, GRAY, BLACK };//culorile 


Graph* initGRAPH(Graph* graf,int n) 
{
    graf = (Graph*)malloc(sizeof(Graph));//alocam mememorie
    graf->nrNodes = n;
    graf->v = (Node**)malloc(n*sizeof(Node*));//alocam memorie pt n noduri
    for (int i = 0; i < graf->nrNodes; i++) 
    {
        graf->v[i]= (Node*)malloc(sizeof(Node));//aloc memorie nodului curent

        graf->v[i]->adjSize = 0;
        graf->v[i]->adj = NULL;
        graf->v[i]->color = WHITE;
        graf->v[i]->key = i;
        //la inceput, declaram toare nodurile ca fiind radacini
        graf->v[i]->parent = -1;
        graf->v[i]->descoperire = -1;
        graf->v[i]->finalizare= -1;

    }
    return graf;
}

void free_graph(Graph* graph)//elibereaza memoria din graf
{
    if (graph->v != NULL)
    {
        for (int i = 0; i < graph->nrNodes; ++i) 
        {
            if (graph->v[i] != NULL) 
            {
                if (graph->v[i]->adj != NULL) 
                {
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


int sePoate = 1;//daca se poate face ordonare topologica sau nu

void dfs( Node* s,int &time ,Operation* op)
{
    op->count();
    s->descoperire = time;
    time++;
    op->count();
    s->color =GRAY;//il marcam vizitat
    op->count();
    Node** adiacente = s->adj;
    op->count();
    for(int c=0;c<s->adjSize;c++)
    {
        //se parcurg toti vecinii nodului
        op->count();

        op->count();
        if (adiacente[c]->color == WHITE)//daca e nevizitat
        {
            op->count();
            adiacente[c]->parent = s->key;//ii punem parintele
            dfs(adiacente[c],time,op);//se apeleaza dfs
        }else 
            if (adiacente[c]->color == GRAY)//daca se intoarce la un nod unde nu s-a finalizat cautarea vecinilor
            {
                sePoate = 0;//nu putem avea ordonare topologica
            }
       
        
    }

    op->count();
    s->color = BLACK;//i-am vizitat toti vecinii
    time++; 
    op->count();
    s->finalizare = time;
}
void legatura(Node* x, Node* y)//leaga 2 noduri
{
    
    if (x->adjSize == 0) 
    {
        x->adj = (Node**)malloc(sizeof(Node*));
        x->adj[x->adjSize] = y;
        x->adjSize++;
    }
    else
    {
        x->adj = (Node**)realloc(x->adj, (x->adjSize + 1) * sizeof(Node*));
        x->adj[x->adjSize] = y;
        x->adjSize++;
    }
    
    
}

void performance()
{
    int n, i;
    Profiler p("dfs");

    // vary the number of edges
    for (n = 1000; n <= 4500; n += 100) {
        Operation op = p.createOperation("dfs-edges", n);
        Graph *graph=NULL;
        graph=initGRAPH(graph,100);
        //initialize the nodes of the graph
        int adiacenta[100][100] = { 0 };

       
        // TODO: generate n random edges
        // make sure the generated graph is connected
        for (int i = 1; i < graph->nrNodes - 1; i++) //ca sa fie conex leg primul nod cu toate celelalte
        {
            adiacenta[0][i] = adiacenta[i][0] = 1;
            legatura(graph->v[0], graph->v[i]);
        }
        for (int i = graph->nrNodes - 1; i < n; i++)//generez restul de muchii distincte 
        {
            int x = rand() % graph->nrNodes;//luam doua noduri random
            int y = rand() % graph->nrNodes;
            while (x == y || adiacenta[x][y] == 1) {
                 x = rand() % graph->nrNodes;
                 y = rand() % graph->nrNodes;
            }

            legatura(graph->v[x], graph->v[y]);//facem legatura intre ele
            adiacenta[x][y] =1;//il marcam ca si creat
        }
        int time = 0;
        dfs(graph->v[0],time, &op);
        free_graph(graph);

    }

    // vary the number of vertices
    for (n = 100; n <= 200; n += 10)
    {
        int adiacenta[300][300] = { 0 };
        Operation op = p.createOperation("dfs-vertices", n);
        Graph *graph=NULL;
        graph=initGRAPH(graph,n);
        //initialize the nodes of the graph
        
        // TODO: generate 4500 random edges
        // make sure the generated graph is connected
        for (int i = 1; i < graph->nrNodes - 1; i++) //ca sa fie conex leg primul nod cu toate celelalte
        {
            adiacenta[0][i] = adiacenta[i][0] = 1;
            legatura(graph->v[0], graph->v[i]);
        }
        for (int i = graph->nrNodes - 1; i < 4500; i++)//generez n-nr de noduri  muchii distincte
        {
            int u = rand() % graph->nrNodes;
            int v = rand() % graph->nrNodes;
            while (u == v || adiacenta[u][v] == 1) {
                u = rand() % graph->nrNodes;
                v = rand() % graph->nrNodes;
            }

            legatura(graph->v[u], graph->v[v]);
            adiacenta[u][v] = 1;
        }
        int time = 0;
        dfs(graph->v[0],time, &op);
        free_graph(graph);
    }

    p.showReport();
}
void demo() {
    Profiler p("dfs");
    Graph* gg = NULL;
        gg=initGRAPH(gg, 9);
    legatura(gg->v[8],gg->v[7]);
    legatura(gg->v[0], gg->v[1]);
    legatura(gg->v[0], gg->v[2]);
    legatura(gg->v[2], gg->v[3]);
    legatura(gg->v[2], gg->v[4]);
    legatura(gg->v[4], gg->v[8]);
    legatura(gg->v[3], gg->v[5]);
    legatura(gg->v[3], gg->v[6]);
    legatura(gg->v[3], gg->v[7]);
    int time = 0;
    Operation nou = p.createOperation("dfs_demo", 0);
    
        dfs(gg->v[0], time,&nou);
    

    printf("Vectorul de tati este: \n");

    for (int i = 0; i < gg->nrNodes; i++)
    {
        printf("%d ", gg->v[i]->parent);
    }
    printf("\nTimpii de descoperire sunt: \n");
    for (int i = 0; i < gg->nrNodes; i++)
    {
        printf("%d ", gg->v[i]->descoperire);
    }
    printf("\nTimpii de finalizare sunt: \n");
    for (int i = 0; i < gg->nrNodes; i++) 
    {
        printf("%d ", gg->v[i]->finalizare);
    }
    //sortarea topologica
    if (sePoate)
    {
        int key[100];
        int timpi_fin[100];
        for (int i = 0; i < gg->nrNodes; i++) {
            key[i] = gg->v[i]->key;
            timpi_fin[i] = gg->v[i]->finalizare;
        }
      
        for (int i = 0; i < gg->nrNodes; i++) //parcurgem toate nodurile
        {
            for (int j = 0; j < gg->nrNodes; j++) 
            {   //sortare descrescatoare dupa timpii de finalizare
                if (timpi_fin[i]> timpi_fin[j])
                {
                    int aux;
                    aux = key[i];
                    key[i] = key[j];
                    key[j] = aux;

                    aux = timpi_fin[i];
                    timpi_fin[i] = timpi_fin[j];
                    timpi_fin[j] = aux;

                }
            }
        }
   
    printf("\nSortare topologica: \n");
    for (int i = 0; i < gg->nrNodes; i++)
    {
        printf("%d ",key[i]);
    }
    }
    else {
        printf("\nNu se poate face o sortare topologica");

    }
    

}
int main() {
   // demo();
    performance();
    return 0;
}