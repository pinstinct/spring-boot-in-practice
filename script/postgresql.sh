docker run -d \
-p 15432:5432 \
-e POSTGRES_PASSWORD=a123456789! \
-e POSTGRES_DB=spring-boot-practice \
-e TZ=Asia/Seoul \
--name PostgreSQL003 \
postgres
