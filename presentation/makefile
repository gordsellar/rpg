# Importation des variables

all: presentation

# Génération du pdf
presentation:
	pdflatex presentation.tex;\
	pdflatex presentation.tex;\
	make cleanrep

# Nettoyage total
clean: cleanpdf cleanrep

# Nettoyage du répertoire
cleanrep:	
	rm -rf *.aux *.cb *.cb2 *.log *.toc *.out *.nav *.snm


# Nettoyage des pdf
cleanpdf:
	rm -rf *.pdf
	rm -rf ./images/*.pdf
