# AMT-Carfleet
## Auteurs : Mario Tomic, Janis Chiffelle

Etape 02

![uml](/docs/uml.png)

Etape 03

Bonne pratiques :

- Chaque enregistrement doit être representé sur une ligne distincte
- Chaque niveau d'imbrication doit avoir un tab supplémentaire
- Avoir les keys en minuscules
- Pas de tirets pour les noms, des underscore à la place
- Toujours avoir un élément root

Choix de Jackson :

Nous avons choisi jackson pour s'occuper du JSON car la librairie est bien documenté avec de nombreux examples.
Nous avons également essayer d'autre librairies comme GSON, mais l'approche de Jackson avec la représentation du json sous forme d'arbre nous convenait mieux.
Du fait que notre projet deserialise des petits fichiers json, nous n'avons pas pris en compte la performance lors de notre chois.

[Comparaison des performance des librairies pour le JSON](https://www.overops.com/blog/the-ultimate-json-library-json-simple-vs-gson-vs-jackson-vs-json/)
