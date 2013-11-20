include makevars

# Génération du pdf
all: 
	TEXINPUTS=${TEXINPUTS}:$(CLSPATH);\
	export TEXINPUTS;\
	pdflatex $(NOM_FICHIER).tex;\
	bibtex $(NOM_FICHIER);\
	pdflatex $(NOM_FICHIER).tex;\
	pdflatex $(NOM_FICHIER).tex;\
	pdflatex $(NOM_FICHIER).tex;\
	make cleanrep;\
	
# Nettoyage total
clean: cleanrep cleanpdf

# Nettoyage du répertoire
cleanrep:	
	rm -rf *.aux *.cb *.cb2 *.log *.toc *.out *.glg *.gls *.ist *.glo *.xdy *.lof *.bbl *.blg

# Nettoyage des pdf
cleanpdf:	
	rm -rf $(NOM_FICHIER).pdf 
