Le but du projet est de créer un service similaire aux azure container apps sur le cloud Azure.
Pour le moment j'ai surtout créé la plupart des services et fait une version automatisée avec docker compose, je vais bientot faire une version avec kubernetes et ensuite je vais implémenter les services entièrement.
J'ai donc fait une pipeline qui permet de déployer directement dans ma registry privée sur docker hub pour pouvoir après utiliser ses images avec kubernetes. (je vais surement remplacer par une registry privée en local).

J'ai décidé de passer mon project-service en non réactif et d'utiliser spring web basique et donc d'utiliser la bdd postgres, c'était totalement overkill et ça complexifiait les services. (et mongo reactive est plutôt nul)