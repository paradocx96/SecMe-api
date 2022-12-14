# Docs for the Azure Web Apps Deploy action: https://github.com/Azure/webapps-deploy
# More GitHub Actions for Azure: https://github.com/Azure/actions

```dockerfile

name: Docker Image CI

on:
  push:
    branches: [ "docker-deploy" ]
  pull_request:
    branches: [ "docker-deploy" ]

jobs:
  build:    
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v3
      - name: Run shell script
        run: echo commit hash is ${{ github.sha }}
        
      - name: Login to Docker Hub
        uses: docker/login-action@v1
        with:
          username: ${{ secrets.DOCKER_HUB_USERNAME }}
          password: ${{ secrets.DOCKER_HUB_ACCESS_TOKEN }}

      - name: Build and push to DockerHub
        uses: docker/build-push-action@v2
        with:
          context: .
          file: ./Dockerfile
          push: true
          tags: ${{ secrets.DOCKER_HUB_USERNAME }}/secme-api:${{ github.sha }}

```