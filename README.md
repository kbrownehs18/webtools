# webtools
Web tools for springboot

### REDIS

~~~
spring.cache.type=Redis
spring.redis.host=${REDIS_HOST:localhost}
spring.redis.port=${REDIS_PORT:6379}
spring.redis.password=${REDIS_PASS:scnjl}
spring.redis.timeout=${REDIS_TIMEOUT:5000ms}
spring.redis.database=${REDIS_DB:0}
spring.redis.lettuce.pool.max-active=${REDIS_MAX_ACTIVE:200}
spring.redis.lettuce.pool.max-wait=${REDIS_MAX_WAIT:1ms}
spring.redis.lettuce.pool.max-idle=${REDIS_MAX_IDLE:50}
spring.redis.lettuce.pool.min-idle=${REDIS_MIN_WAIT:10}
~~~

### DATABASE

~~~
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.type=com.zaxxer.hikari.HikariDataSource

spring.datasource.hikari.pool-name=DBPool
spring.datasource.hikari.minimum-idle=${DB_IDLE:10}
spring.datasource.hikari.maximum-pool-size=${DB_POOL_SIZE:200}
spring.datasource.hikari.auto-commit=true
spring.datasource.hikari.idle-timeout=${DB_IDLE_TIMEOUT:30000}
spring.datasource.hikari.max-lifetime=${DB:MAX_LIFETIME:1800000}
spring.datasource.hikari.connection-timeout=${DB_CONNECTION_TIMEOUT:30000}

spring.jpa.properties.hibernate.ddl-auto=None
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.show-sql=true
#spring.jpa.properties.hibernate.format_sql=true
#spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.open-in-view=false

spring.jackson.time-zone=GMT+8
spring.jackson.date-format=yyyy-MM-dd HH:mm:ss

logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
logging.level.org.hibernate.engine.QueryParameters=DEBUG
~~~

### SERVER

~~~
server.port=${SERVER_PORT:8080}
server.servlet.context-path=${CONTEXT_PATH:/}
~~~