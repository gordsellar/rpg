include makevars

# Génération des pdf
all: 
	TEXINPUTS=${TEXINPUTS}:$(CLSPATH);\
	export TEXINPUTS;\
	for file in *.tex; do\
		pdflatex $$file;\
		bibtex $$file;\
		pdflatex $$file;\
	done;
	make cleanrep;\
	
# Nettoyage total
clean: cleanrep cleanpdf

# Nettoyage du répertoire
cleanrep:	
	rm -rf *.aux *.cb *.cb2 *.log *.toc *.out *.glg *.gls *.ist *.glo *.xdy *.lof *.bbl *.blg *~

# Nettoyage des pdf
cleanpdf:	
	rm -rf $(NOM_FICHIER).pdf 
