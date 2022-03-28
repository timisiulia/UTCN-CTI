#include <iostream>
#include "Profiler.h"

#define maxsize 10000
#define stepsize 100
#define nr_teste 5
Profiler p("Multimi");

typedef struct node //definire element pentru viitoarea lista
{
	int val;//valoarea nodului
	int rank;//sa se stie cate subeturi se afla in set 
	struct node* parinte;
} Node;
typedef struct muchie //definire element pentru viitoarea lista
{
	Node* u;//nod plecare
	Node* v;//nod destinatie
	int cost;
} Muchie;

void maxheapify( Muchie arr[], int n, int i)
{
	int largest = i;
	int st = 2 * i + 1;
	int dr = 2 * i + 2;

	if (st < n && arr[st].cost > arr[largest].cost)
		largest = st;
	if (dr < n && arr[dr].cost > arr[largest].cost)
		largest = dr;

	if (largest != i)
	{
		Muchie aux;
		aux = arr[i];
		arr[i] = arr[largest];
		arr[largest] = aux;
		maxheapify(arr, n, largest);
	}

}
void buildheap(Muchie arr[], int n)
{
	for (int i = n / 2 - 1; i >= 0; i--)
		maxheapify(arr, n, i);
}
void heapsort(Muchie arr[], int n)
{
	
	buildheap(arr, n);

	for (int i = n - 1; i > 0; i--)
	{

	Muchie aux;
		aux = arr[0];
		arr[0] = arr[i];
		arr[i] = aux;
		maxheapify(arr, i, 0);
	}
	
}
Node* makeset (int val,Operation op)//creaza un nod de tipul set
{
	Node* n = (Node*)malloc(sizeof(Node));//se aloca memorie pt nod
	
	op.count(3);
	n->parinte = n;//parintele e el insusi
	n->rank = 0;//se face un subset nou si ii dam valoarea 0
	n->val = val;//nosul primeste o valoare
	
	return n;
}
Node* findset(Node* n, Operation op)//caut parintele unui set  
{
	op.count();
	if (n != n->parinte)//daca ii diferit de parinte
	{
		op.count();
		n->parinte = findset(n->parinte,op);//cauta parintele
	}
	return n->parinte;
}
void link(Node* a, Node* b, Operation op)//face legatura pt union
{
	op.count();
	if (a->rank > b->rank)//care multime e mai mare
	{
		op.count();
		b->parinte=a;//leaga multimea mai mica la cea mai mare
	}
	else//invers
	{
		op.count(2);
		a->parinte=b;
		if (a->rank == b->rank)//daca e egal
		{
			op.count();
			b->rank = b->rank + 1;//se creste rank-ul
		}
	}
}
void union1 (Node* a, Node* b, Operation op)//face legatura intre subseturi
{
	link(findset(a,op), findset(b,op),op);
}

Muchie makeEdge(Node* a, Node* b, int w)//pune muchia intre cele 2 noduri
{
	Muchie e;
	e.u = a;//nod plecare
	e.v = b;//nod destinatie
	e.cost = w;//costul nodului
	return e;
}
Muchie* kruscal( Muchie w[],int nrvarfuri , int nrmuchii, Operation op_find, Operation op_union, Operation op_make)
{
	Muchie* rezultat = (Muchie*)calloc(nrvarfuri, sizeof(Muchie));
	for (int i = 0; i < nrvarfuri; i++)
	{
		makeset(i,op_make);//pt fiecare nod se face cate un set diferit
	}
	heapsort(w, nrmuchii);//ordoneaza dupa cost
	int muchirez = 0;//pe ce poz in sirul final pun muchia
	for (int i = 0; i < nrmuchii; i++) 
	{
		if (findset(w[i].u,op_find) != findset(w[i].v,op_find) )//daca pt o muchie nodul de plecare si nodul destinatie au accelasi parinte
		{
			rezultat[muchirez]=w[i];//pun muchia curenta in rezultat
			  muchirez++;
			  union1(w[i].u, w[i].v,op_union);//leg subseturile din care face parte nodul de start si nodul destinatie
		}
	}
	return rezultat;
}
void masuratori()
{

	for (int j = 0; j < nr_teste; j++)
	{


		for (int n = stepsize; n <= maxsize; n += stepsize)
		{
			printf("%d ", n);
			Operation op_find = p.createOperation("Find", n);
			Operation op_union = p.createOperation("Union", n);
			Operation op_make = p.createOperation("Make", n);
			Operation op = p.createOperation("Nimic", n);
			
			Node** varfuri = (Node**)malloc(n * sizeof(Node*));
			for (int i = 0; i < n; ++i)//creez n noduri
				varfuri[i] = makeset(i,op);

			Muchie edge[85000];
			for (int i = 0; i < n-1; i++) //ca sa fie conex leg primul nod cu toate celelalte
			{
				edge[i] = makeEdge(varfuri[0], varfuri[i + 1], rand() % n);
			}
			for (int i = n - 1; i < n * 4; i++)//generez 3*n muchii distincte
			{
				edge[i] = makeEdge(varfuri[rand()%n],varfuri[rand()%n], rand() % n);
			}
			kruscal(edge, n, n * 4, op_find, op_union, op_make);//apelez krusccal
		

		}

	}
	p.divideValues("Find", nr_teste);
	p.divideValues("Union", nr_teste);
	p.divideValues("Make", nr_teste);

	p.createGroup("STATISTICI DE ORDINE", "Make", "Union", "Find");
	p.showReport();
}
void demo()
{
	Operation nou = p.createOperation("Nou", 0);
	Node* arr[10];
	for (int i = 0; i < 10; ++i)
	{
		arr[i] = makeset(i,nou);
	}
	union1(arr[1], arr[3],nou);
	union1(arr[2], arr[7],nou);
	union1(arr[5], arr[7],nou);
	union1(arr[7], arr[4],nou);
	union1(arr[3], arr[8],nou);
	for (int i = 0; i < 10; ++i)
	printf("key %d parinte %d\n", arr[i]->val,findset(arr[i],nou)->val);
	int n = 4;
	int m = 6;
	Muchie E[7];//muchie
	Node* V[6];//varfuri
	for (int i = 0; i < 4; ++i)
		V[i] = makeset(i,nou);
	E[0] = makeEdge(V[0], V[1], 6);
	E[1] = makeEdge(V[1], V[2], 3);
	E[2] = makeEdge(V[0], V[3], 5);
	E[3] = makeEdge(V[2], V[1], 2);
	E[4] = makeEdge(V[1], V[3], 1);
	E[5] = makeEdge(V[0], V[3], 6);
	Muchie* REZ = kruscal( E, n, m, nou, nou, nou);
	for (int i = 0; i < n-1; i++) {
		printf("%d %d cost %d\n", REZ[i].u->val, REZ[i].v->val, REZ[i].cost);
	}



}
int main()
{
	demo();
	//masuratori();
}

