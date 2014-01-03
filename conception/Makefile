FICHIERS_DIA=$(subst .dia,.pdf,$(wildcard *.dia))
FICHIERS_EPS=$(subst .eps,.pdf,$(wildcard *.eps))
FICHIERS_FIG=$(subst .fig,.pdf,$(wildcard *.fig))

.SUFFIXES : .pdf .eps .tex .fig .dia

all: ${FICHIERS_EPS} ${FICHIERS_DIA} ${FICHIERS_FIG}

.eps.pdf:
	epstopdf $*.eps

.fig.pdf:
	fig2dev -L pdf $*.fig $*.pdf

.dia.eps:
	dia --export=$*.eps --filter=eps-builtin $*.dia

clean:
	rm -f *~  *.pdf
