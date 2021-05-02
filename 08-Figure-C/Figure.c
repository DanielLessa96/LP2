#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int r,g,b;
} Color;

struct Figure;
typedef void (* Figure_Print) (struct Figure*);

typedef struct Figure {
    int x, y;
    Color fg, bg;
    void (* print) (struct Figure*);
} Figure;

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int w, h;
} Poly;

void poly_print (Poly* this) {
    Figure* sup = (Figure*) this;
    printf("Losango com vertices em: (%d,%d), (%d,%d), (%d,%d) e (%d,%d).\n",
           sup->x, sup->y , sup->x + this->w, sup->y + this->h, sup->x, sup->y + 2 * this->h, sup->x - this->w, sup->y + this->h);
}

Poly* poly_new (int x, int y, int w, int h) {
    Poly*   this  = malloc(sizeof(Poly));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) poly_print;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
}

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int w, h;
} Ellipse;

void Ellipse_print (Ellipse* this) {
    Figure* sup = (Figure*) this;
    printf("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
           this->w, this->h, sup->x, sup->y);
}

Ellipse* ellipse_new (int x, int y, int w, int h) {
    Ellipse* this = malloc(sizeof(Ellipse));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) Ellipse_print;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
}

///////////////////////////////////////////////////////////////////////////////

void main (void) {
    Figure* figs[4] = {
        (Figure*) poly_new(10,20,100,200),
        (Figure*) ellipse_new(40,10,140,300),
        (Figure*) poly_new(30,15,150,150),
        (Figure*) ellipse_new(210,110,305,130)
    };
    int i;

    ///

    for (i=0; i<4; i++) {
        figs[i]->print(figs[i]);
    }

    ///

    for (i=0; i<4; i++) {
        free(figs[i]);
    }
}
