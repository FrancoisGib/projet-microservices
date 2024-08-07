![](Microservices.png)
Le but du projet est de créer un service similaire aux azure container apps sur le cloud Azure.
Pour le moment j'ai surtout créé la plupart des services et fait une version automatisée avec docker compose, je vais bientot faire une version avec kubernetes et ensuite je vais implémenter les services entièrement.
J'ai donc fait une pipeline qui permet de déployer directement dans ma registry privée sur docker hub pour pouvoir après utiliser ses images avec kubernetes. (je vais surement remplacer par une registry privée en local).

J'ai ajouté un service qui gère directement les namespaces kubernetes, il y aura un namespace par organisation.
Pour l'instant les formulaires ne sont pas fait, je lance seulement un déploiement nginx avec un pod et un service.
Au début j'avais une ancienne version de l'api kubernetes donc beaucoup de choses ne marchaient pas, j'ai perdu beaucoup de temps pour rien haha.
Il me reste à implémenter la sécurité des différents services, renforcer la gateway et automatiser tout ça puis écrire les tests, et faire des queues rabbitmq pour faire des événements entre les services.

Le schéma au dessus n'est donc plus à jour.