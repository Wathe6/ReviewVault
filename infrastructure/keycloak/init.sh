#!/bin/bash
set -eu

KC=/opt/keycloak/bin/kcadm.sh
SERVER=http://localhost:8080

attempt=0
until "$KC" config credentials \
    --server "$SERVER" \
    --realm master \
    --user "$KEYCLOAK_ADMIN" \
    --password "$KEYCLOAK_ADMIN_PASSWORD" >/dev/null 2>&1; do
  attempt=$((attempt + 1))
  if [ "$attempt" -ge 30 ]; then
    echo "Keycloak is unavailable or admin credentials are invalid" >&2
    exit 1
  fi
  sleep 2
done

if ! "$KC" get "realms/$KEYCLOAK_REALM" >/dev/null 2>&1; then
  "$KC" create realms -s "realm=$KEYCLOAK_REALM" -s enabled=true
fi

users=$("$KC" get users -r "$KEYCLOAK_REALM" \
  -q "username=$KEYCLOAK_USERNAME" -q exact=true --fields username)

if [[ "$users" != *'"username"'* ]]; then
  "$KC" create users -r "$KEYCLOAK_REALM" \
    -s "username=$KEYCLOAK_USERNAME" \
    -s "email=$KEYCLOAK_USERNAME@example.com" \
    -s enabled=true \
    -s "firstName=$KEYCLOAK_USERNAME" \
    -s "lastName=$KEYCLOAK_USERNAME"

  "$KC" set-password -r "$KEYCLOAK_REALM" \
    --username "$KEYCLOAK_USERNAME" \
    --new-password "$KEYCLOAK_DEMO_PASSWORD"
fi

postman_clients=$("$KC" get clients -r "$KEYCLOAK_REALM" \
  -q clientId=postman --fields clientId --format csv --noquotes)

if ! grep -Fxq postman <<< "$postman_clients"; then
  "$KC" create clients -r "$KEYCLOAK_REALM" \
    -s clientId=postman \
    -s protocol=openid-connect \
    -s enabled=true \
    -s publicClient=true \
    -s standardFlowEnabled=true \
    -s directAccessGrantsEnabled=false \
    -s 'redirectUris=["https://oauth.pstmn.io/v1/callback"]'
fi

for role in USER ADMIN EDITOR; do
  if ! "$KC" get "roles/$role" -r "$KEYCLOAK_REALM" >/dev/null 2>&1; then
    "$KC" create roles -r "$KEYCLOAK_REALM" -s "name=$role"
  fi
done

alice_roles=$("$KC" get-roles -r "$KEYCLOAK_REALM" \
  --uusername "$KEYCLOAK_USERNAME")

if ! grep -Eq '"name"[[:space:]]*:[[:space:]]*"USER"' <<< "$alice_roles"; then
  "$KC" add-roles -r "$KEYCLOAK_REALM" \
    --uusername "$KEYCLOAK_USERNAME" \
    --rolename USER
fi

echo "Keycloak initialization completed"
