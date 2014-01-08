# Importation des variables

all: presentation

# Génération du pdf
presentation:
	pdflatex presentation.tex;\
	make cleanrep

# Nettoyage total
clean: cleanpdf cleanrep

# Nettoyage du répertoire
cleanrep:	
	rm *.aux *.cb *.cb2 *.log *.toc *.out *.nav *.snm


# Nettoyage des pdf
cleanpdf:	
	rm $(NOM_FICHIER).pdf 
	rm ./images/*.pdf
