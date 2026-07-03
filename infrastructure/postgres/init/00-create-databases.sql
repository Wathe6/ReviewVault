CREATE USER keycloak_user WITH PASSWORD 'keycloak_pass';
CREATE DATABASE keycloak_db OWNER keycloak_user;
GRANT ALL PRIVILEGES ON DATABASE keycloak_db TO keycloak_user;

CREATE USER media_user WITH PASSWORD 'media_pass';
CREATE DATABASE media_db OWNER media_user;
GRANT ALL PRIVILEGES ON DATABASE media_db TO media_user;

CREATE USER profile_user WITH PASSWORD 'profile_pass';
CREATE DATABASE profile_db OWNER profile_user;
GRANT ALL PRIVILEGES ON DATABASE profile_db TO profile_user;

CREATE USER reivew_user WITH PASSWORD 'reivew_pass';
CREATE DATABASE reivew_db OWNER reivew_user;
GRANT ALL PRIVILEGES ON DATABASE reivew_db TO reivew_user;