Un personnage a au moins des connaissances de base :
   - Etat de santé
   - Temps qu'il fait
   - Appetit
______________________________________________________________

Exceptions levése en fonction des méthodes

chercherEntiteDansZone (Zone)
   Exception : PersonnagePasDansLaZone

discuter(Personnage)
   Exception : PersonnagePasDansLaZone

deplacer()
   Exception : ZoneInaccessible	

donnerObjet(Personnage)
   Exception : PersonnagePasDansLaZone

recupererObjet(Personnage)
   Exception : PersonnagePasDansLaZone


________________________________________________________________

Infos :

Quand un personnage se fait tuer, on peut obtenir des connaissances sur son cadavre

Secret dans Connaissance décide de si un personnage peut ou non obtenir cette connaissance en faisant AcquerirConnaissancesDeLaZone

IntelligenceMin defini le niveau d'intelligence minimum que le joueur doit avoir pour obtenir cette connaissance

Le niveau d'intelligence d'un personnage augment avec le nombre de connaissances qu'il possède


