SERVICES = project-service api-gateway logs-service kube-service

docker: build-docker
	docker compose \
	--file ./deploy/docker/docker-compose.yml \
	--project-directory . \
	--env-file ./deploy/docker/.env \
	up

build-docker: build-jar
	docker build --file ./deploy/docker/Dockerfile-minimified-jre --tag minimified-jre .

	rm -rf data
	mkdir data
	mkdir data/auth

	docker compose \
	--env-file ./deploy/docker/.env \
	--file ./deploy/docker/docker-compose.yml \
	--project-directory . \
	build
	docker run --rm -ti xmartlabs/htpasswd user password > data/auth/htpasswd

build-jar:
	mvn clean install -Dmaven.test.skip

kubernetes: build-jar build-kube run-kube

CREATE_FIRST = postgres rabbitmq kube-service api-gateway
CREATE_AFTER = logs-service project-service

run-kube:
	kubectl create -f deploy/kubernetes/policies.yaml # une seule fois

	@$(foreach SERVICE,$(KUBE_SERVICES), kubectl apply -f deploy/kubernetes/${SERVICE}.yaml;)

build-kube: build-jar
	docker build --file ./deploy/docker/Dockerfile-minimified-jre --tag minimified-jre .
	@$(foreach SERVICE,$(SERVICES), docker build -t ${SERVICE}:local --file ${SERVICE}/Dockerfile .; minikube image load ${SERVICE}:local;)


KUBE_SERVICES = postgres rabbitmq api-gateway logs-service kube-service project-service
stop-kube:
	kubectl delete -f deploy/kubernetes/policies.yaml
	@$(foreach SERVICE,$(KUBE_SERVICES), kubectl delete -f deploy/kubernetes/${SERVICE}.yaml;)
