FROM postgres:13.2-alpine
ENV POSTGRES_DB taskmanager-db
ENV POSTGRES_USER user
ENV POSTGRES_PASSWORD secret