![](Microservices.png)

problème avec les registry docker fix, il me faut donc un fichier htpasswd
htpasswd -bnB user password >> auth/htpasswd
ce fichier htpasswd contiendra les usernames et password encodés pour se connecter à la registry ou alors un seul par organisation.

puis docker login $monip:9000 (port de ma gateway) (attention: ip en clair publique !!!!)

docker run -itd \
  -p 5000:5000 \
  --name registry-test \
  -v "$(pwd)"/auth:/auth \
  -e "REGISTRY_AUTH=htpasswd" \
  -e "REGISTRY_AUTH_HTPASSWD_REALM=Registry Realm" \
  -e REGISTRY_AUTH_HTPASSWD_PATH=/auth/htpasswd \
 -e HTTP_PROXY=http://localhost:9000 registry

il faudra un jour que je setup des certificats pour https mais en attendant

"insecure-registries": [
    "$monip:9000"
]
pour autoriser à renvoyer de l'http et à passer par la gateway

il faut que je config docker compose pour gérer les network là tout le monde a accès à tout le monde j'aime pas ça.
Le but à la fin est d'utiliser kubernetes plutot que docker compose mais je suis nul avec kubernetes donc pour le moment c'est compose
