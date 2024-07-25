SERVICES = project-service user-service api-gateway logs-service

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



kubernetes: build-kube run-kube

KUBE_SERVICES = postgres rabbitmq api-gateway user-service logs-service

run-kube:
	@$(foreach SERVICE,$(KUBE_SERVICES), kubectl apply -f deploy/kubernetes/${SERVICE};)

build-kube: build-jar
	docker build --file ./deploy/docker/Dockerfile-minimified-jre --tag minimified-jre .
	@$(foreach SERVICE,$(SERVICES), docker build -t francoisgib/microservices:${SERVICE} --file ${SERVICE}/Dockerfile .; docker push francoisgib/microservices:${SERVICE};)