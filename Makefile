SERVICES = project-service user-service api-gateway logs-service postgres mongo rabbitmq

docker: build-docker
	docker compose \
	--file ./deploy/docker/docker-compose.yml \
	--project-directory . \
	--env-file ./deploy/docker/.env \
	up

build-docker: build-jar
	docker compose \
	--env-file ./deploy/docker/.env \
	--file ./deploy/docker/docker-compose.yml \
	--project-directory . \
	build

build-jar:
	mvn clean install -Dmaven.test.skip

all:
	@$(foreach SERVICE,$(SERVICES), echo ${SERVICE};)

kubernetes: build-kube

build-kube: