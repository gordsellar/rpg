include makevars

CIBLES=$(patsubst %.tex, %.pdf, $(wildcard *.tex))

# Génération des pdf
all: $(CIBLES)
	make cleanrep;\
	
# Nettoyage total
clean: cleanrep cleanpdf

# Nettoyage du répertoire
cleanrep:	
	rm -rf *.aux *.cb *.cb2 *.log *.toc *.out *.glg *.gls *.ist *.glo *.xdy *.lof *.bbl *.blg *~

# Nettoyage des pdf
cleanpdf:	
	rm -rf $(CIBLES)

# Génération d'un pdf
%.pdf: %.tex
	TEXINPUTS=${TEXINPUTS}:$(CLSPATH);\
	export TEXINPUTS;\
	pdflatex $?;\
	bibtex $(shell basename $? .tex);\
	pdflatex $?;\
	pdflatex $?;\
	make cleanrep;
