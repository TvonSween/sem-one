FROM mysql:8.4
COPY world.sql /docker-entrypoint-initdb.d
ENV MYSQL_ROOT_PASSWORD example
# Mac Fix
ENV MYSQL_ROOT_HOST=%